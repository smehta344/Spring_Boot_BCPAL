package com.altimetrik.bcp.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.altimetrik.bcp.dao.UserAccessRepo;
import com.altimetrik.bcp.entity.UserAccess;

@Controller
@Configuration
@EnableWebMvc
@RequestMapping
public class ViewController extends WebMvcConfigurerAdapter{
	
	@Autowired
	UserAccessRepo userAccessRepo;
	
	@RequestMapping("/info")
	public @ResponseBody String userInfo(Authentication authentication) {
		String msg = "";
		for (GrantedAuthority authority : authentication.getAuthorities()) {
		     String role = authority.getAuthority();
		 }
		return msg;
	}
	
	@RequestMapping("/index")
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		return mav;
	}
	@RequestMapping("/dashboard")
	public ModelAndView dashboard() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("dashboard");
		return mav;
	}
	@RequestMapping("/attendance")
	public ModelAndView attendance() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("attendance");
		return mav;
	}
	@RequestMapping("/today_summary")
	public ModelAndView getTodaySummary() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("today_summary");
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
	
	@RequestMapping("/person")
	@ResponseBody
	public ResponseEntity<?> getUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		UserAccess access = userAccessRepo.getUserAccessByUserId(username);
		System.out.println(access);
		Map<String,String> map = new HashMap<>();
		map.put("username",username);
		map.put("isAdmin",access.isAdmin());
		map.put("isManager",access.isManager());
		map.put("haveAccessToSummary",access.haveAccessToAddSummary());
		map.put("haveAccessToFileUpload",access.haveAccessToFileUpload());
		map.put("status",access.getStatus());
		return ResponseEntity.ok().body(map);
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public ModelAndView logoutPage (HttpServletRequest request, HttpServletResponse response) {
	    ModelAndView mav = new ModelAndView();
	    mav.setViewName("redirect:/");
	    return mav;
	}
	
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
                "/webjars/**",
                "/img/**",
                "/css/**",
                "/js/**",
                "/fonts/**")
                .addResourceLocations(
                        "classpath:/META-INF/resources/webjars/",
                        "classpath:/templates/img/",
                        "classpath:/templates/css/",
                        "classpath:/templates/js/",
                        "classpath:/templates/fonts/");
    }
	
	
}
