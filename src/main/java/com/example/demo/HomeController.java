package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    MessagesRepository messagesRepository;

    @RequestMapping("/")
    public String listMessages(Model model){
        model.addAttribute("messages", messagesRepository.findAll());
        return "list";
    }
    @GetMapping("/add")
    public String messagesForm(Model model){
        model.addAttribute("messages", new Messages());
        return "messagesform";
    }
    @PostMapping("/process")
    public String processForm(@Valid Messages messages, BindingResult result){
        if(result.hasErrors()){
            return "messagesform";
        }
        messagesRepository.save(messages);
        return "redirect:/";
    }
    @RequestMapping("/detail/{id}")
    public String showMessages(@PathVariable("id") long id, Model model){
        model.addAttribute("messages", messagesRepository.findOne(id));
        return "show";
    }
    @RequestMapping("/delete/{id}")
    public String delTweet(@PathVariable("id") long id){
        messagesRepository.delete(id);
        return "redirect:/";
    }

}
