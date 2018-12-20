package com.amycohen.userauth.controllers;

import com.amycohen.userauth.model.ApplicationUser;
import com.amycohen.userauth.repositories.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;

@Controller
public class ApplicationUserController {

    @Autowired
    ApplicationUserRepository appUserRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView homepage(ModelAndView mv) {
        mv.setViewName("index");
        return mv;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView goToSignUpPage(Principal p, Model m, ModelAndView mv) {
        System.out.println(p);
        ApplicationUser user = new ApplicationUser();
        m.addAttribute("user", user);
        mv.setViewName("signup");
        return mv;
    }


    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public RedirectView signUpNewUser(@RequestParam("first-name") String firstname,
                                        @RequestParam("last-name") String lastname,
                                        @RequestParam("date-of-birth") String dateOfBirth,
                                      @RequestParam("username") String username,
                                      @RequestParam("password") String password,
                                        @RequestParam("bio") String bio,
                                      ModelAndView mv) {

        password = bCryptPasswordEncoder.encode(password);
        ApplicationUser newUser = new ApplicationUser(username, password, firstname, lastname, dateOfBirth, bio);
        appUserRepo.save(newUser);
        System.out.println("New User Added: \n" + newUser);

        // maybe autologin?  FROM Cheatsheet.md
        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new RedirectView("/profile");
    }
    @RequestMapping(value="/profile", method = RequestMethod.GET)
    public ModelAndView viewProfilePage (ModelAndView mv, Model m, Principal p) {
        ApplicationUser user = appUserRepo.findByUsername(p.getName());
        m.addAttribute("user", user);
        mv.setViewName("profile");
        return mv;
    }

    @RequestMapping(value="/userinfo", method = RequestMethod.GET)
    public ModelAndView viewOneUserHTML (ModelAndView mv) {
        mv.setViewName("userInfo");
        return mv;
    }

    @RequestMapping(value="/user/{id}", method = RequestMethod.GET)
    public ModelAndView viewOneUser (ModelAndView mv) {
        mv.setViewName("userInfo");
        return mv;
    }
}
