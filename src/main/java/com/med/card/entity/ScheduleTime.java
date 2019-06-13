package com.med.card.entity;

import lombok.Getter;

import java.time.LocalTime;

@Getter
public enum ScheduleTime {
    TEN(LocalTime.of(10, 0)), ELEVEN(LocalTime.of(11, 0)),
    TWELVE(LocalTime.of(12, 0)), THIRTEEN(LocalTime.of(13, 0)),
    FOURTEEN(LocalTime.of(14, 0)), FIFTEEN(LocalTime.of(15, 0)),
    SIXTEEN(LocalTime.of(16, 0)), SEVENTEEN(LocalTime.of(17, 0));

    private LocalTime time;

    ScheduleTime(LocalTime time) {
        this.time = time;
    }
}
