package com.javaproject.notificationservice.entity;

import com.javaproject.notificationservice.utils.NotificationType;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
@Table(name="notification")
public class Notification {

    @Id
    @Column(name="user_id")
    private Long userId;

    @Enumerated(STRING)
    @Column(length=50, nullable=false, unique=false)
    private NotificationType type;

    @Temporal(TIMESTAMP)
    @Column(name="sended_timestamp", nullable=false, unique = true)
    private Date sendedTimestamp;

}
