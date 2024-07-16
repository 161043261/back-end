package main

// You can send and receive values with the channel operator <-
// The data flows in the direction of the arrow.

// ch <- v   // Send v to channel ch.
// v := <-ch // Receive from ch, and assign value to v.

// Like maps and slices, channels must be created before use:
// ch := make(chan int)

// By default, sends and receives block until the other side is ready.
// This allows goroutines to synchronize without explicit locks or condition variables.
import "fmt"

func sum(s []int, ch chan int) {
	ret := 0
	for _, v := range s {
		ret += v
	}
	ch <- ret // send ret to ch
}

func main() {
	s := []int{1, 6, 1, 0, 4, 3, 2, 6, 1}
	ch := make(chan int)
	go sum(s[:len(s)/2], ch)             // left
	go sum(s[len(s)/2:], ch)             // right
	right, left := <-ch, <-ch            // receive from ch
	fmt.Println(left, right, left+right) // 8 16 24

	// Channels can be buffered.
	// Provide the buffer length as the second argument to make to initialize a buffered channel

	//    ===================
	// <- 0 1 2 3 4 5 6 7 8 9 <-
	//    ===================
	dec := make(chan int, 10)
	fmt.Println("len(dec) =", len(dec)) // len(dec) = 0
	fmt.Println("cap(dec) =", cap(dec)) // cap(dec) = 10
	for i := 0; i < 10; i++ {
		dec <- i
	}
	// dec <- 10 // BLOCK: Sends to a buffered channel block only when the buffer is full.
	for len(dec) > 0 {
		fmt.Print(<-dec, " ") // 0 1 2 3 4 5 6 7 8 9
	}
	// fmt.Print(<-ch10) // BLOCK: Receives block when the buffer is empty.

	// A sender can close a channel to indicate that no more values will be sent.
	// Receivers can test whether a channel has been closed: `v, ok := <-ch`
	// `ok` is false if there are no more values to receive and the channel is closed.

	// Only the sender should close a channel, never the receiver.
	// Sending on a closed channel will cause a panic.
	fmt.Println()
	go fibonacci(cap(dec), dec)
	for i := range dec { // receives values from the channel repeatedly until it is closed.
		fmt.Print(i, " ")
	}
}

func fibonacci(n int, dec chan int) {
	curr, next := 0, 1
	for i := 0; i < n; i++ {
		dec <- curr
		curr, next = next, curr+next
	}
	close(dec)
}
