package com.example.redis.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class Member implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
