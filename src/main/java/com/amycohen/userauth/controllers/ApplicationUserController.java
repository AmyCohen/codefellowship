package com.amycohen.userauth.controllers;

import com.amycohen.userauth.model.ApplicationUser;
import com.amycohen.userauth.repositories.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

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
    public ModelAndView goToSignUp(ModelAndView mv) {
        mv.setViewName("signup");
        return mv;
    }


    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public RedirectView signUpNewUser(@RequestParam("username") String username,
                                        @RequestParam("first-name") String firstname,
                                        @RequestParam("last-name") String lastname,
                                        @RequestParam("password") String password,
                                        @RequestParam("date-of-birth") String dateOfBirth,
                                        @RequestParam("bio") String bio) {

        password = bCryptPasswordEncoder.encode(password);
        ApplicationUser newUser = new ApplicationUser(username, firstname, lastname, password, dateOfBirth, bio);
        appUserRepo.save(newUser);
        System.out.println("New User Added: \n" + newUser);
        return new RedirectView("/");
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
