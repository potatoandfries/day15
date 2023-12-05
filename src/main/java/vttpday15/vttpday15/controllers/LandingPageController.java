package vttpday15.vttpday15.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import vttpday15.vttpday15.Utils;
import vttpday15.vttpday15.models.Item;

// @Controller
@RequestMapping(path= {"/",  "/index.html"})
public class LandingPageController {
    
    @Autowired @Qualifier("myredis") // telling spring to look for the bean
	private RedisTemplate<String, String> template;

    // @GetMapping
    public String getIndex(Model m, HttpSession session){
        List<Item> cart = Utils.getCart(session);
        m.addAttribute("item", new Item());
        m.addAttribute("cart", cart);
        return "index";
    }
}
