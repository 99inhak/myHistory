package com.myLife.myHistory.Service;


import com.myLife.myHistory.DATA.Entity.UserInformation;
import com.myLife.myHistory.DATA.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserLoginService {
    @Autowired
    private UserRepository userRepository;

    public int login(UserInformation userInformation){
        int state = 0; // 0=패스워드 불일치, 1=성공, 2=아이디가 등록되지 않음
        String passwordResult = null;


        // userRepository.findById(userInformation.getId())의 반환값이 Optional인 이유는
        // findById 메소드가 특정 ID를 가진 엔티티를 찾을 수 없는 경우를 안전하게 처리하기 위해서입니다.
        // Optional은 Java 8에서 도입된 클래스로, 값이 있을 수도 있고 없을 수도 있는 상황을 모델링하는 데 사용됩니다
        // Optional은 내부적으로 값을 포함하거나 포함하지 않을 수 있으며,
        // 값이 없는 경우 null 대신에 Optional.empty()를 반환합니다.
        // 따라서 findById 메소드는 찾고자 하는 엔티티가 데이터 저장소에 존재하면 그 값을 포함하는 Optional을 반환하고,
        // 그렇지 않으면 비어 있는 Optional을 반환합니다.
        // 이렇게 하면 null을 직접 다루는 것보다 안전하게 코드를 작성할 수 있습니다.
        // 예를 들어, Optional의 isPresent() 메소드를 사용하여 값이 있는지 확인하고,
        // get() 메소드를 사용하여 값을 가져올 수 있습니다.
        // 또는 orElse() 메소드를 사용하여 값이 없는 경우 기본값을 제공할 수도 있습니다.
        Optional<UserInformation> result = userRepository.findById(userInformation.getId()); // 회원 구별을 위해 로그인한 아이디로 검색

        // 값이 있으면 true
        if (result.isPresent())
        {
            passwordResult = result.get().getPassword();

            // 현재 사용자가 입력한 비밀번호 == 사용자가 입력한 아이디의 서버 DB에 저장된 비밀번호
            if (Objects.equals(userInformation.getPassword(), passwordResult))
            {
                state = 1;
            }
            else
            { // 다른 비밀번호
                return state;
            }
        }
        else
        {
            // 등록되지 않은 id
            state = 2;
            return state;
        }
        return state;
    }
}
