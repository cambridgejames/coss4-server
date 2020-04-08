package cn.net.powerinv.coss4.competition.controller;

import cn.net.powerinv.coss4.competition.vo.UserPlayerDTO;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class PlayerControllerTest {

    @Test
    public void createPlayer() {
        UserPlayerDTO userPlayerDTO1 = new UserPlayerDTO();
        UserPlayerDTO userPlayerDTO2 = new UserPlayerDTO(1, "1", "1", 1, 1, 1);
        String uuid1 = UUID.nameUUIDFromBytes(userPlayerDTO1.toString().getBytes()).toString();
        String uuid2 = UUID.nameUUIDFromBytes(userPlayerDTO2.toString().getBytes()).toString();
        String password1 = uuid1.substring(0, 8);
        String password2 = uuid2.substring(0, 8);
        System.out.println(password1);
        System.out.println(password2);
    }
}