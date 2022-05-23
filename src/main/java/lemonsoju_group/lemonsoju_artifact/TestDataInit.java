package lemonsoju_group.lemonsoju_artifact;

import lemonsoju_group.lemonsoju_artifact.domain.Basket;
import lemonsoju_group.lemonsoju_artifact.domain.Item;
import lemonsoju_group.lemonsoju_artifact.domain.User;
import lemonsoju_group.lemonsoju_artifact.service.BasketService;
import lemonsoju_group.lemonsoju_artifact.service.ItemService;
import lemonsoju_group.lemonsoju_artifact.service.OrderService;
import lemonsoju_group.lemonsoju_artifact.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.criteria.Order;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final ItemService itemService;
    private final UserService userService;
    private final BasketService basketService;
    private final OrderService orderService;

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init(){

        /**
         * 테스트 유저 추가
         */
        User userA = new User();
        userA.setUid("lemon");
        userA.setPwd("asd123");
        userA.setName("james");
        userService.join(userA);

        User userB = new User();
        userB.setUid("lemon2");
        userB.setPwd("asd123");
        userB.setName("tom");
        userService.join(userB);


        /**
         * 테스트 상품 추가
         */
        Item itemA = new Item();
        itemA.setName("itemA");
        itemA.setPrice(10000);
        itemA.setStockQuantity(10);

        Item itemB = new Item();
        itemB.setName("itemB");
        itemB.setPrice(20000);
        itemB.setStockQuantity(20);

        itemService.saveItem(itemA);
        itemService.saveItem(itemB);

        /**
         * 테스트 주문 추가
         */

        orderService.order(userA.getId(), itemA.getId(), 5);
        orderService.order(userB.getId(), itemB.getId(), 4);


        /**
         * 장바구니 추가
         */

        Basket basketA = new Basket();
        basketA.setBasketPrice(itemA.getPrice());

        basketService.addBasket(userA.getId(), itemA.getId());
    }
}
