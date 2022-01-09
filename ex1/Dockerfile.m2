FROM openjdk:11

COPY ./src /usr/src/myapp
WORKDIR /usr/src/myapp

EXPOSE 54545

RUN javac ds/trabalho/parte1/Peer.java

CMD ["java", "ds.trabalho.parte1.Peer", "m2", "m3"]
