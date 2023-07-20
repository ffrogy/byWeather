package byweather.ffrogy.springbootdeveloper.controller;

import byweather.ffrogy.springbootdeveloper.dto.AddUserRequest;
import byweather.ffrogy.springbootdeveloper.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserApiController {

    private final UserService userService;

    @PostMapping("/user")
    public String signUp(AddUserRequest request){
        userService.save(request);  // 회원가입 메서드 호출
        return "redirect:/login";   // 회원가입 완료된 후에 로그인 ㅍ이지로 이동
    }
}
