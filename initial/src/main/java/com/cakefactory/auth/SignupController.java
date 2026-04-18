package com.cakefactory.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/signup")
class SignupController {

    private final SignupService signupService;

    public SignupController(SignupService signupService) {
        this.signupService = signupService;
    }

    @GetMapping
    String signup(Model model) {
        model.addAttribute("basketTotal", 0);
        return "signup";
    }

    // @GetMapping
    // String signup() {
    // return "signup";
    // }

    @PostMapping
    String signup(String email, String password, String addressLine1, String addressLine2, String postcode) {
        if (this.signupService.accountExists(email)) {
            return "redirect:/login";
        }

        this.signupService.register(email, password, addressLine1, addressLine2, postcode);
        return "redirect:/";
    }
}
