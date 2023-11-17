package com.vehicleassistancediary.web;

import com.vehicleassistancediary.model.binding.RegisterCarServiceBindingModel;
import com.vehicleassistancediary.model.binding.RegisterUserBindingModel;
import com.vehicleassistancediary.service.ServiceService;
import com.vehicleassistancediary.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/registration")
public class CarServiceRegisterController {
    private final ServiceService serviceService;

        public CarServiceRegisterController( ServiceService serviceService) {

            this.serviceService = serviceService;
        }

//        @GetMapping("/register")
//        public String register(){
//            return "register";
//        }
//
//        @PostMapping("/register/service")
//        public String registerConfirm(@Valid RegisterCarServiceBindingModel registerCarServiceBindingModel,
//                                      BindingResult bindingResult, RedirectAttributes redirectAttributes){
//            if(bindingResult.hasErrors() && !registerCarServiceBindingModel.getPassword().equals(registerCarServiceBindingModel.getConfirmPassword())){
//                redirectAttributes.addAttribute("registerCarServiceBindingModel", registerCarServiceBindingModel);
//                redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerCarServiceBindingModel", bindingResult);
//                return "redirect:/register/service";
//
//            }
//            serviceService.registerService(registerCarServiceBindingModel);
//
//            return "redirect:/users/login";
//        }
//        @ModelAttribute
//        public RegisterCarServiceBindingModel registerCarServiceBindingModel(){
//            return new RegisterCarServiceBindingModel();
//        }
}
