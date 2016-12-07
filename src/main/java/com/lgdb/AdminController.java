package com.lgdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.text.ParseException;

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
    //END Countries

    //Visa addCompany
    @GetMapping("/admin/addCompany")
    public String addCompanyForm(Model model){
        model.addAttribute("company", new Company());
        model.addAttribute("Countries", adminRepository.getCountries());
        return "addCompany";
    }

    @PostMapping("/admin/saveCompany")
    public ModelAndView addCompanySubmit(@ModelAttribute Company company){

        if(company.getDefund()!=""){
            try {
                company.setDateDefund();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        adminRepository.saveCompany(company);

        return new ModelAndView("addCompany").addObject("Countries", adminRepository.getCountries());
    }





}
