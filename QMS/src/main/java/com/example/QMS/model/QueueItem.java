package com.example.QMS.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;


@Entity
@Data
@Table(name = "queue_item")
@AllArgsConstructor


public class QueueItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private String serviceType;
    private String status;     // WAITING / SERVED
     // OPD / Cardiology / Emergency

    public QueueItem() {}

    public QueueItem(String customerName, String serviceType, String status, String department) {
        this.customerName = customerName;
        this.serviceType = serviceType;
        this.status = status;

    }

    // Getters & Setters
    public Long getId() { return id; }
    public String getCustomerName() { return customerName; }
    public String getServiceType() { return serviceType; }
    public String getStatus() { return status; }


    public void setId(Long id) { this.id = id; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public void setServiceType(String serviceType) { this.serviceType = serviceType; }
    public void setStatus(String status) { this.status = status; }

}

