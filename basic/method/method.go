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

func (v *Vertex) Scale(f float64) {
	v.X = v.X * f
	v.Y = v.Y * f
}

func main() {
	f := f64(-math.Sqrt2)
	fmt.Println(f.Abs())

	v := Vertex{3, 4}
	fmt.Println(v.Abs())
	v.Scale(10)
	fmt.Println(v.Abs())
}
