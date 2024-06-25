package greeting

import (
	"regexp"
	"testing"
)

// TestHelloName calls greeting.hello with a name, checking for a valid return value.
func TestHelloName(t *testing.T) {
	name := "OS X"
	want := regexp.MustCompile(`\b` + name + `\b`)
	msg, err := hello("OS X")
	if !want.MatchString(msg) || err != nil {
		t.Fatalf(`hello("OS X") = %q, %v, want match for %#q, nil`, msg, err, want)
	}
}

// TestHelloEmpty calls greeting.hello with an empty string, checking for an error.
func TestHelloEmpty(t *testing.T) {
	msg, err := hello("")
	if msg != "" || err == nil {
		t.Fatalf(`hello("") = %q, %v, want "", error`, msg, err)
	}
}
