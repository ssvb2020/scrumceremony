package com.sis.scrumceremony.serviceimpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sis.scrumceremony.constants.AppConstants;
import com.sis.scrumceremony.entity.Feedback;
import com.sis.scrumceremony.entity.Retrospective;
import com.sis.scrumceremony.exception.RetrospectiveNotFoundException;
import com.sis.scrumceremony.repository.FeedbackRepository;
import com.sis.scrumceremony.repository.RetrospectiveRepository;
import com.sis.scrumceremony.service.RetrospectiveService;

@Service
public class RetrospectiveServiceImpl implements RetrospectiveService {

	protected final static Logger logger = LoggerFactory.getLogger(RetrospectiveServiceImpl.class);

	@Autowired
	private RetrospectiveRepository retrospectiveRepository;

	@Autowired
	private FeedbackRepository feedbackRepository;

	public Retrospective saveRetrospective(Retrospective retrospective) {
		logger.info("start:saveRetrospective");
		List<Feedback> feedbackList = new ArrayList<>();
		for (int i = 0; i < retrospective.getFeedbackList().size(); i++) {
			Feedback feedback = new Feedback();
			feedback.setFeedbackParticipantName(retrospective.getFeedbackList().get(i).getFeedbackParticipantName());
			feedbackList.add(feedback);
			feedback.setRetrospective(retrospective);
		}
		retrospective.setFeedbackList(feedbackList);
		retrospective = retrospectiveRepository.save(retrospective);
		return retrospective;

	}

	public List<Retrospective> findRetrospectiveByDate(String creationDate) {
		logger.info("start:findRetrospectiveByDate " + creationDate);
		List<Retrospective> retrospectiveList = new ArrayList<Retrospective>();
			
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(AppConstants.DATE_FORMAT);
		try {
			Date date = simpleDateFormat.parse(creationDate);
			retrospectiveList = retrospectiveRepository.findAllByCreationDate(date);
			
			if (retrospectiveList.size() == 0) {
				 throw new RetrospectiveNotFoundException(AppConstants.RETROSPECTIVE_NOT_FOUND);
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		logger.info("retrospectiveList size " + retrospectiveList.size());
		return retrospectiveList;
	}

	public Page<Retrospective> getRetrospectiveByPageAndSize(int page, int size) {
		logger.info("start:getRetrospectiveByPageAndSize page : " + page + " size : "  + size);
		Pageable paging = PageRequest.of(page, size);
		Page<Retrospective> retrospectiveList = retrospectiveRepository.findAll(paging);
		
		if (retrospectiveList.getTotalElements() == 0) {
			 throw new RetrospectiveNotFoundException(AppConstants.RETROSPECTIVE_NOT_FOUND);
		}
		
		logger.info("Total elements size " + retrospectiveList.getTotalElements());
		return retrospectiveList;
	}

	@Override
	public Feedback updateFeedbackDetails(int feedbackId, Feedback feedback) {
		Feedback feedbackResult = feedbackRepository.findByFeedbackId(feedbackId);
		
		if (feedbackResult == null) {
			 throw new RetrospectiveNotFoundException(AppConstants.RETROSPECTIVE_NOT_FOUND);
		}
		
		if (!feedback.getFeedbackParticipantName().isEmpty()) {
			feedbackResult.setFeedbackParticipantName(feedback.getFeedbackParticipantName());
		}

		if (!feedback.getFeedbackType().isEmpty()) {
			feedbackResult.setFeedbackType(feedback.getFeedbackType());
		}

		if (!feedback.getFeedbackComment().isEmpty()) {
			feedbackResult.setFeedbackComment(feedback.getFeedbackComment());
		}

		
		if (feedbackResult != null && feedbackResult.getFeedbackId() > 0) {
			feedbackRepository.save(feedbackResult);
		}

		return feedbackResult;
	}

	@Override
	public Feedback addFeedbackDetails(int retrospectiveId, Feedback feedback) {
		logger.info("start:addFeedbackDetails : " + retrospectiveId);
		Retrospective retrospective = retrospectiveRepository.findByRetrospectiveId(retrospectiveId);
		
		if (retrospective == null) {
		    throw new RetrospectiveNotFoundException(AppConstants.RETROSPECTIVE_NOT_FOUND);
		}
		
		
		Feedback feedbackSave = new Feedback();

		if (!feedback.getFeedbackParticipantName().isEmpty()) {
			feedbackSave.setFeedbackParticipantName(feedback.getFeedbackParticipantName());
		}

		if (!feedback.getFeedbackType().isEmpty()) {
			feedbackSave.setFeedbackType(feedback.getFeedbackType());
		}

		if (!feedback.getFeedbackComment().isEmpty()) {
			feedbackSave.setFeedbackComment(feedback.getFeedbackComment());
		}

		if (retrospective != null && retrospective.getRetrospectiveId() > 0) {
			feedbackSave.setRetrospective(retrospective);
		}

		feedbackRepository.save(feedbackSave);
		return feedbackSave;
	}

	@Override
	public Retrospective findByRetrospectiveId(int retrospectiveId) {
		logger.info("start:findByRetrospectiveId : " + retrospectiveId);
		Retrospective retrospective = retrospectiveRepository.findByRetrospectiveId(retrospectiveId);
		
		if (retrospective == null) {
			 throw new RetrospectiveNotFoundException(AppConstants.RETROSPECTIVE_NOT_FOUND);
		}
		
		
		return retrospective;
	}
}