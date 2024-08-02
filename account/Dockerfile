FROM maven:3-openjdk-17

WORKDIR /account
COPY . .
RUN mvn clean install

CMD mvn spring-boot:run