package com.myLife.myHistory.DATA.Repository;

import com.myLife.myHistory.DATA.Entity.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Entity 의 주석으로 함께 이해하도록 하자
@Repository
public interface UserRepository extends JpaRepository<UserInformation, String> {

}
