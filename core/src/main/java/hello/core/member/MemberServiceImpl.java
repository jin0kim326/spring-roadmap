package hello.core.member;

public class MemberServiceImpl implements MemberService {
    /**
     * MemberRepository 인터페이스 의존
     * MemoryMemberRepository 구현체 의존
     * 추상화/구체화 둘다에 의존 -> DIP 위반
     */
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
