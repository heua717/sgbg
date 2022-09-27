#자바 버전
FROM openjdk:15-jdk-alpine as builder

WORKDIR /app

COPY . .

RUN chmod +x gradlew
RUN gradlew bootJAR



FROM openjdk:15-jdk-alpine
#가져올 파일 jar또는 war
ARG JAR_FILE=build/libs/*.jar

#war파일 복사
COPY --from=builder ${JAR_FILE} app.jar

#포트번호 설정
EXPOSE 8080

#ENTRYPOINT 명령을 지정, app.war 실행
ENTRYPOINT ["java","-jar","/app.jar"]
