package main

import (
	"fmt"
	"math"
)

// Functions are values too. They can be passed around just like other values.
// Function values may be used as function arguments and return values.
func compute(fn func(float64, float64) float64) float64 {
	return fn(3, 4)
}

// the adder function returns a closure. Each closure is bound to its own sum variable.
func adder() func(int) int {
	sum := 0
	// Go functions may be closures.
	// A closure is a function value that references variables from outside its body.
	// The function may access and assign to the referenced variables;
	// in this sense the function is "bound" to the variables.
	return func(x int) int {
		fmt.Printf("sum = %v, ", sum)
		sum += x
		return sum
	}
}

func main() {
	hypot := func(x, y float64) float64 {
		return math.Sqrt(x*x + y*y)
	}
	fmt.Println(hypot(5, 12))      // 13
	fmt.Println(compute(hypot))    // 5
	fmt.Println(compute(math.Pow)) // 3^4 = 81

	pos, neg := adder(), adder()
	for i := 0; i < 5; i++ {
		fmt.Printf("pos(%v) = %v; ", i, pos(i))
		fmt.Printf("neg(%v) = %v\n", -2*i, neg(-2*i))
	}
}
