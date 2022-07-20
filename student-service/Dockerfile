FROM openjdk:11


WORKDIR /app/

COPY ./pom.xml /app
COPY ./.mvn ./.mvn
COPY ./mvnw .
COPY ./pom.xml .

COPY ./src ./src
RUN ./mvnw clean package -Dmaven.test.skip

RUN mkdir ./logs
COPY /target/student-service-0.0.1-SNAPSHOT.jar .
EXPOSE 8080

CMD ["java", "-jar", "student-service-0.0.1-SNAPSHOT.jar"]