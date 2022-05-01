package lemonsoju_group.lemonsoju_artifact.service;

import lemonsoju_group.lemonsoju_artifact.domain.*;
import lemonsoju_group.lemonsoju_artifact.repository.BasketRepository;
import lemonsoju_group.lemonsoju_artifact.repository.ItemDataRepository;
import lemonsoju_group.lemonsoju_artifact.repository.UserDataRepository;
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
    private final UserDataRepository userRepository;
    private final ItemDataRepository itemRepository;

    /**
     * 장바구니 추가
     */

    @Transactional
    public void addBasket(Long userId, Long itemId){

        Optional<Basket> findBasket = basketRepository.findOneByItem(itemId);
        User user = userRepository.findById(userId).orElse(null);
        Item item = itemRepository.findById(itemId).orElse(null);

        if (!findBasket.isEmpty()){
            Basket basket = findBasket.orElse(null);
            int count = basket.getCount() + 1;
            basket.setCount(count);
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
