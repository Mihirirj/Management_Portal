package com.it.dashboard.issue.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import com.it.dashboard.issue.domain.Student;
import com.it.dashboard.issue.domain.StudentModel;

@Controller
@RequestMapping(value = "/issue")
public class StudentController {

  
   
   /*@RequestMapping(value = "/addStudent", method = RequestMethod.POST)
   public String addStudent(@ModelAttribute("SpringWeb")Student student, 
   ModelMap model) {
      model.addAttribute("name", student.getName());
      model.addAttribute("age", student.getAge());
     // model.addAttribute("id", student.getId());
      
      return "/issue/result";
   }*/
   
   @RequestMapping(value = "/addStudent", method = RequestMethod.POST)
   public String sayHello(StudentModel person, Model model) {
       model.addAttribute("person", person);
       System.out.println("fname "+person.getStudent().getFirstName());
       System.out.println("subj "+person.getSubject().getSubjectName());
       return "/issue/student";
   }
}
