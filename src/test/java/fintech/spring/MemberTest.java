package fintech.spring;

import fintech.spring.entity.Member;
import fintech.spring.repository.MemberRepository;
import fintech.spring.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest //스프링 컨테이너로 테스트 실행
@Transactional //테스트 후 롤백
public class MemberTest {
    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void join() throws Exception{
        // given : member 객체 생성
        Member member = new Member();
        member.setName("winter");
        member.setId("elsa");
        member.setOrg("sm");
        member.setActive(true);

        // when : 멤버 가입시 멤버 서비스 스프링 컨텍스트가 사용된걸 확인
        Member storedMember = memberService.addUser(member);

        //then : 그 멤버를 다시 찾을 때 둘 이름이 같아야함
        Member foundMember = memberRepository.findById(storedMember.getSeq()).get();
        assertEquals(member.getName(), foundMember.getName());

    }

}
