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

import com.googlecode.slideshare4j.api.response.generated.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;


public class GetSlideShowResponseHandler extends AbstractResponseHandler {

    public static final Object TAG_TAG = "Tag";

    public static final String ATTR_COUNT = "Count";

    public static final String ATTR_OWNER = "Owner";

    public static final Object TAG_RELATED_SLIDESHOW_ID = "RelatedSlideshowID";

    public static final String ATTR_RANK = "Rank";

    public static final Object TAG_TAGS = "Tags";

    public static final Object TAG_RELATED_SLIDESHOWS = "RelatedSlideshows";

    public static final Object TAG_SLIDESHOW = "Slideshow";

    public static final Object TAG_STATUS = "Status";

    public static final Object TAG_DOWNLOAD = "Download";

    public static final Object TAG_SLIDESHOW_TYPE = "SlideshowType";

    public static final Object TAG_INCONTEST = "InContest";

    public static final Object TAG_AUDIO = "Audio";

    public static final Object TAG_NUM_SLIDES = "NumSlides";

    public static final Object TAG_NUM_VIEWS = "NumViews";

    public static final Object TAG_NUM_FAVORITES = "NumFavorites";

    public static final Object TAG_NUM_COMMENTS = "NumComments";

    public static final Object TAG_NUM_DOWNLOADS = "NumDownloads";

    public static final Object TAG_PRIVACY_LEVEL = "PrivacyLevel";

    public static final Object TAG_FLAG_VISIBLE = "FlagVisible";

    public static final Object TAG_SHOW_ON_SS = "ShowOnSS";

    public static final Object TAG_SEC_URL = "SecretURL";

    public static final Object TAG_ALLOW_EMBED = "AllowEmbed";

    public static final Object TAG_SHARE_WITH_CONTACTS = "ShareWithContacts";

    public static final Object TAG_CREATED = "Created";

    public static final Object TAG_UPDATED = "Updated";

    private final Slideshow slideshow = new Slideshow();

    private Tag tag;

    private RelatedSlideshowID relatedSlideshowID;

    public void handleStartOf(String uri, String localName, String qName,
                              Attributes attributes) throws SAXException {
        if (localName.equals(TAG_TAGS)) {
            slideshow.setTags(new Tags());
        }
        if (localName.equals(TAG_RELATED_SLIDESHOWS)) {
            slideshow.setRelatedSlideshows(new RelatedSlideshows());
        } else if (localName.equals(TAG_TAG)) {
            tag = new Tag();
            tag.setCount(parseLong(attributes.getValue(ATTR_COUNT)));
            tag.setOwner(parseBoolean(attributes.getValue(ATTR_OWNER)));
        } else if (localName.equals(TAG_RELATED_SLIDESHOW_ID)) {
            relatedSlideshowID = new RelatedSlideshowID();
            relatedSlideshowID.setRank(parseInteger(attributes.getValue(ATTR_RANK)));
        }

    }

    public Slideshow getSlideshow() {
        return slideshow;
    }

    public void handleEndOf(String uri, String localName, String qName)
            throws SAXException {
        if (localName.equals(TAG_SLIDESHOW) || localName.equals(TAG_TAGS) || localName.equals(TAG_RELATED_SLIDESHOWS)) {
            return;
        } else if (localName.equals(TAG_TAG)) {
            tag.setValue(value);
            slideshow.getTags().getTag().add(tag);
        } else if (localName.equals(TAG_RELATED_SLIDESHOW_ID)) {
            relatedSlideshowID.setValue(value);
            slideshow.getRelatedSlideshows().getRelatedSlideshowID().add(relatedSlideshowID);
        } else if (localName.equals(TAG_STATUS)) {
            slideshow.setStatus(ConversionStatus.fromValue(parseInteger(value)));
        } else if (localName.equals(TAG_SLIDESHOW_TYPE)) {
            slideshow.setSlideshowType(SlideshowType.fromValue(parseInteger(value)));
        } else {
            super.setValue(slideshow, localName, value);
        }

        value = "";
    }

    @Override
    public Object getResult() {
        return this.getSlideshow();
    }

}
