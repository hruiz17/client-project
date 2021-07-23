FROM openjdk:11-jdk-slim
COPY "./build/libs/servicio-registro-1.0.jar" "servicio-registro-1.0.jar"
EXPOSE 8091
ENTRYPOINT ["java","-Dspring.profiles.active=prod","-jar","servicio-registro-1.0.jar"]