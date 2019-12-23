package com.example.redis;

import com.example.redis.entity.Member;
import com.example.redis.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.time.LocalDateTime;

@EnableCaching
@SpringBootApplication
@RequiredArgsConstructor
public class RedisApplication implements CommandLineRunner {

    private final MemberRepository memberRepository;

    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Member a = Member.builder().name("a").refreshTime(LocalDateTime.now()).build();
        Member b = Member.builder().name("b").refreshTime(LocalDateTime.now()).build();
        Member c = Member.builder().name("c").refreshTime(LocalDateTime.now()).build();
        Member d = Member.builder().name("d").refreshTime(LocalDateTime.now()).build();

        memberRepository.save(a);
        memberRepository.save(b);
        memberRepository.save(c);
        memberRepository.save(d);
    }
}
