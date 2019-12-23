package com.example.redis.service;

import com.example.redis.entity.Member;
import com.example.redis.entity.MemberDto;
import com.example.redis.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public MemberDto findMemberById(Long idx) {
        Member member = memberRepository.findById(idx).orElseThrow(EntityNotFoundException::new);
        log.info("Find Member by idx...{}", idx);
        return new MemberDto(member.getName());
    }
}
