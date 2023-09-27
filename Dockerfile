FROM gradle:latest as builder

WORKDIR /

COPY build.gradle settings.gradle /

COPY src /src

RUN gradle build

FROM openjdk:latest

WORKDIR /app

COPY --from=builder /build/libs/IBHubLive-0.0.1.jar .

ENTRYPOINT ["java", "-jar", "IBHubLive-0.0.1.jar"]
