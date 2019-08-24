FROM openjdk:8-alpine

COPY target/uberjar/swag-app.jar /swag-app/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/swag-app/app.jar"]
