FROM adoptopenjdk/openjdk11
ARG RUN_ENV
ARG JASYPT_ENCRYPTOR_PASSWORD
ARG MYSQL_USER
ARG MYSQL_PASSWORD
ARG MYSQL_HOST
ARG MYSQL_PORT
ARG MYSQL_DATABASE

ENV RUN_ENV=${RUN_ENV} \
    JASYPT_ENCRYPTOR_PASSWORD=${JASYPT_ENCRYPTOR_PASSWORD} \
    MYSQL_USER=${MYSQL_USER} \
    MYSQL_PASSWORD=${MYSQL_PASSWORD} \
    MYSQL_HOST=${MYSQL_HOST} \
    MYSQL_PORT=${MYSQL_PORT} \
    MYSQL_DATABASE=${MYSQL_DATABASE} 
CMD mvn clean package -DskipTests
ARG JAR_FILE_PATH=target/*.jar
COPY  ${JAR_FILE_PATH} app.jar
ENTRYPOINT ["java","-jar", "app.jar"]
