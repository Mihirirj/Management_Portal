package com.it.dashboard.ajax;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.it.dashboard.ajax.Contact;

@Controller
@SessionAttributes
@RequestMapping(value = "/ajax")
public class CustomerController {

       
       
       /**
        * Will show list of customers from table 'customers'
        * @param cust
        * @param uiModel
        * @return
        */
       @RequestMapping("list")
       public String listCustomers(@ModelAttribute Customer cust, Model uiModel) {
              List<Customer> custList = getCustomerList();
              uiModel.addAttribute("list", custList);
              return "customerlist";
       }

      

       
       
       private List<Customer> getCustomerList()
       {
    	   Customer c1 = new Customer();
    	   c1.setCustId("001");
    	   c1.setCompName("ABC");
    	   c1.setContactName("Amal");
    	   
    	   Customer c2 = new Customer();
    	   c2.setCustId("002");
    	   c2.setCompName("XYZ");
    	   c2.setContactName("Perera");
    	   
    	   Customer c3 = new Customer();
    	   c3.setCustId("003");
    	   c3.setCompName("Otek");
    	   c3.setContactName("Silva");
    	   
    	   List<Customer> cusList = new ArrayList<Customer>();
    	   cusList.add(c1);
    	   cusList.add(c2);
    	   cusList.add(c3);
    	   
    	   return cusList;
       }
}