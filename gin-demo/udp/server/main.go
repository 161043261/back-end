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

	fmt.Println("Go server listening on port 3302")
	for {
		var recvBuffer [128]byte // receive buffer

		n, remoteAddr, err := udpConn.ReadFromUDP(recvBuffer[:])
		if err != nil {
			fmt.Println(err)
		}
		fmt.Printf(
			"\nNanosecond timestamp: %v\n"+
				"Recieved message: %v\n"+
				"Bytes count: %v\n"+
				"Client address: %v\n",
			time.Now().UnixNano(), string(recvBuffer[:n]), n, remoteAddr,
		)

		var sendBuffer [128]byte
		copy(sendBuffer[:], "Server: pong!")
		// response
		_, err = udpConn.WriteToUDP(sendBuffer[:], remoteAddr)
		if err != nil {
			fmt.Println(err)
		}
	}
}
