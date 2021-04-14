package jp.co.atelier.hello;

import jp.co.atelier.hello.discount.DiscountPolicy;
import jp.co.atelier.hello.discount.FixDiscountPolicy;
import jp.co.atelier.hello.member.MemberRepository;
import jp.co.atelier.hello.member.MemberService;
import jp.co.atelier.hello.member.MemberServiceImpl;
import jp.co.atelier.hello.member.MemoryMemberRepository;
import jp.co.atelier.hello.order.OrderService;
import jp.co.atelier.hello.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(
                memberRepository()
        );
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(
                memberRepository()
                , discountPolicy()
        );
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }
}
