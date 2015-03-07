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

import com.googlecode.slideshare4j.api.response.generated.Tag;
import com.googlecode.slideshare4j.api.response.generated.Tags;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class GetUserTagsResponseHandler extends AbstractResponseHandler {

    private final Tags tags = new Tags();

    private Tag tag;


    public void handleStartOf(String uri, String localName, String qName,
                              Attributes attributes) throws SAXException {
        if (localName.equals(GetSlideShowResponseHandler.TAG_TAG)) {
            tag = new Tag();
            tag.setCount(parseLong(attributes.getValue(GetSlideShowResponseHandler.ATTR_COUNT)));
        }
    }

    public Tags getTags() {
        return tags;
    }

    public void handleEndOf(String uri, String localName, String qName)
            throws SAXException {
        if (localName.equals(GetSlideShowResponseHandler.TAG_TAG)) {
            tag.setValue(value);
            tags.getTag().add(tag);
            tag = null;
        }
        value = "";
    }

    @Override
    public Object getResult() {
        return this.getTags();
    }

}
