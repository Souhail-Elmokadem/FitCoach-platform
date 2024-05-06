package com.fitcoach.fitcoach.dao.Repository;

import com.fitcoach.fitcoach.dao.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {
    Page<Client> findByEmailContainingOrFirstNameContainingOrLastNameContaining(String email, String firstname, String lastname, PageRequest pageRequest);
}
