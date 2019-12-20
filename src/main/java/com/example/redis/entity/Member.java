package com.example.redis.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@RedisHash("member")
public class Member implements Serializable {

    @Id
    private Long idx;
    private String name;
    private LocalDateTime refreshTime;

    @Builder
    public Member(Long idx, String name, LocalDateTime refreshTime) {
        this.idx = idx;
        this.name = name;
        this.refreshTime = refreshTime;
    }

    public void refresh(String name, LocalDateTime refreshTime) {
        if (refreshTime.isAfter(this.refreshTime)) {
            this.name = name;
            this.refreshTime = refreshTime;
        }
    }
}
