package lemonsoju_group.lemonsoju_artifact.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {
    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id") // 외래키 가져오기
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    private OrderStatus status; // 주문상태 [ORDER, CANCEL]

    //== 연관관계 메서드 ==//
    public void setUser(User user){
        this.user = user; // User를 Order에 저장
        user.getOrders().add(this); // Order를 User에 저장 // ex) order.setUser(user) 한 줄로 양쪽 모두 저장 가능
    }

    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem); // OrderItem를 Order에 저장
        orderItem.setOrder(this); // Order를 orderItem에 저장
    }
}


