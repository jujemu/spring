package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        this.memberRepository = new MemoryMemberRepository();
        this.memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }


    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void duplicateMemberName_ThenThrown() {
        String name = "name";

        Member member1 = new Member();
        member1.setName(name);

        Member member2 = new Member();
        member2.setName(name);

        //when //then
        assertThrows(IllegalArgumentException.class, () -> {
            memberService.join(member1);
            memberService.join(member2);
        });
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}