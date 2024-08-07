package main

import (
	"fmt"
	"net"
	"time"
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

	for i := 0; i < 1; i++ {
		var sendBuffer [128]byte // send buffer
		copy(sendBuffer[:], "Client: ping!")
		_, err = udpConn.Write(sendBuffer[:])
		if err != nil {
			panic(err)
		}

		var recvBuffer [128]byte // receive buffer
		n, remoteAddr, err := udpConn.ReadFromUDP(recvBuffer[:])
		if err != nil {
			fmt.Println(err)
		}
		fmt.Printf(
			"\nNanosecond timestamp: %v\n"+
				"Recieved message: %v\n"+
				"Bytes count: %v\n"+
				"Server address: %v\n",
			time.Now().UnixNano(), string(recvBuffer[:n]), n, remoteAddr,
		)
	}
}
