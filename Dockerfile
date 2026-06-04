#########################################################
# 목적 : egovframe-web-5.0.0.war를 tomcat으로 구동하는 도커 이미지 생성
# 실행방법 : 프로젝트 루트디렉토리에서(Dockerfile 경로) 아래 명령어 실행
# (명령어) docker build . -t egovframe-web:5.0.0
#########################################################

# tomcat base image 설정 (10.1-jdk17-temurin)
FROM tomcat:10.1-jdk17-temurin
COPY ./target/egovframe-web-5.0.0.war /usr/local/tomcat/webapps/app.war
# tomcat의 web서버 포트설정
EXPOSE 8080
# 컨테이너 시작 시 tomcat이 구동되도록 해당 명령을 실행
ENTRYPOINT ["catalina.sh","run"]