package com.task.backend.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ActivityType {
    REGISTRATION,
    LOGIN,
    CONVERSATION_CREATION,
    UNKNOWN
}
