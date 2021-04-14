package jp.co.atelier.hello.order;

import jp.co.atelier.hello.discount.DiscountPolicy;
import jp.co.atelier.hello.member.Member;
import jp.co.atelier.hello.member.MemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

//    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        System.out.println("# constructor #");
//        System.out.println("memberRepository = " + memberRepository);
//        System.out.println("discountPolicy = " + discountPolicy);
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        System.out.println("# setter #");
//        System.out.println("memberRepository = " + memberRepository);
//        this.memberRepository = memberRepository;
//    }
//
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        System.out.println("# setter #");
//        System.out.println("discountPolicy = " + discountPolicy);
//        this.discountPolicy = discountPolicy;
//    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
