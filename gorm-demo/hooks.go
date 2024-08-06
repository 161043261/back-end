package main

import (
	"fmt"
	"gorm.io/gorm"
)

// BeforeUpdate A update hook.
// update hooks: BeforeSave, BeforeUpdate, AfterSave, AfterUpdate
func (u *User) BeforeUpdate(*gorm.DB) (err error) {
	if u.ID == 0 {
		fmt.Println("ID is 0!")
	}
	return
}

// BeforeDelete A delete hook.
func (e *Email) BeforeDelete(tx *gorm.DB) (err error) {
	if e.ID == 0 {
		fmt.Println("ID is 0!")
	}
	return
}
