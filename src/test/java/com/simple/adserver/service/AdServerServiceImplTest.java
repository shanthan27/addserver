package com.simple.adserver.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.sample.adserver.domain.AdCampaigns;
import com.sample.adserver.exceptions.AdAlreadyExistsWithThePartnerIdException;
import com.sample.adserver.repository.AdServerRepository;
import com.sample.adserver.service.AdServerService;
import com.sample.adserver.service.AdServerServiceImpl;
import com.simple.adserver.util.AdCampaignsUitl;

@RunWith(MockitoJUnitRunner.class)
public class AdServerServiceImplTest {

	@Mock
    private AdServerRepository adServerRepository;

    private AdServerService adServerService;
    
    @Before
    public void setUp() throws Exception {
    	adServerService = new  AdServerServiceImpl(adServerRepository);
    }
    
    @Test
    public void shouldSaveNewAd_GivenThereDoesNotExistOneWithTheSameId_ThenTheSavedAdShouldBeReturned() throws Exception {
        final AdCampaigns savedAd = stubRepositoryToReturnAdOnSave();
        final AdCampaigns adCampaigns = AdCampaignsUitl.createAd();
        final AdCampaigns returnedAd = adServerService.save(adCampaigns);
        // verify repository was called with user
        verify(adServerRepository, times(1)).save(adCampaigns);
        assertEquals("Returned Ad should come from the repository", savedAd, returnedAd);
    }

    private AdCampaigns stubRepositoryToReturnAdOnSave() {
    	AdCampaigns ad = AdCampaignsUitl.createAd();
        when(adServerRepository.save(any(AdCampaigns.class))).thenReturn(ad);
        return ad;
    }
    
    
    @Test
    public void shouldSaveNewAd_GivenThereExistsOneWithTheSameId_ThenTheExceptionShouldBeThrown() throws Exception {
        stubRepositoryToReturnExistingAd();
        try {
        	adServerService.save(AdCampaignsUitl.createAd());
            fail("Expected exception");
        } catch (AdAlreadyExistsWithThePartnerIdException ignored) {
        }
        verify(adServerRepository, never()).save(any(AdCampaigns.class));
    }

    private void stubRepositoryToReturnExistingAd() {
        final AdCampaigns adCampaigns = AdCampaignsUitl.createAd();
        when(adServerRepository.findOne(adCampaigns.getPartner_id())).thenReturn(adCampaigns);
    }

    @Test
    public void shouldListAllAds_GivenThereExistSome_ThenTheCollectionShouldBeReturned() throws Exception {
        stubRepositoryToReturnExistingAds(10);
        Collection<AdCampaigns> list = adServerService.getAllAds();
        assertNotNull(list);
        assertEquals(10, list.size());
        verify(adServerRepository, times(1)).findAll();
    }

    private void stubRepositoryToReturnExistingAds(int howMany) {
        when(adServerRepository.findAll()).thenReturn(AdCampaignsUitl.createAdList(howMany));
    }

    @Test
    public void shouldListAllAds_GivenThereNoneExist_ThenTheEmptyCollectionShouldBeReturned() throws Exception {
        stubRepositoryToReturnExistingAds(0);
        Collection<AdCampaigns> list = adServerService.getAllAds();
        assertNotNull(list);
        assertTrue(list.isEmpty());
        verify(adServerRepository, times(1)).findAll();
    }
}
