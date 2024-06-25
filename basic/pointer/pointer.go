package main

import "fmt"

// Go has pointers, a pointer holds the memory address of a value.
// The & operator generates a pointer to its operand.
// The * operator denotes the pointer's underlying value.
// Unlike C, Go has no pointer arithmetic.
func main() {
	i, j := 42, 2701
	var p *int = &i // point to i
	fmt.Println(*p) // read i through the pointer
	*p = 21         // set i through the pointer
	fmt.Println(i)
	p = &j         // point to j
	*p = *p / 37   // divide j through the pointer
	fmt.Println(j) // see the new value of j
}
