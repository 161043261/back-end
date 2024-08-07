package main

import (
	"fmt"
	"net"
	"time"
)

func main() {
	udpConn, err := net.ListenUDP(
		"udp", &net.UDPAddr{
			IP:   net.IPv4(0, 0, 0, 0),
			Port: 3302,
		},
	)
	if err != nil {
		panic(err)
	}

	defer func(udpConn *net.UDPConn) {
		err := udpConn.Close()
		if err != nil {
			fmt.Println(err)
		}
	}(udpConn)
	var recvBuf [128]byte // receive buffer
	var sendBuf [128]byte
	copy(sendBuf[:], "pong")

	fmt.Println("Go server listening on port 3302")
	for {

		n, remoteAddr, err := udpConn.ReadFromUDP(recvBuf[:])
		if err != nil {
			fmt.Println(err)
		}
		// receive
		fmt.Printf(
			"\nNanosecond timestamp: %v\n"+
				"Recieved message: %v\n"+
				"Bytes count: %v\n"+
				"Client address: %v\n",
			time.Now().UnixNano(), string(recvBuf[:n]), n, remoteAddr,
		)

		// response
		_, err = udpConn.WriteToUDP(sendBuf[:], remoteAddr)
		if err != nil {
			fmt.Println(err)
		}
	}
}
