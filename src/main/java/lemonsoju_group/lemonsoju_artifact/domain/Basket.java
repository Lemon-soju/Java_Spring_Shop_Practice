package lemonsoju_group.lemonsoju_artifact.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Setter @Getter
public class Basket {

    @Id @GeneratedValue
    @Column(name = "basket_id")
    private Long id;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private int basketPrice;
    private int count;

    //== 연관관계 메서드 ==//

    public void setUser(User user){
        this.user = user; // User를 Basket에 저장
        user.setBasket(this); // Basket을 User에 저장
    }

    //== 생성 메서드 ==//
    public static Basket createBasket(User user, Item item, int price, int count){
        Basket basket = new Basket();
        basket.setUser(user);
        basket.setItem(item);
        basket.setBasketPrice(price);
        basket.setCount(count);
        return basket;
    }


}
