FROM bellsoft/liberica-openjdk-debian:17
EXPOSE 8761
RUN adduser --system spring-boot && addgroup --system spring-boot && addgroup spring-boot spring-boot
USER spring-boot
WORKDIR /app
COPY target/eureka-server-0.0.1-SNAPSHOT.jar ./application.jar
ENTRYPOINT ["java", "-jar", "./application.jar", "by.zemich.Eureka.server.EurekaServerApplication"]

# КОМАНДА ДЛЯ СОЗДАНИЯ ОБРАЗА: docker build -t eureka-server:1 .
# ЗАПУСК КОНТЕЙНЕРА: docker run -p 8761:8761 --name=eureka-discover-service  eureka-server:1

#FROM: Указывает базовый образ, на основе которого будет создан ваш Docker-контейнер. В данном случае используется образ с OpenJDK 17 от BellSoft на базе Debian. Этот образ предоставляет необходимую среду для выполнения Java-приложений.

#RUN: Выполняет команды в контейнере на этапе его построения. В данном случае создаётся системный пользователь spring-boot и группа spring-boot.
#adduser --system spring-boot: Создаёт системного пользователя spring-boot.
#addgroup --system spring-boot: Создаёт системную группу spring-boot.
#addgroup spring-boot spring-boot: Добавляет пользователя spring-boot в группу spring-boot.


#USER: Устанавливает пользователя, под которым будут выполняться следующие команды в Dockerfile и процессы в контейнере. В данном случае, это пользователь spring-boot, созданный на предыдущем шаге. Это обеспечивает безопасность, так как процесс не будет выполняться от имени root.
#WORKDIR: Устанавливает рабочую директорию для последующих команд COPY, ADD, RUN, CMD, ENTRYPOINT. В данном случае, рабочей директорией устанавливается /app.
#COPY: Копирует файлы или директории с хостовой машины в файловую систему контейнера. Здесь копируется содержимое директории target/dependency в директорию .lib внутри контейнера. Эта команда предполагает, что библиотечные зависимости вашего проекта уже были собраны и помещены в target/dependency.

#Основные моменты
#FROM: Указывает базовый образ с необходимой версией JDK.
#RUN: Создаёт пользователя и группы для безопасного выполнения приложения.
#USER: Устанавливает безопасного пользователя для выполнения последующих команд.
#WORKDIR: Устанавливает рабочую директорию для контейнера.
#COPY: Копирует зависимости и основной JAR файл в контейнер.
#ENTRYPOINT: Определяет команду для запуска Java-приложения внутри контейнера.