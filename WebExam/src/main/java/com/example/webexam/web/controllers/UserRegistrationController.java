package com.example.webexam.web.controllers;

import com.example.webexam.model.dtos.UserRegistrationDTO;
import com.example.webexam.repository.UserRepository;
import com.example.webexam.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class UserRegistrationController {

    private final UserService userService;
    private final SecurityContextRepository securityContextRepository;
    private final UserRepository userRepository;

    public UserRegistrationController(UserService userService,
                                      SecurityContextRepository securityContextRepository, UserRepository userRepository) {
        this.userService = userService;
        this.securityContextRepository = securityContextRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/users/register")
    public String renderRegisterPage() {
        return "auth-register";
    }

    @PostMapping("/users/register")
    public String handleRegisterUserForm(UserRegistrationDTO registrationDTO,
                                  RedirectAttributes redirectAttributes,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {

        //Check if first and last name are empty
        if (registrationDTO.getFirstName().isBlank() || registrationDTO.getLastName().isBlank()) {
            redirectAttributes.addFlashAttribute("namesAreEmpty", true);
            return "redirect:/users/register";
        }

        //Check if email exists
        if (userRepository.findUserEntityByEmail(registrationDTO.getEmail()).isPresent()) {
            redirectAttributes.addFlashAttribute("userExists", true);
            return "redirect:/users/register";
        }

        if (!validateEmail(registrationDTO.getEmail())) {
            redirectAttributes.addFlashAttribute("invalidEmail", true);
            return "redirect:/users/register";
        }

        //Password is less than 5 or more than 20 symbols
        if (registrationDTO.getPassword().length() < 5 || registrationDTO.getPassword().length() > 20) {
            redirectAttributes.addFlashAttribute("passwordIsInvalid", true);
            return "redirect:/users/register";
        }

//        userService.registerUser(registrationDTO, successfulAuth -> {
//            SecurityContextHolderStrategy strategy = SecurityContextHolder.getContextHolderStrategy();
//
//            SecurityContext context = strategy.createEmptyContext();
//            context.setAuthentication(successfulAuth);
//            strategy.setContext(context);
//            securityContextRepository.saveContext(context, request, response);
//        });

        userService.registerUser(registrationDTO);

        return "redirect:/";
    }

    private boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
}
