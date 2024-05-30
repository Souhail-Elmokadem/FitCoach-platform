package com.fitcoach.fitcoach.dao.Repository;

import com.fitcoach.fitcoach.dao.entity.Chat;
import com.fitcoach.fitcoach.dao.entity.Client;
import com.fitcoach.fitcoach.dao.entity.Coach;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat,Long>{
    Chat findByCoachIdAndClientId(Long coachId, Long clientId);
    Void deleteAllByClientAndCoach(Client client,Coach coach);

    List<Chat> findAllByClientAndCoach(Client client, Coach coach);
};