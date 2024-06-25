// >>> basic types >>>
// bool
// string
// int  int8  int16  int32  int64
// uint uint8 uint16 uint32 uint64 uintptr
// byte -> alias for uint8
// rune -> alias for int32, represents a Unicode code point
// float32 float64
// complex64 complex128

package main

import (
	"fmt"
	"math"
	"math/cmplx"
	"runtime"
	"time"
)

func basicType() {
	var (
		ToBe   bool       = false
		MaxInt uint64     = 1<<64 - 1
		z      complex128 = cmplx.Sqrt(-5 + 12i)
	)
	fmt.Printf("Type: %T Value: %v\n", ToBe, ToBe)
	fmt.Printf("Type: %T Value: %v\n", MaxInt, MaxInt)
	fmt.Printf("Type: %T Value: %v\n", z, z)
}

// The expression T(v) converts the value v to the type T
func typeConversions() {
	var x, y int = 3, 4
	var f float64 = math.Sqrt(float64(x*x + y*y))
	var z uint = uint(f)
	fmt.Println(x, y, z)
}

// Constants cannot be declared using the := syntax.
func pow(x, n, lim float64) float64 {
	if v := math.Pow(x, n); v < lim {
		return v
	}
	return lim
}

// Switch cases evaluate cases from top to bottom, stopping when a case succeeds.
func switchOS() {
	fmt.Print("Go runs on ")
	switch os := runtime.GOOS; os {
	case "darwin":
		fmt.Println("OS X.")
	case "linux":
		fmt.Println("Linux.")
	default:
		// freebsd, openbsd, plan9, windows...
		fmt.Printf("%s.\n", os)
	}
}

// Switch without a condition is the same as switch true.
// This construct can be a clean way to write long if-then-else chains.
func switchTrue() {
	t := time.Now()
	switch { // switch true {...}
	case t.Hour() < 12:
		fmt.Println("Good morning.")
	case t.Hour() < 17:
		fmt.Println("Good afternoon.")
	default:
		fmt.Println("Good evening.")
	}
}

// A defer statement defers the execution of a function until the surrounding function returns.
// The deferred call's arguments are evaluated immediately,
// but the function call is not executed until the surrounding function returns.

// Deferred function calls are pushed onto a stack.
// When a function returns, its deferred calls are executed in last-in-first-out order.
func deferDemo() {
	fmt.Println("counting")
	for i := 0; i < 10; i++ {
		defer fmt.Print(i)
	}
	fmt.Println("done")
}

func main() {
	basicType()
	typeConversions()
	pow(3, 2, 10)
	pow(3, 3, 20)
	switchOS()
	switchTrue()
	deferDemo()
}
