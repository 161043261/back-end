package main

import (
	"fmt"
	"gorm.io/gorm"
	"strconv"
)

func remove(db *gorm.DB) {
	db.Exec("drop table if exists emails")
	err := db.AutoMigrate(&Email{})
	if err != nil {
		panic(err)
	}
	for i := 1; i < 12; i++ {
		db.Create(&Email{UserId: i, Email: strconv.Itoa(i) + "@bronya.com"})
	}

	/*
		Delete a Record
		When deleting a record, the deleted value needs to have primary key, or it will trigger a batch delete
	*/

	email := Email{ID: 1}
	db.Delete(&email)
	// delete from emails where id = 1
	db.Where("email = ?", "2@bronya.com").Delete(&Email{})
	// delete from emails where email = "2@bronya.com"

	/*
		Delete with primary key
	*/

	db.Delete(&Email{}, 3)
	// delete from emails where id = 3
	db.Delete(&Email{}, "4")
	// delete from emails where id = 4

	var emails []Email
	db.Delete(&emails, []int{5, 6})
	// delete from emails where id in (5, 6)

	/*
		Batch Delete
	*/
	db.Where("email like ?", "7%").Delete(&Email{})
	// delete from emails where email like "7%"
	db.Delete(&Email{}, "email like ?", "8%")
	// delete from emails where email like "8%"

	emails = []Email{{ID: 9}, Email{ID: 10}}
	db.Delete(&emails)
	// delete from emails where id in (9, 10)
	db.Delete(&emails, "email like ?", "%bronya%")
	// delete from emails where email like "%bronya%" and id in (9, 10) // No record will be deleted.

	/*
		If you perform a batch delete without any conditions, gorm will NOT run it, but return error.
		You have to use some conditions or use raw SQL or enable AllowGlobalUpdate mode.
	*/
	err = db.Delete(&Email{}).Error
	if err != nil {
		fmt.Println(err) // where conditions required
	}

	err = db.Delete(&[]Email{{Email: "11@bronya.com"}}).Error
	if err != nil {
		fmt.Println(err) // where conditions required
	}
	// db.Where("1 = 1").Delete(&Email{})
	// db.Exec("delete from emails")
	// db.Session(&gorm.Session{AllowGlobalUpdate: true}).Delete(&Email{})

	/*
		returning data from deleted rows
	*/

	/*
		Soft Delete
		If your model includes a gorm.DeleteAt field, which is included in gorm.Model (e.g. Resp), it will get soft delete ability automatically.
		When calling Delete, the record will NOT be removed from the databases, but gorm will set DeletedAt value to the current time,
		and the data is NOT findable with normal query methods anymore.
	*/

	db.Exec("drop table if exists resps")
	err = db.AutoMigrate(&Resp{})
	if err != nil {
		panic(err)
	}
	for i := 1; i < 4; i++ {
		db.Create(&Resp{Code: uint(i), Status: strconv.Itoa(i)})
	}

	resp := Resp{}
	db.Delete(&resp, 1)
	// update resps set deleted_at = now() where code = 1

	// batch soft delete
	db.Where("id in ?", []int{2, 3}).Delete(&Resp{})
	// update resps set deleted_at = now() where id in (2, 3)

	// soft delete records will be ignored when querying
	resp = Resp{}
	db.Find(&resp, 3)
	// select * from resps where id = 3 // empty set

	// find soft deleted records
	db.Unscoped().Where("id = 3").Find(&resp)
	fmt.Println(resp)

	// delete permanently
	db.Unscoped().Delete(&resp)
}
