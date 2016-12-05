package com.lgdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2016-12-05.
 */
@Controller
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @RequestMapping(method = RequestMethod.GET, path = "/admin/")
    public ModelAndView adminPage() {
        return new ModelAndView("adminPage");
    }

    //Visa admin-sidan
    @RequestMapping(method = RequestMethod.GET, path = "/addCountry")
    public ModelAndView countryPage() {
        return new ModelAndView("addCountry").addObject("Countries", adminRepository.getCountries());
    }

    @RequestMapping(method = {RequestMethod.POST}, path = "/saveCountry")
    public String saveCountry(@RequestParam String countryName) {
        adminRepository.saveCountry(countryName);
        return "redirect:/addCountry";
    }


}
