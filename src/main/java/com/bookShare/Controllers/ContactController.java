package com.bookShare.Controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.bookShare.Entidades.Contacts;
import com.bookShare.Services.ContactService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/bookShare/contacts")
public class ContactController {

    @Autowired
    private ContactService contactsService;

    @PostMapping("/add")
    public Contacts createContacts(@RequestBody Contacts contacts) {
        if (contacts.getuserId() == null) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID de usuario no puede ser nulo");
    }
        return contactsService.createContacts(contacts);
    }

    @GetMapping("/list")
    public List<Contacts> getAllContacts() {
        return contactsService.getAllContacts();
    }

    @GetMapping("/{id}")
    public Optional<Contacts> getContactsById(@PathVariable Long id) {
        return contactsService.getContactsById(id);
    }

    @PostMapping("/update/{id}")
    public Contacts updateContacts(@PathVariable Long id, @RequestBody Contacts contacts) {
        return contactsService.updateContacts(id, contacts);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteContacts(@PathVariable Long id) {
        System.out.println(id);
        contactsService.deleteContacts(id);
        return "Eliminado correctamente";
    }

    @GetMapping("/user/{userId}")
    public List<Contacts> getBooksByUserId(@PathVariable Long userId) {
        return contactsService.getContactsByUserId(userId);
    }

}
