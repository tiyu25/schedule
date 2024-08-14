package com.sparta.schedule.entity;

import com.sparta.schedule.dto.ScheduleRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
public class Schedule {
    private Long scheduleId; //스케줄 아이디
    private String contents; //할일
    private String username; //담당자명
    private String password; //패스워드
    private String createdDate; //작성일
    private String updatedDate; //수정일

    public Schedule(ScheduleRequestDto requestDto) {
        this.contents = requestDto.getContents();
        this.username = requestDto.getUsername();
        this.password = requestDto.getPassword();

        //작성일, 수정일 yyyy-MM-dd HH:mm:ss 형식으로 변경
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        this.createdDate = now.format(formatter);
        this.updatedDate = now.format(formatter);
    }

    public void update(ScheduleRequestDto requestDto) {
        this.contents = requestDto.getContents();
        this.username = requestDto.getUsername();

        // 수정일을 현재 시간으로 갱신
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        this.updatedDate = now.format(formatter);
    }
}
