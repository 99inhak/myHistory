package com.myLife.myHistory.Controller;

import com.myLife.myHistory.DATA.Entity.UserInformation;
import com.myLife.myHistory.Service.UserLoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


// @Controller는 스프링 프레임워크에서 제공하는 어노테이션으로,
// 웹 요청을 처리하는 컨트롤러 클래스를 나타냅니다. 이 어노테이션은 클래스에 적용되며,
// 해당 클래스의 메소드는 특정 URL에 접속했을 때 실행됩니다.
// @Controller 어노테이션을 사용하면, 특정 URL에 접속했을 때 해당 URL과 연결된 메소드가 실행될 수 있게 됩니다.
// 이 메소드는 웹 요청을 처리하고, 그 결과를 뷰로 반환합니다.

// rest와 일반 컨트롤러
// @Controller와 @RestController는 스프링 프레임워크에서 웹 요청을 처리하는 컨트롤러 클래스를 나타내는
// 어노테이션입니다. 이들 어노테이션의 주요 차이점은 HTTP 응답 본문이 생성되는 방식입니다.
// @Controller는 주로 뷰 이름을 반환하여 웹 페이지를 렌더링하는데 사용됩니다.
// HTTP 응답 본문에 데이터를 직접 넣으려면 @ResponseBody를 추가로 사용해야 합니다.
// @RestController는 RESTful 웹 서비스에서 사용되는 특수 컨트롤러로,
// @Controller와 @ResponseBody의 조합입니다 @RestController를 사용하면,
// 메소드가 뷰 대신 객체를 반환하고, 객체 데이터는 JSON 또는 XML 형식으로 HTTP 응답에 담아서 전송됩니다.
// 따라서, RESTful 웹 서비스를 만드는 경우 @Controller와 @ResponseBody를 사용하는 것보다
// @RestController를 사용하는 것이 좋습니다

//하지만 사용자가 로그인 상태에 따라 다른 페이지를 보여주는 경우(로그인 페이지 또는 메인 페이지),
// 그리고 로그인 정보를 세션에 저장하는 경우에는 @Controller 어노테이션을 사용하는 것이 적합합니다.
// 이는 페이지 이동과 세션 관리 등의 기능을 필요로 하기 때문입니다.

@Controller
public class MainController {

    @Autowired
    HttpSession httpSession;

    @GetMapping("/") // 메인페이지 입장
    // Model 객체는 내가 뷰로 값을 보낼 때 사용하는 것.
    // HttpServletRequest 객체는 http 요청에 대한 정보를 제공하는 인터페이스. 여기 들어온 사람 정보.
    public String MainPage(HttpServletRequest httpServletRequest, Model model) {


        // 현재 세션을 반환합니다. 이 메소드의 매개변수로 false를 전달하면, 현재 세션이 존재하지 않을 경우
        // null을 반환하고 새로운 세션을 생성하지 않습니다. (로그인 하는 순간 만들어야함)
        httpSession = httpServletRequest.getSession(false);

        // 세션이 있는 경우
        if(httpSession != null){

            // isRequestedSessionIdValid()는 세션의 유효성 검사.
            // true면 요청한 세션 ID가 유효.
            if (httpServletRequest.isRequestedSessionIdValid()) {
                // 유효
                // 세션이 로그인 할 때 만들었던 세션인지 확인
                if(httpSession.getAttribute("Saved_User_Information_Sessions") != null){
                    // 세션 안에 있는 로그인할 때 저장한 저장된 정보 가져옴
                    String saved_user_information_session = (String)httpSession.getAttribute("Saved_User_Information_Sessions");
                    // 뷰에 로그인한 유저 세션 정보 전달
                    model.addAttribute("user_id", saved_user_information_session);

                    // 로그인한 메인페이지 이동
                    return "LoggedInMainPage";
                }
                else {
                    return "MainPage";
                }
            }
            //세션만료 페이지 이동
            else {

                return "SessionFaledPage";
            }
        }
        // 세션이 없는 경우
        else {
            // 그냥 메인페이지 이동
            return "MainPage";
        }
    }
}
