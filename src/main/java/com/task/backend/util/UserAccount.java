package com.task.backend.util;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserAccount extends UserDetails {

    Long getSeq();

    String getEmail();
}
