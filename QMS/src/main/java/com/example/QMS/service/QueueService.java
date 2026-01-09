package com.example.QMS.service;

import com.example.QMS.model.QueueItem;
import com.example.QMS.repository.QueueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueueService {

    private final QueueRepository repository;

    public QueueService(QueueRepository repository) {
        this.repository = repository;
    }

    // Add a customer to the queue
    public QueueItem addToQueue(QueueItem item) {
        item.setStatus("WAITING"); // default status
        return repository.save(item);
    }


    // Serve a customer by marking status as SERVED
    public QueueItem serveCustomer(Long id) {
        QueueItem item = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        item.setStatus("SERVED");
        return repository.save(item);
    }

    // ✅ FIXED METHOD
    public List<QueueItem> getQueueByServiceType(String serviceType) {
        return repository.findByServiceTypeAndStatusOrderByIdAsc(
                serviceType,
                "WAITING"
        );
    }
}

