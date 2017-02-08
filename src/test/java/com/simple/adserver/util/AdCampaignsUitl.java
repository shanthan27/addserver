package com.simple.adserver.util;

import java.util.ArrayList;
import java.util.List;

import com.sample.adserver.domain.AdCampaigns;

public class AdCampaignsUitl {
	private static final String ID = "id";
    private static final String DURATION = "Duration of the ad";
    private static final String CONTENT = "Ad content goes here";

    private AdCampaignsUitl() {
    }

    public static AdCampaigns createAd() {
        return new AdCampaigns(ID, DURATION,CONTENT);
    }

    public static List<AdCampaigns> createAdList(int howMany) {
        List<AdCampaigns> adList = new ArrayList<AdCampaigns>();
        for (int i = 0; i < howMany; i++) {
        	adList.add(new AdCampaigns(ID + "#" + i, DURATION, CONTENT));
        }
        return adList;
    }
}
