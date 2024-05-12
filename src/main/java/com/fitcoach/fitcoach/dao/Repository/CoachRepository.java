package com.fitcoach.fitcoach.dao.Repository;

import com.fitcoach.fitcoach.dao.entity.Coach;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.Collection;

public interface CoachRepository extends JpaRepository<Coach,Long> {
    Page<Coach> findByEmailContainingOrFirstNameContainingOrLastNameContaining(String email,String firstName,String lastName, PageRequest pageable);
    Page<Coach> findByEmailContaining(String kw, PageRequest pageable);

    Coach findByEmail(String email);




}
