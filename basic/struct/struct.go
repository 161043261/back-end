package main

import "fmt"

// A struct is a collection of fields.
type Vertex struct {
	X, Y int
}

// To access the field X of a struct when we have the struct pointer p
// we could write (*p).X
// However, that notation is cumbersome,
// so the language permits us instead to write just p.X without the explicit dereference.
func main() {
	v := Vertex{1, 2}
	v.X = 3
	p := &v // p is a struct pointer
	p.X = 1e2
	v1 := Vertex{X: 1}
	v2 := Vertex{Y: 2}
	v3 := Vertex{}
	fmt.Println(v, v1, v2, v3) // {100 2} {1 0} {0 2} {0 0}
}
