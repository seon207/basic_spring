package com.example.startspring.service;

import com.example.startspring.domain.Member;
import com.example.startspring.repository.MemberRepository;
import com.example.startspring.repository.MemoryMemberRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
//command+shift+t -> 테스트 쉽게 하기
//@Service    //spring에 올라올 때 컨테이너에 저장하기 위함 -> controller에서 사용 가능

@Transactional
public class MemberService {
    private MemberRepository memberRepository = new MemoryMemberRepository();

    //@Autowired  //생성자 호출
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */

    public Long join(Member member) {
        //같은 이름이 있는 중복 회원 x
        validateDuplicateMember(member);    //중복 회원 검증
        memberRepository.save(member);
        return member.getId();


    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
//            try {
//                throw new IllegalStateException("이미 존재하는 회원입니다.");
//            } catch (IllegalStateException e) {
//                throw new IllegalStateException(e);
//            }
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
