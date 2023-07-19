package com.example.startspring.repository;

import com.example.startspring.domain.Member;

import java.util.*;

//implements MemberRepository 후 option+enter -> 모두 구현 가능
//@Repository
public class MemoryMemberRepository implements MemberRepository{
   private static Map<Long, Member> store = new HashMap<>();
   private static long sequence = 0L;
    @Override
    public Member save(Member member) {
        member.setId(++sequence);   //id값 세팅
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny(); //끝까지 돌리면서 하나 찾으면 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){   //store를 비움
        store.clear();
    }
}
