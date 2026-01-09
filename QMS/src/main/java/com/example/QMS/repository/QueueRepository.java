package com.example.QMS.repository;

import com.example.QMS.model.QueueItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QueueRepository extends JpaRepository<QueueItem, Long>{

    List<QueueItem> findByServiceTypeAndStatusOrderByIdAsc(
            String serviceType,
            String status
    );
}
