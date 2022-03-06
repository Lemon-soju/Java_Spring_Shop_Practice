package lemonsoju_group.lemonsoju_artifact.service;

import lemonsoju_group.lemonsoju_artifact.domain.*;
import lemonsoju_group.lemonsoju_artifact.repository.BasketRepository;
import lemonsoju_group.lemonsoju_artifact.repository.ItemRepository;
import lemonsoju_group.lemonsoju_artifact.repository.OrderRepository;
import lemonsoju_group.lemonsoju_artifact.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BasketService {

    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final BasketRepository basketRepository;

    /**
     * 장바구니 추가
     */
    @Transactional
    public Long addBasket(User user, Item item, int count){

        // 주문생성
        Basket basket = Basket.createBasket(user, item, item.getPrice());

        // 주문 저장
        basketRepository.save(basket);

        return basket.getId();
    }

    public List<Basket> findBaskets() {
        return basketRepository.findAll();
    }

    public Basket findOne(Long basketId) {
        return basketRepository.findOne(basketId);
    }

    @Transactional
    public void delete(Basket basket){
        basketRepository.delete(basket);
    }

}
