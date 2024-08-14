CREATE TABLE IF NOT EXISTS SCHEDULE
(
    scheduleId int primary key comment '스케줄 ID',
    contents varchar(100) not null comment '할일',
    username varchar(100) not null comment '담당자 ID',
    password int not null comment '비밀번호',
    createdData date not null comment '작성일',
    updateDate date not null comment '수정일'
);