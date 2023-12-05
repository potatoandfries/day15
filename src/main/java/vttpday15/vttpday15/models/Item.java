package vttpday15.vttpday15.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public class Item {

    // Generally they don't hardcode the messages since there are different languages, there is usually a message file
    @NotEmpty(message = "Please enter the name of the item!")
    private String name;

    @Min(value = 1, message = "You must order at least 1!")
    @Max(value = 10, message = "You cannot order more than 10!")
    private Integer quantity;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Item [name=" + name + ", quantity=" + quantity + "]";
    }
    
    
}
