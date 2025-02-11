package com.task.backend.service;

import com.task.backend.dto.request.MemberRequestDto;
import com.task.backend.entity.Member;
import com.task.backend.repository.MemberRepository;
import com.task.backend.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {


    private final BCryptPasswordEncoder encoder;
    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;

    /**
     * 맴버 등록
     * @param memberRequestDto
     * @return
     */
    public String register(MemberRequestDto memberRequestDto) {
        if (memberRepository.findByEmail(memberRequestDto.email()).isPresent()) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }
        var member = memberRequestDto.ofEntity();
        member.setPassword(encoder.encode(member.getPassword()));
        return memberRepository.save(member).getEmail();

    }

    /**
     * 로그인
     * @param memberRequestDto
     * @return
     */
    public String login(MemberRequestDto memberRequestDto) {
        Member user = memberRepository.findByEmail(memberRequestDto.email())
            .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 이메일 또는 비밀번호입니다."));

        if (!encoder.matches(memberRequestDto.password(), user.getPassword())) {
            throw new IllegalArgumentException("유효하지 않은 이메일 또는 비밀번호입니다.");
        }

        return jwtUtil.generateToken(memberRequestDto.email());
    }

}
