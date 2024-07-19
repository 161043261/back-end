package main

import (
	"fmt"
	"gorm.io/driver/mysql"
	"gorm.io/gorm"
)

type User struct {
	Id       int
	Username string
	Password string
	Age      int
	Sex      string
	Email    string
}

// DROP TABLE IF EXISTS users
func main() {
	dsn := "root:0228@tcp(localhost:3306)/bronya?charset=utf8&parseTime=True&loc=Local"
	db, err := gorm.Open(mysql.Open(dsn), &gorm.Config{})
	if err != nil {
		panic("failed to connect database")
	}

	//  migrate schema
	db.AutoMigrate(&User{})

	// create a table named 'users'
	db.Create(&User{
		// id (primary key)
		Username: "Java",
		Password: "Java",
		Age:      1,
		Sex:      "female",
		Email:    "java@bronya.com"})

	// read
	var user User
	// select * from users where id = 1
	db.First(&user, 1)
	// select * from users where username = "Java"
	db.First(&user, "username = ?", "Java")

	// update users set age = 2 where id = 1
	db.Model(&user).Update("Age", 2)
	db.Model(&user).Updates(User{ // User, map[string]interface{} is both ok
		Username: "Go",
		Password: "Go",
		Age:      2,
		Sex:      "male",
		Email:    "go@bronya.com"})
	fmt.Println(user) // {1 Go Go 2 male go@bronya.com}

	// delete from users where id = 1
	db.Delete(&user, 1)
}
