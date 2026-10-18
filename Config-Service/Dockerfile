# 1. Define la imagen base de Java (JRE 21) muy ligera basada en Alpine Linux.
# Es fundamental que la versión coincida con la que usaste para compilar el proyecto (ej. Java 21).
FROM eclipse-temurin:21-jdk-alpine

# 2. Crea un punto de montaje temporal en el contenedor. Útil para que Spring Boot y Tomcat guarden datos temporales de forma eficiente.
VOLUME /tmp

# 3. Copia el archivo compilado (.jar) desde la carpeta "target" del microservicio hacia dentro del contenedor, renombrándolo como "app.jar".
COPY target/*.jar app.jar

# 4. Define el comando que se ejecutará automáticamente cuando el contenedor arranque (es equivalente a ejecutar "java -jar /app.jar" en la terminal).
ENTRYPOINT ["java","-jar","/app.jar"]
