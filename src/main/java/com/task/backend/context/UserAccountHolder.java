package com.task.backend.context;


import com.task.backend.util.UserAccount;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserAccountHolder {

    private UserAccountHolder() {
        throw new UnsupportedOperationException();
    }

    public static UserAccount get() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        return Optional.ofNullable(authentication)
            .map(Authentication::getPrincipal)
            .filter(auth -> auth instanceof UserAccount)
            .map(principal -> (UserAccount) principal)
            .orElseThrow(() -> new NoSuchElementException("Cannot get a user account"));
    }

    public static <T extends UserAccount> T get(final Class<T> type) {
        return type.cast(get());
    }

    public static Long getId() {
        return get().getSeq();
    }

    public static Long getIdOrDefault(final Long defaultValue) {
        try {
            return getId();
        } catch (NoSuchElementException ue) {
            return defaultValue;
        }
    }
}
