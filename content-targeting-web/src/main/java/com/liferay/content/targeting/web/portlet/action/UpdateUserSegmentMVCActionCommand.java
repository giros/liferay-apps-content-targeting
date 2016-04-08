/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.content.targeting.web.portlet.action;

import com.liferay.content.targeting.api.model.Rule;
import com.liferay.content.targeting.api.model.RulesRegistry;
import com.liferay.content.targeting.exception.InvalidNameException;
import com.liferay.content.targeting.exception.InvalidRuleException;
import com.liferay.content.targeting.exception.InvalidRulesException;
import com.liferay.content.targeting.model.RuleInstance;
import com.liferay.content.targeting.model.UserSegment;
import com.liferay.content.targeting.service.RuleInstanceService;
import com.liferay.content.targeting.service.UserSegmentService;
import com.liferay.content.targeting.util.PortletKeys;
import com.liferay.content.targeting.web.portlet.ContentTargetingMVCCommand;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.transaction.TransactionInvokerUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletMode;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pavel Savinov
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + PortletKeys.CT_ADMIN,
		"mvc.command.name=" + ContentTargetingMVCCommand.UPDATE_USER_SEGMENT
	},
	service = MVCActionCommand.class
)
public class UpdateUserSegmentMVCActionCommand extends BaseMVCActionCommand {

	protected void deleteRuleInstances(List<RuleInstance> ruleInstances)
		throws Exception {

		for (RuleInstance ruleInstance : ruleInstances) {
			_ruleInstanceService.deleteRuleInstance(

				ruleInstance.getRuleInstanceId());
		}
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long userSegmentId = ParamUtil.getLong(actionRequest, "userSegmentId");

		Map<Locale, String> nameMap = LocalizationUtil.getLocalizationMap(
			actionRequest, "name");
		Map<Locale, String> descriptionMap =
			LocalizationUtil.getLocalizationMap(actionRequest, "description");

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			actionRequest);

		try {
			Callable<UserSegment> userSegmentCallable = new UserSegmentCallable(
				actionRequest, actionResponse, themeDisplay.getUserId(),
				userSegmentId, nameMap, descriptionMap, serviceContext);

			UserSegment userSegment = TransactionInvokerUtil.invoke(
				transactionConfig, userSegmentCallable);

			boolean saveAndContinue = ParamUtil.get(
				actionRequest, "saveAndContinue", false);

			if (saveAndContinue) {
				String redirect = ParamUtil.get(actionRequest, "redirect", "");

				actionResponse.setRenderParameter(
					"mvcRenderCommandName",
					ContentTargetingMVCCommand.EDIT_USER_SEGMENT);
				actionResponse.setRenderParameter("redirect", redirect);
				actionResponse.setRenderParameter(
					"p_p_mode", PortletMode.VIEW.toString());
				actionResponse.setRenderParameter(
					"userSegmentId",
					String.valueOf(userSegment.getUserSegmentId()));

				addSuccessMessage(actionRequest, actionResponse);
			}
			else {
				sendRedirect(actionRequest, actionResponse);
			}
		}
		catch (Exception e) {
			PortalUtil.copyRequestParameters(actionRequest, actionResponse);

			SessionErrors.add(actionRequest, e.getClass(), e);

			if (e instanceof InvalidNameException ||
				e instanceof InvalidRulesException ||
				e instanceof PrincipalException) {

				SessionMessages.add(
					actionRequest,
					PortalUtil.getPortletId(actionRequest) +
						SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);

				actionResponse.setRenderParameter(
					"mvcPath", "/edit_user_segment.jsp");
			}
			else {
				_log.error(e);

				actionResponse.setRenderParameter("mvcPath", "/error.jsp");
			}
		}
		catch (Throwable t) {
			_log.error(t);

			actionResponse.setRenderParameter("mvcPath", "/error.jsp");
		}
	}

	@Reference(unbind = "-")
	protected void setCampaignService(UserSegmentService userSegmentService) {
		_userSegmentService = userSegmentService;
	}

	@Reference(
		target = "(mvc.command.name=" + ContentTargetingMVCCommand.EDIT_USER_SEGMENT + ")"
	)
	protected void setMVCRenderCommand(MVCRenderCommand mvcRenderCommand) {
		_mvcRenderCommand = mvcRenderCommand;
	}

	@Reference(unbind = "-")
	protected void setRuleInstanceService(
		RuleInstanceService ruleInstanceService) {

		_ruleInstanceService = ruleInstanceService;
	}

	@Reference
	protected void setRulesRegistry(RulesRegistry rulesRegistry) {
		_rulesRegistry = rulesRegistry;
	}

	protected void unsetMVCRenderCommand(MVCRenderCommand mvcRenderCommand) {
		_mvcRenderCommand = null;
	}

	protected void unsetRulesRegistry(RulesRegistry rulesRegistry) {
		_rulesRegistry = null;
	}

	protected List<InvalidRuleException> updateRules(
			long userSegmentId, PortletRequest request,
			PortletResponse response)
		throws Exception {

		EditUserSegmentMVCRenderCommand editUserSegmentMVCActionCommand =
			(EditUserSegmentMVCRenderCommand)_mvcRenderCommand;

		List<RuleInstance> requestRuleInstances =
			editUserSegmentMVCActionCommand.getRulesFromRequest(
				request, response);

		List<RuleInstance> ruleInstances = ListUtil.copy(
			_ruleInstanceService.getRuleInstances(userSegmentId));

		List<InvalidRuleException> ruleExceptions = new ArrayList<>();

		if (requestRuleInstances.isEmpty()) {
			deleteRuleInstances(ruleInstances);

			return ruleExceptions;
		}

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			request);

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		for (RuleInstance requestRuleInstance : requestRuleInstances) {
			Rule rule = _rulesRegistry.getRule(
				requestRuleInstance.getRuleKey());

			if (rule == null) {
				continue;
			}

			String typeSettings = null;

			try {
				typeSettings = rule.processRule(
					request, response, requestRuleInstance.getRuleGuid(),
					requestRuleInstance.getValues());
			}
			catch (InvalidRuleException ire) {
				ire.setRuleGuid(requestRuleInstance.getRuleGuid());

				ruleExceptions.add(ire);
			}
			catch (Exception e) {
				ruleExceptions.add(new InvalidRuleException(e.getMessage()));
			}

			try {
				if (requestRuleInstance.getRuleInstanceId() > 0) {
					RuleInstance ruleInstance =
						_ruleInstanceService.updateRuleInstance(
							requestRuleInstance.getRuleInstanceId(),
							typeSettings, serviceContext);

					ruleInstances.remove(ruleInstance);
				}
				else {
					_ruleInstanceService.addRuleInstance(
						themeDisplay.getUserId(),
						requestRuleInstance.getRuleKey(), userSegmentId,
						typeSettings, serviceContext);
				}
			}
			catch (PortalException pe) {
				_log.error("Cannot update rule", pe);
			}
		}

		deleteRuleInstances(ruleInstances);

		return ruleExceptions;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		UpdateUserSegmentMVCActionCommand.class);

	private MVCRenderCommand _mvcRenderCommand;
	private RuleInstanceService _ruleInstanceService;
	private RulesRegistry _rulesRegistry;
	private UserSegmentService _userSegmentService;

	private class UserSegmentCallable implements Callable<UserSegment> {

		@Override
		public UserSegment call() throws Exception {
			UserSegment userSegment = null;

			if (_userSegmentId > 0) {
				userSegment = _userSegmentService.updateUserSegment(
					_userSegmentId, _nameMap, _descriptionMap, _serviceContext);
			}
			else {
				userSegment = _userSegmentService.addUserSegment(
					_userId, _nameMap, _descriptionMap, _serviceContext);
			}

			List<InvalidRuleException> ruleExceptions = updateRules(
				userSegment.getUserSegmentId(), _portletRequest,
				_portletResponse);

			if (!ruleExceptions.isEmpty()) {
				throw new InvalidRulesException(ruleExceptions);
			}

			return userSegment;
		}

		private UserSegmentCallable(
			PortletRequest portletRequest, PortletResponse portletResponse,
			long userId, long userSegmentId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, ServiceContext serviceContext) {

			_portletRequest = portletRequest;
			_portletResponse = portletResponse;
			_userId = userId;
			_userSegmentId = userSegmentId;
			_nameMap = nameMap;
			_descriptionMap = descriptionMap;
			_serviceContext = serviceContext;
		}

		private final Map<Locale, String> _descriptionMap;
		private final Map<Locale, String> _nameMap;
		private final PortletRequest _portletRequest;
		private final PortletResponse _portletResponse;
		private final ServiceContext _serviceContext;
		private final long _userId;
		private final long _userSegmentId;

	}

}