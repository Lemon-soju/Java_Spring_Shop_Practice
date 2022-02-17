package lemonsoju_group.lemonsoju_artifact.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class User {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;
    private String uid;
    private String pwd;
    private String name;
    private String point;
    private String rank;

    @OneToMany(mappedBy = "user") // 연관관계에서 slave로 맵핑됨
    private List<Order> orders = new ArrayList<>();

    @OneToOne(mappedBy = "user")
    private Basket basket;
}
