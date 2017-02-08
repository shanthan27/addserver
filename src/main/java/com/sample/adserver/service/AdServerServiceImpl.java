package com.sample.adserver.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.sample.adserver.domain.AdCampaigns;
import com.sample.adserver.exceptions.AdAlreadyExistsWithThePartnerIdException;
import com.sample.adserver.exceptions.AdDoesNotExistsWithThePartnerIdException;
import com.sample.adserver.repository.AdServerRepository;

@Service
@Validated
public class AdServerServiceImpl implements AdServerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdServerServiceImpl.class);
    private final AdServerRepository repository;
    
    @Inject
    public AdServerServiceImpl(final AdServerRepository repository) {
        this.repository = repository;
    }
    
    @Override
    @Transactional
	public AdCampaigns save(AdCampaigns adCampaigns) {
		LOGGER.debug("Creating {}", adCampaigns);
		AdCampaigns existing = repository.findOne(adCampaigns.getPartner_id());
        if (existing != null) {
            throw new AdAlreadyExistsWithThePartnerIdException(
                    String.format("There already exists ad with this partner id=%s", adCampaigns.getPartner_id()));
        }
        return repository.save(adCampaigns);
	}

	@Override
	@Transactional
	public List<AdCampaigns> getAllAds() {
		LOGGER.debug("Retrieving the list of all Ad Campaigns");
        return repository.findAll();
	}

	@Override
	@Transactional
	public AdCampaigns getAd(String partner_id) {
		AdCampaigns adCampaigns = repository.findOne(partner_id);
		if(adCampaigns == null){
			throw new AdDoesNotExistsWithThePartnerIdException(
                    String.format("There is no Ad with this partnar id=%s", partner_id));
		}
		return adCampaigns;
	}
}
