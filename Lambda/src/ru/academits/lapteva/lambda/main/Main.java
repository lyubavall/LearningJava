package ru.academits.lapteva.lambda.main;

import ru.academits.lapteva.lambda.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Person person1 = new Person("Anna", 19);
        Person person2 = new Person("Ivan", 22);
        Person person3 = new Person("Anastasia", 20);
        Person person4 = new Person("Vladimir", 17);
        Person person5 = new Person("Anna", 15);
        Person person6 = new Person("Catherine", 45);

        ArrayList<Person> peopleList = new ArrayList<>();
        peopleList.add(person1);
        peopleList.add(person2);
        peopleList.add(person3);
        peopleList.add(person4);
        peopleList.add(person5);
        peopleList.add(person6);

        List<String> uniqueNames = peopleList.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors .toList());
        System.out.println("Список уникальных имен: " + uniqueNames);

        String names = uniqueNames.stream()
                .collect(Collectors.joining(", ", "Имена: ", "."));
        System.out.println(names);

        List<Person> minors = peopleList.stream()
                .filter(person -> person.getAge() < 18)
                .collect(Collectors .toList());
        System.out.println("Список людей младше 18 лет: " + minors);

        OptionalDouble averageAge = minors.stream()
                .mapToDouble(Person::getAge)
                .average();
        if (averageAge.isPresent()) {
            System.out.println("Средний возраст людей младше 18 лет: " + averageAge.getAsDouble());
        } else {
            System.out.println("Нет людей младше 18 лет");
        }

        Map<String, Double> peopleMap = peopleList.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.averagingInt(Person::getAge)));
        System.out.println("Средний возраст по именам" + peopleMap);

        List<Person> peopleAged20To40Years = peopleList.stream()
                .filter(person -> person.getAge() >= 20 && person.getAge() <= 45)
                .sorted((p1, p2) -> p2.getAge() - p1.getAge())
                .collect(Collectors .toList());
        System.out.println("Люди от 20 до 45: " + peopleAged20To40Years);
    }
}
