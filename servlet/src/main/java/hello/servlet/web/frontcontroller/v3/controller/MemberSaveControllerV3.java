package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v3.ModelView;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {

    private final MemberRepository memberRepository = MemberRepository.getInstance();


    @Override
    public ModelView process(Map<String, String> params) {

        String username = params.get("username");
        int age = Integer.parseInt(params.get("age"));

        Member member = Member.create(username, age);
        memberRepository.save(member);

        String viewName = "save-result";
        ModelView modelView = new ModelView(viewName);
        modelView.addModelParam("member", member);

        return modelView;
    }
}
