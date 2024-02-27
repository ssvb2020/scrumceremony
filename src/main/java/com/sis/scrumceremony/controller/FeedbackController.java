package com.sis.scrumceremony.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sis.scrumceremony.entity.Feedback;
import com.sis.scrumceremony.repository.FeedbackRepository;
import com.sis.scrumceremony.service.RetrospectiveService;

@RestController
@RequestMapping(value = "/feedback")
public class FeedbackController {

	protected final static Logger logger = LoggerFactory.getLogger(FeedbackController.class);

	@Autowired
	private RetrospectiveService retrospectiveService;

	@Autowired
	FeedbackRepository feedbackRepository;

	@ResponseBody
	@RequestMapping(value = "/feedbacks")
	public List<Feedback> getAllFeedback() {
		logger.info("start: getAllFeedback ");
		List<Feedback> feedbackresponse = (List<Feedback>) feedbackRepository.findAll();
		return feedbackresponse;
	}

	@RequestMapping(value = "/update/{feedbackId}", method = RequestMethod.PUT)
	@ResponseBody
	public Feedback updateFeedback(@PathVariable int feedbackId, @RequestBody Feedback feedback) {
		logger.info("start:updateFeedback : " + feedbackId);
		Feedback feedbackresponse = retrospectiveService.updateFeedbackDetails(feedbackId, feedback);
		return feedbackresponse;
	}

	@RequestMapping(value = "/add/{retrospectiveId}", method = RequestMethod.POST)
	@ResponseBody
	public Feedback addFeedback(@PathVariable int retrospectiveId, @RequestBody Feedback feedback) {
		logger.info("start:addFeedback for retrospectiveId " + retrospectiveId);
		Feedback feedbackresponse = retrospectiveService.addFeedbackDetails(retrospectiveId, feedback);
		return feedbackresponse;
	}

}