/*
 * Copyright 2010 Rajendra Patil
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.googlecode.slideshare4j.api.response.handlers;

import com.googlecode.slideshare4j.api.response.generated.AmountWithCurrency;
import com.googlecode.slideshare4j.api.response.generated.Campaigns;
import com.googlecode.slideshare4j.api.response.generated.LeadCampaign;
import com.googlecode.slideshare4j.api.response.generated.PromotionCampaign;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class GetUserCampaignsResponseHandler extends AbstractResponseHandler {

    public static final String TAG_LEAD_CAMPAIGN = "LeadCampaign";

    public static final String TAG_PROMO_CAMPAIGN = "PromotionCampaign";

    public static final String TAG_MAX_DAILY = "MaximumDailySpend";

    public static final String TAG_COST_PER_LEAD = "CostPerLead";

    public static final String ATTR_CURRENCY = "Currency";

    public static final String TAG_STARTED_AT = "StartedAt";

    public static final String TAG_PAUSED_AT = "PausedAt";

    public static final String TAG_CREATED_AT = "CreatedAt";

    public static final String TAG_UPDATED_AT = "UpdatedAt";

    public static final String TAG_ENDED_AT = "EndedAt";

    public static final String TAG_FORM_BLOCKING = "FormBlocking";

    public static final String TAG_FOR_DOWNLOAD = "ForDownload";

    public static final String TAG_FOR_SIDEBAR = "ForSidebar";

    private final Campaigns campaigns = new Campaigns();

    private LeadCampaign leadCampaign;

    private AmountWithCurrency mdp;

    private AmountWithCurrency cpl;

    private PromotionCampaign promotionCampaign;


    public void handleStartOf(String uri, String localName, String qName,
                              Attributes attributes) throws SAXException {
        if (localName.equals(TAG_LEAD_CAMPAIGN)) {
            leadCampaign = new LeadCampaign();
        } else if (localName.equals(TAG_PROMO_CAMPAIGN)) {
            promotionCampaign = new PromotionCampaign();
        } else if (localName.equals(TAG_MAX_DAILY)) {
            mdp = new AmountWithCurrency();
            mdp.setCurrency(attributes.getValue("Currency"));
        } else if (localName.equals(TAG_COST_PER_LEAD)) {
            cpl = new AmountWithCurrency();
            cpl.setCurrency(attributes.getValue("Currency"));
        }
    }

    public Campaigns getCampaigns() {
        return campaigns;
    }

    public void handleEndOf(String uri, String localName, String qName)
            throws SAXException {
        if (localName.equals(TAG_LEAD_CAMPAIGN)) {
            campaigns.getLeadCampaign().add(leadCampaign);
            leadCampaign = null;
        } else if (localName.equals(TAG_PROMO_CAMPAIGN)) {
            campaigns.getPromotionCampaign().add(promotionCampaign);
            promotionCampaign = null;
        } else if (localName.equals(TAG_MAX_DAILY)) {
            mdp.setValue(parseDouble(value));
            if (leadCampaign != null) {
                leadCampaign.setMaximumDailySpend(mdp);
            } else if (promotionCampaign != null) {
                promotionCampaign.setMaximumDailySpend(mdp);
            }
        } else if (localName.equals(TAG_COST_PER_LEAD)) {
            cpl.setValue(parseDouble(value));
            if (leadCampaign != null) {
                leadCampaign.setCostPerLead(cpl);
            }
        } else {
            if (leadCampaign != null) {
                super.setValue(leadCampaign, localName, value);
            } else if (promotionCampaign != null) {
                super.setValue(promotionCampaign, localName, value);
            }
        }
        value = "";

    }

    @Override
    public Object getResult() {
        return this.getCampaigns();
    }

}
