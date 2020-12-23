FROM openjdk:8
VOLUME /tmp
ADD servicio-oauth-0.0.1-SNAPSHOT.jar servicio-oauth.jar
ENTRYPOINT ["java","-jar","/servicio-oauth.jar"]