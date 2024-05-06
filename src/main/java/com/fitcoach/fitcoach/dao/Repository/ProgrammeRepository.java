package com.fitcoach.fitcoach.dao.Repository;

import com.fitcoach.fitcoach.dao.entity.Coach;
import com.fitcoach.fitcoach.dao.entity.Programme;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface ProgrammeRepository extends JpaRepository<Programme,Long> {
    Collection<Programme> findAllByCoach(Coach coach);
    Page<Programme> findByNomContaining(String nom, PageRequest pageRequest);
}
