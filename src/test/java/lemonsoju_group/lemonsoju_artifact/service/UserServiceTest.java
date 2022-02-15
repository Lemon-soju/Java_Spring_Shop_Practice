package lemonsoju_group.lemonsoju_artifact.service;

import lemonsoju_group.lemonsoju_artifact.domain.User;
import lemonsoju_group.lemonsoju_artifact.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired UserService userService;
    @Autowired UserRepository userRepository;

    @Test
    @Rollback(value = false)
    public void 회원가입() throws Exception {
        //given
        User user = new User();
        user.setName("kim");

        //when
        Long savedId = userService.join(user);

        //then
        assertEquals(user, userRepository.findOne(savedId));
        
    }
    
    @Test(expected = IllegalStateException.class)
    public void 중복회원예외() throws Exception{
        //given
        User user1 = new User();
        user1.setName("kim");
        User user2 = new User();
        user2.setName("kim");
    
        //when
        userService.join(user1);
        userService.join(user2); // 예외가 발생해야 한다.

        //then
        fail("예외가 발생해야 한다");
    }

}