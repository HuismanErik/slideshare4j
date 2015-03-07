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

import com.googlecode.slideshare4j.api.response.generated.Meta;
import com.googlecode.slideshare4j.api.response.generated.Slideshows;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class SearchSlideShowsResponseHandler extends AbstractResponseHandler {

    public static final String TAG_META = "Meta";

    public static final String TAG_SLIDESHOWS = "Slideshows";

    private GetSlideShowResponseHandler slideshowHandler;

    private final Slideshows slideshows = new Slideshows();

    private Meta meta;


    public void handleStartOf(String uri, String localName, String qName,
                              Attributes attributes) throws SAXException {
        if (localName.equals(GetSlideShowResponseHandler.TAG_SLIDESHOW)) {
            slideshowHandler = new GetSlideShowResponseHandler();
            slideshowHandler.handleStartOf(uri, localName, qName, attributes);
        } else if (slideshowHandler != null) {
            slideshowHandler.handleStartOf(uri, localName, qName, attributes);
        } else if (localName.equals(TAG_META)) {
            meta = new Meta();
        }
    }

    public void characters(char[] ch, int start, int length)
            throws SAXException {
        if (slideshowHandler != null) {
            slideshowHandler.characters(ch, start, length);
        } else {
            value += new String(ch, start, length);
        }

    }

    public Slideshows getSlideshows() {
        return slideshows;
    }

    public void handleEndOf(String uri, String localName, String qName)
            throws SAXException {
        if (localName.equals(TAG_SLIDESHOWS)) {
            return;
        }
        if (localName.equals(GetSlideShowResponseHandler.TAG_SLIDESHOW)) {
            slideshows.getSlideshow().add(slideshowHandler.getSlideshow());
            slideshowHandler = null;
        } else if (localName.equals(TAG_META)) {
            slideshows.setMeta(meta);
            meta = null;
        } else if (slideshowHandler != null) {
            slideshowHandler.handleEndOf(uri, localName, qName);
        } else {
            super.setValue(meta, localName, value);
        }
        value = "";

    }

    @Override
    public Object getResult() {
        return this.getSlideshows();
    }

}
