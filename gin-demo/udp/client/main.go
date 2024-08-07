package main

import (
	"fmt"
	"net"
)

func main() {
	udpConn, err := net.DialUDP(
		// socket dialog
		"udp", &net.UDPAddr{
			// local address
			IP:   net.IPv4(127, 0, 0, 1),
			Port: 2222,
		}, &net.UDPAddr{
			// remote address
			IP:   net.IPv4(127, 0, 0, 1),
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

	for i := 0; i < 3; i++ {
		sendBuffer := []byte("Client requests message: Konnichiwa!") // send buffer
		_, err = udpConn.Write(sendBuffer)
		if err != nil {
			panic(err)
		}

		recvBuffer := make([]byte, 1024) // receive buffer
		n, remoteAddr, err := udpConn.ReadFromUDP(recvBuffer)
		if err != nil {
			fmt.Println(err)
		}
		fmt.Printf(
			"\nRecieved data: %v\n"+
				"Bytes count: %v\n"+
				"Server address: %v\n", string(recvBuffer[:n]), n, remoteAddr,
		)
	}
}
