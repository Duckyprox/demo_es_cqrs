package com.example.demo_cqrs_es.es.util;

import com.example.demo_cqrs_es.entities.User;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserUtil {

    public static List<User> USER_LIST = new ArrayList<>();

    public static List<User> generateUserList() {
        USER_LIST = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
//            USER_LIST.add(new User(i, "User_" + i, new Role(i, "ROLE_" + i)));
        }
        return USER_LIST;
    }

    public static List<User> getUsers() {
        if (CollectionUtils.isEmpty(USER_LIST)) {
            return generateUserList();
        }
        return USER_LIST;
    }
}
