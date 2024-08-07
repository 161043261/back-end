package main

/*
	Bind form-data request with custom struct
*/

import (
	"fmt"
	"github.com/gin-gonic/gin"
	"net/http"
)

type MappingA struct {
	PropA string `form:"columnA"`
}

type MappingB struct {
	A     MappingA
	PropB string `form:"columnB"`
}

type MappingC struct {
	PA    *MappingA
	PropC string `form:"columnC"`
}

type MappingD struct {
	AnonymousX struct {
		PropX string `form:"columnX"`
	}
	PropD string `form:"columnD"`
}

func ServiceB(context *gin.Context) {
	var mapper MappingB
	/*
		Bind checks the Method and Content-Type to select a binding engine automatically,
		Depending on the "Content-Type" header different bindings are used, for example:
		"application/json" --> JSON binding
		"application/xml"  --> XML binding
		It parses the request's body as JSON if Content-Type == "application/json" using JSON or XML as a JSON input.
		It decodes the json payload into the struct specified as a pointer.
		It writes a 400 error and sets Content-Type header "text/plain" in the response if input is not valid.
	*/
	err := context.Bind(&mapper)
	if err != nil {
		fmt.Println(err)
	}

	context.JSON(
		http.StatusOK, gin.H{
			"a": mapper.A,
			"b": mapper.PropB,
		},
	)
}

func ServiceC(context *gin.Context) {
	var mapper MappingC
	err := context.Bind(&mapper)
	if err != nil {
		fmt.Println(err)
	}

	context.JSON(
		200, map[string]any{
			"a": mapper.PA,
			"c": mapper.PropC,
		},
	)
}

func ServiceD(context *gin.Context) {
	var mapper MappingD
	err := context.Bind(&mapper)
	if err != nil {
		fmt.Println(err)
	}

	context.JSON(
		200, map[string]any{
			"x": mapper.AnonymousX,
			"d": mapper.PropD,
		},
	)
}

func main() {
	router := gin.Default()
	router.GET("/b", ServiceB)
	router.GET("/c", ServiceC)
	router.GET("/d", ServiceD)
	err := router.Run()
	if err != nil {
		panic(err)
	}
}

// curl http://localhost:8080/b?columnA=hello&columnB=world // {"a":{"PropA":"hello"},"b":"world"}
// curl http://localhost:8080/c?columnA=hello&columnC=world // {"a":{"PropA":"hello"},"c":"world"}
// curl http://localhost:8080/d?columnX=hello&columnD=world // {"d":"world","x":{"PropX":"hello"}}
