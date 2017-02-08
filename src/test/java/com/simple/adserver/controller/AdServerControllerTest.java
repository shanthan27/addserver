package com.simple.adserver.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.sample.adserver.controller.AdServerController;
import com.sample.adserver.domain.AdCampaigns;
import com.sample.adserver.service.AdServerService;
import com.simple.adserver.util.AdCampaignsUitl;

@RunWith(MockitoJUnitRunner.class)
public class AdServerControllerTest {
	 @Mock
	    private AdServerService adServerService;

	    private AdServerController adServerController;

	    @Before
	    public void setUp() throws Exception {
	    	adServerController = new AdServerController(adServerService);
	    }

	    @Test
	    public void shouldCreateAd() throws Exception {
	        final AdCampaigns savedAd = stubServiceToReturnStoredAd();
	        final AdCampaigns ad = AdCampaignsUitl.createAd();
	        AdCampaigns returnedAd = adServerController.createAdCampaigns(ad);
	        // verify ad was passed to AdServerService
	        verify(adServerService, times(1)).save(ad);
	        assertEquals("Returned ad should come from the service", savedAd, returnedAd);
	    }

	    private AdCampaigns stubServiceToReturnStoredAd() {
	        final AdCampaigns ad = AdCampaignsUitl.createAd();
	        when(adServerService.save(any(AdCampaigns.class))).thenReturn(ad);
	        return ad;
	    }
	    
	    private AdCampaigns stubServiceToReturnStoredAd(String id) {
	        final AdCampaigns ad = AdCampaignsUitl.createAd();
	        when(adServerService.getAd(any(String.class))).thenReturn(ad);
	        return ad;
	    }

	    @Test
	    public void shouldListAllAds() throws Exception {
	    	stubServiceToReturnExistingAds(10);
	        Collection<AdCampaigns> ads = adServerController.getAllAds();
	        assertNotNull(ads);
	        assertEquals(10, ads.size());
	        // verify user was passed to UserService
	        verify(adServerService, times(1)).getAllAds();
	    }

	    private void stubServiceToReturnExistingAds(int howMany) {
	        when(adServerService.getAllAds()).thenReturn(AdCampaignsUitl.createAdList(howMany));
	    }
	    
	    @Test
	    public void shouldReturnAdOfPassedPartnerId() throws Exception{
	    	final AdCampaigns savedAd = stubServiceToReturnStoredAd("id");
	    	AdCampaigns returnedAd = adServerController.getAd("id");
	    	assertEquals(savedAd.getPartner_id(), returnedAd.getPartner_id());
	    }
}
