package main

import (
	"fmt"
	"time"
)

// The error type is a built-in interface
// type error interface {
//     Error() string
// }

type TimeErr struct {
	When time.Time
	What string
}

// function Error() implicitly implements the built-in error interface
func (e *TimeErr) Error() string {
	return fmt.Sprintf("at %v, %s", e.When, e.What)
}

// The pointer type of the CustomErr is the implementation class of the error interface
func run() error {
	return &TimeErr{
		time.Now(),
		"Timezone Error"}
}

func main() {
	if err := run(); err != nil {
		// the "fmt" package looks for the error interface when printing errors.
		fmt.Println(err) // ... Timezone Error
	}
}
