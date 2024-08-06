package main

import (
	"fmt"
	// "gorm.io/driver/sqlite"
	"gorm.io/driver/mysql"
	"gorm.io/gorm"
)

func start() {
	dsn := "root:0228@tcp(localhost:3306)/bronya?charset=utf8&parseTime=True&loc=Local"
	datetimePrecision := 2
	db, err := gorm.Open(
		mysql.New(
			mysql.Config{
				// data source name
				DSN: dsn,
				// add default size for string fields, by default, will use db type `longtext` for fields without size,
				// not a primary key, no index defined and don't have default values
				DefaultStringSize: 256,
				// disable datetime precision support
				DisableDatetimePrecision: true,
				// default datetime precision
				DefaultDatetimePrecision: &datetimePrecision,
				// drop & create index when rename index
				DontSupportRenameIndex: false,
				// use change when rename column
				DontSupportRenameColumn: false,
				// smart configure based on used version
				SkipInitializeWithVersion: true,
			},
		), &gorm.Config{},
	)
	if err != nil {
		panic("failed to connect database")
	}
	// $ show create table resps;
	// create table resps (
	//     id         bigint unsigned not null auto_increment,
	//     created_at datetime        default null,
	//     updated_at datetime        default null,
	//     deleted_at datetime        default null,
	//     code       bigint unsigned default null,
	//     status     varchar(256)    default null,
	//     primary key (id),
	//     key idx_resps_deleted_at (deleted_at)
	// )
	err = db.AutoMigrate(&Resp{})
	if err != nil {
		return
	}
	// insert into desps (created_at, updated_at, code, status) values (now(), now(), 200, "OK");
	db.Create(&Resp{Code: 200, Status: "OK"})
	var resp Resp
	// resp = select * from resps where id = 1
	db.First(&resp, 1)
	// resp = select * from resp where code = 200
	db.First(&resp, "code = ?", 200)

	// update resps set status = "Not Found" where id = 1;
	db.Model(&resp).Update("Status", "Not Found")
	fmt.Println(resp)

	// update resps set code = 500, status = "Bad Gateway" where id = 1;
	db.Model(&resp).Updates(map[string]any{"Code": 500, "Status": "Bad Gateway"})
	fmt.Println(resp)

	// update resps set deleted_at = now() where id = 1;
	db.Delete(&resp, 1)
	fmt.Println(resp)
}
