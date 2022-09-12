FROM openjdk:17-jdk-alpine
ARG JAR_FILE=build/libs/pautacontrol-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} pautacontrol.jar
ENTRYPOINT ["java","-jar","/pautacontrol.jar"]