package com.it.dashboard.issue.mvc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.it.dashboard.issue.domain.PivAmount;
import com.it.dashboard.issue.repo.PivAmountDao;
@Controller
@RequestMapping("/rest/PivAmounts")
public class PivAmountRestController {
	@Autowired
	private PivAmountDao pivAmountDao;
	
	/*@RequestMapping(value="pivNo/{pivNo}", method=RequestMethod.GET, produces="application/json")
    public @ResponseBody PivAmount lookupPivAmountBypivNo(@PathVariable("pivNo") String pivNo)
    {
        return pivAmountDao.findByPivNo(pivNo);
    }*/
	
	
	@RequestMapping(method=RequestMethod.GET, produces="application/json")
    public @ResponseBody List<PivAmount> listAllPivAmounts()
    {
        return pivAmountDao.findAllOrderedPivNo("PIV");
    }

}
