package com.lgdb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2016-12-05.
 */
@Controller
public class AdminController {

    @RequestMapping(method = RequestMethod.GET, path = "/admin/")
    public ModelAndView adminPage() {
        return new ModelAndView("adminPage");
    }

}
