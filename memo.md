# build Dockerfile 
docker build -t image_name -f docker/Dockerfile.dev .

# Login container with Volume
docker run -v $(pwd)/app:/usr/src/http -it devhttpkt /bin/bash

# gradle init
```
root@0f10e60ddacf:/usr/src/http# gradle init --type=kotlin-application

Welcome to Gradle 6.5.1!

Here are the highlights of this release:
 - Experimental file-system watching
 - Improved version ordering
 - New samples

For more details see https://docs.gradle.org/6.5.1/release-notes.html

Starting a Gradle Daemon (subsequent builds will be faster)

Select build script DSL:
  1: Groovy
  2: Kotlin
Enter selection (default: Kotlin) [1..2] 1

Project name (default: http): 
Source package (default: http): 

BUILD SUCCESSFUL in 2m 23s
2 actionable tasks: 2 executed
```

# test
## GET
```shell script
$ curl localhost:8080
// => <h1>Test Kotlin!!</h1>
```

## POST
```shell script
$ curl localhost:8080 -X POST -d "Message Body!!"
```
