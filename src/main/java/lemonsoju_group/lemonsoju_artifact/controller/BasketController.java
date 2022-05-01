package lemonsoju_group.lemonsoju_artifact.controller;

import lemonsoju_group.lemonsoju_artifact.SessionConst;
import lemonsoju_group.lemonsoju_artifact.domain.Basket;
import lemonsoju_group.lemonsoju_artifact.domain.Item;
import lemonsoju_group.lemonsoju_artifact.domain.User;
import lemonsoju_group.lemonsoju_artifact.service.BasketService;
import lemonsoju_group.lemonsoju_artifact.service.ItemService;
import lemonsoju_group.lemonsoju_artifact.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BasketController {

    private final BasketService basketService;
    private final ItemService itemService;
    private final UserService userService;

    @GetMapping("/basket")
    public String list(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser, Model model) {
        List<Basket> baskets = basketService.findBaskets(loginUser);
        model.addAttribute("baskets", baskets);
        return "basket/basketList";
    }

    /**
     * 장바구니 삭제
     */
    @GetMapping("/basket/{basketId}/delete")
    public String deleteBasket(@PathVariable("basketId") Long basketId, Model model){
        Basket basket = basketService.findOne(basketId);
        basketService.delete(basket);
        return "redirect:/basket";
    }

    /**
     * 장바구니 추가
     */
    @GetMapping("/basket/{itemId}/add")
    public String addBasket(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser,
                            @PathVariable("itemId") Long itemId, Model model){
        basketService.addBasket(loginUser.getId(), itemId);
        return "redirect:/basket";
    }
}