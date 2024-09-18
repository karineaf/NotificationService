package com.javaproject.notificationservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Embeddable
public class NotificationKeyEntity implements Serializable {

    @Column(name="user_id", nullable=false)
    private Long userId;

    @Column(name="sent_date", nullable=false)
    private Date sentDate;

    public NotificationKeyEntity() {}


    public NotificationKeyEntity(Long userId, Date sentDate) {
        this.userId = userId;
        this.sentDate = sentDate;
    }

    public Long getUserId() {
        return userId;
    }

    public Date getSentDate() {
        return sentDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationKeyEntity that = (NotificationKeyEntity) o;
        return Objects.equals(userId, that.userId) && Objects.equals(sentDate, that.sentDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, sentDate);
    }
}
