package vttpday15.vttpday15.controllers;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import vttpday15.vttpday15.Utils;
import vttpday15.vttpday15.models.Item;
import vttpday15.vttpday15.services.CartService;

@Controller
@RequestMapping(path = "/cart") //try not to use verbs in resource naming
public class CartController {
    private Logger logger = Logger.getLogger(CartController.class.getName());

    @Autowired
    CartService cartSvc;

    @GetMapping
    public ModelAndView getCart(@RequestParam String name, HttpSession session){
        ModelAndView mav = new ModelAndView("cart");
        List<Item> cart = cartSvc.getCart(name);

        logger.info("Cart: %s - %s\n".formatted(name, cart));

        mav.addObject(Utils.ATTR_ITEM, new Item());
        mav.addObject(Utils.ATTR_CART, cart);
        session.setAttribute(Utils.ATTR_NAME, name);
        session.setAttribute(Utils.ATTR_CART, cart);
        mav.setStatus(HttpStatusCode.valueOf(200));

        return mav;
    }

    @PostMapping(path = "/checkout")
    public String postCartCheckout(HttpSession session){
        List<Item> cart = Utils.getCart(session);
        System.out.printf("Checking out cart: %s\n", cart);

        String name = (String) session.getAttribute(Utils.ATTR_NAME);
        cartSvc.saveCart(name, cart);
        session.invalidate();
        
        return "redirect:/";
    }
    
    @PostMapping
    public ModelAndView postCart(@Valid @ModelAttribute Item item, BindingResult bindings, HttpSession session){


        System.out.printf("item: %s\n", item);
        System.out.printf("error: %b\n", bindings.hasErrors());
        ModelAndView mav = new ModelAndView("cart");
        if (bindings.hasErrors()){
            mav.setStatus(HttpStatusCode.valueOf(400));
            return mav;
        }

        List<Item> cart = Utils.getCart(session);
        cart.add(item);
        mav.addObject(Utils.ATTR_ITEM, new Item());
        mav.addObject(Utils.ATTR_CART, cart);
        mav.setStatus(HttpStatusCode.valueOf(200));
        return mav;
    }
}
