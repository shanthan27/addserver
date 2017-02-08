package com.sample.adserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.adserver.domain.AdCampaigns;

public interface AdServerRepository extends JpaRepository<AdCampaigns, String> {

}
