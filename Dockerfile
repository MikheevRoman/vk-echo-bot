FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Environment variables
# VK API
ENV VK_API_TOKEN=vk1.a.FQr9O0dhQKEMuILs-m1X_dJdKQub_eeXhMk8JVK5WQP_8A5hLD0Fn0FDV_kyaUXygU7ocHPM1nsQqyIqliZNM2jBZ3J-khRbtcODUfy5qEijUDyIPAB7T-TYTX6rPXZmllouFFqMEywWvz39nACxm2pEjaY7eJeMds1H8CtWMZyPW_NGwaUfeI4qg1IREFMd95BCRxwOwERGkeo4MWgG9w
ENV VK_API_VERSION=5.199
ENV VK_API_SECRET=aaQ13axAPQEcczQa
ENV VK_API_CONFIRMATION=05ccc1d6
# LOCALE
ENV LC_ALL=ru_RU.UTF-8
ENV LANG=ru_RU.UTF-8
ENV LANGUAGE=ru_RU.UTF-8

ENTRYPOINT ["java", "-jar", "app.jar"]
