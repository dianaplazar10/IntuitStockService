package com.intuit.stockservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intuit.stockservice.model.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long>{
}
