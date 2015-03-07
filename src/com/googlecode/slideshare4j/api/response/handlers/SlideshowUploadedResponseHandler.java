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

import com.googlecode.slideshare4j.api.response.generated.SlideShowUploaded;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class SlideshowUploadedResponseHandler extends AbstractResponseHandler {

    private final SlideShowUploaded slideShowUploaded = new SlideShowUploaded();


    public void handleStartOf(String uri, String localName, String qName,
                              Attributes attributes) throws SAXException {

    }

    public SlideShowUploaded getSlideShowUploaded() {
        return slideShowUploaded;
    }

    public void handleEndOf(String uri, String localName, String qName)
            throws SAXException {
        if (localName.equals(SlideshowCheckFavoriteResponseHandler.TAG_SLIDESHOW_ID)) {
            slideShowUploaded.setSlideShowID(value.trim());
        }
        value = "";
    }

    @Override
    public Object getResult() {
        return this.getSlideShowUploaded();
    }

}
