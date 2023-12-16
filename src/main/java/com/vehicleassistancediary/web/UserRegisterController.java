package com.vehicleassistancediary.web;

import com.vehicleassistancediary.model.binding.RegisterCarServiceBindingModel;
import com.vehicleassistancediary.model.binding.RegisterUserBindingModel;
import com.vehicleassistancediary.model.entity.dto.ReCaptchaResponseDto;
import com.vehicleassistancediary.service.ReCaptchaService;
import com.vehicleassistancediary.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/registration")
public class UserRegisterController {
    private final UserService userService;
    private final ReCaptchaService reCaptchaService;

    public UserRegisterController(UserService userService, ReCaptchaService reCaptchaService) {
        this.userService = userService;

        this.reCaptchaService = reCaptchaService;
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/user")
    public String registerConfirm(@Valid RegisterUserBindingModel registerUserBindingModel,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes,
                                  @RequestParam("g-recaptcha-response") String reCaptchaResponse) {

        if (bindingResult.hasErrors() && !registerUserBindingModel.getPassword().equals(registerUserBindingModel.getConfirmPassword())) {
            redirectAttributes.addAttribute("registerUserBindingModel", registerUserBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerUserBindingModel", bindingResult);
            return "redirect:/registration/register";

        }
        boolean isBot = !reCaptchaService.verify(reCaptchaResponse)
                        .map(ReCaptchaResponseDto::isSuccess)
                                .orElse(false);
         if (isBot){
             return "redirect:/";
         }
        userService.registerUser(registerUserBindingModel);

        return "redirect:/users/login";
    }

    @PostMapping("/service")
    public String registerConfirm(@Valid RegisterCarServiceBindingModel registerCarServiceBindingModel,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors() && !registerCarServiceBindingModel.getPassword().equals(registerCarServiceBindingModel.getConfirmPassword())) {
            redirectAttributes.addAttribute("registerCarServiceBindingModel", registerCarServiceBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerCarServiceBindingModel", bindingResult);
            return "redirect:/registration/register";

        }
        userService.registerService(registerCarServiceBindingModel);

        return "redirect:/users/login";
    }

    @ModelAttribute
    public RegisterCarServiceBindingModel registerCarServiceBindingModel() {
        return new RegisterCarServiceBindingModel();
    }

    @ModelAttribute
    public RegisterUserBindingModel registerUserBindingModel() {
        return new RegisterUserBindingModel();
    }
}
