<#--
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
-->

<#list dataFactory.getSequence(dataFactory.maxCampaignContentDisplayPageCount) as pageCount>
	<#assign portletIdPrefix = "campaigncontentdisplay_WAR_contenttargetingweb_INSTANCE_P" + pageCount + "P">

	<#assign portletCounts = dataFactory.maxCampaignContentDisplayPortletCount>

	<#assign layoutModel = dataFactory.newLayoutModel(groupId, "campaign_content_display_" + pageCount, "", dataFactory.getPortletLayoutColumn(portletIdPrefix, portletCounts))>

	<@insertLayout
		_layoutModel = layoutModel
	/>

	${layoutCSVWriter.write(layoutModel.friendlyURL + "\n")}

	<#list dataFactory.getSequence(portletCounts) as portletCount>
		<@insertPortletPreferences
			_portletPreferencesModel = dataFactory.newCampaignContentDisplayPortletPreferenceModels(groupId, layoutModel.plid, portletIdPrefix + portletCount)
		/>
	</#list>
</#list>