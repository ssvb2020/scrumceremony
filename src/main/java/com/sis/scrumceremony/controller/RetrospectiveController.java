package com.sis.scrumceremony.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sis.scrumceremony.constants.AppConstants;
import com.sis.scrumceremony.entity.Retrospective;
import com.sis.scrumceremony.service.RetrospectiveService;

@RestController
@RequestMapping(value = "/retrospective")
public class RetrospectiveController {

	protected final static Logger logger = LoggerFactory.getLogger(RetrospectiveController.class);

	@Autowired
	private RetrospectiveService retrospectiveService;

	@RequestMapping(value = "/create", method = RequestMethod.POST,consumes= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@ResponseBody
	public Retrospective saveRetrospective(HttpServletRequest request, @RequestBody Retrospective retrospective) {
		logger.info("start:saveRetrospective");
		Retrospective retrospectiveResponse = retrospectiveService.saveRetrospective(retrospective);
		
		HttpHeaders responseHeaders = new HttpHeaders();
		
		if(AppConstants.CONTENT_TYPE_JSON.equalsIgnoreCase(request.getContentType())) {
			responseHeaders.setContentType(MediaType.APPLICATION_JSON);
		} else if(AppConstants.CONTENT_TYPE_XML.equalsIgnoreCase(request.getContentType())) {
			responseHeaders.setContentType(MediaType.APPLICATION_XML);
		}
		

		return retrospectiveResponse;
	}

	@RequestMapping(value = "/{retrospectiveId}", method = RequestMethod.GET)
	public Retrospective getRetrospective(@PathVariable int retrospectiveId) {
		logger.info("start:getRetrospective by retrospectiveId : " + retrospectiveId);
		Retrospective retrospectiveResponse = retrospectiveService.findByRetrospectiveId(retrospectiveId);
		return retrospectiveResponse;
	}

	@RequestMapping(value = "/pageandsize", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getRetrospectiveByPageAndSize(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "3") int size) {
		logger.info("start:getRetrospectiveByPageAndSize : " + " page " + page + " size :" + size);
		Page<Retrospective> retroList = retrospectiveService.getRetrospectiveByPageAndSize(page, size);
		List<Retrospective> retrospective = new ArrayList<Retrospective>();
		retrospective = retroList.getContent();
		Map<String, Object> response = new HashMap<>();
		response.put("retrospective", retrospective);
		response.put(AppConstants.CURRENT_PAGE, retroList.getNumber());
		response.put(AppConstants.TOTAL_ITEMS, retroList.getTotalElements());
		response.put(AppConstants.TOTAL_PAGES, retroList.getTotalPages());
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@RequestMapping(value = "/byDate", method = RequestMethod.GET)
	public List<Retrospective> getRetrospectiveByDate(@RequestParam String creationDate) {
		logger.info("start:getRetrospectiveByDate : " + " creationDate " + creationDate);
		List<Retrospective> retrospectiveResponse = retrospectiveService.findRetrospectiveByDate(creationDate);
		return retrospectiveResponse;
	}
}