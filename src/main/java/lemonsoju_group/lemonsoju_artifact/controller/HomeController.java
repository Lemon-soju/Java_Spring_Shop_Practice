package lemonsoju_group.lemonsoju_artifact.controller;

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

    private final UserService userService;

    @RequestMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/main")
    public String main()
    {
        return "main";
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("loginForm", new LoginForm());
        log.info("login!!");
        return "login";
    }

    @PostMapping("/login")
    public String verify(@Valid LoginForm form, BindingResult result){

        if (result.hasErrors()){
            return "login";
        }
        if (userService.loginValid(form.getUid(), form.getPwd())){
            return "main";
        }
        return "redirect:/login";
    }

}
