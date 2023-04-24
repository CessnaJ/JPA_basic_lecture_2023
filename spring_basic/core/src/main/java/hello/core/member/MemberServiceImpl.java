package hello.core.member;

import hello.core.discount.DiscountPolicy;

public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public MemberServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);

    }

    @Override
    public Member findMember(Long memberId) {

        return memberRepository.findById(memberId
        );
    }
}
