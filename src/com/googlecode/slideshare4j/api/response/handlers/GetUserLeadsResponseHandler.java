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

import com.googlecode.slideshare4j.api.response.generated.GeoData;
import com.googlecode.slideshare4j.api.response.generated.Lead;
import com.googlecode.slideshare4j.api.response.generated.Leads;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;


public class GetUserLeadsResponseHandler extends AbstractResponseHandler {

    public static final String TAG_LEAD = "Lead";

    public static final String TAG_GEO_DATA = "GeoData";

    public static final String TAG_CITY = "City";

    public static final String TAG_COUNTRY = "Country";

    public static final String TAG_REGION = "Region";

    public static final String TAG_RATING = "Rating";

    public static final String TAG_DELETED_AT = "DeletedAt";

    public static final String TAG_READ_AT = "ReadAt";

    public static final String TAG_PAID_AT = "PaidAt";

    private final Leads leads = new Leads();

    private Lead lead;

    private GeoData geoData;


    public void handleStartOf(String uri, String localName, String qName,
                              Attributes attributes) throws SAXException {
        if (localName.equals(TAG_LEAD)) {
            lead = new Lead();
        } else if (localName.equals(TAG_GEO_DATA)) {
            geoData = new GeoData();
        }
    }

    public Leads getLeads() {
        return leads;
    }

    public void handleEndOf(String uri, String localName, String qName)
            throws SAXException {
        if (localName.equals(TAG_LEAD)) {
            leads.getLead().add(lead);
            lead = null;
        } else if (localName.equals(TAG_GEO_DATA)) {
            lead.setGeoData(geoData);
            geoData = null;
        } else if (localName.equals(TAG_REGION)) {
            if (geoData != null) {
                geoData.setRegion(value);
            }
        } else if (localName.equals(TAG_COUNTRY)) {
            if (geoData != null) {
                geoData.setCountry(value);
            }
        } else if (localName.equals(TAG_CITY)) {
            if (geoData != null) {
                geoData.setCity(value);
            }
        } else {
            super.setValue(lead, localName, value);
        }
        value = "";
    }

    @Override
    public Object getResult() {
        return this.getLeads();
    }

}
