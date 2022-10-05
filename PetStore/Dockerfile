# First stage: complete build environment
FROM maven:3.5.0-jdk-8-alpine AS builder

# add pom.xml and source code
ADD ./pom.xml pom.xml
ADD ./src src/

# package jar
RUN mvn clean package

# Second stage: minimal runtime environment
From openjdk:8-jre-alpine

# copy jar from the first stage
COPY --from=builder target/pet-store-0.0.1-SNAPSHOT.jar pet-store-0.0.1-SNAPSHOT.jar

CMD ["java", "-agentlib:jdwp=transport=dt_socket,address=5005,server=y,suspend=n", "-jar", "pet-store-0.0.1-SNAPSHOT.jar"]