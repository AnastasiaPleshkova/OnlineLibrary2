package ru.pleshkova.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.List;

@Entity
@Table
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_person;
    @Pattern(regexp = "^[А-ЯЁ][а-яё]* [А-ЯЁ][а-яё]* [А-ЯЁ][а-яё]*$", message = "Введите ФИО в формате \"Иванов Иван Иванович\"")
    private String fio;

    @DecimalMax(value = "2010", message = "Возраст не может быть меньше 13")
    @DecimalMin(value = "1904", message = "Возраст не может быть больше 120")
    private int year;

    @OneToMany(mappedBy = "id_person")
    private List<Book> books;

    public int getId_person() {
        return id_person;
    }

    public void setId_person(int id_person) {
        this.id_person = id_person;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Person(){}

    public Person(int id_person, String fio, int year) {
        this.id_person = id_person;
        this.fio = fio;
        this.year = year;
    }

}
