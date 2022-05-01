package lemonsoju_group.lemonsoju_artifact.service;

import lemonsoju_group.lemonsoju_artifact.domain.User;
import lemonsoju_group.lemonsoju_artifact.repository.UserDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserDataRepository userRepository;

    public User login(String loginId, String pwd) {
        return userRepository.findByUid(loginId)
                .filter(m -> m.getPwd().equals(pwd))
                .orElse(null);
    }
}