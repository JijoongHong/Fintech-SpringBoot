package fintech.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
//@Builder // 디자인 컨셉의 빌드업 패턴을 생성해줌
public class Member {
    @Id
    @GeneratedValue //시퀀스 생성
    String id;
    String name;
    String org;
    Boolean active;
}
