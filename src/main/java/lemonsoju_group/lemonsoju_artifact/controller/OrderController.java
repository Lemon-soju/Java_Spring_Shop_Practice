package lemonsoju_group.lemonsoju_artifact.controller;

import lemonsoju_group.lemonsoju_artifact.domain.Item;
import lemonsoju_group.lemonsoju_artifact.domain.Order;
import lemonsoju_group.lemonsoju_artifact.domain.User;
import lemonsoju_group.lemonsoju_artifact.repository.OrderSearch;
import lemonsoju_group.lemonsoju_artifact.service.ItemService;
import lemonsoju_group.lemonsoju_artifact.service.OrderService;
import lemonsoju_group.lemonsoju_artifact.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;
    private final ItemService itemService;

    @GetMapping("/order")
    public String createForm(Model model) {

        List<User> users = userService.findUsers();
        List<Item> items = itemService.findItems();

        model.addAttribute("users", users);
        model.addAttribute("items",items);

        return "order/orderForm";
    }

    @PostMapping("/order")
    public String order(@RequestParam("userId") Long userId,
                        @RequestParam("itemId") Long itemId,
                        @RequestParam("count") int count) {

        orderService.order(userId, itemId, count);

        return "redirect:/orders";
    }

    @GetMapping("/orders")
    public String orderList(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model){
        List<Order> orders = orderService.findOrders(orderSearch);
        model.addAttribute("orders", orders);

        return "order/orderList";
    }

    @PostMapping("/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable("orderId") Long orderId){
        orderService.cancleOrder(orderId);
        return "redirect:/orders";
    }

}

