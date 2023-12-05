package vttpday15.vttpday15.services;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttpday15.vttpday15.models.Item;
import vttpday15.vttpday15.repositories.CartRepository;

@Service
public class CartService {
    
    @Autowired
    private CartRepository cartRepo;

    public List<Item> getCart(String name){
        if (cartRepo.hasCart(name)){
            return cartRepo.loadCart(name);
        }

        return new LinkedList<>();
    }

    public void saveCart(String name, List<Item> cart){
        cartRepo.deleteCart(name);
        cartRepo.saveCart(name, cart);
    }
}
