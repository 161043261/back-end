#!/usr/bin/env zsh

go mod init njupt.edu.cn/hello

# >>> Add new module requirements and sums >>>
# Go will add the quote module as a requirement,
# as well as a go.sum file for use in authenticating the module.
go mod tidy
go run .

# For production use you’d publish the module from its repository (with a module path that reflected its published location),
# where Go tools could find it to download it.
# For now because you haven't published the module yet,
# you need to adapt the module so it can find the code on your local file system.
go mod edit -replace njupt.edu.cn/greetings=../greetings
go mod tidy