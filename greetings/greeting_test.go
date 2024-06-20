package greetings

import (
	"regexp"
	"testing"
)

// TesthelloName calls greetings.hello with a name, checking for a valid return value.
func TesthelloName(t *testing.T) {
	name := "Gladys"
	want := regexp.MustCompile(`\b` + name + `\b`)
	msg, err := hello("Gladys")
	if !want.MatchString(msg) || err != nil {
		t.Fatalf(`hello("Gladys") = %q, %v, want match for %#q, nil`, msg, err, want)
	}
}

// TesthelloEmpty calls greetings.hello with an empty string, checking for an error.
func TesthelloEmpty(t *testing.T) {
	msg, err := hello("")
	if msg != "" || err == nil {
		t.Fatalf(`hello("") = %q, %v, want "", error`, msg, err)
	}
}
