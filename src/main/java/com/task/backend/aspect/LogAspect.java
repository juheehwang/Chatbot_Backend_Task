package com.task.backend.aspect;

import com.task.backend.context.UserAccountHolder;
import com.task.backend.entity.ActivityType;
import com.task.backend.entity.MemberActivity;
import com.task.backend.repository.MemberActivityRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class LogAspect {


    private final MemberActivityRepository memberActivityRepository;

    // 요청이 들어오는 서비스 메서드
    @Pointcut("execution(* com.task.backend.service.*.*(..))")
    public void serviceLayer() {}

    @Around("serviceLayer()")
    public Object logActivity(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();

        // 메서드 이름과 인자 등으로 로그 기록
        String methodName = joinPoint.getSignature().getName();
         // 로그인한 사용자 ID 추출 방식에 맞게 수정
        ActivityType activityType = getActivityType(methodName);

        // 로그를 엔티티로 저장
        memberActivityRepository.save(
            MemberActivity.builder()
                .activityType(activityType)
                .userId(UserAccountHolder.getId())
                .build());

        return result;
    }

    // 메서드 이름에 따른 활동 유형 결정
    private ActivityType getActivityType(String methodName) {
        return switch (methodName) {
            case "register" -> ActivityType.REGISTRATION;
            case "login" -> ActivityType.LOGIN;
            case "createConversation" -> ActivityType.CONVERSATION_CREATION;
            default -> ActivityType.UNKNOWN;
        };
    }
}
