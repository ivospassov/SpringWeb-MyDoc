package com.example.webexam.web;

import com.example.webexam.model.dtos.MessageDTO;
import com.example.webexam.service.contracts.ContactService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ContactsController {

    private final ContactService contactsService;

    public ContactsController(ContactService contactsService) {
        this.contactsService = contactsService;
    }

    @ModelAttribute("messageDTO")
    public MessageDTO messageDTO() {
        return new MessageDTO();
    }

    @GetMapping("/contacts")
    public String renderContactsPage() {
        return "contacts";
    }

    @PostMapping("/contacts/message/post")
    public String handleMessageForm(@Valid MessageDTO messageDTO,
                                    RedirectAttributes redirectAttributes) {

        if (messageDTO.getMessage().length() == 0 || messageDTO.getCity().equals("Empty")) {
            redirectAttributes
                    .addFlashAttribute("isEmpty", false);

            return "redirect:/contacts";
        }

        this.contactsService.sendMessage(messageDTO.getMessage());

        return "redirect:/";
    }
}
