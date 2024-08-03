package com.bookShare.Servicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookShare.Entidades.Request;
import com.bookShare.Repositorios.RequestRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    public Request createRequest(Request request) {
        return requestRepository.save(request);
    }

    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    public Request updateRequest(Long request_id, Request request) {
        request.setRequest_id(request_id);
        return requestRepository.save(request);
    }

    public Optional<Request> getRequestById(Long request_id) {
        return requestRepository.findById(request_id);
    }
    
    public void deleteRequest(Long request_id) {
        requestRepository.deleteById(request_id);
    }

}
