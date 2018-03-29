package com.example.controller;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Locale;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.example.OnRegistrationCompleteEvent;
import com.example.RegistrationListener;
import com.example.domain.User;
import com.example.domain.UserCreateForm;
import com.example.domain.VerificationToken;
import com.example.service.UserService;
@Controller
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    
    @Autowired
    private RegistrationListener rg;
    
    @Autowired
    private UserService userService;

    @Autowired
    ApplicationEventPublisher eventPublisher;
    
    @Autowired
    private MessageSource messages;

    
    @PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
    @RequestMapping("/user/{id}")
    public ModelAndView getUserPage(@PathVariable Long id) {
        LOGGER.debug("User page triggered for user={}", id);
        return new ModelAndView("user", "user", userService.getUserById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("User=%s not found", id))));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/user/create", method = RequestMethod.GET)
    public ModelAndView getUserCreatePage() {
        LOGGER.debug("User create form triggered");
        return new ModelAndView("userCreate", "form", new UserCreateForm());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public String handleUserCreateForm(@Valid @ModelAttribute("form") UserCreateForm form, BindingResult bindingResult,WebRequest  request) {
        LOGGER.debug("Processing user create form={}, bindingResult={}", form, bindingResult);
        final User registered;
        if (bindingResult.hasErrors()) {
            // failed validation
            return "userCreate";
        }
        try {
        	registered=userService.create(form);        
            String appUrl = request.getContextPath();
           // OnRegistrationCompleteEvent tokenKreiraj=new OnRegistrationCompleteEvent(registered, request.getLocale(), appUrl);
            //rg.confirmRegistration(tokenKreiraj);
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), appUrl));
            System.out.println("pozvao se publisher");
            
        } catch (DataIntegrityViolationException e) {
            //email exists
            LOGGER.warn("Duplicate email.", e);
            return "userCreate";
        }
           
        // ok, redirect
        return "redirect:/users";
    }

    @RequestMapping(value = "/public/registrationConfirm", method = RequestMethod.GET)
    public String confirmRegistration(final HttpServletRequest request, final Model model, @RequestParam("token") final String token) throws UnsupportedEncodingException {
    	    Locale locale = request.getLocale();    	     
    	    VerificationToken verificationToken = userService.getVerificationToken(token);
    	    if (verificationToken == null) {
    	        String message = messages.getMessage("auth.message.invalidToken", null, locale);
    	        model.addAttribute("message", message);
    	        return "redirect:/badUser.html?lang=" + locale.getLanguage();
    	    }
    	     
    	    User user = verificationToken.getUser();
    	    Calendar cal = Calendar.getInstance();
    	    if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
    	        String messageValue = messages.getMessage("auth.message.expired", null, locale);
    	        model.addAttribute("message", messageValue);
    	        return "redirect:/badUser.html?lang=" + locale.getLanguage();
    	    } 
    	     
    	    user.setEnabled(true); 
    	    userService.saveRegisteredUser(user); 
    	    return "redirect:/login";
    	    //return "redirect:/login.html?lang=" + request.getLocale().getLanguage(); 
    	}
    
}
