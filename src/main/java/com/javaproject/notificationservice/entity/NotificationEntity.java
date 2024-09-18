package com.javaproject.notificationservice.entity;

import com.javaproject.notificationservice.utils.NotificationType;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import static jakarta.persistence.EnumType.STRING;


@Entity
@Table(name="notification")
public class NotificationEntity implements Serializable {

    @EmbeddedId
    private NotificationKeyEntity id;

    @Column(length=50, nullable=false)
    private String type;

    public NotificationEntity() {}

    public NotificationEntity(NotificationKeyEntity id, String type) {
        this.id = id;
        this.type = type;
    }

    public NotificationKeyEntity getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationEntity that = (NotificationEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type);
    }
}
