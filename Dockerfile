FROM eclipse-temurin:17-jdk-alpine
LABEL authors="lmello"

VOLUME /tmp

ARG AWS_DYNAMO_ENDPOINT
ARG AWS_ACCESS_KEY
ARG AWS_SECRET_KEY
ARG AWS_REGION

COPY target/*.jar app.jar

ENV AWS_DYNAMO_ENDPOINT=${AWS_DYNAMO_ENDPOINT}
ENV AWS_ACCESS_KEY=${AWS_ACCESS_KEY}
ENV AWS_SECRET_KEY=${AWS_SECRET_KEY}
ENV AWS_REGION=${AWS_REGION}

ENTRYPOINT ["java", "-jar", "/app.jar"]