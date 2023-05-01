FROM openjdk:17-oracle
ARG JAR_FILE=build/libs/atcha-*-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar","-Dspring.profiles.active=staging"]
