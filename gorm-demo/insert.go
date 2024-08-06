package main

import (
	"fmt"
	"gorm.io/gorm"
	"time"
)

func create(db *gorm.DB) {
	now := time.Now()
	user := User{Name: "nameA", Age: 1, Birthday: &now}
	err := db.AutoMigrate(&user)
	if err != nil {
		panic(err)
	}

	/*
		Create Record
	*/

	tx := db.Create(&user) // You can NOT pass a struct to 'Create', pass a pointer instead.
	fmt.Println("Primary Key:", user.ID)
	fmt.Println("Inserted records count:", tx.RowsAffected)

	/*
		Create Multiple Records (Batch Insert)
	*/

	users := []*User{
		{Name: "nameB", Age: 2, Birthday: &now},
		{Name: "nameC", Age: 3, Birthday: &now},
		{Name: "nameD", Age: 4, Birthday: &now},
	}
	tx = db.Create(&users)
	// You can specify batch size when creating with CreateInBatches
	// db.CreatInBatches(users, 10) // batch size 10

	/*
		Create a record and assign a value to the fields specified.
	*/
	// db.Select("Name", "Age", "CreatedAt").Create(&User{Name: "Name", Age: 0, Birthday: &now})

	/*
		Create a record and ignore the values for fields passed to omit.
	*/
	// db.Omit("Name", "Age", "CreatedAt").Create(&User{Name: "Name", Age: 0, Birthday: &now})

	/*
		Create From Map (NOTICE: created_at and updated_at will NOT be auto generated)
	*/

	tx = db.Model(User{}).Create(
		map[string]any{
			"Name":     "nameE",
			"Age":      5,
			"Birthday": &now,
			// created_at and updated_at will NOT be auto generated
		},
	)

	err = db.AutoMigrate(&Email{})
	if err != nil {
		panic(err)
	}

	emails := []*Email{
		{UserId: 1, Email: "nameA@go.dev"},
		{UserId: 2, Email: "nameB@go.dev"},
		{UserId: 3, Email: "nameC@go.dev"},
	}
	tx = db.Create(&emails)
}
