package main

import (
	"fmt"
	"io"
	"strings"
)

// Read populates the given byte slice with data and returns the number of bytes populated and an error value.
// It returns an io.EOF error when the stream ends.
// The example code creates a strings.Reader and consumes its output 8 bytes at a time.
func main() {
	r := strings.NewReader("1234567 abcdefg 1char = 1byte = 8bits")
	b := make([]byte, 8) // read 8 bytes at a time
	for {
		n, err := r.Read(b)
		fmt.Printf("n = %v err = %v b = %v\n", n, err, b)
		fmt.Printf("b[:n] = %q\n", b[:n])
		if err == io.EOF {
			break
		}
	}
}
