package com.example.startspring.controller;

import com.example.startspring.domain.Member;
import com.example.startspring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller //spring에서 객체 생성해서 가지고 있음
public class MemberController {
    private final MemberService memberService;

    @Autowired  //스프링 컨테이너에서 memberService 가져옴, depnedency injection
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";  //template에서 createMemberForm 찾음
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());
        memberService.join(member);

        return "redirect:/";    //홈화면으로 되돌아감
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members); //모델에 담기
        return "members/memberList";    //memberList html
    }
}
