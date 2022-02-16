package lemonsoju_group.lemonsoju_artifact.service;

import lemonsoju_group.lemonsoju_artifact.domain.Item;
import lemonsoju_group.lemonsoju_artifact.domain.Order;
import lemonsoju_group.lemonsoju_artifact.domain.OrderItem;
import lemonsoju_group.lemonsoju_artifact.domain.User;
import lemonsoju_group.lemonsoju_artifact.repository.ItemRepository;
import lemonsoju_group.lemonsoju_artifact.repository.OrderRepository;
import lemonsoju_group.lemonsoju_artifact.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    /**
     * 주문
     */
    @Transactional
    public Long order(Long userId, Long itemId, int count){

        // 엔티티 조회
        User user = userRepository.findOne(userId);
        Item item = itemRepository.findOne(itemId);

        // 주문상품생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        // 주문생성
        Order order = Order.createOrder(user, orderItem);

        // 주문 저장
        orderRepository.save(order);

        return order.getId();
    }

    /**
     * 주문 취소
     */
    @Transactional
    public void cancleOrder(Long orderId){
        //주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);
        // 주문 취소
        order.cancel();
    }

}
