package main

import (
	"fmt"
	"net"
	"time"
)

func main() {
	conn, err := net.Dial("tcp", "127.0.0.1:3300")

	if err != nil {
		panic(err)
	}

	defer func(conn net.Conn) {
		err := conn.Close()
		if err != nil {
			fmt.Println(err)
		}
	}(conn)

	var sendBuf [128]byte
	var recvBuf [128]byte
	copy(sendBuf[:], "ping")

	for i := 0; i < 1; i++ {
		_, err := conn.Write(sendBuf[:])
		if err != nil {
			fmt.Println(err)
		}

		n, err := conn.Read(recvBuf[:])
		if err != nil {
			fmt.Println(err)
		}

		fmt.Printf(
			"\nNanosecond timestamp: %v\n"+
				"Recieved message: %v\n"+
				"Bytes count: %v\n",
			time.Now().UnixNano(), string(recvBuf[:n]), n,
		)
	}

}
