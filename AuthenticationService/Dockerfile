#start with the base image containing Java Runtime
FROM eclipse-temurin:24-jdk

#Infromation around who maintains the project.
MAINTAINER RetailersHubApplication.com

#Add the application jar to the image
COPY target/AuthenticationService-0.0.1-SNAPSHOT.jar AuthenticationService-0.0.1-SNAPSHOT.jar

#Exceute the Application.
ENTRYPOINT ["java", "-jar", "AuthenticationService-0.0.1-SNAPSHOT.jar"]