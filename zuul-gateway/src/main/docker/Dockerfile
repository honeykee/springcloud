#FROM hub.docker.gemii.cc:7443/babynote/wzj-tini-tzdata-openjdk:8-jdk-alpine

FROM adoptopenjdk/openjdk8-openj9:alpine-slim
VOLUME /tmp
COPY --from=hengyunabc/arthas:latest /opt/arthas /opt/arthas
RUN apk add tini
ENTRYPOINT ["/sbin/tini", "--"]
ONBUILD ADD *.jar app.jar
CMD ["java","-Duser.timezone=Asia/Shanghai","-Djava.security.egd=file:/dev/./urandom","-Xshareclasses","-Xquickstart","-jar","/app.jar"]
