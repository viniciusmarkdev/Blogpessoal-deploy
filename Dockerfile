# Etapa de build
FROM maven:3.8.4-openjdk-17 AS build

# Define o diretório de trabalho
WORKDIR /app

# Copia o pom.xml e baixa as dependências
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o código-fonte do projeto
COPY src /app/src

# Compila e empacota o aplicativo
RUN mvn clean package -DskipTests

# Etapa de execução
FROM openjdk:17-jdk-slim

# Define o diretório de trabalho
WORKDIR /app

# Expõe a porta que o aplicativo Spring Boot usará
EXPOSE 8080

# Copia o JAR construído da etapa de build
COPY --from=build /app/target/blogPessoal-0.0.1-SNAPSHOT.jar app.jar

# Define o comando para executar o JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
