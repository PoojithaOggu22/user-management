# Build stage
FROM gradle:8.3.0-jdk17 AS TEMP_BUILD_IMAGE
ENV APP_HOME=/usr/app
WORKDIR $APP_HOME
COPY build.gradle settings.gradle $APP_HOME
COPY gradle $APP_HOME/gradle
COPY --chown=gradle:gradle . /home/gradle/src
COPY . $APP_HOME
USER root
RUN chown -R gradle /home/gradle/src
RUN chmod +x ./gradlew  # Add execute permission to gradlew
RUN ./gradlew build

# Actual container
FROM openjdk:18
ENV ARTIFACT_NAME=rewards-app-0.0.1-SNAPSHOT.jar
ENV APP_HOME=/usr/app
WORKDIR $APP_HOME
COPY --from=TEMP_BUILD_IMAGE $APP_HOME/rewards-app/build/libs/$ARTIFACT_NAME .

EXPOSE 8080
ENTRYPOINT exec java -jar ${ARTIFACT_NAME}
