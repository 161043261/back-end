package main

import (
	"fmt"
	"strings"
)

// The type [n]T is an array of n values of type T.
// The type []T is a slice with elements of type T.

// An array has a fixed size.
// A slice is a dynamically-sized, flexible view into the elements of an array.
// In practice, slices are much more common than arrays.
func main() {
	var a [2]string
	a[0] = "Hello"
	a[1] = "World"
	fmt.Println(a[0], a[1])
	fmt.Println(a)

	primes := [6]int{2, 3, 5, 7, 11, 13}
	fmt.Println(primes)

	// A slice is formed by specifying two indices, a low and high bound,
	// separated by a colon: a[low:high]
	// This selects a half-open range which includes the first element, but excludes the last one.
	var subPrimes []int = primes[1:4]
	fmt.Println(subPrimes) // [3 5 7]

	names := [4]string{
		"Aa",
		"Bb",
		"Cc",
		"Dd",
	}
	fmt.Println(names)

	// A slice does not store any data,
	// it just describes a section of an underlying array.
	// Changing the elements of a slice modifies the corresponding elements of its underlying array.
	// Other slices that share the same underlying array will see those changes.
	b := names[0:2]
	c := names[1:3]
	fmt.Println(b, c)

	c[0] = "XXX"
	fmt.Println(b, c)
	fmt.Println(names)

	// A slice literal is like an array literal without the length.
	d := [3]bool{true, true, false} // This is an array literal

	// And this creates the same array as above,
	// then builds a slice that references it
	e := []bool{true, true, false}
	fmt.Println(d, e)

	f := []struct {
		i int
		b bool
	}{{2, true},
		{3, false},
		{5, true},
		{7, true},
		{11, false},
		{13, true}}
	fmt.Println(f)

	// The default is zero for the low bound and
	// the length of the slice for the high bound.
	g := []int{2, 3, 5, 7, 11, 13}
	g = g[1:4]
	fmt.Println(g)
	g = g[:2]
	fmt.Println(g)
	g = g[1:]
	fmt.Println(g)

	// A slice has both a length and a capacity.
	// The length of a slice is the number of elements it contains.
	// The capacity of a slice is the number of elements in the underlying array,
	// counting from the first element in the slice.
	h := []int{2, 3, 5, 7, 11, 13}
	printSlice(h)
	h = h[:0] // Slice the slice to give it zero length.
	printSlice(h)
	h = h[:4] // Extend its length.
	printSlice(h)
	h = h[2:] // Drop its first two values.
	printSlice(h)

	// The zero value of a slice is nil
	// A nil slice has a length and capacity of 0 and has no underlying array.
	var j []int
	fmt.Println(j, len(j), cap(j))
	if j == nil {
		fmt.Println("nil!")
	}

	// Slices can be created with the built-in make function;
	// this is how you create dynamically-sized arrays.
	k := make([]int, 5)
	printSlice(k)         // len=5 cap=5 [0 0 0 0 0]
	k = make([]int, 0, 5) // len=0 cap=5 []
	printSlice(k)
	k = k[:2] // len=2 cap=5 [0 0]
	printSlice(k)
	k = k[2:5] // len=3 cap=3 [0 0 0]
	printSlice(k)

	// Slices can contain any type, including other slices.
	board := [][]string{
		[]string{"The", "prefix"},
		{"[]string", "can"},
		{"be", "omitted"},
	}

	for i := 0; i < len(board); i++ {
		fmt.Printf("%s\n", strings.Join(board[i], " "))
	}

	// func append(s []T, vs ...T) []T
	// The first parameter s of append is a slice of type T,
	// and the rest are T values to append to the slice.

	// The resulting value of is a slice containing all the elements of the original slice plus the provided values.
	// If the backing array of is too small to fit all the given values a bigger array will be allocated.
	// The returned slice will point to the newly allocated array
	var l []int
	printSlice(l)
	// append works on nil slices.
	l = append(l, 0)
	printSlice(l)
	// The slice grows as needed.
	// We can add more than one element at a time.
	l = append(l, 1, 2, 3)
	printSlice(l)

	// The range form of the for loop iterates over a slice or map.
	// When ranging over a slice, two values are returned for each iteration.
	// The first is the index, and the second is a copy of the element at that index.
	var pow = []int{1, 2, 4, 8, 16, 32, 64, 128}
	for i, v := range pow {
		fmt.Printf("2**%d = %d\n", i, v)
	}

	// If you only want the index, you can omit the second variable.
	for i := range pow {
		pow[i] = 1 << uint(i) // == 2**i
	}
	// You can skip the index or value by assigning to _
	for _, value := range pow {
		fmt.Printf("%d ", value)
	}

}

func printSlice(s []int) {
	fmt.Printf("len=%d cap=%d %v\n", len(s), cap(s), s)
}
