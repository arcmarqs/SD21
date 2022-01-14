FROM openjdk:11

COPY ./src /usr/src/myapp
WORKDIR /usr/src/myapp

EXPOSE 52502

RUN chmod 755 gradlew &&\
    ./gradlew build &&\
    ./gradlew installDist

CMD ["./build/install/src/bin/peer-serv", "m2"]

