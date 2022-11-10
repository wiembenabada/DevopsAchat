FROM openjdk:11
COPY ./target/achat-1.0.jar achat-1.0.jar
EXPOSE 8086
ENTRYPOINT ["java","-jar","/achat-1.0.jar"]

