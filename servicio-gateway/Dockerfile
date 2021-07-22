FROM openjdk:11-jdk-slim
COPY "./build/libs/service-gateway-1.0.jar" "service-gateway.jar"
EXPOSE 8092
CMD ["java","-jar","service-gateway.jar"]