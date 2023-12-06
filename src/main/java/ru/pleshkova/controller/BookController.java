package ru.pleshkova.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.pleshkova.models.Book;
import ru.pleshkova.models.Person;
import ru.pleshkova.services.BooksService;
import ru.pleshkova.services.PeopleService;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BooksService booksService;
    private final PeopleService peopleService;
    @Autowired
    public BookController(BooksService booksService, PeopleService peopleService) {
        this.booksService = booksService;
        this.peopleService = peopleService;
    }

    @GetMapping()
    public String getAllBooks(Model model) {
        model.addAttribute("books", booksService.findAll());
        return "book/index";
    }

    @PostMapping()
    public String addNewBook(@ModelAttribute("book") @Valid Book book, BindingResult result) {
        if (result.hasErrors()) {
            return "book/new";
        }
        booksService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/new")
    public String createNewBookForm(@ModelAttribute("book") Book book) {
        return "book/new";
    }

    @GetMapping("/{id}/edit")
    public String editBookForm(@PathVariable("id") int id, Model model) {
        model.addAttribute("book",booksService.findById(id));
        return "book/edit";
    }

    @GetMapping("/{id}")
    public String getOneBook(Model model, @PathVariable("id") int id) {
        Book book = booksService.findById(id);
        model.addAttribute("book", book);
        model.addAttribute("person", book.getId_person()); // personDAO.get(booksService.findById(id).getId_person().getId_person()));
        model.addAttribute("people", peopleService.findAll());
        return "book/show";
    }

    @PatchMapping("/{id}")
    public String editBook(@ModelAttribute("book") @Valid Book book, BindingResult result, @PathVariable("id") int id) {
        if (result.hasErrors()) {
            return "book/edit";
        }
        booksService.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        booksService.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/release")
    public String releaseBookTaker(@PathVariable("id") int id) {
        Book book = booksService.findById(id);
        book.setId_person(null);
        booksService.update(id, book);
//        bookDAO.release(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/take")
    public String setBookTaker(@PathVariable("id") int id_book, @ModelAttribute("person") Person person) {
//        bookDAO.take(id_book, person.getId_person());
        Person p = peopleService.findById(person.getId_person());
        Book book = booksService.findById(id_book);
        book.setId_person(p);
        booksService.update(id_book, book);
        return "redirect:/books";
    }
}
