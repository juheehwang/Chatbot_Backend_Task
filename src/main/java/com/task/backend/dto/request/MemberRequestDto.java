package com.task.backend.dto.request;

import com.task.backend.entity.Member;
import com.task.backend.entity.Member.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record MemberRequestDto(
    @Email @NotBlank String email,
    @NotBlank String password,
    @NotBlank String name
) {
    // 맴버용
    public Member ofEntity() {
        return new Member(name, password, email, Role.MEMBER);
    }
}
