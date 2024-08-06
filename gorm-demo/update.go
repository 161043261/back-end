package main

import (
	"fmt"
	"gorm.io/gorm"
	"time"
)

func update(db *gorm.DB) {
	user := User{}
	err := db.AutoMigrate(&user)
	if err != nil {
		panic(err)
	}

	/*
		Save ALL fields when performing the updating SQL.
		Save is a combination function. If save value does not contain primary key, it will execute Create (insert),
		otherwise it will execute update with all fields. Do NOT use Save with Model, it's an undefined behavior.
	*/

	db.First(&user)
	user.Name = "nameA1"
	db.Save(&user) // basic update

	now := time.Now()
	user = User{
		Name:      "nameF",
		Age:       6,
		Birthday:  &now,
		CreatedAt: now,
	}
	db.Save(&user) // insert: equivalent to db.Create(&User{Name: "nameF", Age: 6})
	user.Name = "nameF1"
	db.Save(&user) // update
	// update users set name = "nameF1", age = 6, updated_at = now() where id = 6

	/*
		Update single column
		When updating a single column with Update, it needs to have some conditions.
		When using the Model method and its value has a primary value, the primary key will be used to build the condition
	*/

	// Update with conditions
	db.Model(&User{}).Where("name = ?", "nameB").Update("name", "nameB1") // ID is 0!
	// update users set name = "nameB1", updated_at = now() where name = "nameB";

	// Update with Model (a struct)
	user = User{ID: 3}
	db.Model(&user).Update("name", "nameC1")
	// update users set name = "nameC1", updated_at = now() where id = 3;

	// Update with conditions and Model (a struct)
	user = User{ID: 4}
	db.Model(&user).Where("name = ?", "nameD").Update("name", "nameD1")
	// update users set name = "nameD1", updated_at = now() where id = 4 and name = "nameD"

	/*
		Updates multiple columns
		Updates supports updating with struct or map[string]any
		when updating with struct it will ignore zero fields (0, false, "").
	*/

	// Updates fields with struct will ignore zero fields (0, false, "").
	user = User{}
	db.First(&user, 5)
	// select * from users where id = 5;
	db.Model(&user).Updates(User{Name: "nameE1", Age: 0})
	// update users set name = "nameE1", updated_at = now() where id = 5

	// Update fields with map
	db.Model(&user).Updates(map[string]any{"name": "nameE2", "age": 0})
	// update users set name = "nameE2", age = 0, updated_at = now() where id = 5

	/*
		update selected fields or ignore some fields using Select, Omit
	*/

	// Select with Map
	db.Model(&user).Select("name").Updates(map[string]any{"name": "nameE3", "age": 3})
	// update users set name = "nameE3" where id = 5

	// Omit with Map
	db.Model(&user).Omit("name").Updates(map[string]any{"name": "nameE4", "age": 4})
	// update users set age = 4 where id = 5

	// Select with Struct (You can select zero value fields now).
	db.Model(&user).Select("name", "Age" /* "Name", "Age" is also ok */).Updates(User{Name: "nameE4", Age: 0})
	// update users set name = "nameE4", age = 0 where id = 5

	// @Deprecated Select ALL fields (id, name, age, email, ...) include zero fields
	// db.Model(&user).Select("*").Updates(User{Name: "", Age: 0})

	// @Deprecated Select ALL fields (id, name, age, email, ...) include zero fields but omit "name"
	// db.Model(&user).Select("*").Omit("name").Updates(User{Name: "", Age: 5})

	/*
		Batch Updates
	*/

	// If you have NOT specified a record having a primary key with Model, gorm will perform a batch update
	// If you perform a batch update without any conditions, gorm will NOT run it and return error.
	email := "example@example.com"
	db.Model(&User{}).Where("name like ?", "name%").Updates(User{Email: &email})
	// update users set email = "example@example.com" where name like "name%"

	// Update with map
	db.Table("users").Where("id in ?", []int{5, 6}).Updates(map[string]any{"age": 0})
	// update users set age = 0 where id in (5, 6);

	err = db.Model(&User{}).Update("email", "example@gmail.com").Error
	if err != nil {
		fmt.Println(err)
	}

	db.Model(&User{}).Where("1 = 1").Update("email", "example@gmail.com") // ok
	// update users set email = "example@gmail.com" where 1 = 1

	db.Exec("update users set email = ?", "example@outlook.com") // ok
	// update users set email = "example@outlook.com"

	db.Session(&gorm.Session{AllowGlobalUpdate: true}).Model(&User{}).Update("email", "example@bronya.com") // ok
	// update users set name = "example@bronya.com"
}
