package main

import (
	"fmt"
	"net/http"
	"os"
)

func native() {
	http.HandleFunc(
		"/hello", func(w http.ResponseWriter, r *http.Request) {
			// func (ResponseWriter) Write([]byte) (int, error)
			_, err := w.Write([]byte("<h1>native.go</h1>"))
			source, err := os.ReadFile("./native.go")
			// func Fprintln(w io.Writer, a ...any) (n int, err error)
			_, err = fmt.Fprintln(w, string(source))
			if err != nil {
				fmt.Println(err)
			}
		},
	)

	err := http.ListenAndServe(":8080", nil)
	if err != nil {
		panic(err)
	}
}
