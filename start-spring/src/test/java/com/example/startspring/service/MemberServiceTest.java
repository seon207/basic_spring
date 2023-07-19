package com.example.startspring.service;

import com.example.startspring.domain.Member;
import com.example.startspring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    //테스트는 한글도 가능

    MemberService memberService;
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();    //다른 리포지토리를 이용중

    //각 테스트 실행 전
    //같은 레포지토리 사용
    @BeforeEach
    public void beforeEach(){
        memberRepository =  new MemoryMemberRepository()
;        memberService = new MemberService(memberRepository);
    }
    //실행 후 db값 날려줌
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }
    @Test
    void 회원가입() {
        //given, 주어진 상황에서
        Member member = new Member();
        member.setName("hello");

        //when, 이런 동작을 했을 때
        Long saveId = memberService.join(member);

        //then, 이런 결과가 나와야 함
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
        //static으로 바꾸는 법 -> option+enter -> Assertions.assertThat에서 그냥 assertThat만 사용가능
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);

        //try-catch문을 간단하게
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        /*try {
            memberService.join(member2);
            fail();
        }catch(IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/
        //then

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}