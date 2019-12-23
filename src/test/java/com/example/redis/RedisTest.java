package com.example.redis;

import com.example.redis.entity.Member;
import com.example.redis.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class RedisTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @AfterEach
    public void reset() {
        memberRepository.deleteAll();
    }

    @Test
    public void registrationAndQueryTest() {
        //given
        Long idx = 1L;
        LocalDateTime refreshTime = LocalDateTime.of(2019, 12, 4, 0, 0);
        String name = "icarus";

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
    public void modifyTest() {
        //given
        Long idx = 1L;
        LocalDateTime refreshTime = LocalDateTime.of(2019, 11, 4, 0, 0);
        String name = "icarus";

        memberRepository.save(Member.builder()
                .idx(idx)
                .name(name)
                .refreshTime(refreshTime)
                .build());

        //when
        Member savedMember = memberRepository.findById(idx).get();
        savedMember.refresh("leafy", LocalDateTime.of(2019, 11, 5, 0, 0));
        memberRepository.save(savedMember);


        //then
        log.info(savedMember.getIdx().toString());
        log.info(savedMember.getName());
        log.info(savedMember.getRefreshTime().toString());
    }

    @Test
    public void stringRedisTest() {
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        values.set("name", "icarus");
        values.set("age", "26");
    }
}
