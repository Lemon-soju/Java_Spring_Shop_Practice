package lemonsoju_group.lemonsoju_artifact;


import lemonsoju_group.lemonsoju_artifact.domain.User;
import lemonsoju_group.lemonsoju_artifact.repository.UserDataRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserDataRepository userRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    public void testUser() throws Exception{
        //given
        User user = new User();
        user.setName("lemon01");

        //when
        Long savedId = userRepository.save(user).getId();
        User findUser = userRepository.findById(savedId).orElse(null);

        //then
        Assertions.assertThat(findUser.getId()).isEqualTo(user.getId());
        Assertions.assertThat(findUser.getName()).isEqualTo(user.getName());
        Assertions.assertThat(findUser).isEqualTo(user);
    }
}