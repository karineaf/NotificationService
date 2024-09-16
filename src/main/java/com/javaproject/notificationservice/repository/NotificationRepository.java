package com.javaproject.notificationservice.repository;

import com.javaproject.notificationservice.entity.NotificationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends CrudRepository<NotificationEntity, Long>{

    List<NotificationEntity> findAllByUserId(Long userId);

}
