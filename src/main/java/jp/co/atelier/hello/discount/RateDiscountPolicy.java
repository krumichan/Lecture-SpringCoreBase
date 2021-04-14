package jp.co.atelier.hello.discount;

import jp.co.atelier.hello.member.Grade;
import jp.co.atelier.hello.member.Member;

public class RateDiscountPolicy implements DiscountPolicy {

    private final int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {

        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
