package com.it.dashboard.issue.mvc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.it.dashboard.issue.domain.PivDetail;
import com.it.dashboard.issue.repo.PivDetailDao;
@Controller
@RequestMapping("/rest/PivDetails")
public class PivDetailRestController {
	@Autowired
	private PivDetailDao pivDetailDao;
	
	@RequestMapping(value="/refNo/{referenceNo}", method=RequestMethod.GET, produces="application/json")
    public @ResponseBody PivDetail lookupPivDetailByReferenceNo(@PathVariable("referenceNo") String referenceNo)
    {
        return pivDetailDao.findByReferenceNo(referenceNo,"PIV");
    }
	
	@RequestMapping(value="pivNo/{pivNo}", method=RequestMethod.GET, produces="application/json")
    public @ResponseBody PivDetail lookupPivDetailBypivNo(@PathVariable("pivNo") String pivNo)
    {
        return pivDetailDao.findByReferenceNo(pivNo,"PIV");
    }
	
	
	@RequestMapping(method=RequestMethod.GET, produces="application/json")
    public @ResponseBody List<PivDetail> listAllPivDetails()
    {
        return pivDetailDao.findAllOrderedReferenceNo("PIV");
    }

}
