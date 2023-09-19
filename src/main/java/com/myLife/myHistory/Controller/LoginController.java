package com.myLife.myHistory.Controller;

import com.myLife.myHistory.DATA.Entity.UserInformation;
import com.myLife.myHistory.Service.UserLoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class LoginController {
    @Autowired
    HttpSession httpSession;
    @Autowired
    UserLoginService userLoginService;



    @GetMapping("/LoginPage") // 로그인 페이지 입장
    public String LoginPage() {
        return "LoginPage";
    }


    // 현재 LoginPage에서 폼 태그에서 로그인 정보를 서버로 보냈다. 그걸 받는 방법은
    // @RequestParam("id") String id, @RequestParam("pw") String pw 해서 받는데 나중에
    // 받는 데이터의 개수가 많아지면 계속 추가할건가?
    // Entity에서 만들어둔 도메인 클래스 UserInformation 이용하여 받아온다.
    // spring은  HTTP 요청의 파라미터를 도메인 클래스의 인스턴스로 자동으로 바인딩하는 기능을 제공합니다.
    // 이를 Command 객체라고 부르며, 이 기능을 사용하면 요청 파라미터를 일일이 @RequestParam으로 받지 않고,
    // 한 번에 도메인 클래스의 인스턴스로 받을 수 있습니다.
    @PostMapping("/LoginPro") // 로그인 처리
    public String LoginPro(UserInformation userInformation, RedirectAttributes redirectAttributes) {
        System.out.println(userInformation);
        if(userInformation.getId() == null ||userInformation.getPassword() == null || userInformation.getId().isEmpty() || userInformation.getPassword().isEmpty()){
            redirectAttributes.addFlashAttribute("errorMessage", "아이디와 패스워드를 입력해주세요!");
            return "redirect:/LoginPage";
        }

        int state = userLoginService.login(userInformation); // 로그인 서비스 실행
        if (state == 1) { // 로그인 성공
            // 세션에 로그인 정보 추가
            httpSession.setAttribute("userSession", userInformation.getId());
            return "redirect:/";
        } else if (state == 0) {
            redirectAttributes.addFlashAttribute("errorMessage", "아이디나 패스워드가 틀렸습니다.");
            return "redirect:/LoginPage";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "아이디나 패스워드가 틀렸습니다.");
            return "redirect:/LoginPage";
        }
    }

    @GetMapping("/LogOutPro")
    public String LogOutPro(){
        httpSession.invalidate();
        return "redirect:/";
    }
}
