FROM alpine

RUN apk add openjdk8

EXPOSE 8080

ADD . /secret-spawn-web
WORKDIR /secret-spawn-web

RUN ./gradlew build

CMD ["./start.sh"]