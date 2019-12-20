package com.example.redis;

import com.example.redis.entity.Member;
import com.example.redis.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class RedisTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void registrationAndQueryTest() {
        //given
        Long idx = 2L;
        LocalDateTime refreshTime = LocalDateTime.of(2019, 12, 4, 0, 0);
        String name = "leafy";

        Member member = Member.builder()
                .idx(idx)
                .name(name)
                .refreshTime(refreshTime)
                .build();

        //when
        memberRepository.save(member);

        //then
        Member savedMember = memberRepository.findById(1L).get();
        assertEquals(savedMember.getName(), "icarus");
        assertEquals(savedMember.getRefreshTime(), refreshTime);
        assertEquals(savedMember.getIdx(), idx);
    }

    @Test
    public void findTest() {
        //given
        Long idx = 1L;

        //when
        Member foundMember = memberRepository.findById(idx).get();

        //then
        log.info(foundMember.getIdx().toString());
        log.info(foundMember.getName());
        log.info(foundMember.getRefreshTime().toString());
    }
}
