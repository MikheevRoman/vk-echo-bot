# Task description

Необходимо выполнить интеграцию с BotAPI VK. [https://vk.com/dev/bots_docs](https://vk.com/dev/bots_docs)  

В рамках задания нужно создать бота который будет цитировать присланный ему текст. Пример взаимодействия с подобным ботом см. на картинке:

![](https://lh7-rt.googleusercontent.com/docsz/AD_4nXc3pAdHt2WNunl7zv_5qyu3ALCYRHKO2gO44vgSJWuJEYuyOV0jTps0Cfv2Zv01J4q3rkZfYD_BBtQKLzopIIB3yFZMlzHmOH8hXAfVyGceWkRzjIvaGGA36lPlYi1xqzG4MWVSnzTrB-PwD_zCFaiD30D7?key=-DhPVIwxkF2vP-CGvjzkZA)
## Минимальные требования к реализации

- Нужно приложить ссылку на git репозиторий с исходниками
- Реализация должна быть на языке Kotlin или Java. Использование Kotlin будет преимуществом, но не является обязательным
- Должен использоваться фреймворк Spring Boot
- Нельзя использовать готовые библиотеки для реализации VkApi 

Все остальные критерии (такие как структура кода, покрытие тестами, конфигурация, документация и др) являются опциональными, но будут также оцениваться

При реализации может потребоваться использование внешних https адресов для локальной машины. Для этого можно использовать ngrok.
# Run application (Linux)

0. (If necessary) Install Docker
```bash
sudo apt-get update
sudo apt-get install docker-ce docker-ce-cli containerd.io docker-buildx-plugin
```

1. Clone repository
```bash
git clone https://github.com/MikheevRoman/vk-echo-bot.git
```

2. Open Dockerfile and set VK API ENV variables according to your values
```dockerfile
FROM maven:3.8.5-openjdk-17 AS build  
WORKDIR /app  
COPY pom.xml .  
COPY src ./src  
RUN mvn clean package -DskipTests  
  
FROM openjdk:17-jdk-slim  
WORKDIR /app  
COPY --from=build /app/target/*.jar app.jar  
  
# Environment variables  
# VK API  
ENV VK_API_TOKEN=YOUR_TOKEN
ENV VK_API_VERSION=5.199  
ENV VK_API_SECRET=YOUR_SECRET  
ENV VK_API_CONFIRMATION=YOUR_CONFIRMATION 
# LOCALE  
ENV LC_ALL=ru_RU.UTF-8  
ENV LANG=ru_RU.UTF-8  
ENV LANGUAGE=ru_RU.UTF-8  
  
ENTRYPOINT ["java", "-jar", "app.jar"]
```

3. Build a Docker image
```bash
sudo docker build -t vk-echo-bot-app .
```

4. Run the Docker container
```bash
sudo docker run -p 8080:8080 vk-echo-bot-app
```

