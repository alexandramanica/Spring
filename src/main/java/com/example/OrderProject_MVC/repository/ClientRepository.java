package com.example.OrderProject_MVC.repository;

import com.example.OrderProject_MVC.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    //optional se pune cand te astepti la cel mult un rezulat
    //list se pune cand te astepti la mai multe
    Optional<Client> findByEmail(String email);
}
