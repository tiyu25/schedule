package com.sparta.schedule.dto;

import com.sparta.schedule.entity.Schedule;
import lombok.Getter;

//클라이언트에 데이터를 반환할 때 사용할 클래스
@Getter
public class ScheduleResponseDto {
    private Long scheduleId; //스케줄 아이디
    private String contents; //할일
    private String username; //담당자명
    private String password; //패스워드
    private String createdDate; //작성일
    private String updatedDate; //수정일

    public ScheduleResponseDto(Schedule schedule) {
        this.scheduleId = schedule.getScheduleId();
        this.contents = schedule.getContents();
        this.username = schedule.getUsername();
        this.password = schedule.getPassword();
        this.createdDate = schedule.getCreatedDate();
        this.updatedDate = schedule.getUpdatedDate();
    }
}
