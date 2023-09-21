package com.myLife.myHistory.DATA.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Collection;

// @Entity 어노테이션은 JPA(Java Persistence API)에서 제공하는 기능으로,
// 클래스와 데이터베이스 테이블 간의 매핑을 선언하는 데 사용됩니다.
// 이 어노테이션을 사용하면, 해당 클래스의 인스턴스는 데이터베이스의 테이블 레코드와 일대일로 매핑되며,
// JPA가 이를 관리합니다.
// 만약 이 방법을 사용하지 않고 구현한다면 JDBC를 직접사용한다.
// jdbc 연결을 위해 드라이버 클래스랑 Connection 객체에 로컬과 포트 정보넣고 db의 이름과 패스워드 값을 넣는다.
// PrepareStatement로 sql문을 넘기고 ResertSet을 통하여 결과를 if문에 rs.next()해서 뽑아낸다.

// @Data 어노테이션은 Lombok 라이브러리에서 제공하는 어노테이션이다.
// @Getter, @Setter, @RequiredArgsConstructor, @ToString, @EqualsAndHashCode을 한꺼번에 설정해줌.
// 원래 선언부 밑에 게터세터 쫘라락 해둬야함.

// 결론 :
// @Entity와 @Data 어노테이션은 도메인 모델 클래스를 정의하는 데 사용되며,
// DAO와 DTO는 이러한 도메인 모델을 기반으로 데이터베이스 작업을 수행하고 결과를 전달하는 데 사용됩니다.
// 사실상 DAO는 Repository 어노테이션이 그 역할을, DTO는 DATA 어노테이션이 그 역할을 한다고 보면 됨.

// @Entity : JDBC 커넥션까지 DB랑 1:1 맵핑까지만
// @DATA : 게터세터 구현만
// @Repository : psmt랑 rs쪽 담당. db 작업 수행쪽.

// DAO : 데이터베이스와 연동되며 CRUD 작업
// DTO : 데이터 교환을 위한 객체로 응답이나 요청 데이터를 담는 데에 사용

// @Table 어노테이션을 생략하면 엔티티 클래스의 이름이 테이블 이름으로 사용됩니다.
@Entity
@Data
@Table(name="user_information")
public class UserInformation {

    @Id
    private String id; // 사용자 아이디

    private String password; // 사용자 비밀번호
    private String username; // 사용자 이름


}
