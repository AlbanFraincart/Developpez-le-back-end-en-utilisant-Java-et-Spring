package com.backend.projet3OC.repository;

import com.backend.projet3OC.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findByRentalId(Long rentalId);

//    List<Message> findByUserId(Long userId);

}
