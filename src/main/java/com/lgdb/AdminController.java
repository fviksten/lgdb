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

    //# # # ADMIN - PAGE # # #
    @RequestMapping(method = RequestMethod.GET, path = "/admin")
    public ModelAndView adminPage() {
        return new ModelAndView("adminPage");
    }
    //# # # END ADMIN - PAGE # # #

    // ### ADDCOUNTRY ###
    @GetMapping("/admin/addCountry")
    public String countryPage(Model model) {
        model.addAttribute("country", new Country());
        model.addAttribute("Countries", adminRepository.getCountries());
        return "addCountry";
    }//### END ADDCOUNTRY ###

    //### save Country ###
    @PostMapping("/admin/saveCountry")
    public String saveCountry(@ModelAttribute Country country, Model model) {
        adminRepository.saveCountry(country);
        model.addAttribute("Countries", adminRepository.getCountries());
        return "addCountry";
    }//### END save Country ###

    //### edit Country ###
    @RequestMapping(method = RequestMethod.GET, path = "/admin/editCountry/{id}")
    public String editCountry(@PathVariable int id, Model model) {
        model.addAttribute("country", adminRepository.getCountry(id));
        return "editCountry";
    }//### END editCountry ###

    @PostMapping("/admin/alterCountry")
    public String alterCountry(@ModelAttribute Country country) {
        System.out.println("Alter Country(id): " + country.getId() + " " + country.getName());
        adminRepository.alterCountry(country);
        return "redirect:/admin/addCountry";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/admin/deleteCountry/{id}")
    public String deleteCountry(@PathVariable int id) {
        System.out.println("Deleted Country(id): " + id);
        adminRepository.deleteCountry(id);
        return "redirect:/admin/addCountry";
    }

    //#########################
    //# # # END Countries # # #

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
