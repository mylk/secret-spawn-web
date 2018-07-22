## About

This is the web implementation of the [secret-spawn](https://github.com/mylk/secret-spawn/) CLI application.
`secret-spawn` generates memorizable passwords out of valid English words and phrases.
For more information refer to the project's README file [here](https://github.com/mylk/secret-spawn/blob/master/README.md).

This application is written in Java, on-top of the Spring Boot framework.

## Run the application

Please find below the commands to run the application locally or in a Docker container.
After running the application, visit [http://localhost:8080](http://localhost:8080).

### Run locally

The application uses Gradle so, to build and run the application, just execute:

```
./gradlew bootRun
```

### Run in a container

Alternatively to run the application in a container, first build the Docker image of the application:


```
docker build -t mylkohrly/secret-spawn-web .
```

Run a container using the previously built image:


```
docker run -p 8080:8080 mylkohrly/secret-spawn-web
```
