package com.fitcoach.fitcoach.dao.Repository;

import com.fitcoach.fitcoach.dao.entity.Client;
import com.fitcoach.fitcoach.dao.entity.Coach;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface ClientRepository extends JpaRepository<Client,Long> {
    Page<Client> findByEmailContainingOrFirstNameContainingOrLastNameContaining(String email, String firstname, String lastname, PageRequest pageRequest);
    @Query("SELECT c FROM Client c WHERE c.coach = :coach AND " +
            "(c.email LIKE %:kw% OR c.firstName LIKE %:kw% OR c.lastName LIKE %:kw%)")
    Page<Client> findByCoachAndKeyword(@Param("coach") Coach coach, @Param("kw") String kw, Pageable pageable);

    Client findByEmail(String email);
    Collection<Client> findByCoachId(int id);

}
