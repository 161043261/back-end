package main

import "fmt"

type Vertex struct {
	Lat, Long float64
}

// A map maps keys to values.
// The zero value of a map is nil. A nil map has no keys.
var m1 map[string]Vertex

func main() {
	// The make function returns an initialized map of the given type.
	m1 = make(map[string]Vertex)
	// Insert or update an element in map m1
	m1["Bell Labs"] = Vertex{40.68433, -74.39967}
	// Retrieve an element
	fmt.Println(m1["Bell Labs"])
	// Test that a key is present with a two-value assignment
	elem, ok := m1["Bell Labs"]
	fmt.Println(elem, ok) // {40.68433 -74.39967} true
	// Delete an element
	delete(m1, "Bell Labs")
	// Test
	elem, ok = m1["Bell Labs"]
	fmt.Println(elem, ok) // {0 0} false

	var m2 = map[string]Vertex{
		//      Key: Value
		"Bell Labs": Vertex{40.68433, -74.39967},
		// If the top-level type is just a type name,
		// you can omit it from the elements of the literal.
		"Google": {37.42202, -122.08408},
	}
	fmt.Println(m2)
}
