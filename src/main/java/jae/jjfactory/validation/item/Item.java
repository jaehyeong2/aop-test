package jae.jjfactory.validation.item;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Item {
    private Long id;

    @NotNull
    private String itemName;

    @NotNull
    private Integer price;

    @NotNull
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}

