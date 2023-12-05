package vttpday15.vttpday15;

import java.util.LinkedList;
import java.util.List;

import jakarta.servlet.http.HttpSession;
import vttpday15.vttpday15.models.Item;

public class Utils {
    public static final String ATTR_ITEM = "item";
    public static final String ATTR_CART = "cart";
    public static final String ATTR_NAME = "name";
    public static final String BEAN_REDIS = "myredis";
    
    public static final Integer F_NAME = 0;
    public static final Integer F_QUANTITY = 1;

    public static List<Item> getCart(HttpSession session){
        List<Item> cart = (List<Item>) session.getAttribute(ATTR_CART);
        if (null == cart){
            cart = new LinkedList<>();
            session.setAttribute(ATTR_CART, cart);
        }

        return cart;
    }
}
