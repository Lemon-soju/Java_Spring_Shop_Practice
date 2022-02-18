package lemonsoju_group.lemonsoju_artifact.controller;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemForm {

    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

}
