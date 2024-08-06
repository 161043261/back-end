package main

import (
	"gorm.io/driver/mysql"
	"gorm.io/gorm"
)

func connect() *gorm.DB {
	dsn := "root:0228@tcp(localhost:3306)/bronya?charset=utf8&parseTime=True&loc=Local"
	db, err := gorm.Open(mysql.Open(dsn), &gorm.Config{})
	if err != nil {
		panic(err)
	}

	db.Exec("drop table if exists users")
	db.Exec("drop table if exists emails")
	return db
}
