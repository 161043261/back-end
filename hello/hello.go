package main

import (
	"fmt"
	"log"
	"njupt.edu.cn/greetings"
)

func main() {
	// Set properties of the predefined Logger, including the log entry prefix
	// and a flag to disable printing the time, source file, and line number.
	log.SetPrefix("greetings: ")
	log.SetFlags(0)

	// Request a greeting message.
	message, err := greetings.Hello("Gladys")
	// If an error was returned, print it to the console and exit the program.
	if err != nil {
		// Fatal is equivalent to Print followed by a call to os.Exit(1).
		log.Fatal(err)
	}
	// If no error was returned, print the returned message to the console.
	fmt.Println(message)
}
