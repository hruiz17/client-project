FROM openjdk:11-jdk-slim
COPY "./build/libs/config-service-1.0.jar" "config-service-1.0.jar"
EXPOSE 8090
ENTRYPOINT ["java","-jar","config-service-1.0.jar"]