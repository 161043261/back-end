package main

import (
	"github.com/gin-gonic/gin"
	"net/http"
)

func main() {
	router := gin.Default()

	router.GET(
		"/json", func(context *gin.Context) {
			data := map[string]any{
				"content": "故郷の原風景",
				"tag":     "<em>",
			}
			context.JSON(http.StatusOK, data)
			// Using AsciiJSON to Generates ASCII-only JSON with escaped non-ASCII characters.
			context.AsciiJSON(http.StatusOK, data)
		},
	)

	err := router.Run(":8080")
	if err != nil {
		panic(err)
	}
}
