package com.it.dashboard.issue.controller;

import java.util.ArrayList;
import java.util.List;







import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.it.dashboard.issue.domain.Contact;
import com.it.dashboard.issue.domain.Contact1;
import com.it.dashboard.issue.domain.ContactForm;
import com.it.dashboard.issue.domain.ContactForm1;

@Controller
@RequestMapping(value = "/issue")
public class ContactController {

	
	/*private static List<Contact> contacts = new ArrayList<Contact>();

	static {
		contacts.add(new Contact("Barack", "Obama", "barack.o@whitehouse.com", "147-852-965"));
		contacts.add(new Contact("George", "Bush", "george.b@whitehouse.com", "785-985-652"));
		contacts.add(new Contact("Bill", "Clinton", "bill.c@whitehouse.com", "236-587-412"));
		contacts.add(new Contact("Ronald", "Reagan", "ronald.r@whitehouse.com", "369-852-452"));
	}*/
	
	
	
	/*@RequestMapping(value = "/addContact", method = RequestMethod.POST)
	public ModelAndView save1(@ModelAttribute("contactForm") ContactForm contactForm) {
		//System.out.println(contactForm.getStudent().getFirstName());
		System.out.println(contactForm.getContacts());
		//List<Contact> contacts = contactForm.getContacts();
		
		if(null != contacts && contacts.size() > 0) {
			//ContactController.contacts = contacts;
			for (Contact1 contact : contacts) {
				System.out.printf("%s \t %s \n", contact, contact.getAcCd());
			}
		}
		
		return new ModelAndView("/issue/add_contact", "contactForm", contactForm);
	}*/
	
	@RequestMapping(value = "/addContact1", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("contactForm") ContactForm contactForm) {
		System.out.println(contactForm);
		System.out.println(contactForm.getContacts());
		List<Contact> contacts = contactForm.getContacts();
		
		if(null != contacts && contacts.size() > 0) {
			//ContactController.contacts = contacts;
			for (Contact contact : contacts) {
				System.out.printf("%s \t %s \n", contact.getPhone(), contact.getLastname());
			}
		}
		
		return new ModelAndView("issue/add_contact", "contactForm", contactForm);
	}
	
	@RequestMapping(value = "/addContact", method = RequestMethod.POST)
	public ModelAndView savePiv(@ModelAttribute("contactForm") ContactForm1 contactForm) {
		System.out.println(contactForm);
		System.out.println(contactForm.getContacts());
		List<Contact1> contacts = contactForm.getContacts();
		
		if(null != contacts && contacts.size() > 0) {
			//ContactController.contacts = contacts;
			for (Contact1 contact : contacts) {
				System.out.printf("%s \t %s \n", contact.getAcNm(), contact.getAmount());
			}
		}
		
		return new ModelAndView("issue/add_contact_piv", "contactForm", contactForm);
	}
	
	
}
