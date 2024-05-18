package com.fitcoach.fitcoach.dao.Repository;

import com.fitcoach.fitcoach.dao.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Long> {
}
