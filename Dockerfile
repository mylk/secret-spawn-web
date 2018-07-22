FROM openjdk:8

EXPOSE 8080

ADD . /secret-spawn-web
WORKDIR /secret-spawn-web

CMD ["./gradlew", "bootRun"]