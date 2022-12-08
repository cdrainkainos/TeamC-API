FROM maven:3.6.3-jdk-11
WORKDIR /code
COPY . /code

ARG DB_HOST
ARG DB_PASSWORD
ARG DB_USERNAME
ARG DB_NAME

ENV DB_HOST ${DB_HOST}
ENV DB_PASSWORD ${DB_PASSWORD}
ENV DB_USERNAME ${DB_USERNAME}
ENV DB_NAME ${DB_NAME}

RUN mvn clean install -DskipTests=true
EXPOSE 8080
CMD ["java","-jar","/code/target/JavaWebService-1.0-SNAPSHOT.jar","server","/code/config.yml"]

# docker build -t backend:0.1
# --build-arg DB_HOST=academy2020.cpc8rvmbbd9k.eu-west-2.rds.amazonaws.com
# --build-arg DB_USERNAME=BlazejR
# --build-arg DB_PASSWORD=password
# --build-arg DB_NAME=AgileSprints_SamB .
