package com.sample.adserver.service;

import java.util.List;

import com.sample.adserver.domain.AdCampaigns;

public interface AdServerService {
	
	public AdCampaigns save(AdCampaigns adCampaigns);
	
	public List<AdCampaigns> getAllAds();
	
	public AdCampaigns getAd(String partnerId);
}
