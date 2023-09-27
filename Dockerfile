FROM gradle:latest as builder

WORKDIR /app

COPY build.gradle.kts settings.gradle.kts /app/
COPY gradle /app/gradle
COPY gradlew /app/
COPY gradlew.bat /app/

RUN chmod +x ./gradlew

COPY src /app/src

RUN ./gradlew clean build

FROM openjdk:latest

WORKDIR /app

COPY --from=builder /app/build/libs/IBHubLive-all.jar .

ENTRYPOINT ["java", "-jar", "IBHubLive-all.jar"]
