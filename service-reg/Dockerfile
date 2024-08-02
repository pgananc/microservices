FROM maven:3-openjdk-17

WORKDIR /service-reg
COPY . .
RUN mvn clean install

CMD mvn spring-boot:run