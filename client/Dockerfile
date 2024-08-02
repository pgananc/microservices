FROM maven:3-openjdk-17

WORKDIR /client
COPY . .
RUN mvn clean install

CMD mvn spring-boot:run