package lemonsoju_group.lemonsoju_artifact.controller;

import lemonsoju_group.lemonsoju_artifact.domain.Basket;
import lemonsoju_group.lemonsoju_artifact.service.BasketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BasketController {

    private final BasketService basketService;


    @GetMapping("/basket")
    public String list(Model model) {
        List<Basket> baskets = basketService.findBaskets();
        model.addAttribute("baskets", baskets);
        return "basket/basketList";
    }

    /**
     * 장바구니 삭제
     */
    @GetMapping("/basket/{basketId}/delete")
    public String deleteBasket(@PathVariable("basketId") Long basketId, Model model){
        log.info("hello");
        Basket basket = basketService.findOne(basketId);
        basketService.delete(basket);

        return "redirect:/basket";
    }
}