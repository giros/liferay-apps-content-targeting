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

package com.liferay.content.targeting.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link TrackingActionInstance}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TrackingActionInstance
 * @generated
 */
@ProviderType
public class TrackingActionInstanceWrapper implements TrackingActionInstance,
	ModelWrapper<TrackingActionInstance> {
	public TrackingActionInstanceWrapper(
		TrackingActionInstance trackingActionInstance) {
		_trackingActionInstance = trackingActionInstance;
	}

	@Override
	public Class<?> getModelClass() {
		return TrackingActionInstance.class;
	}

	@Override
	public String getModelClassName() {
		return TrackingActionInstance.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("trackingActionInstanceId", getTrackingActionInstanceId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("trackingActionKey", getTrackingActionKey());
		attributes.put("campaignId", getCampaignId());
		attributes.put("reportInstanceId", getReportInstanceId());
		attributes.put("alias", getAlias());
		attributes.put("referrerClassNameId", getReferrerClassNameId());
		attributes.put("referrerClassPK", getReferrerClassPK());
		attributes.put("elementId", getElementId());
		attributes.put("eventType", getEventType());
		attributes.put("typeSettings", getTypeSettings());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long trackingActionInstanceId = (Long)attributes.get(
				"trackingActionInstanceId");

		if (trackingActionInstanceId != null) {
			setTrackingActionInstanceId(trackingActionInstanceId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String trackingActionKey = (String)attributes.get("trackingActionKey");

		if (trackingActionKey != null) {
			setTrackingActionKey(trackingActionKey);
		}

		Long campaignId = (Long)attributes.get("campaignId");

		if (campaignId != null) {
			setCampaignId(campaignId);
		}

		Long reportInstanceId = (Long)attributes.get("reportInstanceId");

		if (reportInstanceId != null) {
			setReportInstanceId(reportInstanceId);
		}

		String alias = (String)attributes.get("alias");

		if (alias != null) {
			setAlias(alias);
		}

		Long referrerClassNameId = (Long)attributes.get("referrerClassNameId");

		if (referrerClassNameId != null) {
			setReferrerClassNameId(referrerClassNameId);
		}

		Long referrerClassPK = (Long)attributes.get("referrerClassPK");

		if (referrerClassPK != null) {
			setReferrerClassPK(referrerClassPK);
		}

		String elementId = (String)attributes.get("elementId");

		if (elementId != null) {
			setElementId(elementId);
		}

		String eventType = (String)attributes.get("eventType");

		if (eventType != null) {
			setEventType(eventType);
		}

		String typeSettings = (String)attributes.get("typeSettings");

		if (typeSettings != null) {
			setTypeSettings(typeSettings);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new TrackingActionInstanceWrapper((TrackingActionInstance)_trackingActionInstance.clone());
	}

	@Override
	public int compareTo(
		com.liferay.content.targeting.model.TrackingActionInstance trackingActionInstance) {
		return _trackingActionInstance.compareTo(trackingActionInstance);
	}

	/**
	* Returns the alias of this tracking action instance.
	*
	* @return the alias of this tracking action instance
	*/
	@Override
	public java.lang.String getAlias() {
		return _trackingActionInstance.getAlias();
	}

	/**
	* Returns the campaign ID of this tracking action instance.
	*
	* @return the campaign ID of this tracking action instance
	*/
	@Override
	public long getCampaignId() {
		return _trackingActionInstance.getCampaignId();
	}

	/**
	* Returns the company ID of this tracking action instance.
	*
	* @return the company ID of this tracking action instance
	*/
	@Override
	public long getCompanyId() {
		return _trackingActionInstance.getCompanyId();
	}

	/**
	* Returns the create date of this tracking action instance.
	*
	* @return the create date of this tracking action instance
	*/
	@Override
	public Date getCreateDate() {
		return _trackingActionInstance.getCreateDate();
	}

	/**
	* Returns the element ID of this tracking action instance.
	*
	* @return the element ID of this tracking action instance
	*/
	@Override
	public java.lang.String getElementId() {
		return _trackingActionInstance.getElementId();
	}

	/**
	* Returns the event type of this tracking action instance.
	*
	* @return the event type of this tracking action instance
	*/
	@Override
	public java.lang.String getEventType() {
		return _trackingActionInstance.getEventType();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _trackingActionInstance.getExpandoBridge();
	}

	/**
	* Returns the group ID of this tracking action instance.
	*
	* @return the group ID of this tracking action instance
	*/
	@Override
	public long getGroupId() {
		return _trackingActionInstance.getGroupId();
	}

	/**
	* Returns the modified date of this tracking action instance.
	*
	* @return the modified date of this tracking action instance
	*/
	@Override
	public Date getModifiedDate() {
		return _trackingActionInstance.getModifiedDate();
	}

	/**
	* Returns the primary key of this tracking action instance.
	*
	* @return the primary key of this tracking action instance
	*/
	@Override
	public long getPrimaryKey() {
		return _trackingActionInstance.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _trackingActionInstance.getPrimaryKeyObj();
	}

	@Override
	public java.lang.String getReferrerClassName() {
		return _trackingActionInstance.getReferrerClassName();
	}

	/**
	* Returns the referrer class name ID of this tracking action instance.
	*
	* @return the referrer class name ID of this tracking action instance
	*/
	@Override
	public long getReferrerClassNameId() {
		return _trackingActionInstance.getReferrerClassNameId();
	}

	/**
	* Returns the referrer class p k of this tracking action instance.
	*
	* @return the referrer class p k of this tracking action instance
	*/
	@Override
	public long getReferrerClassPK() {
		return _trackingActionInstance.getReferrerClassPK();
	}

	/**
	* Returns the report instance ID of this tracking action instance.
	*
	* @return the report instance ID of this tracking action instance
	*/
	@Override
	public long getReportInstanceId() {
		return _trackingActionInstance.getReportInstanceId();
	}

	@Override
	public java.lang.String getTrackingActionGuid() {
		return _trackingActionInstance.getTrackingActionGuid();
	}

	/**
	* Returns the tracking action instance ID of this tracking action instance.
	*
	* @return the tracking action instance ID of this tracking action instance
	*/
	@Override
	public long getTrackingActionInstanceId() {
		return _trackingActionInstance.getTrackingActionInstanceId();
	}

	/**
	* Returns the tracking action key of this tracking action instance.
	*
	* @return the tracking action key of this tracking action instance
	*/
	@Override
	public java.lang.String getTrackingActionKey() {
		return _trackingActionInstance.getTrackingActionKey();
	}

	/**
	* Returns the type settings of this tracking action instance.
	*
	* @return the type settings of this tracking action instance
	*/
	@Override
	public java.lang.String getTypeSettings() {
		return _trackingActionInstance.getTypeSettings();
	}

	/**
	* Returns the user ID of this tracking action instance.
	*
	* @return the user ID of this tracking action instance
	*/
	@Override
	public long getUserId() {
		return _trackingActionInstance.getUserId();
	}

	/**
	* Returns the user name of this tracking action instance.
	*
	* @return the user name of this tracking action instance
	*/
	@Override
	public java.lang.String getUserName() {
		return _trackingActionInstance.getUserName();
	}

	/**
	* Returns the user uuid of this tracking action instance.
	*
	* @return the user uuid of this tracking action instance
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _trackingActionInstance.getUserUuid();
	}

	/**
	* Returns the uuid of this tracking action instance.
	*
	* @return the uuid of this tracking action instance
	*/
	@Override
	public java.lang.String getUuid() {
		return _trackingActionInstance.getUuid();
	}

	@Override
	public Map<java.lang.String, java.lang.String> getValues() {
		return _trackingActionInstance.getValues();
	}

	@Override
	public int hashCode() {
		return _trackingActionInstance.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _trackingActionInstance.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _trackingActionInstance.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _trackingActionInstance.isNew();
	}

	@Override
	public void persist() {
		_trackingActionInstance.persist();
	}

	/**
	* Sets the alias of this tracking action instance.
	*
	* @param alias the alias of this tracking action instance
	*/
	@Override
	public void setAlias(java.lang.String alias) {
		_trackingActionInstance.setAlias(alias);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_trackingActionInstance.setCachedModel(cachedModel);
	}

	/**
	* Sets the campaign ID of this tracking action instance.
	*
	* @param campaignId the campaign ID of this tracking action instance
	*/
	@Override
	public void setCampaignId(long campaignId) {
		_trackingActionInstance.setCampaignId(campaignId);
	}

	/**
	* Sets the company ID of this tracking action instance.
	*
	* @param companyId the company ID of this tracking action instance
	*/
	@Override
	public void setCompanyId(long companyId) {
		_trackingActionInstance.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this tracking action instance.
	*
	* @param createDate the create date of this tracking action instance
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_trackingActionInstance.setCreateDate(createDate);
	}

	/**
	* Sets the element ID of this tracking action instance.
	*
	* @param elementId the element ID of this tracking action instance
	*/
	@Override
	public void setElementId(java.lang.String elementId) {
		_trackingActionInstance.setElementId(elementId);
	}

	/**
	* Sets the event type of this tracking action instance.
	*
	* @param eventType the event type of this tracking action instance
	*/
	@Override
	public void setEventType(java.lang.String eventType) {
		_trackingActionInstance.setEventType(eventType);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_trackingActionInstance.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_trackingActionInstance.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_trackingActionInstance.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this tracking action instance.
	*
	* @param groupId the group ID of this tracking action instance
	*/
	@Override
	public void setGroupId(long groupId) {
		_trackingActionInstance.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this tracking action instance.
	*
	* @param modifiedDate the modified date of this tracking action instance
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_trackingActionInstance.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_trackingActionInstance.setNew(n);
	}

	/**
	* Sets the primary key of this tracking action instance.
	*
	* @param primaryKey the primary key of this tracking action instance
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_trackingActionInstance.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_trackingActionInstance.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public void setReferrerClassName(java.lang.String referrerClassName) {
		_trackingActionInstance.setReferrerClassName(referrerClassName);
	}

	/**
	* Sets the referrer class name ID of this tracking action instance.
	*
	* @param referrerClassNameId the referrer class name ID of this tracking action instance
	*/
	@Override
	public void setReferrerClassNameId(long referrerClassNameId) {
		_trackingActionInstance.setReferrerClassNameId(referrerClassNameId);
	}

	/**
	* Sets the referrer class p k of this tracking action instance.
	*
	* @param referrerClassPK the referrer class p k of this tracking action instance
	*/
	@Override
	public void setReferrerClassPK(long referrerClassPK) {
		_trackingActionInstance.setReferrerClassPK(referrerClassPK);
	}

	/**
	* Sets the report instance ID of this tracking action instance.
	*
	* @param reportInstanceId the report instance ID of this tracking action instance
	*/
	@Override
	public void setReportInstanceId(long reportInstanceId) {
		_trackingActionInstance.setReportInstanceId(reportInstanceId);
	}

	@Override
	public void setTrackingActionGuid(java.lang.String trackingActionGuid) {
		_trackingActionInstance.setTrackingActionGuid(trackingActionGuid);
	}

	/**
	* Sets the tracking action instance ID of this tracking action instance.
	*
	* @param trackingActionInstanceId the tracking action instance ID of this tracking action instance
	*/
	@Override
	public void setTrackingActionInstanceId(long trackingActionInstanceId) {
		_trackingActionInstance.setTrackingActionInstanceId(trackingActionInstanceId);
	}

	/**
	* Sets the tracking action key of this tracking action instance.
	*
	* @param trackingActionKey the tracking action key of this tracking action instance
	*/
	@Override
	public void setTrackingActionKey(java.lang.String trackingActionKey) {
		_trackingActionInstance.setTrackingActionKey(trackingActionKey);
	}

	/**
	* Sets the type settings of this tracking action instance.
	*
	* @param typeSettings the type settings of this tracking action instance
	*/
	@Override
	public void setTypeSettings(java.lang.String typeSettings) {
		_trackingActionInstance.setTypeSettings(typeSettings);
	}

	/**
	* Sets the user ID of this tracking action instance.
	*
	* @param userId the user ID of this tracking action instance
	*/
	@Override
	public void setUserId(long userId) {
		_trackingActionInstance.setUserId(userId);
	}

	/**
	* Sets the user name of this tracking action instance.
	*
	* @param userName the user name of this tracking action instance
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_trackingActionInstance.setUserName(userName);
	}

	/**
	* Sets the user uuid of this tracking action instance.
	*
	* @param userUuid the user uuid of this tracking action instance
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_trackingActionInstance.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this tracking action instance.
	*
	* @param uuid the uuid of this tracking action instance
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_trackingActionInstance.setUuid(uuid);
	}

	@Override
	public void setValues(Map<java.lang.String, java.lang.String> values) {
		_trackingActionInstance.setValues(values);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<com.liferay.content.targeting.model.TrackingActionInstance> toCacheModel() {
		return _trackingActionInstance.toCacheModel();
	}

	@Override
	public com.liferay.content.targeting.model.TrackingActionInstance toEscapedModel() {
		return new TrackingActionInstanceWrapper(_trackingActionInstance.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _trackingActionInstance.toString();
	}

	@Override
	public com.liferay.content.targeting.model.TrackingActionInstance toUnescapedModel() {
		return new TrackingActionInstanceWrapper(_trackingActionInstance.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _trackingActionInstance.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TrackingActionInstanceWrapper)) {
			return false;
		}

		TrackingActionInstanceWrapper trackingActionInstanceWrapper = (TrackingActionInstanceWrapper)obj;

		if (Validator.equals(_trackingActionInstance,
					trackingActionInstanceWrapper._trackingActionInstance)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _trackingActionInstance.getStagedModelType();
	}

	@Override
	public TrackingActionInstance getWrappedModel() {
		return _trackingActionInstance;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _trackingActionInstance.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _trackingActionInstance.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_trackingActionInstance.resetOriginalValues();
	}

	private final TrackingActionInstance _trackingActionInstance;
}