FROM openjdk:8-alpine as build
WORKDIR /workspace/app

COPY src src
COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .

RUN tr -d '\015' <./mvnw >./mvnw.sh && mv ./mvnw.sh ./mvnw && chmod 700 mvnw

RUN ./mvnw install

FROM openjdk:8-alpine as run
VOLUME /tmp

ARG JAR_FILE=/workspace/app/target/*.jar
COPY --from=build ${JAR_FILE} app.jar

CMD ["sh", "-c", "java -Dserver.port=$PORT ${JAVA_OPTS} -jar /app.jar"]