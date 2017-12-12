[![Prog.kiev.ua](logo.png)](https://prog.kiev.ua)

## Java PRO, Homework

[**<- Java OOP**](https://github.com/YuriiSalimov/Prog.kiev.ua-JavaOOP)
|
[**Final Project ->**](https://github.com/YuriiSalimov/AlexCoffee)

### Занятие 1. Reflection & Annotations

#### [Задание № 1.1](/src/main/java/com/salimov/yurii/lesson01/task01)
Создать аннотацию, которая принимает параметры для метода. 
Написать код, который вызовет метод, помеченный этой аннотацией, 
и передаст параметры аннотации в вызываемый метод.
```java
class SomeClass {
    
    @Test(a = 2, b = 5)
    public void test(int a, int b) {
        // code
    }
}
```

#### [Задание № 1.2](/src/main/java/com/salimov/yurii/lesson01/task02)
Написать класс TextContainer, который содержит в себе строку. 
С помощью механизма аннотаций указать
1) в какой файл должен сохраниться текст; 
2) метод, который выполнит сохранение. 

Написать класс Saver, который сохранит поле класса TextContainer 
в указанный файл.
```java
@SaveTo(path = "c:\\file.txt")
class Container {
    
    String text = "...";
    
    @Saver
    public void save(/*...*/) {
        // code
    }
}
```

#### [Задание № 1.3](/src/main/java/com/salimov/yurii/lesson01/task03)
Написать код, который сериализирует и десериализирует в/из файла все 
значения полей класса, которые отмечены аннотацией @Save.

-------------------------------------------------------------------------

### Занятие 2. XML & JSON

#### [Задание № 2.1](/src/main/java/com/salimov/yurii/lesson02/task01)
Есть список поездов, представленный с виде XML. Вывести на экран информацию о тех поездах, которые
отправляются сегодня с 15:00 до 19:00.
```xml
<?xml version="1.0" encoding="UTF-8"?>
<trains>
    <train id="1">
        <from>Kyiv</from>
        <to>Donetsk</to>
        <date>19.12.2013</date>
        <departure>15:05</departure>
    </train>
    <train id="2">
        <from>Lviv</from>
        <to>Donetsk</to>
        <date>19.12.2013</date>
        <departure>19:05</departure>
    </train>
</trains>
```
Написать код для добавления новых поездов в существующий XML.

#### [Задание № 2.2](/src/main/java/com/salimov/yurii/lesson02/task02)
Распарсить следующую структуру данных:
```json
{
    "name": "...",
    "surname": "...",
    "phones": ["044-256-78-90", "066-123-45-67", "..."],
    "sites": ["http://site1.com", "http://site2.com", "..."],
    "address": {
        "country": "...",
        "city": "...",
        "street": "..."
    }
}
```

#### [Задание № 2.3](/src/main/java/com/salimov/yurii/lesson02/task03)
Написать парсер для Yahoo Finance в режиме XML (format = xml).

-------------------------------------------------------------------------

### Занятие 4. Java EE

#### [Задание № 4.2](/src/main/java/com/salimov/yurii/lesson04/task02)
Создать проект «Анкета». Сделать возможность ввода пользователем его имени, 
фамилии, возраста и ответов на 2-3 вопроса. Данные должны отправляться на 
сервер, который в ответ должен вернуть статистику по ответам в виде
HTML документа.

-------------------------------------------------------------------------

### Занятие 6. БД и SQL

#### [Задание № 6.2](/src/main/java/com/salimov/yurii/lesson06/task02)
##### [Задание № 6.2. Hibernate](/src/main/java/com/salimov/yurii/lesson06/task02H)
Спроектировать базу «Квартиры». Каждая запись в базе содержит данные о 
квартире (район, адрес, площадь, кол. комнат, цена). Сделать возможность 
выборки квартир из списка по параметрам.

#### [Задание № 6.3](/src/main/java/com/salimov/yurii/lesson06/task03)
Создать проект «База данных заказов». Создать таблицы «Товары» , «Клиенты» 
и «Заказы». Написать код для добавления новых клиентов, товаров и оформления 
заказов.

-------------------------------------------------------------------------

### Занятие 7. JPA / Hibernate


#### [Задание № 7.1](/src/main/java/com/salimov/yurii/lesson07/task01)
Создать таблицу «Меню в ресторане». Колонки: название блюда, его стоимость, 
вес, наличие скидки. Написать код для добавления записей в таблицу и их выборки 
по критериям «стоимость от-до», «только со скидкой», выбрать набор блюд так, 
чтобы их суммарный вес был не более 1 КГ.

#### [Задание № 7.2 *](/src/main/java/com/salimov/yurii/lesson07/task02)
Создать базу данных «Банк» с таблицами «Пользователи», «Транзакции», «Счета» 
и «Курсы валют». Счет бывает 3-х видов: USD, EUR, UAH. Написать запросы для 
пополнения счета в нужной валюте, перевода средств с одного счета на другой, 
конвертации валюты по курсу в рамках счетов одного пользователя. Написать
запрос для получения суммарных средств на счету одного пользователя в UAH 
(расчет по курсу).

-------------------------------------------------------------------------

### Занятие 8. Spring MVC

#### Задание № 8.1
<!--#### [Задание № 8.1](/src/main/java/com/salimov/yurii/lesson08/task01)-->
Сделать кнопку при нажатии на которую выведется список всех загруженных 
фотографий с их id.

#### Задание № 8.2
<!--#### [Задание № 8.2](/src/main/java/com/salimov/yurii/lesson08/task02)-->
Сделать возможность выбрать из списка часть фото и удалить одним нажатием 
на кнопку.

#### [Задание № 8.3](/src/main/java/com/salimov/yurii/lesson08/task03)
Решить задачу про архиватор с помощью Spring MVC.

-------------------------------------------------------------------------

[**<- Java OOP**](https://github.com/YuriiSalimov/Prog.kiev.ua-JavaOOP)
|
[**Final Project ->**](https://github.com/YuriiSalimov/AlexCoffee)

[_**Yurii Salimov**_](https://www.linkedin.com/in/yurii-salimov)
([yuriy.alex.salimov@gmail.com](mailto:yuriy.alex.salimov@gmail.com))
