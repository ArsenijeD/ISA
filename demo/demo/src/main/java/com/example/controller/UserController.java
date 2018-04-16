package com.example.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.example.OnRegistrationCompleteEvent;
import com.example.RegistrationListener;
import com.example.domain.Cinema;
import com.example.domain.MyRole;
import com.example.domain.User;
import com.example.domain.UserCreateForm;
import com.example.domain.VerificationToken;
import com.example.repository.UserRepository;
import com.example.service.UserService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/public")
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
    @RequestMapping(value = "	", method = RequestMethod.GET)
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
    
//	    @RequestMapping(
//				value = "/getUserAdmins/{admins}", 
//				method = RequestMethod.GET, 
//				produces = MediaType.APPLICATION_JSON_VALUE,
//				consumes = MediaType.APPLICATION_JSON_VALUE)
//		public Set<User> getCinemaAdmins(@PathVariable("admins") Set<Long> ids) {
//			System.out.println("pogodjen getUserAdmins");
//			return userService.getUsersByIdIn(ids);
//	
//		}
    
	    @RequestMapping(
				value = "/getOnlyUsers", 
				method = RequestMethod.GET, 
				produces = MediaType.APPLICATION_JSON_VALUE)
		public List<User> getUsersOnly() {
			
	    	System.out.println("pogodjen /getOnlyUsers");
			List<User> all = userService.getAll();
			List<User> users = new ArrayList<User>();
			
			for (User u : all) {
				
				if (u.getRole().getName().equals("user"))
					users.add(u);
			}
			
			return users;
			
		}
	    
	    @RequestMapping(
				value = "/getFanZoneAdmins", 
				method = RequestMethod.GET, 
				produces = MediaType.APPLICATION_JSON_VALUE)
		public List<User> getFanZoneAdmins() {
			
	    	System.out.println("pogodjen /getFanZoneAdmins");
			List<User> all = userService.getAll();
			List<User> fanZoneAdmins = new ArrayList<User>();
			
			for (User u : all) {
				
				if (u.getRole().getName().equals("fan_zone_admin"))
					fanZoneAdmins.add(u);
			}
			
			return fanZoneAdmins;
			
		}
	    
	    @RequestMapping(value = "/changeUserRole", method = RequestMethod.PUT)
		public @ResponseBody Boolean changeUserRole(@RequestBody User u){
		 
			
			System.out.println("POGODJEN CONTROLLER /changeUserRole");
			try {
				
				userService.updateUserRole(u);
				
			} catch (Exception e) {
				
				e.printStackTrace();
				return false;
			}
			
			return true;
		}
    
}
