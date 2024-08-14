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

    // 전체 일정 조회
    @GetMapping("/schedules")
    public List<ScheduleResponseDto> getAllSchedules() {
        // Map To List
        List<ScheduleResponseDto> responseList = scheduleList.values().stream().
                map(ScheduleResponseDto::new).toList();

        return responseList;
    }

    // 2단계: 아이디로 일정 조회
    @GetMapping("/schedules/{scheduleId}")
    public ScheduleResponseDto getScheduleById(@PathVariable Long scheduleId) {
        //해당 일정이 DB에 존재하는지 확인
        if(scheduleList.containsKey(scheduleId)) {
            //해당 일정 가져오기
            Schedule schedule = scheduleList.get(scheduleId);
            return new ScheduleResponseDto(schedule);
        } else {
            throw new IllegalArgumentException("선택한 일정이 존재하지 않습니다.");
        }
    }


    // 3단계: 수정일, 담당자명 필터 일정 조회
    @GetMapping("/schedules/filter")
    public List<ScheduleResponseDto> searchSchedule(@RequestParam(required = false) String updatedDate,@RequestParam(required = false) String username) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // 필터링 조건에 맞게 일정 목록을 필터링
        List<ScheduleResponseDto> filteredSchedules = scheduleList.values().stream()
                .filter(schedule -> (updatedDate == null || schedule.getUpdatedDate().startsWith(updatedDate)) &&
                        (username == null || schedule.getUsername().equals(username)))
                .sorted(Comparator.comparing(Schedule::getUpdatedDate).reversed())
                .map(ScheduleResponseDto::new)
                .collect(Collectors.toList());

        return filteredSchedules;
    }


    // 4단계: 선택한 일정 수정
    @PutMapping("/schedules/param")
    public Long updateSchedule(@RequestParam Long studentId, @RequestParam String password, @RequestBody ScheduleRequestDto requestDto) {
        //해당 일정이 DB에 존재하는지 확인
        if(scheduleList.containsKey(studentId)) {
            //해당 일정 가져오기
            Schedule schedule = scheduleList.get(studentId);

            if(schedule.getPassword().equals(password)) {
                // 요청된 데이터로 일정 업데이트
                schedule.update(requestDto);

                // 일정 업데이트 (수정일 갱신)
                schedule.update(requestDto);

                //수정된 일정의 정보를 ResponseDto로 변환하여 반환
                ScheduleResponseDto responseDto = new ScheduleResponseDto(schedule);

                return responseDto.getScheduleId();
            } else {
                throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
            }
        } else {
            throw new IllegalArgumentException("선택한 일정이 존재하지 않습니다.");
        }
    }


    // 5단계: 선택 일정 삭제
    @DeleteMapping("/schedules/param")
    public Long deleteSchedule(@RequestParam Long studentId, @RequestParam String password) {
        if(scheduleList.containsKey(studentId)) {
            //해당 일정 가져오기
            Schedule schedule = scheduleList.get(studentId);

            if(schedule.getPassword().equals(password)) {
                scheduleList.remove(studentId);
                return studentId;
            } else {
                throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
            }
        } else {
            throw new IllegalArgumentException("선택한 일정이 존재하지 않습니다.");
        }
    }
}
