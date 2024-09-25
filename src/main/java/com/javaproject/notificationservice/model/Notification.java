package com.javaproject.notificationservice.model;


import lombok.*;

import java.util.Date;


@Getter
@EqualsAndHashCode
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Notification {

    private Long userId;
    private String type;
    private String message;
    private Date sentDate;
}
