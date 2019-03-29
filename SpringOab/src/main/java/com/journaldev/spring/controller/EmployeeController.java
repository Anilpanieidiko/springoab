package com.journaldev.spring.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import com.journaldev.spring.model.Employee;
import com.journaldev.spring.model.Item;
import com.journaldev.spring.model.Items;
import com.journaldev.spring.model.Suplimenter;
import com.oab.Request;
import com.oab.response.Data;
import com.oab.response.FinancialTransaction;
import com.oab.response.Header;
import com.oab.response.Reply;

import iso.std.iso._20022.tech.xsd.pacs_002_001.BranchAndFinancialInstitutionIdentification5;
import iso.std.iso._20022.tech.xsd.pacs_002_001.BranchData2;
import iso.std.iso._20022.tech.xsd.pacs_002_001.Document;
import iso.std.iso._20022.tech.xsd.pacs_002_001.FIToFIPaymentStatusReportV06;
import iso.std.iso._20022.tech.xsd.pacs_002_001.FinancialInstitutionIdentification8;
import iso.std.iso._20022.tech.xsd.pacs_002_001.GroupHeader53;
import iso.std.iso._20022.tech.xsd.pacs_002_001.OriginalGroupHeader1;
import iso.std.iso._20022.tech.xsd.pacs_002_001.PaymentTransaction52;
import iso.std.iso._20022.tech.xsd.pacs_002_001.SupplementaryData1;
import iso.std.iso._20022.tech.xsd.pacs_002_001.SupplementaryDataEnvelope1;
import iso.std.iso._20022.tech.xsd.pacs_002_001.TransactionGroupStatus3Code;
import iso.std.iso._20022.tech.xsd.pacs_002_001.TransactionIndividualStatus3Code;
import iso.std.iso._20022.tech.xsd.pacs_008_001.GroupHeader49;

/**
 * Handles requests for the Employee service.
 */
@Controller
public class EmployeeController {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	//Map to store employees, ideally we should use database
	Map<Integer, Employee> empData = new HashMap<Integer, Employee>();
	
	@RequestMapping(value = EmpRestURIConstants.DUMMY_EMP, method = RequestMethod.GET)
	public @ResponseBody List<Employee> getDummyEmployee() {
		logger.info("Start getDummyEmployee");
		List<Employee> empList= new ArrayList<Employee>();
		Employee emp = new Employee();
		emp.setId(9999);
		emp.setName("Dummy");
		emp.setCreatedDate(new Date());
		empData.put(9999, emp);
		empList.add(emp);
		return empList;
	}
	
	@RequestMapping(value = EmpRestURIConstants.GET_EMP, method = RequestMethod.GET)
	public @ResponseBody Employee getEmployee(@PathVariable("id") int empId) {
		logger.info("Start getEmployee. ID="+empId);
		
		return empData.get(empId);
	}
	
	@RequestMapping(value = EmpRestURIConstants.GET_ALL_EMP, method = RequestMethod.GET)
	public @ResponseBody List<Employee> getAllEmployees() {
		logger.info("Start getAllEmployees.");
		List<Employee> emps = new ArrayList<Employee>();
		Set<Integer> empIdKeys = empData.keySet();
		for(Integer i : empIdKeys){
			emps.add(empData.get(i));
		}
		return emps;
	}
	
	@RequestMapping(value = EmpRestURIConstants.CREATE_EMP, method = RequestMethod.POST)
	public @ResponseBody Employee createEmployee(@RequestBody Employee emp) {
		logger.info("Start createEmployee.");
		emp.setCreatedDate(new Date());
		empData.put(emp.getId(), emp);
		return emp;
	}
	
	@RequestMapping(value = EmpRestURIConstants.DELETE_EMP, method = RequestMethod.PUT)
	public @ResponseBody Employee deleteEmployee(@PathVariable("id") int empId) {
		logger.info("Start deleteEmployee.");
		Employee emp = empData.get(empId);
		empData.remove(empId);
		return emp;
	}
	
	
	@RequestMapping(value="/oabRequest",method= RequestMethod.POST)
	public @ResponseBody com.journaldev.spring.model.OABResponse test(@RequestBody com.journaldev.spring.model.OABRequest oabRequest) throws Exception
	{
		System.out.println("DOen comng");
		
		
		
		JAXBContext context;
		 
        context = JAXBContext.newInstance(iso.std.iso._20022.tech.xsd.pacs_008_001.Document.class);
        Unmarshaller um = context.createUnmarshaller();
        //System.out.println(oabRequest.getContent());
        JAXBElement<iso.std.iso._20022.tech.xsd.pacs_008_001.Document> root= (JAXBElement<iso.std.iso._20022.tech.xsd.pacs_008_001.Document>) um.unmarshal(new ByteArrayInputStream(oabRequest.getContent().getBytes()));
		iso.std.iso._20022.tech.xsd.pacs_008_001.Document dm= root.getValue();
		System.out.println(dm.getFIToFICstmrCdtTrf().getGrpHdr().getMsgId());
		
		
		
		
		
		com.journaldev.spring.model.OABResponse ob= new com.journaldev.spring.model.OABResponse();
		
		ob.setFormat(oabRequest.getFormat());
		ob.setId(oabRequest.getId());
		Document doc= new Document();
		FIToFIPaymentStatusReportV06 fito=new FIToFIPaymentStatusReportV06();
		GroupHeader53 groupHeader= new GroupHeader53();
		groupHeader.setCreDtTm(dm.getFIToFICstmrCdtTrf().getGrpHdr().getCreDtTm());
		BranchAndFinancialInstitutionIdentification5 branch= new BranchAndFinancialInstitutionIdentification5();
		FinancialInstitutionIdentification8 finacial= new FinancialInstitutionIdentification8();
		finacial.setBICFI(dm.getFIToFICstmrCdtTrf().getGrpHdr().getInstdAgt().getFinInstnId().getBICFI());
		branch.setFinInstnId(finacial);
		
		
		groupHeader.setInstdAgt(branch);
		fito.setGrpHdr(groupHeader);
		doc.setFIToFIPmtStsRpt(fito);
		

		ByteArrayOutputStream stream = new ByteArrayOutputStream();
        context = JAXBContext.newInstance(Document.class);
        Marshaller m = context.createMarshaller();
        XMLStreamWriter xmlStreamWriter = XMLOutputFactory.newInstance().createXMLStreamWriter(stream);

        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        xmlStreamWriter.setPrefix("psy","urn:iso:std:psys:request");
        
       // Node n= Node.
      
        m.marshal(doc, stream );
        
        String finalString = new String(stream.toByteArray());
        System.out.println(finalString);
        
       
        
        //ob.setContent("<![CDATA[" + finalString.toString() + "]]>");
       ob.setContent(finalString);
        System.out.println(ob.getContent());
		//groupHeader.setInstgAgt(new BranchAndFinancialInstitutionIdentification5().setFinInstnId(new FinancialInstitutionIdentification8().setBICFI("")));
		return ob;
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/oabTransaction",method=RequestMethod.POST)
public @ResponseBody Document getOabMessage(@RequestBody byte[] document)
	
	{
		
		
		
		
		System.out.println("ok coming");
		
		
		JAXBContext context;
		Unmarshaller um;
		JAXBElement<iso.std.iso._20022.tech.xsd.pacs_008_001.Document> root = null;
		 
        try {
			context = JAXBContext.newInstance(iso.std.iso._20022.tech.xsd.pacs_008_001.Document.class,SupplementaryDataEnvelope1.class);
			um = context.createUnmarshaller();
			root= (JAXBElement<iso.std.iso._20022.tech.xsd.pacs_008_001.Document>) um.unmarshal(new ByteArrayInputStream(document));
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		iso.std.iso._20022.tech.xsd.pacs_008_001.Document dm= root.getValue();
		
		List<iso.std.iso._20022.tech.xsd.pacs_008_001.SupplementaryData1> supl= dm.getFIToFICstmrCdtTrf().getSplmtryData();
		
Element elm=(Element) supl.get(0).getEnvlp().getAny();
		
System.out.println(elm.getLocalName()+":::"+elm.getTextContent());
NodeList chilItems=elm.getChildNodes();
NodeList chl=chilItems.item(0).getChildNodes();
NamedNodeMap attributeList=chl.item(0).getAttributes();
System.out.println("After child:"+attributeList.item(0).getNodeValue());
System.out.println(chl.item(0).getTextContent());



		System.out.println(elm.getFirstChild().getLocalName());
		 
		System.out.println(dm.getFIToFICstmrCdtTrf().getGrpHdr().getMsgId());
		
		
		
		Document doc= new Document();
		
		FIToFIPaymentStatusReportV06 fito= new FIToFIPaymentStatusReportV06();
		
		GroupHeader53 group= new GroupHeader53();
		GroupHeader49 group49= dm.getFIToFICstmrCdtTrf().getGrpHdr();
		group.setMsgId(group49.getMsgId());
		group.setCreDtTm(group49.getCreDtTm());
		iso.std.iso._20022.tech.xsd.pacs_008_001.BranchAndFinancialInstitutionIdentification5 branch= new iso.std.iso._20022.tech.xsd.pacs_008_001.BranchAndFinancialInstitutionIdentification5();
		branch.setBrnchId(group49.getInstgAgt().getBrnchId());
		BranchAndFinancialInstitutionIdentification5 br5=new BranchAndFinancialInstitutionIdentification5();
		String BICFI=group49.getInstgAgt().getFinInstnId().getBICFI();
		
		FinancialInstitutionIdentification8 finacial= new FinancialInstitutionIdentification8();
		finacial.setBICFI(BICFI);
		br5.setFinInstnId(finacial);
		
		
		  BranchData2 branch2= new BranchData2();
		  branch2.setId(group49.getInstgAgt().getBrnchId().getId());
		  br5.setBrnchId(branch2);
		 
		group.setInstgAgt(br5);
		
		BranchAndFinancialInstitutionIdentification5 br6=new BranchAndFinancialInstitutionIdentification5();
		FinancialInstitutionIdentification8 finacialinstd= new FinancialInstitutionIdentification8();
		
		finacialinstd.setBICFI(group49.getInstdAgt().getFinInstnId().getBICFI());
		br6.setFinInstnId(finacialinstd);
		group.setInstdAgt(br6);
		OriginalGroupHeader1 orgnGroupheader= new OriginalGroupHeader1();
		orgnGroupheader.setOrgnlMsgId(group49.getMsgId());
		orgnGroupheader.setOrgnlMsgNmId("pacs.008.001.05");
		orgnGroupheader.setGrpSts(TransactionGroupStatus3Code.ACSC);
		List<OriginalGroupHeader1> org= new ArrayList<OriginalGroupHeader1>();
		org.add(orgnGroupheader);
		PaymentTransaction52 paymentTranscation= new PaymentTransaction52();
		int rnumber=((int)(Math.random() * 100000)) % 1000;
		System.out.println(rnumber);
		
		paymentTranscation.setStsId(group49.getMsgId()+String.valueOf(rnumber));
		String txId=dm.getFIToFICstmrCdtTrf().getCdtTrfTxInf().get(0).getPmtId().getTxId().toString();
		paymentTranscation.setOrgnlTxId(txId);
		paymentTranscation.setTxSts(TransactionIndividualStatus3Code.ACSC);
		
		List<PaymentTransaction52> paymentList= new ArrayList<PaymentTransaction52>();
		paymentList.add(paymentTranscation);
		
		
		SupplementaryData1 suplData= new SupplementaryData1();
		
		SupplementaryDataEnvelope1 suplEnvlp= new SupplementaryDataEnvelope1();
		Item item= new Item();
		item.setValue("testdata");
		System.out.println(elm);
		item.setkey(attributeList.item(0).getNodeValue().trim());
		
		List<Item> itemList= new ArrayList<Item>();
		itemList.add(item);
		Items items= new Items();
		items.setItem(itemList);
		Suplimenter sup=new Suplimenter();
		sup.setItems(items);
		

		suplEnvlp.setAny(sup);
		suplData.setEnvlp(suplEnvlp);
		
		
		List<SupplementaryData1> supList= new ArrayList<SupplementaryData1>();
		supList.add(suplData);
		fito.setTxInfAndSts(paymentList);
		fito.setOrgnlGrpInfAndSts(org);;
		fito.setGrpHdr(group);
		fito.setSplmtryData(supList);
		doc.setFIToFIPmtStsRpt(fito);
		return doc;
		
	}
	
	
	//OabRequest Ends
	
	//SingleEntry posting Starts
	
	@RequestMapping(value="/singleEntryPosting", method=RequestMethod.POST)
	public @ResponseBody Reply processSingleEntryPosting(@RequestBody Request request) throws UnsupportedEncodingException
	{
		
		com.oab.Header requestHeader= request.getHeader();
		
		Reply reply= new Reply();
		
		Header header= new Header();
		
		header.setChannel(request.getHeader().getChannel());
		header.setChannelReserved(requestHeader.getChannelReserved());
		header.setTransactionDate(requestHeader.getTransactionDate());
		header.setTransactionTime(requestHeader.getTransactionTime());
		header.setTransactionReference(requestHeader.getTransactionReference());
		header.setUser(requestHeader.getUser());
		header.setWorkstation(requestHeader.getWorkstation());
		header.setReplyStatus("FIN");
		String todayAsString = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String timeNow= new SimpleDateFormat("hh:mm:ss").format(new Date());
		header.setReplyDate(todayAsString);
		header.setReplyTime(timeNow);
		Data data=new Data();
		
		Random rnd = new Random();
		String random= getAlphaNumericString(9);
		 
		    System.out.println(random);
		FinancialTransaction finacialTrans= new FinancialTransaction();
		finacialTrans.setReference(todayAsString+"@"+String.valueOf(random));
		data.setFinancialTransaction(finacialTrans);
		reply.setHeader(header);
		reply.setData(data);
		return reply;
		
	}
	
	//Single Entry Posting ends here
	
	
	
	//Double Entry posting starts
	
	
	@RequestMapping(value="/doubleEntryPosting",method=RequestMethod.POST)
	
	public @ResponseBody Reply doubleEntryPosting(@RequestBody com.oab.doubleentryrequest.Request request) {
		
		
		System.out.println(request.getData().getDoubleEntryFinancialTransaction().getCredit().getAccount());
		
		  com.oab.doubleentryrequest.Header requestHeader= request.getHeader();
		  
		  Reply reply= new Reply();
		  
		  Header header= new Header();
		  
		  header.setChannel(request.getHeader().getChannel());
		  header.setChannelReserved(requestHeader.getChannelReserved());
		  header.setTransactionDate(requestHeader.getTransactionDate());
		  header.setTransactionTime(requestHeader.getTransactionTime());
		  header.setTransactionReference(requestHeader.getTransactionReference());
		  header.setUser(requestHeader.getUser());
		  header.setWorkstation(requestHeader.getWorkstation());
		  header.setReplyStatus("FIN"); String todayAsString = new
		  SimpleDateFormat("yyyy-MM-dd").format(new Date()); String timeNow= new
		  SimpleDateFormat("hh:mm:ss").format(new Date());
		  header.setReplyDate(todayAsString); header.setReplyTime(timeNow); Data
		  data=new Data();
		  
		  //  \u000d Random rnd = new Random(); \u000d  String random= getAlphaNumericString(9);
		  
		  System.out.println(random); FinancialTransaction finacialTrans= new
		  FinancialTransaction();
		  finacialTrans.setReference(todayAsString+"@"+String.valueOf(random));
		  data.setFinancialTransaction(finacialTrans); reply.setHeader(header);
		  reply.setData(data);
		  return reply;
		 
		
		
	}
	
	//Double Entry Posting Ends HEre
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//For random string generator
	private static String getAlphaNumericString(int n) 
	    { 
	  
	        // length is bounded by 256 Character 
	        byte[] array = new byte[256]; 
	        new Random().nextBytes(array); 
	  
	        String randomString 
	            = new String(array, Charset.forName("UTF-8")); 
	  
	        // Create a StringBuffer to store the result 
	        StringBuffer r = new StringBuffer(); 
	  
	        // Append first 20 alphanumeric characters 
	        // from the generated random String into the result 
	        for (int k = 0; k < randomString.length(); k++) { 
	  
	            char ch = randomString.charAt(k); 
	  
	            if (((ch >= 'a' && ch <= 'z') 
	                 || (ch >= 'A' && ch <= 'Z') 
	                 || (ch >= '0' && ch <= '9')) 
	                && (n > 0)) { 
	  
	                r.append(ch); 
	                n--; 
	            } 
	        } 
	  
	        // return the resultant string 
	        return r.toString(); 
	    } 
	
//	@RequestMapping(value="/test",method=RequestMethod.POST)
//	public @ResponseBody Document getOabMessage(@RequestBody iso.std.iso._20022.tech.xsd.pacs_008_001.Document document)
//	{
//System.out.println(document.getFIToFICstmrCdtTrf().getGrpHdr().getInstdAgt().getBrnchId().getId());
//		return null;
//		
//	}
}
