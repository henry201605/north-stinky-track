package com.cepa.wc.springboot.support.request;

import com.cepa.wc.beans.User;
import com.cepa.wc.springboot.support.Requests;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class UserRequest {
    public static User getCurrentUser() {
        User user = new User();;

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            if (request != null) {
                user = (User) request.getAttribute(Requests.CURRENT_USER);
            }
        }

        return user;
    }
}
