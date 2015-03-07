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

import com.googlecode.slideshare4j.api.response.generated.Group;
import com.googlecode.slideshare4j.api.response.generated.Groups;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;


public class GetUserGroupsResponseHandler extends AbstractResponseHandler {

    private static final String TAG_GROUP = "Group";

    public static final Object TAG_NUM_POSTS = "NumPosts";

    public static final Object TAG_NUM_SLIDES = "NumSlideshows";

    public static final Object TAG_NUM_MEMBERS = "NumMembers";

    public static final Object TAG_QUERY_NAME = "QueryName";

    public static final Object TAG_URL = "URL";

    private final Groups groups = new Groups();

    private Group group;


    public void handleStartOf(String uri, String localName, String qName,
                              Attributes attributes) throws SAXException {
        if (localName.equals(TAG_GROUP)) {
            group = new Group();
        }
    }

    public Groups getGroups() {
        return groups;
    }

    public void handleEndOf(String uri, String localName, String qName)
            throws SAXException {
        if (localName.equals(TAG_GROUP)) {
            groups.getGroup().add(group);
            group = null;
        } else if (localName.equals(GetSlideShowsByGroupResponseHandler.TAG_NAME)) {
            group.setName(value);
        } else if (group != null) {
            super.setValue(group, localName, value);
        }
        value = "";

    }

    @Override
    public Object getResult() {
        return this.getGroups();
    }

}
