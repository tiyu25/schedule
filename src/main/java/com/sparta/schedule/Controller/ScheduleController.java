package com.sparta.schedule.Controller;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.entity.Schedule;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ScheduleController {
    private final Map<Long, Schedule> scheduleList = new HashMap<>();

    // 1단계: 일정 작성
    @PostMapping("/schedules")
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto) {
        // RequestDto -> Entity
        Schedule schedule = new Schedule(requestDto);

        // Max ID check
        Long maxId = scheduleList.size() > 0 ? Collections.max(scheduleList.keySet()) + 1 : 1;
        schedule.setScheduleId(maxId);

        // DB 저장
        scheduleList.put(maxId, schedule);

        // Entity -> ResponseDto
        ScheduleResponseDto scheduleReponseDto = new ScheduleResponseDto(schedule);

        return scheduleReponseDto;
    }

}
