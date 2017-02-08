package com.sample.adserver.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class AdCampaigns {
	@Id
    @NotNull
    @Size(max = 64)
    @Column(name = "partner_id", nullable = false, updatable = false)
    private String partner_id;

    @NotNull
    @Size(max = 64)
    @Column(name = "duration", nullable = false)
    private String duration;
    
    @NotNull
    @Size(max = 400)
    @Column(name = "ad_content", nullable = false)
    private String ad_content;
    
    AdCampaigns() {
    }

    public AdCampaigns(final String partner_id, final String duration, final String ad_content) {
        this.partner_id = partner_id;
        this.duration = duration;
        this.ad_content = ad_content;
    }

	public String getPartner_id() {
		return partner_id;
	}

	public void setPartner_id(String partner_id) {
		this.partner_id = partner_id;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getAd_content() {
		return ad_content;
	}

	public void setAd_content(String ad_content) {
		this.ad_content = ad_content;
	}

	@Override
	public String toString() {
		return "AdCampaigns [partner_id=" + partner_id + ", duration=" + duration + ", ad_content=" + ad_content + "]";
	}
    
    
}
