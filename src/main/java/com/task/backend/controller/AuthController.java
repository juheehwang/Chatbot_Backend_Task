package com.task.backend.controller;

import com.task.backend.dto.request.MemberRequestDto;
import com.task.backend.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final MemberService memberService;

    @GetMapping("/register")
    public ResponseEntity<String> register(@RequestBody MemberRequestDto memberRequestDto) {

        return ResponseEntity.ok()
            .body(memberService.register(memberRequestDto));
    }

    @GetMapping("/login")
    public ResponseEntity<String> logIn(@RequestBody MemberRequestDto memberRequestDto){
        return ResponseEntity.ok(memberService.login(memberRequestDto));
    }
}
