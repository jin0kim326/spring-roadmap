package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
public class MemberServiceIntegrationTest {
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("jy");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    /**
     * 예외인 경우의 테스트가 더 중요하다.
     */
    @Test
    public void 회원가입_중복예외() throws Exception {
        // given
        Member member1 = new Member();
        member1.setName("jy");

        Member member2 = new Member();
        member2.setName("jy");

        // when
        memberService.join(member1);

        // then
        assertThatThrownBy(() -> memberService.join(member2)).isInstanceOf(IllegalStateException.class);
    }

    @Test
    public void 회원전체찾기() throws Exception {
    }

    @Test
    public void 회원찾기() throws Exception {
    }
}
