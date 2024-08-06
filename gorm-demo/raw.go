package main

import (
	"fmt"
	"gorm.io/gorm"
)

func raw(db *gorm.DB) {
	create(db)
	var result Result
	db.Exec("select id, name, age from users where id = 1")
	db.Raw("select id, name, age from users where id = ?", 2).Scan(&result)

	/*
		DryRun
		Generate SQL and its arguments without executing, can be used to prepare or test generated SQL
	*/

	user := User{}
	stmt := db.Session(&gorm.Session{DryRun: true}).First(&user, 3).Statement
	fmt.Println(stmt.SQL.String()) // select * from users where users.id = ? order by users.id limit ?
	fmt.Println(stmt.Vars)         // [3 1]

	/*
		ToSQL
		Returns generated SQL without executing
	*/

	sql := db.ToSQL(
		func(tx *gorm.DB) *gorm.DB {
			return tx.Model(&User{}).Where("id < ?", 5).Limit(3).Order("age desc").Find(&[]User{})
			// select * from users where id < 5 order by age desc limit 3
		},
	)
	fmt.Println(sql)

	row := db.Table("users").Where("name = ?", "nameA").Select("name, age").Row()
	var name string
	var age int
	err := row.Scan(&name, &age)
	if err != nil {
		panic(err)
	}
	fmt.Println(name, age) // nameA 1

	rows, _ := db.Raw("select name, age from users where id in ?", []int{2, 3}).Rows()
	for rows.Next() {
		err = rows.Scan(&name, &age)
		if err != nil {
			panic(err)
		}
		fmt.Println(name, age)
		// nameB 2
		// nameC 3
	}

	rows, err = db.Model(&User{}).Where("name = ?", "nameA").Select("name, age").Rows()
	user = User{}
	for rows.Next() {
		err = db.ScanRows(rows, &user) // ScanRows scan a row into user
		fmt.Println(user)              // { ... nameA 1 ... }
		if err != nil {
			return
		}
	}
}
