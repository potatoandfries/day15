package vttpday15.vttpday15.repositories;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import vttpday15.vttpday15.Utils;
import vttpday15.vttpday15.models.Item;

@Repository
public class CartRepository {

    @Autowired @Qualifier(Utils.BEAN_REDIS) // telling spring to look for the bean
	private RedisTemplate<String, String> template;

    public boolean hasCart(String name){
        return template.hasKey(name);
    }

    public List<Item> loadCart(String name){
        ListOperations<String, String> opsList = template.opsForList();
        Long size = opsList.size(name);
        List<String> items = opsList.range(name, 0, size);
        List<Item> cart = new LinkedList<>();
        for (String i: items){
            Item item = new Item();
            item.setName(i.split("-")[Utils.F_NAME].trim());
            item.setQuantity(Integer.parseInt(i.split("-")[Utils.F_QUANTITY].trim()));
            cart.add(item);
        }

        return cart;
    }

    public void deleteCart(String name){
        if (template.hasKey(name)){
            template.delete(name);
        }
    }

    public void saveCart(String name, List<Item> cart){
        ListOperations<String, String> opsList = template.opsForList();
        for (Item item : cart){
            opsList.leftPush(name, "%s-%d".formatted(item.getName(), item.getQuantity()));
        }
    }
}
