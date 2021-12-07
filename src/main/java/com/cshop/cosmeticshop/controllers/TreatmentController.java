package com.cshop.cosmeticshop.controllers;

import com.cshop.cosmeticshop.domain.intity.Order;
import com.cshop.cosmeticshop.domain.intity.Cart;
import com.cshop.cosmeticshop.exception.TreatmentNotFoundException;
import com.cshop.cosmeticshop.service.CartService;
import com.cshop.cosmeticshop.service.TreatmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author:Pave1Pal
 * Controller for displaying treatments and making appointment
 */
@Controller
@Slf4j
@RequestMapping("/treatment")
@SessionAttributes({"treatment_order", "treatment_cart"})
@RequiredArgsConstructor
public class TreatmentController {

    private final TreatmentService treatmentService;
    private final CartService cartService;

    /**
     * method return Cart object in model which use in view
     */
    @ModelAttribute("treatment_cart")
    public Cart getTreatmentCart() {
        return new Cart();
    }

    /**
     * Get method return page with treatments and also returning page provide opportunity to start an appointment
     **/
    @GetMapping
    public String showTreatments(Model model, Order order,
                                 @PageableDefault(sort = {"price"}, direction = Sort.Direction.ASC) Pageable pageable) {
       var page = treatmentService.findAll(pageable);
       model.addAttribute("treatments", page);
       model.addAttribute("treatment_order", order);
        return "/treatments";
    }

    /**
     *Get method return page with treatment info using its id
     */
    @GetMapping(path = "{id}")
    public String getTreatment(@PathVariable("id") Long id,
                               Model model) throws TreatmentNotFoundException {
        var treatment = treatmentService.findById(id);
        model.addAttribute("treatment", treatment);
        return "treatment";
    }

    /**
     * Post method handle a form of cart with treatments and throw you to OrderController - /appointment-order.
     * If cart with treatments is empty method return you to /treatments
     * If validation is successful method calculate total price of cart with treatments
     **/
    @PostMapping
    public String appointmentProcess(@Valid @ModelAttribute("treatment_cart") Cart treatmentCart,
                                     @ModelAttribute("treatment_order") Order serviceOrder,
                                     Errors errors) {
        if (errors.hasErrors()) {
            return "/treatments";
        }
        cartService.calculatePrice(treatmentCart);
        return "redirect:/appointment-order";
    }
}
