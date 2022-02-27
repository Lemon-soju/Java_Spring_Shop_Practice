package lemonsoju_group.lemonsoju_artifact.controller;

import lemonsoju_group.lemonsoju_artifact.domain.User;
import lemonsoju_group.lemonsoju_artifact.service.LoginService;
import lemonsoju_group.lemonsoju_artifact.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final LoginService loginService;

    @RequestMapping("/")
    public String login(){
        return "login";
    }

    @GetMapping("/home")
    public String main()
    {
        return "home";
    }

    @GetMapping("/login")
    public String loginForm(Model model){
        model.addAttribute("loginForm", new LoginForm());
        log.info("login!!");
        return "loginForm";
    }

    @PostMapping("/login")
    public String verify(@Valid LoginForm form, BindingResult result){

        if (result.hasErrors()){
            return "loginForm";
        }

        User loginUser = loginService.login(form.getUid(), form.getPwd());

        if (loginUser == null){
            result.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "loginForm";
        }

        return "redirect:/home";
    }

}
