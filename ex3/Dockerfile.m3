FROM openjdk:11

COPY ./src /usr/src/myapp
WORKDIR /usr/src/myapp

EXPOSE 54547

RUN javac ds/trabalho/parte3/Peer.java

CMD ["java", "ds.trabalho.parte3.Peer", "m3", "m4", "m1", "m2"]

