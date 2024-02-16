# Версии
    Java 17
    Используется система сборки Maven. 
    Версию и версии зависимостей можно посмотреть в файле pom.xml.

# Сборка проекта
    mvn compile

# Запуск
    mvn exec:java -Dexec.mainClass=nsu.maxwell.Main -Dexec.args="./src/main/resources/old.json ./src/main/resources/new.json" 

# Запуск тестов
    mvn test