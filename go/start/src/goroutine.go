package main

import (
	"fmt"
	"time"
)

// A goroutine is a lightweight thread managed by the Go runtime.

// go f(x, y, z); // starts a new goroutine running f(x, y, z)
// The evaluation of f, x, y, and z happens in the current goroutine
// and the execution of f happens in the new goroutine.

// Goroutines run in the same address space, so access to shared memory must be synchronized.
func say(s string) {
	for i := 0; i < 5; i++ {
		time.Sleep(1000 * time.Millisecond)
		fmt.Println(i, s)
	}
}

func main() {
	go say("Go")
	say("Java") // starts a new goroutine running
}
