package main

import (
	"fmt"
	"gorm.io/gorm"
	"time"
)

func query(db *gorm.DB) {
	/*
		user: destination object
	*/

	user := User{}
	err := db.AutoMigrate(&user)
	if err != nil {
		panic(err)
	}

	// Get the first record ordered by primary key
	// If no primary key defined, results will be ordered by first field.
	tx := db.First(&user) // select * from users order by id limit 1
	fmt.Println(user)
	fmt.Println("Selected records count:", tx.RowsAffected)

	// userMap: destination object
	// model (table) is specified using db.Model()
	userMap := map[string]any{}
	db.Model(&User{}).First(&userMap)
	db.Table("users").Take(&userMap)

	// Get one record, no specified order
	user = User{}
	db.Take(&user) // select * from users limit 1

	// Get last record, ordered by primary key desc
	user = User{}
	db.Last(&user) // select * from users order by id desc limit 1

	user = User{}
	tx = db.Limit(1).Find(&user)

	/*
		Select with conditions
	*/

	user = User{}
	db.First(&user, 3) // select * from users where id = 3

	user = User{}
	db.First(&user, "id = ?", "3") // select * from users where id = 3

	var users []User
	db.Find(&users, []int{1, 2, 3})

	// When the destination object user has a primary value,
	// the primary key will be used to build the condition.
	user = User{ID: 4}
	db.First(&user) // select * from users where id = 4;

	// Get all records
	db.Find(&users) // select * from users

	/*
		Where
	*/

	// Get first matched record
	user = User{}
	db.Where("name = ?", "nameA").First(&user)
	// select * from users where name = "nameA" order by id limit 1

	// Get all matched records
	db.Where("name != ?", "nameA").Find(&users)
	// select * from users where name != "nameA"

	// in
	db.Where("name in ?", []string{"nameA", "nameB"}).Find(&users)
	// select * from users where name in ("nameA", "nameB")

	// like
	db.Where("name like ?", "name%").Find(&users)
	// select * from users where name like "name%"

	// time
	t, err := time.Parse("2006-01-02 15:04:05", "2024-08-01 00:00:00")
	if err != nil {
		panic(err)
	}
	db.Where("created_at > ?", t).Find(&users)
	// select * from users where created_at > "2024-08-01 00:00:00"

	// between
	db.Where("created_at between ? and ?", t, time.Now()).Find(&users)
	// select * from users where created_at between "2024-08-01 00:00:00" and now()

	// struct as condition
	db.Where(&User{Name: "nameA"}).First(&users)
	// select * from users where name = "nameA"

	// map as condition
	db.Where(map[string]any{"name": "nameA"}).Find(&users)
	// select * from users where name = "nameA"

	// slice of primary keys
	db.Where([]int64{1, 2, 3}).Find(&users)
	// select * from users where id in (1, 2, 3)

	// When querying with struct, gorm will only query with non-zero fields.
	// To include zero values in the query conditions, use a map instead.
	db.Where(&User{Name: "nameA", Age: 0}).Find(&users)
	// select * from users where name = "nameA"
	db.Where(map[string]any{"name": "nameA", "age": 0}).Find(&users)
	// select * from users where name = "nameA" and age = 0;

	// When searching with struct, you can specify which particular fields from the struct to use in the query conditions.
	db.Where(&User{Name: "nameA", Age: 1}, "name").Find(&users) // "Name" is also ok
	// select * from users where name = "nameA"

	/*
		inline conditions
	*/

	db.First(&user, "name = ?", "NameA")
	// select * from users where name = "NameA"
	db.Find(&users, User{Name: "nameA"}) // struct
	// select * from users where name = "nameA"
	db.Find(&users, map[string]any{"name": "nameA"}) // map
	// select * from users where name = "nameA"

	/*
		Not
	*/

	user = User{}
	db.Not("name = ?", "nameA").First(&user)
	// select * from users where not name = "nameA" order by id limit 1
	db.Not(map[string]any{"name": []string{"nameA", "nameB"}}).Find(&users)
	// select * from users where name not in ("nameA", "nameB")
	db.Not(User{Name: "nameA", Age: 1}).First(&users) // struct
	// select * from users where name != "nameA" and age != 1 order by id limit 1
	user = User{}
	db.Not([]int{1, 2, 3}).First(&user)
	// select * from users where id NOT in (1, 2, 3) order by id limit 1

	/*
		Or
	*/

	db.Where("name = ?", "nameA").Or("name = ?", "nameB").Find(&users)
	// select * from users where name = "nameA" or name = "nameB"
	db.Where("name = ?", "nameA").Or(User{Name: "nameB", Age: 2}).Find(&users)
	// select * from users where name = "nameA" or (name = "nameB" and age = 2)
	db.Where("name = 'nameA'").Or(map[string]any{"name": "nameB"}).Find(&users)
	// select * from users where name = "nameA" or name = "nameB"

	/*
		Select allows you to specify the fields
	*/

	db.Select("name", "age").Find(&users)
	// select name, age from users;
	db.Select([]string{"name", "age"}).Find(&users) // slice
	// select name, age from users;

	/*
		Order
	*/

	db.Order("age desc, name").Find(&users)
	// select * from users order by age desc, name
	db.Order("age desc").Order("name").Find(&users)
	// select * from users order by age desc, name

	/*
		Limit & Offset
	*/

	db.Limit(3 /* startIndex = 0; pageSize = 3; */).Find(&users)
	// select * from users limit 3 # equivalent to `limit 0, 3`

	// Cancel limit condition with -1.
	users1 := make([]User, 0)
	db.Limit(3).Find(&users).Limit(-1).Find(&users1)
	// users  -> select * from users limit 0, 3 # equivalent to `limit 3`
	// users1 -> select * from users

	db.Limit(2 /* pageSize = 2 */).Offset(1 /* startIndex = 1 */).Find(&users)
	// select * from users limit 1, 2;

	/*
		Group & Having (set global sql_mode=(select replace(@@sql_mode,'only_full_group_by',''));)
	*/

	result := Result{}
	db.Model(&User{}).Select("date(created_at), sum(age) as total").Where(
		"name like ?", "name%",
	).Group("name").First(&result)
	// select date(created_at), sum(age) as total from users where name like "name%" group by name order by users.id limit 1

	var results []Result
	db.Model(&User{}).Select("date(created_at), sum(age) as total").Group("name").Having(
		"name = ?", "nameA",
	).Find(&results)
	// select date(created_at), sum(age) as total from users group by name having name = "nameA"

	db.Table("users").Select("date(created_at) as date, sum(age) as total").Group("date(created_at)").Having(
		"sum(age) > ?", 4,
	).Scan(&results)
	// select date(created_at), sum(age) as total from users group by date(created_at) having sum(age) > 4

	_, err = db.Table("users").Select("date(created_at) as date, sum(age) as sum").Group("date(created_at)").Rows()
	_, err = db.Table("users").Select("date(created_at) as date, sum(age) as sum").Group("date(created_at)").Having(
		"sum(age) > ?", 4,
	).Rows()

	/*
		todo Distinct
	*/

	db.Distinct("name", "age").Order("name, age desc").Find(&users)
	// fixme select distinct name, age from users order by name, age desc

	/*
		Join
	*/

	type Answer struct {
		Name  string
		Email string
	}

	var answers []Answer
	db.Model(&User{}).Select("users.name, emails.email").Joins("left join emails on users.id = emails.user_id").Scan(&answers)
	// select users.name, emails.email from users left join emails on users.id = emails.user_id;

	db.Table("users").Select("users.name, emails.email").Joins("left join emails on users.id = emails.user_id").Scan(&results)
	// select users.name, emails.email from users left join emails on users.id = emails.user_id;

	_, err = db.Table("users").Select("users.name, emails.email").Joins("left join emails on users.id = emails.user_id").Rows()

	/*
		Scan: scanning results into a struct, works similarly to Find
	*/

	user = User{}
	db.Table("users").Select("name", "age").Where("name = ?", "nameA").Scan(&user)

	/*
		Raw SQL
	*/

	db.Raw("select name, age from users where name = ?", "nameA").Scan(&users)
}
