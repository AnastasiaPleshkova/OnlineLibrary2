package ru.pleshkova.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_book")
    private int id_book;
    @ManyToOne
    @JoinColumn(name = "id_person", referencedColumnName = "id_person")
    private Person id_person;
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(max=100, message = "Длина названия не больше 100 символов")
    @Column(name = "name")
    private String name;
    @NotEmpty(message = "Поле не может быть пустым")
//    @Pattern(regexp = "^[А-ЯЁ][а-яё]* [А-ЯЁ][а-яё]*$", message = "Введите автора в формате \"Иван Иванов\"")
    @Size(max=50, message = "Длина не больше 50 символов")
    @Column(name = "author")
    private String author;

//    @Pattern(regexp = "\\d{4}", message = "Введите год издания книги")
    @DecimalMin(value = "1000", message = "Значение не может быть меньше 1000")
    @DecimalMax(value = "3000", message = "Значение не может быть больше 3000")
    @Column(name = "year")
    private int year;
    @Override
    public String toString() {

        return "Book{" +
                "id_book=" + id_book +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                '}';
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public int getId_book() {
        return id_book;
    }
    public void setId_book(int id_book) {
        this.id_book = id_book;
    }

    public Person getId_person() {
        return id_person;
    }

    public void setId_person(Person id_person) {
        this.id_person = id_person;
    }


    public Book(String name, String author, int year) {
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public Book(){}

}
