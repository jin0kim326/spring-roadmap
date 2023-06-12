package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("jinyoung");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() throws Exception {
        Member member1 = new Member();
        member1.setName("jy1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("jy2");
        repository.save(member2);

        Member result = repository.findByName("jy1").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() throws Exception {
        Member member1 = new Member();
        member1.setName("jy1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("jy2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}