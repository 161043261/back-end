package main

import (
	"fmt"
	"math"
)

type Vertex struct {
	X, Y float64
}

type f64 float64

// You can declare a method on non-struct types, too.
// You can only declare a method with a receiver whose type is defined in the same package as the method.
// You cannot declare a method with a receiver whose type is defined in another package (which includes the built-in types such as ). int

func (f f64) Abs() float64 {
	if f < 0 {
		return float64(-f)
	}
	return float64(f)
}

// A method is a function with a special receiver argument.
// The receiver appears in its own argument list between the func keyword and the method name.

func (v Vertex) Abs() float64 {
	return math.Sqrt(v.X*v.X + v.Y*v.Y)
}

// Methods with pointer receivers can modify the value to which the receiver points.
// Since methods often need to modify their receiver, pointer receivers are more common than value receivers.

// With a value receiver, the method operates on a copy of the original value.
// This is the same behavior as for any other function argument.
// The Scale method must have a pointer receiver to change the Vertex value declared in the main function.

func (v *Vertex) Scale(f float64) {
	v.X = v.X * f
	v.Y = v.Y * f
}

func ScaleFunc(v *Vertex, f float64) {
	v.X = v.X * f
	v.Y = v.Y * f
}

func main() {
	f := f64(-math.Sqrt2)
	fmt.Println(f.Abs())

	v := Vertex{3, 4}
	p := &v
	fmt.Println(p.Abs()) // ok
	// the method call p.Abs() can be interpreted as (*p).Abs()

	v.Scale(2) // ok
	// also, the method call v.Scale() can be interpreted as (&v).Scale()

	// ScaleFunc(v, 2)  // compile error
	ScaleFunc(&v, 2) // ok
	fmt.Println(v.Abs())
}

// There are two reasons to use a pointer receiver.
// The first is so that the method can modify the value that its receiver points to.
// The second is to avoid copying the value on each method call.
// This can be more efficient if the receiver is a large struct, for example.
