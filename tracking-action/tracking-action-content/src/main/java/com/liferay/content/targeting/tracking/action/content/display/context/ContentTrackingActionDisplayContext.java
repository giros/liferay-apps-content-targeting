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

package com.liferay.content.targeting.tracking.action.content.display.context;

import com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.content.targeting.analytics.util.AnalyticsUtil;
import com.liferay.content.targeting.display.context.BaseTrackingActionDisplayContext;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eudaldo Alonso
 */
public class ContentTrackingActionDisplayContext
	extends BaseTrackingActionDisplayContext {

	public ContentTrackingActionDisplayContext(HttpServletRequest request) {
		super(request);
	}

	public AssetEntry getAssetEntry() {
		if (_assetEntry != null) {
			return _assetEntry;
		}

		_assetEntry = AssetEntryLocalServiceUtil.fetchAssetEntry(
			getAssetEntryId());

		return _assetEntry;
	}

	public long getAssetEntryId() {
		if (_assetEntryId != null) {
			return _assetEntryId;
		}

		_assetEntryId = GetterUtil.getLong(
			displayContext.get("assetEntryId"), 0L);

		return _assetEntryId;
	}

	public List<AssetRendererFactory<?>> getAssetRendererFactories() {
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		return AssetRendererFactoryRegistryUtil.getAssetRendererFactories(
			themeDisplay.getCompanyId(), true);
	}

	public boolean isTrackingContentEnabled() {
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		return AnalyticsUtil.isAnalyticsContentEnabled(
			themeDisplay.getScopeGroupId());
	}

	private AssetEntry _assetEntry;
	private Long _assetEntryId;

}