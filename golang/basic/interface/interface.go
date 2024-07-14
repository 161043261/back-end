package main

import (
	"fmt"
	"math"
)

// IAbser An interface type is defined as a set of method signatures.
// A value of interface type can hold any value that implements those methods.
type IAbser interface {
	Abs() float64
}

type f64 float64

// Abs Type 'f64' implements the interface 'IAbser'
// Interfaces are implemented implicitly
func (f f64) Abs() float64 {
	if f < 0 {
		return float64(-f)
	}
	return float64(f)
}

type Vertex struct {
	X, Y float64
}

// Abs Type '*Vertex' implements the interface 'IAbser'
// but type 'Vertex' does NOT implements the interface 'IAbser'
func (v *Vertex) Abs() float64 {
	if v == nil {
		return -1
	} // gracefully handle being called with a nil receiver
	return math.Sqrt(v.X*v.X + v.Y*v.Y)
}

func main() {
	var a IAbser
	f := f64(-math.Sqrt2)
	v := Vertex{3, 4}
	a = f
	fmt.Println(a.Abs()) // 1.414
	a = &v
	fmt.Println(a.Abs()) // 5
	var u *Vertex
	a = u
	fmt.Println(a.Abs()) // -1

	//TODO The empty interface: interface{}, specifies zero methods
	// An empty interface can hold values of ANY type (Every type implements at least zero methods.)
	var i interface{}
	desc(i) // nil
	i = 1
	desc(i) // int
	i = "what"
	desc(i) // string

	// Type Assertion
	var j interface{} = "what"
	s := j.(string)
	fmt.Println(s) // what

	nf, ok := j.(float64)
	fmt.Println(nf, ok) // 0 false

	// Type Switches
	do(22)      // int
	do("hello") // string
	do(true)    // what?

	// Stringer: equals to Java @Override toString()
	p := Person{"Tomcat", 22}
	fmt.Println(p) // name: Tomcat, age: 22
}

func desc(i interface{}) {
	fmt.Printf("%T\n", i)
}

// do Type Switches
// A type switch is a construct that permits several type assertions in series.
func do(i interface{}) {
	switch v := i.(type) {
	case int:
		fmt.Printf("%T\n", v)
	case string:
		fmt.Printf("%T\n", v)
	default:
		fmt.Println("what?")
	}
}

// Person
// type Stringer interface { String() string; }
// A 'Stringer' is a type that can describe itself as a string.
// The fmt package looks for this interface to print values.
type Person struct {
	Name string
	Age  int
}

// String Type 'Person' implements the interface 'Stringer'
func (p Person) String() string {
	// Sprintf formats according to a format specifier and returns the resulting string.
	return fmt.Sprintf("name: %v, age: %v", p.Name, p.Age)
}
