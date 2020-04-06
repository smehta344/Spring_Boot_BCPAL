package com.altimetrik.bcp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Controller
@Configuration
@EnableWebMvc
@RequestMapping
public class ViewController extends WebMvcConfigurerAdapter{
	@RequestMapping("/info")
	public @ResponseBody String userInfo(Authentication authentication) {
		String msg = "";
		for (GrantedAuthority authority : authentication.getAuthorities()) {
		     String role = authority.getAuthority();
             System.out.println("controlererere hi--------"+authority.getAuthority());
		 }
		return msg;
	}
	
	@RequestMapping("/index")
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView();
		System.out.println("index view called....");
		/*for (GrantedAuthority authority : authentication.getAuthorities()) {
		     String role = authority.getAuthority();
            System.out.println("controlererere hi--------"+authority.getAuthority());
		 }*/
		mav.setViewName("index");
		return mav;
	}
	@RequestMapping("/dashboard")
	public ModelAndView dashboard() {
		ModelAndView mav = new ModelAndView();
		System.out.println("dashboard view called....");
		mav.setViewName("dashboard");
		return mav;
	}
	@RequestMapping("/attendance")
	public ModelAndView attendance() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("attendance");
		return mav;
	}
	@RequestMapping("/delivery")
	public ModelAndView delivery() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("delivery");
		return mav;
	}
	@RequestMapping("/contact")
	public ModelAndView contact() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("contact");
		return mav;
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public ModelAndView logoutPage (HttpServletRequest request, HttpServletResponse response) {
		System.out.println("--------Logout--->");
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    ModelAndView mav = new ModelAndView();
	    mav.setViewName("redirect:/");
	    return mav;//You can redirect wherever you want, but generally it's a good practice to show login screen again.
	}
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
                "/webjars/**",
                "/img/**",
                "/css/**",
                "/js/**")
                .addResourceLocations(
                        "classpath:/META-INF/resources/webjars/",
                        "classpath:/templates/img/",
                        "classpath:/templates/css/",
                        "classpath:/templates/js/");
    }
	
	
}
