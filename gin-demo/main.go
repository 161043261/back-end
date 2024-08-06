package main

import (
	"fmt"
	"github.com/gin-gonic/gin"
	"net/http"
)

var users = make(map[string]string)

func setupRouter() *gin.Engine {
	// gin.DisableConsoleColor() // disable console color
	router := gin.Default() // router

	// Ping test
	router.GET(
		"/ping", func(context *gin.Context) {
			context.String(http.StatusOK, "pong")
		},
	)

	// Get user value
	router.GET(
		"/user/:name", func(context *gin.Context) { // context is a wrapper struct for *http.Request
			user := context.Params.ByName("name")
			value, ok := users[user]
			if ok {
				context.JSON(http.StatusOK /* 200 */, gin.H{"user": user, "value": value}) // type H map[string]any
			} else {
				context.JSON(http.StatusOK, map[string]any{"user": user, "status": "no value"})
			}
		},
	)

	//  authorized := router.Group("/")
	//  authorized.Use(
	//  	gin.BasicAuth(
	//  		gin.Accounts{
	//  			"userA": "pwdA",
	//  			"userB": "pwdB",
	//  		},
	//  	),
	//  )
	authorized := router.Group(
		"/", gin.BasicAuth(
			gin.Accounts{
				// type Accounts map[string]string

				// user
				"userA": "pwdA", // name:userA password:pwdA
				"userB": "pwdB", // name:userA password:pwdB
			},
		),
	)

	/*
		example curl for /admin with "authorization" header
		base64("userA:pwdA") = "dXNlckE6cHdkQQ=="

		curl -X POST http://localhost:8080/admin \
		-H 'authorization: Basic dXNlckE6cHdkQQ==' \
		-H 'content-type: application/json' \
		-d '{ "key": "value" }'
	*/
	authorized.POST(
		"/admin" /* admin is also ok */, func(context *gin.Context) {
			// type assertion
			name := context.MustGet(gin.AuthUserKey).(string) // const AuthUserKey = "user"
			// parse json
			var json struct {
				Value string `json:"key" binding:"required"`
			}
			if context.Bind(&json) == nil {
				users[name] = json.Value
				context.JSON(http.StatusOK, map[string]any{"status": "ok"})
			}
		},
	)
	return router
}

func main() {
	fmt.Println(base64("userA:pwdA"))
	router := setupRouter() // Listen and Server in 0.0.0.0:8080
	err := router.Run(":8080")
	if err != nil {
		panic(err)
	}
}
