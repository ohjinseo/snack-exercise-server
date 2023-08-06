package com.soma.snackexercise.dto.group.request;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GroupCreateRequest {
    private String name; // 그룹 이름

    private String emozi; // 그룹 대표 이모지

    private String color; // 그룹 대표 색상

    private String description; // 그룹 소개 글

    private Integer maxMemberNum; // 최대 참여 인원 수

    private Integer goalRelayNum; // 목표 릴레이 횟수

    @JsonSerialize(using = LocalTimeSerializer.class) // 객체 -> JSON
    @JsonDeserialize(using = LocalTimeDeserializer.class) // JSON -> 객체
    private LocalTime startTime; // 시작 시간

    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    private LocalTime endTime; // 종료 시간

    private String penalty; // 벌칙

    private Integer checkIntervalTime; // 미션 수행 체크 시간 간격

    private Integer checkMaxNum; // 일별 미션 수행 체크 최대 횟수

    private Integer existDays;
}
