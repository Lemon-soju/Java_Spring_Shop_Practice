package lemonsoju_group.lemonsoju_artifact.service;

import lemonsoju_group.lemonsoju_artifact.domain.User;
import lemonsoju_group.lemonsoju_artifact.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.lang.Boolean.TRUE;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor // final이 붙은 클래스 자동으로 인젝션하여 생성자를 생성한다
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    /**
     * 회원 가입 ==============> 이후 회원이 이미 존재할 경우 코드 구현할 예정
     */
    @Transactional
    public Long join(User user) {
        validateDuplicateUser(user);
        userRepository.save(user);
        return user.getId();
    }

    private void validateDuplicateUser(User user) {
        List<User> findUsers = userRepository.findByUid(user.getUid());
        if (!findUsers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
    }

    /**
     * 로그인 검증 구현 ===========> 이후 로그인에 실패한 경우 문구를 출력하도록 구현할 예정
     */
    public Boolean loginValid(String uid, String pwd){
        List<User> findUsers = userRepository.findByUid(uid);
        if (findUsers.isEmpty()){
            return false;
        }
        log.info(findUsers.get(0).getPwd());
        log.info(pwd);
        if(findUsers.get(0).getPwd().equals(pwd)){
                return TRUE;
        }
        return false;
    }

    /**
     * 회원 전체 조회
     */
    public List<User> findUsers(){
        return userRepository.findAll();
    }

    public User findOne(Long userId){
        return userRepository.findOne(userId);
    }


}
