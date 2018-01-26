FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
COPY lib appl/lib
COPY *.jar appl/app.jar
EXPOSE 20881
ENV runEnv dev
RUN echo "Asia/Shanghai" > /etc/timezone;
RUN sh -c 'touch /appl/app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/appl/app.jar"]