package jp.co.atelier.hello;

import jp.co.atelier.hello.member.Grade;
import jp.co.atelier.hello.member.Member;
import jp.co.atelier.hello.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        MemberService memberService = new MemberServiceImpl();

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = ac.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1l);

        System.out.println("new member = " + member.getName());
        System.out.println("find member = " + findMember.getName());
    }
}
