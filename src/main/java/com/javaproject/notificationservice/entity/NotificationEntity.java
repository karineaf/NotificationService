package com.javaproject.notificationservice.entity;

import com.javaproject.notificationservice.utils.NotificationType;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

import static jakarta.persistence.EnumType.STRING;


@Entity
@Table(name="notification")
public class NotificationEntity implements Serializable {

    @EmbeddedId
    private NotificationKeyEntity id;

    @Enumerated(STRING)
    @Column(length=50, nullable=false)
    private NotificationType type;

    public NotificationEntity() {}

    public NotificationEntity(NotificationKeyEntity id, NotificationType type) {
        this.id = id;
        this.type = type;
    }

    public NotificationKeyEntity getId() {
        return id;
    }

    public NotificationType getType() {
        return type;
    }
}
