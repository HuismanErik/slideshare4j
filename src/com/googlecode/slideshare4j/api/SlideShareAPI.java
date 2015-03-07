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
package com.googlecode.slideshare4j.api;

import com.googlecode.slideshare4j.api.request.*;
import com.googlecode.slideshare4j.api.response.generated.*;
import com.googlecode.slideshare4j.exceptions.SlideShareServiceException;

import java.net.Proxy;

public interface SlideShareAPI {

    String PARAM_USERNAME = "username";

    String PARAM_USERNAME_FOR = "username_for";

    String PARAM_PASSWORD = "password";

    String PARAM_SLIDESHOW_ID = "slideshow_id";

    String PARAM_SLIDESHOW_URL = "slideshow_url";

    String PARAM_TAG = "tag";

    String PARAM_GROUP_NAME = "group_name";

    String PARAM_CAMPAIGN_ID = "campaign_id";

    String PARAM_QUERY = "q";

    String PARAM_SLIDESHOW_TITLE = "slideshow_title";

    String PARAM_SLIDESHOW_SRCFILE = "slideshow_srcfile";

    String PARAM_SLIDESHOW_UPLOAD_URL = "upload_url";

    void setProxy(Proxy proxy);

    // 1.0
    Slideshow getSlideshow(String id, SlideshowParams options)
            throws SlideShareServiceException;

    // 1.0
    Slideshow getSlideshowByUrl(String url, SlideshowParams options)
            throws SlideShareServiceException;

    // 1.0
    SlideshowsByTag getSlideshowsByTag(String tagName,
                                              SlideshowsByTagParams params) throws SlideShareServiceException;

    SlideshowsByGroup getSlideshowsByGroup(String groupName,
                                                  SlideshowsByGroupParams params) throws SlideShareServiceException;

    // 1.0
    SlideshowsByUser getSlideshowsByUser(String userName,
                                                SlideshowsByUserParams params) throws SlideShareServiceException;

    // 1.0
    Slideshows searchSlideshows(String queryString,
                                       SlideshowsSearchParams params) throws SlideShareServiceException;

    // 1.0
    Groups getUserGroups(String userName, UserGroupsParams params)
            throws SlideShareServiceException;

    Contacts getUserContacts(String userName, UserContactsParams params)
            throws SlideShareServiceException;

    Tags getUserTags(String userName, String password,
                            UserTagsParams params) throws SlideShareServiceException;

    // 1.0
    SlideShowEdited editSlideshow(String userName, String password,
                                         String slideshowId, SlideshowUpdateParams params)
            throws SlideShareServiceException;

    // 1.0
    SlideShowDeleted deleteSlideshow(String userName, String password,
                                            String slideshowId) throws SlideShareServiceException;

    // 1.0
    SlideShowUploaded uploadSlideshow(String userName, String password,
                                             String title, String srcFile, String fromUrl,
                                             SlideshowUploadParams params) throws SlideShareServiceException;

    SlideShowFavorited addFavorite(String userName,
                                          String password, String slideshowId) throws SlideShareServiceException;

    SlideShowIsFavorite checkFavorite(String userName,
                                             String password, String slideshowId) throws SlideShareServiceException;

    Campaigns getUserCampaigns(String userName, String password)
            throws SlideShareServiceException;

    Leads getUserLeads(String userName, String password,
                              LeadsParams params) throws SlideShareServiceException;

    Leads getUserCampaignLeads(String userName, String password,
                                      String campaignId, LeadsParams params) throws SlideShareServiceException;

}
