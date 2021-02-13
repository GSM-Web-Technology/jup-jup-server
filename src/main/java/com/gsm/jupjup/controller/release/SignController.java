package com.gsm.jupjup.controller.release;

import com.gsm.jupjup.advice.exception.CDuplicateEmailException;
import com.gsm.jupjup.advice.exception.CEmailSigninFailedException;
import com.gsm.jupjup.config.security.JwtTokenProvider;
import com.gsm.jupjup.dto.admin.SignInDto;
import com.gsm.jupjup.dto.admin.SignUpDto;
import com.gsm.jupjup.model.Admin;
import com.gsm.jupjup.model.response.CommonResult;
import com.gsm.jupjup.model.response.ResponseService;
import com.gsm.jupjup.model.response.SingleResult;
import com.gsm.jupjup.repo.AdminRepo;
import com.gsm.jupjup.service.email.EmailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;

@Api(tags = {"1. 회원"})
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000") //해당 origin 승인하기
@RequestMapping(value = "/v1")
public class SignController {

    private String authKey_;

    @Autowired
    private EmailService mss;
    private final AdminRepo adminRepo;
    private final JwtTokenProvider jwtTokenProvider;
    private final ResponseService responseService;
    private final PasswordEncoder passwordEncoder;

    @ApiOperation(value = "로그인", notes = "이메일 회원 로그인을 한다.")
    @PostMapping(value = "/signin")
    public SingleResult<String> signin(@ApiParam(value = "로그인 DTO", required = true) @RequestBody SignInDto signInDto) {
        Admin admin = adminRepo.findByEmail(signInDto.getEmail()).orElseThrow(CEmailSigninFailedException::new);
        /*
        프론트에서 어짜피 회원가입 모달에서 이메일 체크가 됬는지 확인
         */
        if(admin.getRoles().equals("ROLE_NOT_PERMITTED")){
            throw new CEmailSigninFailedException();
        }
        else if (!passwordEncoder.matches(signInDto.getPassword(), admin.getPassword()))
            throw new CEmailSigninFailedException();
        return responseService.getSingleResult(jwtTokenProvider.createToken(String.valueOf(admin.getEmail()), admin.getRoles()));
    }

    @ApiOperation(value = "가입", notes = "회원가입을 한다.")
    @PostMapping(value = "/signup")
    public CommonResult signup(@ApiParam(value = "회원 가입 DTO", required = true) @RequestBody SignUpDto signUpDto) {
        //이메일 중복
        Optional<Admin> admin = adminRepo.findByEmail(signUpDto.getEmail());
        if(admin.isEmpty()){
            adminRepo.save(Admin.builder()
                    .email(signUpDto.getEmail())
                    .password(passwordEncoder.encode(signUpDto.getPassword()))
                    .name(signUpDto.getName())
                    .classNumber(signUpDto.getClassNumber())
                    .roles(Collections.singletonList("ROLE_NOT_PERMITTED"))
                    .build());
        } else {
            throw new CDuplicateEmailException();
        }
        //임의의 authKey 생성 & 이메일 발송
        authKey_ = mss.sendAuthMail(signUpDto.getEmail());

        return responseService.getSuccessResult();
    }

    @ApiOperation(value = "회원가입 이메일 인증", notes = "로그인을 위한 이메일 인증입니다.")
    @Transactional
    @GetMapping("member/signUpConfirm")
    public void signUpConfirm(@RequestParam String email, @RequestParam String AuthKey){
        if(authKey_.equals(AuthKey)){
            Admin admin = adminRepo.findByEmail(email).orElseThrow(CEmailSigninFailedException::new);
            admin.setRoles(Collections.singletonList("ROLE_USER"));
        } else {
            System.out.println("인증번호가 올바르지 않습니다.");
        }
    }
}