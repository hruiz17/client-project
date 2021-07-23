FROM openjdk:11-jdk-slim
COPY "./build/libs/servicio-gateway-1.0.jar" "servicio-gateway-1.0.jar"
EXPOSE 8080
ENTRYPOINT ["java","-jar","servicio-gateway-1.0.jar"]