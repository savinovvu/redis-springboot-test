
FROM gradle:6.2-jdk11 as build-container
USER root

COPY ./ /home/gradle


RUN gradle clean build && \
    cp /home/gradle/build/libs/redis-test.jar /opt && \
    rm -rf /home/gradle/**





FROM openjdk:11-jre-slim as run-container

COPY --from=build-container /opt/redis-test.jar /opt/redis-test.jar



