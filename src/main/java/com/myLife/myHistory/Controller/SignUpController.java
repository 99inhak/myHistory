package com.myLife.myHistory.Controller;

import com.myLife.myHistory.DATA.Entity.UserInformation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SignUpController {

    @GetMapping("/SignUpPage")
    public String SignUpPage(){
        return "SignUpPage";
    }

    @PostMapping("/SignUpPro")
    public String SignUpPro(UserInformation userInformation, RedirectAttributes redirectAttributes){
        if(userInformation.getId().isEmpty()||userInformation.getPassword().isEmpty()||
        userInformation.getUsername().isEmpty()){
            redirectAttributes.addFlashAttribute("errorMessage", "빈 칸이 존재합니다. 입력해주세요!");
            return "redirect:/SignUpPage";
        }
        return "";
    }
}
