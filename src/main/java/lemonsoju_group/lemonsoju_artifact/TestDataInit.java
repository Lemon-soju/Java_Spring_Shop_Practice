package lemonsoju_group.lemonsoju_artifact;

import lemonsoju_group.lemonsoju_artifact.domain.Basket;
import lemonsoju_group.lemonsoju_artifact.domain.Item;
import lemonsoju_group.lemonsoju_artifact.domain.User;
import lemonsoju_group.lemonsoju_artifact.repository.ItemRepository;
import lemonsoju_group.lemonsoju_artifact.repository.UserRepository;
import lemonsoju_group.lemonsoju_artifact.service.BasketService;
import lemonsoju_group.lemonsoju_artifact.service.ItemService;
import lemonsoju_group.lemonsoju_artifact.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final ItemService itemService;
    private final UserService userService;
    private final BasketService basketService;

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
        userA.setName("lemon_soju");

        userService.join(userA);


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
         * 장바구니 추가
         */

        Basket basketA = new Basket();
        basketA.setBasketPrice(itemA.getPrice());

        basketService.addBasket(userA.getId(), itemA.getId());
    }
}
