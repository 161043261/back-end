package greeting

import (
	"errors"
	"fmt"
	"math/rand"
)

// hello returns a greeting for the named person.
func hello(name string) (string, error) {
	// If no name was given, return an error with a message.
	if name == "" {
		return "", errors.New("empty name")
	}
	// Create a message using a random format.
	message := fmt.Sprintf(randomFormat(), name)
	return message, nil
}

// hellos returns a map that associates each of the named people with a greeting message.
func Hellos(names []string) (map[string]string, error) {
	// A map to associate names with messages.
	messages := make(map[string]string)
	// Loop through the received slice of names, calling the hello function to get a message for each name.
	for _, name := range names {
		message, err := hello(name)
		if err != nil {
			return nil, err
		}
		// In the map, associate the retrieved message with the name
		messages[name] = message
	}
	return messages, nil
}

// randomFormat returns one of a set of greeting messages.
// The return message is selected at random.
func randomFormat() string {
	// A slice of message formats.

	// array: var arr [3]int
	// slice: var sli []int
	formats := []string{
		"Hi, %v!",
		"Hey, %v!",
		"Hello, %v!",
	}
	// Return a randomly selected message format by
	// specifying a random index for the slice of formats.
	return formats[rand.Intn(len(formats))]
}
