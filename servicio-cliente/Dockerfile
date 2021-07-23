FROM openjdk:11-jdk-slim
COPY "./build/libs/servicio-cliente-1.0.jar" "servicio-cliente-1.0.jar"
EXPOSE 8095
ENTRYPOINT ["java","-jar","servicio-cliente-1.0.jar"]