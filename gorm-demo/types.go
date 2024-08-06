package main

import (
	"database/sql"
	"gorm.io/gorm"
	"time"
)

type Resp struct {
	gorm.Model // Deleted gorm.DeleteAt
	Code       uint
	Status     string
}

type User struct { // User -> users
	// snake_case:
	// id, name, email, age, birthday, member_number, activated_at, created_at, updated_at

	ID   uint // Standard field for the primary key
	Name string
	Age  uint8

	Email    *string    // A pointer to nullable string
	Birthday *time.Time // A pointer to nullable time.Time

	MemberNumber sql.NullString // Uses sql.NullString for nullable string
	ActivatedAt  sql.NullTime   // Uses sql.NullTime for nullable time

	CreatedAt time.Time // Automatically managed by gorm for created time
	UpdatedAt time.Time // Automatically managed by gorm for updated time
}

type Email struct { // Email -> emails
	// snake_case:
	// id, user_id, email
	ID     int
	UserId int
	Email  string
}

type Result struct {
	Date  time.Time
	Total int
}

type Answer struct {
	ID   int
	Name string
	Age  int
}
