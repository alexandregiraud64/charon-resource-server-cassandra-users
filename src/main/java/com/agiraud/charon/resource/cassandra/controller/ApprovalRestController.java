package com.agiraud.charon.resource.cassandra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.approval.Approval;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.context.request.WebRequest;

import com.agiraud.charon.core.dao.ApprovalService;
import com.agiraud.charon.core.dao.SessionService;
import com.agiraud.charon.core.dto.Answer;

import java.util.Collection;
import java.util.UUID;

@Controller
public class ApprovalRestController {
	/* ************************************************************************* */
	// ATTRIBUTES
	/* ************************************************************************* */
	@Autowired
    private ApprovalService approvalService;

	@Autowired
    private SessionService sessionService;

	/* ************************************************************************* */
	// REQUEST MAPPING
	/* ************************************************************************* */

	public ResponseEntity<Collection<Approval>> getAll(WebRequest request, Model model) {
		UUID userId = sessionService.getPrincipalAsCustomUserPrincipal().getUserId();
		Collection<Approval> approvals = approvalService.getByUserId(userId.toString());
		return ResponseEntity.ok(approvals);
	}

	public ResponseEntity<Answer> deleteByClientId(String clientId, WebRequest request, Model model) {
		Answer answer = new Answer();
		answer.setTitle("Revoke approvals");

		UUID userId = sessionService.getPrincipalAsCustomUserPrincipal().getUserId();
		approvalService.delete(approvalService.getByClientIdAndUserId(clientId, userId.toString()));
		answer.setMessage("Deleted.approvals");
		answer.setStatus("Success");
		
        return ResponseEntity.ok(answer);
	}

}
