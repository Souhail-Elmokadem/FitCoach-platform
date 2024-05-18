package com.fitcoach.fitcoach.dao.Repository;

import com.fitcoach.fitcoach.dao.entity.Chat;
import com.fitcoach.fitcoach.dao.entity.Client;
import com.fitcoach.fitcoach.dao.entity.Coach;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat,Long>{
    Chat findByCoachIdAndClientId(Long coachid,Long clientid);
};