#FROM ubuntu:latest AS build:
#Define a imagem base para a primeira etapa do build,
# que será baseada no Ubuntu mais recente. Aqui, o 
#AS build indica que esta fase será chamada de "build", 
#permitindo que seja usada posteriormente no Dockerfile.


FROM ubuntu:latest AS  build







#RUN apt-get update:
#Atualiza a lista de pacotes disponíveis no Ubuntu para garantir que todos os pacotes que serão instalados estejam na versão mais recente.


RUN apt-get update 




# RUN apt-get install openjdk-17-jdk -y:
#Instala o JDK do Java 17 na imagem, que é necessário para compilar e executar aplicações Java.
RUN apt-get install openjdk-17-jdk -y

#
#COPY . .Copia todos os arquivos do diretório atual (no host) para dentro do container, preservando a estrutura de diretórios.
# Isso inclui o código-fonte do projeto.
#
COPY . .




#RUN apt-get install maven -y:Instala o Maven, 
#uma ferramenta de build para projetos Java, que será usada para compilar o código da aplicação.


RUN apt-get install maven -y

# RUN mvn clean install:
#Executa o comando Maven clean install, que remove qualquer build anterior 
#e cria o novo artefato (JAR ou WAR) da aplicação Java a partir do código-fonte.

RUN mvn clean install

#FROM openjdk:17-jdk-slim:
#Inicia a segunda fase do Dockerfile. Aqui, a imagem base usada é a versão "slim" do JDK 17, 
#que é uma versão mais enxuta do Java para rodar a aplicação.

FROM openjdk:17-jdk-slim



#EXPOSE 8080:

#Informa que a aplicação dentro do container irá escutar na porta 8080. Esta é a porta usada para acessar a aplicação.

EXPOSE 8080


#COPY --from=build /target/blogPessoal-0.0.1-SNAPSHOT.jar app.jar:

#Copia o arquivo JAR criado na fase de build (/target/blogPessoal-0.0.1-SNAPSHOT.jar) 
#da primeira fase ("build") para a segunda fase, renomeando-o para app.jar.


COPY --from=build /target/blogPessoal-0.0.1-SNAPSHOT.jar app.jar


#ENTRYPOINT ["java", "-jar" , "app.jar"]:

#Define o comando de inicialização do container.
# Aqui, ele executa o comando java -jar app.jar, que iniciará a aplicação Java contida no arquivo app.jar.

ENTRYPOINT ["java", "-jar" , "app.jar"]