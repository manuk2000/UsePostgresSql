FROM openjdk:17
## Create a non-root user with a home directory
#RUN useradd -m spring
#
## Switch to the non-root user
#USER spring


#CMD mvn clean install

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} orders.jar
ENTRYPOINT ["java","-jar","orders.jar"]