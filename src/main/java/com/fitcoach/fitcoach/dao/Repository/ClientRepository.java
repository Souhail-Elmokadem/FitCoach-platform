package com.fitcoach.fitcoach.dao.Repository;

import com.fitcoach.fitcoach.dao.entity.Client;
import com.fitcoach.fitcoach.dao.entity.Coach;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface ClientRepository extends JpaRepository<Client,Long> {
    Page<Client> findByEmailContainingOrFirstNameContainingOrLastNameContaining(String email, String firstname, String lastname, PageRequest pageRequest);
    Page<Client> findByEmailContainingOrFirstNameContainingOrLastNameContainingAndCoach(String email, String firstname, String lastname, PageRequest pageRequest, Coach coach);

    Client findByEmail(String email);
    Collection<Client> findByCoachId(int id);

}
