package lemonsoju_group.lemonsoju_artifact.service;

import lemonsoju_group.lemonsoju_artifact.domain.*;
import lemonsoju_group.lemonsoju_artifact.repository.BasketDataRepository;
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

    private final BasketDataRepository basketRepository;
    private final UserDataRepository userRepository;
    private final ItemDataRepository itemRepository;

    /**
     * 장바구니 추가
     */

    @Transactional
    public void addBasket(Long userId, Long itemId){

        User user = userRepository.findById(userId).orElse(null);
        Item item = itemRepository.findById(itemId).orElse(null);
        Optional<Basket> findBasket = basketRepository.findOneByUserAndItem(user, item);

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

    public List<Basket> findBaskets(User user) {
        return basketRepository.findAllByUser(user);
    }

    public Basket findOne(Long basketId) {
        return basketRepository.findById(basketId).orElse(null);
    }

    @Transactional
    public void delete(Basket basket){
        basketRepository.delete(basket);
    }

}
