package com.bookShare.Controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.bookShare.Entidades.Request;
import com.bookShare.Services.RequestService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/bookShare/requests")
public class RequestController {

    @Autowired
    private RequestService requestService;

    @PostMapping("/add")
    public Request createRequest(@RequestBody Request request) {
        return requestService.createRequest(request);
    }

    @GetMapping("/list")
    public List<Request> getAllRequests() {
        return requestService.getAllRequests();
    }

    @GetMapping("/{id}")
    public Optional<Request> getRequestById(@PathVariable Long id) {
        return requestService.getRequestById(id);
    }

    @PostMapping("/update/{id}")
    public Request updateRequest(@PathVariable Long id, @RequestBody Request request) {
        return requestService.updateRequest(id, request);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteRequest(@PathVariable Long id) {
        System.out.println(id);
        requestService.deleteRequest(id);
        return "Eliminado correctamente";
    }

}
