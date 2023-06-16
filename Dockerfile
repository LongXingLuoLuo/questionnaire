FROM openjdk:8-jdk-alpine
MAINTAINER longxingluoluo <2473475362@qq.com>
EXPOSE 1241
ADD target/questionnaire-0.0.1-SNAPSHOT.jar questionnaire.jar
ENTRYPOINT exec java -jar /questionnaire.jar