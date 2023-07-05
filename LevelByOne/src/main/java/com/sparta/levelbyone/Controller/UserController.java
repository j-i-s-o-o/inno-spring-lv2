package com.sparta.levelbyone.Controller;

import com.sparta.levelbyone.dto.userDto.LoginRequestDto;
import com.sparta.levelbyone.dto.userDto.SignupRequestDto;
import com.sparta.levelbyone.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public String signupPage(@RequestBody @Valid SignupRequestDto requestDto) {
        userService.signup(requestDto);
        return "회원가입 성공";
    }

    @PostMapping("/login")
    public String loginPage(@RequestBody LoginRequestDto requestDto, HttpServletResponse res) {
        userService.login(requestDto, res);
        return "로그인 성공";
    }

}
