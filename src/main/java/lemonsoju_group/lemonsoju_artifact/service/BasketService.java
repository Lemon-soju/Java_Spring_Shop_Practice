package lemonsoju_group.lemonsoju_artifact.service;

import lemonsoju_group.lemonsoju_artifact.domain.*;
import lemonsoju_group.lemonsoju_artifact.repository.BasketRepository;
import lemonsoju_group.lemonsoju_artifact.repository.ItemRepository;
import lemonsoju_group.lemonsoju_artifact.repository.OrderRepository;
import lemonsoju_group.lemonsoju_artifact.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BasketService {

    private final BasketRepository basketRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    /**
     * 장바구니 추가
     */

    @Transactional
    public void addBasket(Long userId, Long itemId){

        Optional<Basket> findBasket = basketRepository.findOneByItem(itemId);
        User user = userRepository.findOne(userId);
        Item item = itemRepository.findOne(itemId);

        if (!findBasket.isEmpty()){
            Basket basket = findBasket.get();
            int count = basket.getCount() + 1;
            basket.setCount(count);
            basketRepository.save(basket);
        }
        else {
            Basket basket = Basket.createBasket(user, item, item.getPrice(), 1);
            basketRepository.save(basket);
        }
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
