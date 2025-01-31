FROM maven:latest AS build
WORKDIR /app
COPY pom.xml /app/

RUN mvn dependency:resolve
COPY . /app
RUN mvn clean
RUN mvn package -DskipTests -X

FROM maven:latest
COPY --from=build /app/target/*.jar application.jar

RUN apt-get update && apt-get install -y --no-install-recommends postgresql-client
COPY entrypoint.sh entrypoint.sh

ENTRYPOINT [ "sh", "./entrypoint.sh" ]