package tn.org.utss.service.utilData;

import tn.org.utss.entity.User;
import tn.org.utss.entity.enums.Role;

public class UserFakeData {
    public static User user0admin = User.builder().id(1L).name("seif").lastName("rabbeh").role(Role.ADMIN).email("rabbehs@gmail.com").password("123").build();
    public static User user1agent = User.builder().id(2L).name("ali").lastName("rh").role(Role.AGENT).email("rabbehseif@gmail.com").password("123").build();
    public static User user2whAgent = User.builder().id(3L).name("amir").lastName("andolsi").role(Role.WAREHOUSE_AGENT).email("rbaihiseif@gmail.com").password("123").build();

}
