package main

import (
	"fmt"
	"net"
)

func main() {
	udpConn, err := net.ListenUDP(
		"udp", &net.UDPAddr{
			IP:   net.IPv4(0, 0, 0, 0),
			Port: 3333,
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

	fmt.Println("Server listening on port 3333")
	for {
		var recvBuffer [1024]byte // receive buffer
		n, remoteAddr, err := udpConn.ReadFromUDP(recvBuffer[:])
		if err != nil {
			fmt.Println(err)
		}
		fmt.Printf(
			"\nRecieved data: %v\n"+
				"Bytes count: %v\n"+
				"Client address: %v\n", string(recvBuffer[:n]), n, remoteAddr,
		)

		var sendBuffer = []byte("Server responses message: Hello!") // send buffer
		// response
		_, err = udpConn.WriteToUDP(sendBuffer, remoteAddr)
		if err != nil {
			fmt.Println(err)
		}
	}
}
