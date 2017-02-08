package com.sample.adserver.controller;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sample.adserver.domain.AdCampaigns;
import com.sample.adserver.exceptions.AdAlreadyExistsWithThePartnerIdException;
import com.sample.adserver.exceptions.AdDoesNotExistsWithThePartnerIdException;
import com.sample.adserver.service.AdServerService;


@RestController
public class AdServerController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AdServerController.class);
	
	private final AdServerService adServerService;

	@Inject
	public AdServerController(final AdServerService adServerService) {
		this.adServerService = adServerService;
	}
	
	@RequestMapping(value = "/createAd", method = RequestMethod.POST)
    public AdCampaigns createAdCampaigns(@RequestBody @Valid final AdCampaigns adCampaigns) {
        LOGGER.debug("Received request to create the {}", adCampaigns);
        return adServerService.save(adCampaigns);
    }

    @RequestMapping(value = "/getAllAds", method = RequestMethod.GET)
    public List<AdCampaigns> getAllAds() {
        LOGGER.debug("Received request to list all Ads");
        return adServerService.getAllAds();
    }
    
    @RequestMapping(value = "/getAd/{partner_id}", method = RequestMethod.GET)
    public AdCampaigns getAd(@PathVariable("partner_id") String partner_id) {
        LOGGER.debug("Received request to get ad for passed parterId");
        return adServerService.getAd(partner_id);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleAdAlreadyExistsWithThePartnerIdException(AdAlreadyExistsWithThePartnerIdException e) {
        return e.getMessage();
    }
    
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleAdDoesNotExistsWithThePartnerIdException(AdDoesNotExistsWithThePartnerIdException e) {
        return e.getMessage();
    }
}
