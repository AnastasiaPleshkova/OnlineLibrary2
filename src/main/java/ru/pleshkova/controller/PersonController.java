package ru.pleshkova.controller;

import jakarta.validation.Valid;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.pleshkova.models.Book;
import ru.pleshkova.models.Person;
import ru.pleshkova.services.PeopleService;
import ru.pleshkova.util.PersonValidator;

import java.util.List;

@Controller
@RequestMapping("/people")
public class PersonController {
    private final PersonValidator personValidator;
    private final PeopleService peopleService;

    @Autowired
    public PersonController(PersonValidator personValidator, PeopleService peopleService) {
        this.personValidator = personValidator;
        this.peopleService = peopleService;
    }
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", peopleService.findAll());
        return "person/index";
    }

    @PostMapping()
    public String newPerson(@ModelAttribute("person") @Valid Person person, BindingResult result) {
        personValidator.validate(person, result);
        if (result.hasErrors())
            return "person/new";
        peopleService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/new")
    public String formNewPerson(@ModelAttribute("person") Person person) {
        return "person/new";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", peopleService.findById(id));
        model.addAttribute("books", peopleService.getBooksInService(id).getBooks());
        return "/person/show";
    }

    @GetMapping("/{id}/edit")
    public String formEdit(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", peopleService.findById(id));
        return "/person/edit";
    }

    @PatchMapping("/{id}")
    public String edit(@PathVariable("id") int id, @ModelAttribute("person") @Valid Person person, BindingResult result) {
        personValidator.validate(person, result);
        if (result.hasErrors())
            return "person/edit";
        peopleService.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id, @ModelAttribute("person") Person person, BindingResult result) {
        if (result.hasErrors())
            return "person/show";
        peopleService.delete(id);
        return "redirect:/people";
    }

}
