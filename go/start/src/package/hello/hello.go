package main

import (
	"bronya.com/greeting"
	"fmt"
	"log"
)

func main() {
	// Set properties of the predefined Logger, including the log entry prefix
	// and a flag to disable printing the time, source file, and line number.
	log.SetPrefix("greeting: ")
	log.SetFlags(0)

	// A slice of names.
	names := []string{"OS X", "Ubuntu", "Fedora"}
	// Request greeting messages for the names.
	messages, err := greeting.Hellos(names)
	if err != nil {
		log.Fatal(err)
	}
	// If no error was returned, print the returned map of messages to the console.
	fmt.Println(messages)
}
