package com.sparta.schedule.Controller;

import com.sparta.schedule.Service.ScheduleService;
import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {
    private ScheduleService scheduleService = new ScheduleService();

    // 1단계: 일정 작성
    @PostMapping
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.createSchedule(requestDto);
    }

    // 2단계: 전체 일정 조회
    @GetMapping
    public List<ScheduleResponseDto> getAllSchedules() {
        return scheduleService.getAllSchedules();
    }

    // 2단계: 학생 아이디로 일정 조회
    @GetMapping("/{scheduleId}")
    public ScheduleResponseDto getScheduleById(@PathVariable Long scheduleId) {
        return scheduleService.getScheduleById(scheduleId);
    }

    // 3단계: 필터(담당자명, 수정일) 일정 조회
    @GetMapping("/filter")
    public List<ScheduleResponseDto> searchSchedule(@RequestParam(required = false) String updatedDate,@RequestParam(required = false) String username) {
        return scheduleService.searchSchedule(updatedDate, username);
    }

    // 4단계: 일정 수정
    @PutMapping("/param")
    public Long updateSchedule(@RequestParam Long studentId, @RequestParam String password, @RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.updateSchedule(studentId, password, requestDto);
    }

    // 5단계: 일정 삭제
    @DeleteMapping("/param")
    public Long deleteSchedule(@RequestParam Long studentId, @RequestParam String password) {
        return scheduleService.deleteSchedule(studentId, password);
    }

}
