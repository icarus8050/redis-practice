package com.example.redis.controller;

import com.example.redis.entity.MemberDto;
import com.example.redis.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/{idx}")
    @Cacheable(value = "member", key = "#idx")
    public MemberDto getMember(@PathVariable Long idx) {
        return memberService.findMemberById(idx);
    }
}
