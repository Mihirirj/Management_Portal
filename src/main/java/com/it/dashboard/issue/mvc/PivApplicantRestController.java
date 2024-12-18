package com.it.dashboard.issue.mvc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.it.dashboard.issue.domain.PivApplicant;
import com.it.dashboard.issue.repo.PivApplicantDao;

@Controller
@RequestMapping("/rest/PivApplicants")
public class PivApplicantRestController {
	@Autowired
	private PivApplicantDao pivApplicantDao;
	
	@RequestMapping(value="pivNo/{pivNo}", method=RequestMethod.GET, produces="application/json")
    public @ResponseBody PivApplicant lookupPivApplicantBypivNo(@PathVariable("pivNo") String pivNo)
    {
		PivApplicant pivApplicant = null;
		try
		{
			pivApplicant = pivApplicantDao.findByPivNo(pivNo,"PIV");
		}
    
		catch(Exception e)
		{
			e.printStackTrace();
		}
        return pivApplicant;
    }
	
	
	@RequestMapping(method=RequestMethod.GET, produces="application/json")
    public @ResponseBody List<PivApplicant> listAllPivApplicants()
    {
        return pivApplicantDao.findAllOrderedPivNo("PIV");
    }

}
