FROM gradle:latest as builder

WORKDIR /app

COPY build.gradle.kts settings.gradle.kts /app/
COPY gradle /app/gradle
COPY gradlew /app/
COPY gradlew.bat /app/

RUN chmod +x ./gradlew

COPY src /app/src

RUN ./gradlew build

FROM openjdk:latest

WORKDIR /app

COPY --from=builder /app/build/libs/IBHubLive-0.0.1.jar .

ENTRYPOINT ["java", "-jar", "IBHubLive-0.0.1.jar"]
