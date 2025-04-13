<h1 >Проект автоматизации тестирования API для <a href="https://www.avito.ru/">Avito</a></h1>

![](/media/avito.png)

## :page_with_curl: Содержание

* <a href="#tools">Технологии и инструменты</a>

* <a href="#cases">Реализованные проверки</a>

* <a href="#allure">Allure Report отчеты</a>

* <a href="#project">Описание структуры проекта</a>

* <a href="#start">Гайд по запуску автотестов</a>

<a id="tools"></a>
## Технологии и инструменты

| Java                                                                                                   | IntelliJ Idea                                                                                                               | Allure                                                                                                                    | GitHub                                                                                                    | JUnit 5                                                                                                           | Gradle                                                                                                   | REST Assured                                                                                |
|:-------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------|
| <a href="https://www.java.com/"><img src="media/Java.svg" width="50" height="50"  alt="Java"/></a>     | <a id ="tech" href="https://www.jetbrains.com/idea/"><img src="media/Idea.svg" width="50" height="50"  alt="IDEA"/></a> | <a href="https://github.com/allure-framework"><img src="media/Allure.svg" width="50" height="50"  alt="Allure"/></a> | <a href="https://github.com/"><img src="media/GitHub.svg" width="50" height="50"  alt="Github"/></a> | <a href="https://junit.org/junit5/"><img src="media/Junit5.svg" width="50" height="50"  alt="JUnit 5"/></a> | <a href="https://gradle.org/"><img src="media/Gradle.svg" width="50" height="50"  alt="Gradle"/></a> | <a href="https://rest-assured.io/"><img src="media/RestAssured.svg" width="50" height="50"  alt="RestAssured"/></a> |
- В данном проекте автотесты написаны на языке <code>Java</code> с использованием библиотеки <code>REST Assured</code>.
- В качестве сборщика был использован - <code>Gradle</code>.
- Использованы фреймворки <code>JUnit 5</code>.
- Использование Lombok для моделей в API тестах.
- Использована спецификация для API-тестов.

<a id="cases"></a>
## :heavy_check_mark: Реализованные проверки

-  Создание объявления продавцом
-  Получение объявления по его идентификатору
-  Получение всех объявлений по идентификатору продавца
-  Получение статистики по айтем id
-  Получение статистики по айтем id для версии 2
-  Удаление объявления по его идентификатору



<a id="allure"></a>
## <img src="media/Allure.svg" width="25" height="25"/></a> Allure Report отчеты

### Основное окно

<p align="center">
<img title="Allure Dashboard" src="/media/AlllureDashBoard.jpg">
</p>

### Отчеты по тестам

<p align="center">

> В отчете по тестам присутствует развернутая информация по запросам и ответам.

<img title="Allure Tests" src="/media/AllureTests.jpg">
</p>

<a id="project"></a>
## Описание структуры проекта

- **avito_qa/**
    - **media/** - Папка для медиафайлов (скрины для багов, иконки для README.md)
    - **src/**
        - **main/**
            - **java/**
                - **checkers/** - Пакет для проверок
                    - **Checkers.java/** - Класс для проверок
                - **constants/** - Пакет для констант
                    - **Endpoints.java** - Класс с эндпоинтами API
                    - **ErrorConstants.java** - Класс с константами ошибок
                - **model/** - Пакет для моделей данных
                    - **CreateItemRequest.java** - Модель запроса на создание объявления
                    - **ErrorResponse.java** - Модель ответа с ошибкой
                    - **ItemInfo.java** - Модель информации об объявлении
                    - **StatisticResponse.java** - Модель ответа со статистикой объявления
                - **requests/** - Пакет для запросов
                    - **Requests.java** - Класс для работы с запросами
                - **specs/** - Пакет для спецификаций
                    - **Specification.java** - Класс для работы со спецификациями
            - **resources/**
        - **test/**
            - **java/**
                - **tests/**
                    - **BaseTest.java** - Базовый класс для тестов
                    - **CreateItemTest.java** - Тесты для создания объявления
                    - **CreateItemTest.java** - Тесты для удаления объявления
                    - **GetItemsBySellerIdTest.java** - Тесты для получения объявлений по Id продавца
                    - **GetItemTest.java** - Тесты для получения объявлений по айтем Id
                    - **GetStatisticTest.java** - Тесты для получения статистики объявлений
                    - **GetStatisticV2Test.java** - Тесты для получения статистики объявлений (версия 2)
            - **resources/**
- **BUGS.md** - Файл с описанием баг-репортов для задания 2.1
- **TESTCASES.md**  - Файл с описанием тест-кейсов для задания 2.1
  - **task1.md**  - Файл с описанием баг-репортов для задания 1


<a id="start"></a>
## Гайд по запуску автотестов

1. Установка Java Development Kit (JDK)

Убедитесь, что у вас установлена JDK версии 8 или выше. Если нет, скачайте и установите её с <a href="https://www.oracle.com/java/">официального сайта Oracle.</a>

Проверьте установку:

java -version

2. Установка Gradle

Gradle используется для сборки и запуска тестов. Если у вас его нет, установите его <a href="https://gradle.org/install/">официального сайта.</a>

Распакуйте архив и добавьте путь к bin директории Gradle в переменную окружения PATH.

Проверьте установку:

gradle -v

3. Клонирование репозитория

git clone https://github.com/Ekaterina-Polygalova/avito_qa

4. Настройка окружения

Убедитесь, что все зависимости указаны в build.gradle. Если это так, Gradle автоматически загрузит их при первой сборке.

5. Запуск тестов

Для запуска всех тестов выполните:

gradle test

Если вы хотите запустить конкретный тестовый класс, например, CreateItemTest, используйте:

gradle test --tests "tests.CreateItemTest"

6. Просмотр результатов

После выполнения тестов, сгенерируйте отчет Allure с помощью следующей команды:

gradle allureServe