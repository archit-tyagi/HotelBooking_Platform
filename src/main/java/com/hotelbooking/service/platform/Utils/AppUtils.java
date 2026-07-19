package com.hotelbooking.service.platform.Utils;

import com.hotelbooking.service.platform.Entities.UserEntity;
import org.springframework.security.core.context.SecurityContextHolder;

public class AppUtils {

    public static UserEntity getCurrentUser() {
        return (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}