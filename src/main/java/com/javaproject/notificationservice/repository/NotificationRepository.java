package com.javaproject.notificationservice.repository;

import com.javaproject.notificationservice.entity.NotificationEntity;
import com.javaproject.notificationservice.entity.NotificationKeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, NotificationKeyEntity> {

    List<NotificationEntity> findAllByIdUserIdAndType(Long userId, String type);

    List<NotificationEntity> findAllByIdUserId(Long userId);
}
