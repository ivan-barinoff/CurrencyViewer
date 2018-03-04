FROM java:8
ADD build/server-0.1.jar server.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/server.jar"]
EXPOSE 8080