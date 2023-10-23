package com.example.application.services;

import com.example.application.data.SampleAddress;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import dev.hilla.Endpoint;
import dev.hilla.exception.EndpointException;
import java.util.Optional;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Endpoint
@AnonymousAllowed
public class SampleAddressEndpoint {

    private final SampleAddressService service;

    public SampleAddressEndpoint(SampleAddressService service) {
        this.service = service;
    }

    public Page<SampleAddress> list(Pageable page) {
        return service.list(page);
    }

    public Optional<SampleAddress> get(Long id) {
        return service.get(id);
    }

    public SampleAddress update(SampleAddress entity) {
        try {
            return service.update(entity);
        } catch (OptimisticLockingFailureException e) {
            throw new EndpointException("Somebody else has updated the data while you were making changes.");
        }
    }

    public void delete(Long id) {
        service.delete(id);
    }

    public int count() {
        return service.count();
    }

}
