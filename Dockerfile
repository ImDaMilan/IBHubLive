FROM gradle:latest as builder

WORKDIR /app

COPY build.gradle settings.gradle /app/
COPY gradle /app/gradle
COPY gradlew /app/
COPY gradlew.bat /app/

COPY src /app/src

RUN ./gradlew build

FROM openjdk:latest

WORKDIR /app

COPY --from=builder /app/build/libs/IBHubLive-0.0.1.jar .

ENTRYPOINT ["java", "-jar", "IBHubLive-0.0.1.jar"]
