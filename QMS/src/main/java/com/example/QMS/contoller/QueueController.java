package com.example.QMS.contoller;

import com.example.QMS.model.QueueItem;
import com.example.QMS.service.QueueService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/queue")
@CrossOrigin(
        origins = "http://localhost:3000",
        allowCredentials = "true"
)

public class QueueController {

    private final QueueService service;

    // Constructor injection
    public QueueController(QueueService service) {
        this.service = service;
    }

    // Add customer
    @PostMapping("/post")
    public QueueItem addCustomer(@RequestBody QueueItem item) {
        return service.addToQueue(item);
    }

    // View queue by department
    @GetMapping("/{serviceType}")
    public List<QueueItem> getQueueByServiceType(@PathVariable String serviceType) {
        return service.getQueueByServiceType(serviceType);
    }


    // Serve customer
    @PutMapping("/serve/{id}")
    public QueueItem serveCustomer(@PathVariable Long id) {
        return service.serveCustomer(id);
    }
}

