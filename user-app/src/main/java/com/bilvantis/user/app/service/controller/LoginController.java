package com.bilvantis.user.app.service.controller;


import com.bilvantis.user.api.service.LoginService;
import com.bilvantis.user.api.service.impl.JwtTokenService;
import com.bilvantis.user.app.service.util.UserAppConstant;
import com.bilvantis.user.app.service.util.UsersRequestResponseBuilder;
import com.bilvantis.user.data.model.EmployeeDTO;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/login")
@Validated
public class LoginController {

    @Autowired
    @Qualifier("loginServiceImpl")
    private LoginService<EmployeeDTO, Long> loginService;

    @Autowired
    private JwtTokenService jwtTokenService;

    @GetMapping("/auth")
    public ResponseEntity<String> authLoginDetails(@NotNull @RequestParam String employeeId,
                                                               @NotNull @RequestParam String password) {
        return new ResponseEntity<>(
                jwtTokenService.generateToken(loginService.verifyEmployeeLogin(employeeId, password).getEmployeeId()), HttpStatus.OK);

    }
}
