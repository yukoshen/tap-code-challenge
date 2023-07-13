FROM openjdk:11
EXPOSE 8080
ADD target/tap-code-challenge-0.0.1-SNAPSHOT.jar tap-code-challenge.jar
CMD ["java", "-jar", "/tap-code-challenge.jar"]