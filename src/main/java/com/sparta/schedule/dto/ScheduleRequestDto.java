package com.sparta.schedule.dto;

import lombok.Getter;

//클라이언트에 데이터를 반환할 때 사용할 클래스
@Getter
public class ScheduleRequestDto {
    private String contents; //할일
    private String username; //담당자명
    private String password; //패스워드
    private String createdDate; //작성일
    private String updatedDate; //작성일
}
