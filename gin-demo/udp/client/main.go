package main

import (
	"fmt"
	"net"
	"time"
)

func main() {
	udpConn, err := net.DialUDP(
		// socket dialog
		"udp", nil, &net.UDPAddr{
			// remote address
			IP:   net.IPv4(127, 0, 0, 1),
			Port: 3300,
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
	var sendBuf [128]byte // send buffer
	copy(sendBuf[:], "ping")
	var recvBuf [128]byte // receive buffer

	for i := 0; i < 1; i++ {
		_, err = udpConn.Write(sendBuf[:])
		if err != nil {
			panic(err)
		}

		n, remoteAddr, err := udpConn.ReadFromUDP(recvBuf[:])
		if err != nil {
			fmt.Println(err)
		}
		fmt.Printf(
			"\nNanosecond timestamp: %v\n"+
				"Recieved message: %v\n"+
				"Bytes count: %v\n"+
				"Server address: %v\n",
			time.Now().UnixNano(), string(recvBuf[:n]), n, remoteAddr,
		)
	}
}
