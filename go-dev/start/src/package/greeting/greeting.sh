#!/usr/bin/env zsh
go mod init bronya.com/greeting

# At the command line in the greeting directory,
# run the go test command to execute the test.
go test

# You can add the -v flag to get verbose output that lists all of the tests and their results.
go test -v
