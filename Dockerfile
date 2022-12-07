FROM maven:latest
#set maven dependency to specific version?

WORKDIR /code

COPY . /code

ARG host
ARG password
ARG user
ARG DB_NAME

ENV host ${host}
ENV password ${password}
ENV user ${user}
ENV db ${db}

RUN mvn clean install -DskipTests=true

EXPOSE 8080

CMD ["java","-jar", "/code/target/JavaWebService-1.0-SNAPSHOT.jar", "server", "/code/config.yml"]
