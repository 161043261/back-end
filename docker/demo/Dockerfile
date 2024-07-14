FROM openjdk:21-jdk-slim-buster
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
COPY ./demo.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
