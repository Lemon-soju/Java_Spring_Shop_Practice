package lemonsoju_group.lemonsoju_artifact.service;

import lemonsoju_group.lemonsoju_artifact.domain.Item;
import lemonsoju_group.lemonsoju_artifact.domain.Order;
import lemonsoju_group.lemonsoju_artifact.domain.OrderStatus;
import lemonsoju_group.lemonsoju_artifact.domain.User;
import lemonsoju_group.lemonsoju_artifact.exception.NotEnoughStockException;
import lemonsoju_group.lemonsoju_artifact.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired EntityManager em;
    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception {
        //given
        User user = createUser("회원1");

        Item item = createItem("시골 JPA", 10000, 10);

        int orderCount = 2;

        //when
        Long orderId = orderService.order(user.getId(), item.getId(), orderCount);

        //then
        Order getOrder = orderRepository.findOne(orderId);

        assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER, getOrder.getStatus());
        assertEquals("주문한 상품 종류 수가 정확해야 한다.", 1, getOrder.getOrderItems().size());
        assertEquals("주문 가격은 가격 * 수량이다.", 10000 * orderCount, getOrder.getTotalPrice());
        assertEquals("주무 수량만큼 재고가 줄어야 한다.", 8, item.getStockQuantity());
    }


    @Test(expected = NotEnoughStockException.class)
    public void 상품주문_재고수량초과() throws Exception {
        //given
        User user = createUser("회원1");
        Item item = createItem("시골 JPA", 10000, 10);

        int orderCount = 11;

        //when
        orderService.order(user.getId(), item.getId(), orderCount);
    
        //then
        fail("재고 수량 부족 예외가 발생해야 한다.");
    }


    @Test
    public void 주문취소() throws Exception{
        //given
        User user = createUser("lemon");
        Item item = createItem("시골 JPA", 10000, 10);

        int orderCount = 2;
        Long orderId = orderService.order(user.getId(), item.getId(), orderCount);

        //when
        orderService.cancleOrder(orderId);

        //then
        Order getOrder = orderRepository.findOne(orderId);

        assertEquals("주문 취소시 상태는 CANCEL 이다.", OrderStatus.CANCEL, getOrder.getStatus());
        assertEquals("주문이 취소된 상품은 그만큼 재고가 증가해야 한다.", 10, item.getStockQuantity());
    }


    private Item createItem(String name, int price, int stockQuantity) {
        Item item = new Item();
        item.setName(name);
        item.setPrice(price);
        item.setStockQuantity(stockQuantity);
        em.persist(item);
        return item;
    }

    private User createUser(String name) {
        User user = new User();
        user.setName(name);
        em.persist(user);
        return user;
    }
}