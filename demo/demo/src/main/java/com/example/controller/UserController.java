package com.example.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.example.DTO.ReservationTicketDTO;
import com.example.DTO.UserUpdateDTO;
import com.example.domain.Cinema;
import com.example.domain.CurrentUser;
import com.example.domain.Hall;
import com.example.domain.MyRole;
import com.example.domain.Projection;
import com.example.domain.Ticket;
import com.example.domain.User;
import com.example.domain.UserCreateForm;
import com.example.domain.VerificationToken;
import com.example.service.CinemaService;
import com.example.service.TicketService;
import com.example.service.UserService;


@RestController
@CrossOrigin(origins = "*")
//@RequestMapping("/public")
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
    
	@Autowired
	private CinemaService cinemaService;
	
	@Autowired
	private TicketService ticketService;

  // @PreAuthorize("hasAuthority('ADMIN')")
  @RequestMapping(
    		value = "/angularUser",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUserPageAngular(@ModelAttribute("currentUser") CurrentUser currentUser) {
    	System.out.println("treba da vrati:" +currentUser);
        return currentUser.getUser();
    }
    
  
  
    @PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
    @RequestMapping("/user/{id}")
    public ModelAndView getUserPage(@PathVariable Long id) {
        LOGGER.debug("User page triggered for user={}", id);
        return new ModelAndView("user", "user", userService.getUserById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("User=%s not found", id))));
    }
    /*jsp
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/user/create", method = RequestMethod.GET)
    public ModelAndView getUserCreatePage() {
        LOGGER.debug("User create form triggered");
        return new ModelAndView("userCreate", "form", new UserCreateForm());
    }*/

 
   // @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(
			value = "/public/user/create",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE
			)
    public User handleUserCreateForm(@Valid  @RequestBody  UserCreateForm form,BindingResult bindingResult,WebRequest  request) {
        LOGGER.debug("Processing user create form={}, bindingResult={}");//, form, bindingResult);
        final User registered;
        if (bindingResult.hasErrors()) {
            // failed validation
            //return "userCreate";
        	return null;
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
            //return "userCreate";
            return null;
        }
           
        // ok, redirect
        return registered;
    }

    @RequestMapping(value = "/public/registrationConfirm", method = RequestMethod.GET)
    public boolean confirmRegistration(final HttpServletRequest request, final Model model, @RequestParam("token") final String token) throws UnsupportedEncodingException {
    	    Locale locale = request.getLocale();    	     
    	    VerificationToken verificationToken = userService.getVerificationToken(token);
    	    if (verificationToken == null) {
    	       // String message = messages.getMessage("auth.message.invalidToken", null, locale);
    	       //model.addAttribute("message", message);
    	    	System.out.println("token nije dobar");
    	        return false;
    	    }
    	     
    	    User user = verificationToken.getUser();
    	    Calendar cal = Calendar.getInstance();
    	    if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
    	        String messageValue = messages.getMessage("auth.message.expired", null, locale);
    	        model.addAttribute("message", messageValue);
    	        return false;
    	    } 
    	     
    	    user.setEnabled(true); 
    	    userService.saveRegisteredUser(user); 
    	    return true;
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
				
				for(MyRole role:u.getRoles()) {
					if (role.getName().equals("USER"))
						users.add(u);
				}
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
				for(MyRole role:u.getRoles()) {
					if (role.getName().equals("FAN_ZONE_ADMIN"))
						fanZoneAdmins.add(u);
				}
				
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
    
	    @RequestMapping(value ="/changeUserInfo", method = RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
//		public @ResponseBody Boolean changeUserInfo(@RequestBody User u,@ModelAttribute("currentUser") CurrentUser currentUser){
		public @ResponseBody Boolean changeUserInfo(@RequestBody UserUpdateDTO u,@ModelAttribute("currentUser") CurrentUser currentUser){
	    	ModelMapper mp=new ModelMapper();
			
	    	
	    	
			System.out.println("POGODJEN CONTROLLER /changeUserInfo"+mp.map(u,UserUpdateDTO.class));
			try {
				
				 //userService.updateUserInfo(mp.map(u,UserUpdateDTO.class),currentUser.getUser().getId());
				userService.updateUserInfo(u,currentUser.getUser().getId());
				
			} catch (Exception e) {
				
				e.printStackTrace();
				return false;
			}
			
			return true;
		}
	    

		@CrossOrigin(origins = "*")
		@RequestMapping(
				value = "/user/getAllTickets",
				method = RequestMethod.GET,						
				produces = MediaType.APPLICATION_JSON_VALUE
				)
		public List<ReservationTicketDTO> getAllTicketsForUser(@ModelAttribute("currentUser") CurrentUser currentUser) {
				System.out.println("** mida ");
			List<ReservationTicketDTO> reservedTickets=new ArrayList<ReservationTicketDTO>();
			List<Ticket> userTickets= ticketService.getTicketByUser(currentUser.getId());

			List<Cinema> allCinemas=cinemaService.getAll();
			
			for(Ticket ticket : userTickets) {
				
				for(Cinema currentCinema : allCinemas) {
				
					for(Hall currentHall:currentCinema.getHalls()) {
					
						for(Projection currentProjection:currentHall.getProjections()) {
						
							for(Ticket currentTicket:currentProjection.getTickets()) {
							
								if(currentTicket.getId().equals(ticket.getId())) {
									ReservationTicketDTO ticketDTO=new ReservationTicketDTO();
									ticketDTO.setCinema(currentCinema.getName());
									ticketDTO.setMovie(currentProjection.getFilm().getName());
									ticketDTO.setTicket_id(ticket.getId());
									ticketDTO.setDate(currentProjection.getDate());
									ticketDTO.setTime(currentProjection.getTime());
									Date today = new Date();			
									Date date=ticketService.convertDateFromString(currentProjection.getDate());
									if(date.before(today)){
										ticketDTO.setVisited(true);	
									}else if(date.equals(today)) {									
											Calendar now =Calendar.getInstance();
											int currentHour=now.get(Calendar.HOUR_OF_DAY);
											int currentMinute=now.get(Calendar.MINUTE);
											String timeParsedProjection[]=currentProjection.getTime().split(":");
											if(Integer.parseInt(timeParsedProjection[0])*60+Integer.parseInt(timeParsedProjection[1]) +30<currentHour*60+currentMinute) {
												ticketDTO.setVisited(false);	
											}else {
												ticketDTO.setVisited(true);	
											}
											ticketDTO.setVisited(true);	
									}else {
										ticketDTO.setVisited(false);	

									}
									reservedTickets.add(ticketDTO);
								}
								
							}
						}
					}
				}
			}
			
			return reservedTickets;
		}
		
		@CrossOrigin(origins = "*")
		@RequestMapping(
				value = "/user/cancleReservation/{id}",
				method = RequestMethod.GET,						
				produces = MediaType.APPLICATION_JSON_VALUE)
		public boolean cancleReservation(@ModelAttribute("currentUser") CurrentUser currentUser,@PathVariable Long id) {

			return ticketService.cancleTicket(id);

		}
		
}
