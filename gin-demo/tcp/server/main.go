package main

import (
	"bufio"
	"fmt"
	"net"
	"time"
)

func serve(conn net.Conn) {
	defer func(conn net.Conn) {
		err := conn.Close()
		if err != nil {
			fmt.Println(err)
		}
	}(conn)

	var recvBuf [128]byte
	var sendBuf [128]byte
	copy(sendBuf[:], "pong")

	reader := bufio.NewReader(conn)
	n, err := reader.Read(recvBuf[:])
	if err != nil {
		fmt.Println(err)
	}

	fmt.Printf(
		"\nNanosecond timestamp: %v\n"+
			"Recieved message: %v\n"+
			"Bytes count: %v\n",
		time.Now().UnixNano(), string(recvBuf[:n]), n,
	)

	_, err = conn.Write(sendBuf[:])
	if err != nil {
		fmt.Println(err)
	}
}

func main() {
	listener, err := net.Listen("tcp", "127.0.0.1:3302")
	if err != nil {
		panic(err)
	}
	fmt.Println("Go server listening on port 3302")
	for {
		conn, err := listener.Accept() // setup tcp connection
		if err != nil {
			fmt.Println(err)
		}
		go serve(conn) // start a goroutine for handling tcp connection
	}
}
