package com.bookShare.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookShare.Entidades.Contacts;
import com.bookShare.Repositories.ContactRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactsRepository;

    public Contacts createContacts(Contacts contacts) {
        return contactsRepository.save(contacts);
    }

    public List<Contacts> getAllContacts() {
        return contactsRepository.findAll();
    }

    public Contacts updateContacts(Long userId, Contacts contacts) {
        contacts.setuserId(userId);
        return contactsRepository.save(contacts);
    }

    public Optional<Contacts> getContactsById(Long contacts_id) {
        return contactsRepository.findById(contacts_id);
    }

    public void deleteContacts(Long contacts_id) {
        contactsRepository.deleteById(contacts_id);
    }

    public List<Contacts> getContactsByUserId(Long user_id) {
        return contactsRepository.findByUserId(user_id);
    }

}
