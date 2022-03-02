FROM openjdk:17-oraclelinux8
ARG JAR_FILE

COPY target/${JAR_FILE} /app.jar

ENV APP_ARGS=""
ENV JAVA_OPTS=""


CMD java -Xmx512m ${JAVA_OPTS}  -jar app.jar ${APP_ARGS}

EXPOSE 8080
