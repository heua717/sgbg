#자바 버전
FROM openjdk:15-jdk-alpine as builder

# 현재 내 위치는 workspace
# workspace/SGBG/backend/src/main/resources/application.properties
WORKDIR /app

COPY . .
COPY ../../../application.properties ./src/main/resources/

RUN chmod +x ./gradlew
RUN ./gradlew bootJAR


FROM openjdk:15-jdk-alpine 
#war파일 복사
COPY --from=builder app/build/libs/*.jar ./app.jar

#포트번호 설정
EXPOSE 8080

#ENTRYPOINT 명령을 지정, app.war 실행
ENTRYPOINT ["java","-jar","/app.jar"]
