package com.javaproject.notificationservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Date;

@Embeddable
public class NotificationKeyEntity implements Serializable {

    @Id
    @Column(name="user_id")
    private Long userId;

    @Column(name="sent_date", nullable=false, unique = true)
    private Date sentDate;

    public NotificationKeyEntity() {}

    public Long getUserId() {
        return userId;
    }

    public Date getSentDate() {
        return sentDate;
    }
}
