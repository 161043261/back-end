package utils

import (
	b64 "encoding/base64"
	"fmt"
)

func Base64(data string) string {
	sEnc := b64.StdEncoding.EncodeToString([]byte(data))
	fmt.Println("str encoded", sEnc)
	sDec, _ := b64.StdEncoding.DecodeString(sEnc)
	fmt.Println("str decoded", string(sDec))

	// This encodes/decodes using a URL-compatible base64 format.
	uEnc := b64.URLEncoding.EncodeToString([]byte(data))
	fmt.Println("URL encoded", uEnc)
	uDec, _ := b64.URLEncoding.DecodeString(uEnc)
	fmt.Println("URL encoded", string(uDec))

	return sEnc
}
