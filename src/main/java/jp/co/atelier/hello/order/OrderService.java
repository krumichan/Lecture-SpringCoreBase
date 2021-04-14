package jp.co.atelier.hello.order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
