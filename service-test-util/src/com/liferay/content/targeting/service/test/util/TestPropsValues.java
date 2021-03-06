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

package com.liferay.content.targeting.service.test.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Alexander Chow
 * @author Raymond Augé
 */
public class TestPropsValues {

	public static final String COMPANY_WEB_ID;

	public static long getCompanyId() throws PortalException, SystemException {
		if (_companyId > 0) {
			return _companyId;
		}

		Company company = CompanyLocalServiceUtil.getCompanyByWebId(
			TestPropsValues.COMPANY_WEB_ID);

		_companyId = company.getCompanyId();

		return _companyId;
	}

	public static long getGroupId() throws PortalException, SystemException {
		if (_groupId > 0) {
			return _groupId;
		}

		Group group = GroupLocalServiceUtil.getGroup(
			getCompanyId(), GroupConstants.GUEST);

		_groupId = group.getGroupId();

		return _groupId;
	}

	public static long getPlid() throws Exception {
		return getPlid(getGroupId());
	}

	public static long getPlid(long groupId) throws Exception {
		if (_plid > 0) {
			return _plid;
		}

		_plid = LayoutLocalServiceUtil.getDefaultPlid(groupId);

		return _plid;
	}

	public static User getUser() throws PortalException, SystemException {
		if (_user == null) {
			Role role = RoleLocalServiceUtil.getRole(
				getCompanyId(), RoleConstants.ADMINISTRATOR);

			List<User> users = UserLocalServiceUtil.getRoleUsers(
				role.getRoleId(), 0, 2);

			if (!users.isEmpty()) {
				_user = users.get(0);

				_userId = _user.getUserId();
			}
		}

		return _user;
	}

	public static long getUserId() throws PortalException, SystemException {
		if (_userId == 0) {
			User user = getUser();

			if (user != null) {
				_userId = user.getUserId();
			}
		}

		return _userId;
	}

	private static Log _log = LogFactoryUtil.getLog(TestPropsValues.class);

	private static long _companyId;
	private static long _groupId;
	private static long _plid;
	private static User _user;
	private static long _userId;

	static {
		String companyWebId = TestPropsUtil.get("company.web.id");

		try {
			if (Validator.isNull(companyWebId)) {
				companyWebId = PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID);

				TestPropsUtil.set("company.web.id", companyWebId);
			}
		}
		catch (Exception e) {
			_log.fatal("Error initializing test properties", e);
		}

		TestPropsUtil.printProperties();

		COMPANY_WEB_ID = companyWebId;
	}

}