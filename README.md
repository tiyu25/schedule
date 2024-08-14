## API 명세서
| 기능          | Method    | URL                         | request   | response    |
| ------------ | --------- | --------------------------- | --------- | ----------- |
| 일정 등록      | POST      | /api/schedules              | 요청 body  | 등록 정보      |
| 일정 조회      | GET       | /api/schedules              | 요청 param | 다건 응답 정보  |
| 일정 목록 조회  | GET       | /api/schedules/{scheduleId} | 요청 param | 단건 응답 정보  |
| 일정 수정      | PUT       | /api/schedules/param        | 요청 body  | 수정 정보      |
| 일정 삭제      | DELETE    | /api/schedules/param        | 요청 param | -            |

## ERD
<img width="487" alt="KakaoTalk_Photo_2024-08-14-18-54-47" src="https://github.com/user-attachments/assets/fea5806c-5dc9-49bb-98f1-ee614e0ff997">
