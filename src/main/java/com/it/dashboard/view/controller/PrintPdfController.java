package com.it.dashboard.view.controller;
/**
 * @author Paramie
 *
 */

import javax.servlet.http.HttpServletRequest;

import com.it.dashboard.issue.repo.PivDetailDao;
import com.it.dashboard.issue.repo.PivApplicantDao;
import com.it.dashboard.issue.repo.PivDao;
import com.it.dashboard.issue.domain.PivAmountGrid;
import com.it.dashboard.issue.domain.PivApplicant;
import com.it.dashboard.issue.domain.PivDetail;
import com.it.dashboard.issue.domain.PivModel;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Controller
public class PrintPdfController {
	
	@Autowired
	private PivDetailDao PivDetailDao;
	
	@Autowired
	private PivApplicantDao PivApplicantDao;
	
	
	@Autowired
	private PivDao PivDao;
	
	private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
	private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
	private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
	private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);
	
	private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
                paragraph.add(new Paragraph(" "));
        }
}

	@ExceptionHandler(NullPointerException.class)
	public String handleNullException(NullPointerException ex) {

		ModelAndView model = new ModelAndView("admin/index");
		model.addObject("errMsg", "this is null Exception.class");
		
		return "redirect:../admin/index";
	}
	
	@RequestMapping(value="viewprint/pivprint", method = RequestMethod.GET)
	public ModelAndView pivPrint(@ModelAttribute("pivModel") PivModel pivModel,@RequestParam String actionButton,HttpServletRequest request){
		//String FILE="C:/mywork/test.pdf";
		if(actionButton.equals("print"))
		{
			String name = "";
			try
			{
				String pivNo = pivModel.getPivDetail().getId().getPivNo();
				request.getSession().setAttribute("headerName","Piv Print Mode");
				request.getParameter(pivNo);
				String ext = "pdf";
				//create this folder before pressing print
				File dir = new File("C:/mywork");
				name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), ext);
				File FILE = new File(dir, name);
				
				PivModel pivmodel=new PivModel();
			
				PivDetail pivdetails=pivmodel.getPivDetail();
				pivdetails=PivDetailDao.findByPivNo(pivNo,"PIV");
				
				PivApplicant pivapplicant=pivmodel.getPivApplicant();
				pivapplicant=PivApplicantDao.findByPivNo(pivNo,"PIV");
				List<PivAmountGrid> amountList = PivDao.getAcctCodesByPivNo(pivNo,"PIV");
		
				String cebbn=pivdetails.getCebBranchName();
				String refno=pivdetails.getReferenceNo();
				String reftype=pivdetails.getReferenceType();
				Date IssuedDate=pivdetails.getIssuedDate();
				Date ExpireDate=pivdetails.getExpiryDate();
				Date PaidDate=pivdetails.getPaidDate();
				String currency=pivdetails.getCurrencyCode();
				String description=pivdetails.getDescription();
				
				DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
	        
			
				Document doc=new Document();
				PdfWriter.getInstance(doc,new FileOutputStream(FILE));
				doc.open();
				
				Paragraph preface = new Paragraph();
	            // We add one empty line
	            addEmptyLine(preface, 1);
	            // Lets write a big header
	            preface.add(new Paragraph("Pay In Voucher", catFont));
	            
	           
	            addEmptyLine(preface, 1);
	            doc.add(preface);
	            
	            Paragraph subPara = new Paragraph();
	            subPara.add(new Paragraph("Ceylon Electricity Board", redFont));
	            addEmptyLine(subPara, 1);
	            doc.add(subPara);

			
			    PdfPTable table1 = new PdfPTable(3);
		        // the cell object
		        PdfPCell cell;
		        // we add a cell with colspan 3
		        table1.addCell("PIV No");
		        cell = new PdfPCell(new Phrase(pivNo));
		        cell.setColspan(2);
		        table1.addCell(cell);
		        
		        table1.addCell("Ceb Branch Name");
		        cell = new PdfPCell(new Phrase(cebbn));
		        cell.setColspan(2);
		        table1.addCell(cell);
		        
		        table1.addCell("Reference No");
		        cell = new PdfPCell(new Phrase(refno));
		        cell.setColspan(2);
		        table1.addCell(cell);
		        
		        table1.addCell("Reference Type");
		        cell = new PdfPCell(new Phrase(reftype));
		        cell.setColspan(2);
		        table1.addCell(cell);
		        
		        table1.addCell("Issued Date");
		        cell = new PdfPCell(new Phrase());
		        cell.setColspan(2);
		        table1.addCell(cell);
		        
		        table1.addCell("Expiry Date");
		        cell = new PdfPCell(new Phrase());
		        cell.setColspan(2);
		        table1.addCell(cell);
		        
		        table1.addCell("Paid Date");
		        cell = new PdfPCell(new Phrase());
		        cell.setColspan(2);
		        table1.addCell(cell);
		        
		        table1.addCell("Currency Code");
		        cell = new PdfPCell(new Phrase(currency));
		        cell.setColspan(2);
		        table1.addCell(cell);
		        
		        table1.addCell("Description");
		        cell = new PdfPCell(new Phrase(description));
		        cell.setColspan(2);
		        table1.addCell(cell);
		        //table1.addCell("testtesttesttest");
		        
		        doc.add(table1);
				doc.close();
			
			
			}
			catch(Exception e)
			{
				e.printStackTrace();	
			}
			ModelAndView pivprintpdf=new ModelAndView("/viewprint/viewprint");
			pivprintpdf.addObject("success","PDF created Successfully");
			pivprintpdf.addObject("PDFname","PDF Name ="+name);
		//pivprintpdf.addObject("name1",name1);
	
			return pivprintpdf;
		}
		if(actionButton.equals("exit")){
			return new ModelAndView("/admin/mainmenu");
		}
		return null;
		
	}
}
	
	
	
	

	
	