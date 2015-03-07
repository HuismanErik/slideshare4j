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

package com.googlecode.slideshare4j.api.impl;

import com.googlecode.slideshare4j.api.SlideShareAPI;
import com.googlecode.slideshare4j.api.request.*;
import com.googlecode.slideshare4j.api.response.generated.*;
import com.googlecode.slideshare4j.api.response.handlers.SlideShare4jUnmarshaller;
import com.googlecode.slideshare4j.exceptions.SlideShare4jException;
import com.googlecode.slideshare4j.exceptions.SlideShareServiceException;
import com.googlecode.slideshare4j.transport.DefaultTransportImpl;
import com.googlecode.slideshare4j.transport.ITransport;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class SlideShare4jV2Impl implements SlideShareAPI {

    public static final String API_BASE_URL = "https://www.slideshare.net/api/2";

    public static final String GET_SLIDESHOW = API_BASE_URL + "/get_slideshow";
    public static final String GET_SLIDESHOW_BY_TAG = API_BASE_URL
            + "/get_slideshows_by_tag";
    public static final String GET_SLIDESHOW_BY_GROUP = API_BASE_URL
            + "/get_slideshows_by_group";
    public static final String GET_SLIDESHOW_BY_USER = API_BASE_URL
            + "/get_slideshows_by_user";
    public static final String SEARCH_SLIDESHOWS = API_BASE_URL
            + "/search_slideshows";
    public static final String GET_USER_GROUPS = API_BASE_URL
            + "/get_user_groups";
    public static final String GET_USER_CONTACTS = API_BASE_URL
            + "/get_user_contacts";
    public static final String GET_USER_TAGS = API_BASE_URL + "/get_user_tags";
    public static final String EDIT_SLIDESHOW = API_BASE_URL
            + "/edit_slideshow";
    public static final String DELETE_SLIDESHOW = API_BASE_URL
            + "/delete_slideshow";
    public static final String UPLOAD_SLIDESHOW = API_BASE_URL
            + "/upload_slideshow";
    public static final String ADD_FAVORITE = API_BASE_URL + "/add_favorite";
    public static final String CHECK_FAVORITE = API_BASE_URL
            + "/check_favorite";
    public static final String GET_USER_CAMPAIGNS = API_BASE_URL
            + "/get_user_campaigns";
    public static final String GET_USER_LEADS = API_BASE_URL
            + "/get_user_leads";
    public static final String GET_USER_CAMPAIGN_LEADS = API_BASE_URL
            + "/get_user_campaign_leads";

    protected ITransport transport;

    private final Logger logger = Logger.getLogger(SlideShare4jV2Impl.class.getName());

    public SlideShare4jV2Impl() {

    }

    public SlideShare4jV2Impl(String apiKey, String sharedSecret) {
        this.transport = new DefaultTransportImpl(apiKey, sharedSecret);
    }

    private RequestParams getOptionalParams(RequestParams params) {
        if (params == null) return new RequestParams();
        return params;
    }

    public SlideShowDeleted deleteSlideshow(String userName, String password,
                                            String slideshowId) throws SlideShareServiceException {

        Map<String, String> params = new HashMap<String, String>();
        params.put(PARAM_USERNAME, userName);
        params.put(PARAM_PASSWORD, password);
        params.put(PARAM_SLIDESHOW_ID, slideshowId);
        return (SlideShowDeleted) process(DELETE_SLIDESHOW, params, ITransport.METHOD_GET);
    }

    public Slideshow getSlideshow(String id, SlideshowParams params)
            throws SlideShareServiceException {
        params = (SlideshowParams) getOptionalParams(params);
        params.put(PARAM_SLIDESHOW_ID, id);
        return (Slideshow) process(GET_SLIDESHOW, params, ITransport.METHOD_GET);
    }

    public Slideshow getSlideshowByUrl(String url, SlideshowParams params)
            throws SlideShareServiceException {
        params = (SlideshowParams) getOptionalParams(params);
        params.put(PARAM_SLIDESHOW_URL, url);
        return (Slideshow) process(GET_SLIDESHOW, params, ITransport.METHOD_GET);
    }

    public SlideshowsByGroup getSlideshowsByGroup(String groupName,
                                                  SlideshowsByGroupParams params) throws SlideShareServiceException {
        params = (SlideshowsByGroupParams) getOptionalParams(params);
        params.put(PARAM_GROUP_NAME, groupName);
        return (SlideshowsByGroup) process(GET_SLIDESHOW_BY_GROUP, params, ITransport.METHOD_GET);
    }

    public SlideshowsByTag getSlideshowsByTag(String tagName,
                                              SlideshowsByTagParams params) throws SlideShareServiceException {
        params = (SlideshowsByTagParams) getOptionalParams(params);
        params.put(PARAM_TAG, tagName);
        return (SlideshowsByTag) process(GET_SLIDESHOW_BY_TAG, params, ITransport.METHOD_GET);
    }

    public SlideshowsByUser getSlideshowsByUser(String userName,
                                                SlideshowsByUserParams params) throws SlideShareServiceException {
        params = (SlideshowsByUserParams) getOptionalParams(params);
        params.put(PARAM_USERNAME_FOR, userName);
        return (SlideshowsByUser) process(GET_SLIDESHOW_BY_USER, params, ITransport.METHOD_GET);
    }

    public Leads getUserCampaignLeads(String userName, String password,
                                      String campaignId, LeadsParams params) throws SlideShareServiceException {
        params = (LeadsParams) getOptionalParams(params);

        params.put(PARAM_USERNAME, userName);
        params.put(PARAM_PASSWORD, password);
        params.put(PARAM_CAMPAIGN_ID, campaignId);
        return (Leads) process(GET_USER_CAMPAIGN_LEADS, params, ITransport.METHOD_POST);
    }

    public Campaigns getUserCampaigns(String userName, String password)
            throws SlideShareServiceException {
        Map<String, String> params = getOptionalParams(null);
        params.put(PARAM_USERNAME, userName);
        params.put(PARAM_PASSWORD, password);
        return (Campaigns) process(GET_USER_CAMPAIGNS, params, ITransport.METHOD_POST);
    }

    public Contacts getUserContacts(String userName, UserContactsParams params)
            throws SlideShareServiceException {
        params = (UserContactsParams) getOptionalParams(params);
        params.put(PARAM_USERNAME_FOR, userName);
        return (Contacts) process(GET_USER_CONTACTS, params, ITransport.METHOD_GET);
    }

    public Groups getUserGroups(String userName, UserGroupsParams params)
            throws SlideShareServiceException {
        params = (UserGroupsParams) getOptionalParams(params);
        params.put(PARAM_USERNAME_FOR, userName);
        return (Groups) process(GET_USER_GROUPS, params, ITransport.METHOD_GET);
    }


    public Leads getUserLeads(String userName, String password,
                              LeadsParams params) throws SlideShareServiceException {
        params = (LeadsParams) getOptionalParams(params);
        params.put(PARAM_USERNAME, userName);
        params.put(PARAM_PASSWORD, password);
        return (Leads) process(GET_USER_LEADS, params, ITransport.METHOD_POST);
    }

    public Tags getUserTags(String userName, String password,
                            UserTagsParams params) throws SlideShareServiceException {
        params = (UserTagsParams) getOptionalParams(params);
        params.put(PARAM_USERNAME, userName);
        params.put(PARAM_PASSWORD, password);
        return (Tags) process(GET_USER_TAGS, params, ITransport.METHOD_GET);
    }

    public SlideShowIsFavorite checkFavorite(String userName,
                                             String password, String slideshowId) throws SlideShareServiceException {
        Map<String, String> params = getOptionalParams(null);
        params.put(PARAM_USERNAME, userName);
        params.put(PARAM_PASSWORD, password);
        params.put(PARAM_SLIDESHOW_ID, slideshowId);
        return (SlideShowIsFavorite) process(CHECK_FAVORITE, params, ITransport.METHOD_GET);
    }

    public SlideShowFavorited addFavorite(String userName,
                                          String password, String slideshowId) throws SlideShareServiceException {
        Map<String, String> params = getOptionalParams(null);
        params.put(PARAM_USERNAME, userName);
        params.put(PARAM_PASSWORD, password);
        params.put(PARAM_SLIDESHOW_ID, slideshowId);
        return (SlideShowFavorited) process(ADD_FAVORITE, params, ITransport.METHOD_GET);
    }

    private Object process(String URL, Map<String, String> params, int httpMethod)
            throws SlideShareServiceException {
        try {
            logger.info("Requesting URL: " + URL);
            InputStream is = httpMethod == ITransport.METHOD_POST ? transport.post(URL, params) : (httpMethod == ITransport.METHOD_UPLOAD) ? transport.upload(URL, params) : transport.get(URL, params);
            // !FIXME this is a hack until ss api returns unique tag name
            // for each unique response, currently Tag, Group and User are
            // mixed up
            String replace = null, with =  null;
            if(URL.equals(GET_SLIDESHOW_BY_TAG)){
                replace = "Tag";
                with = "SlideshowsByTag";

            }else if (URL.equals(GET_SLIDESHOW_BY_GROUP)){
                replace = "Group";
                with = "SlideshowsByGroup";

            }else if (URL.equals(GET_SLIDESHOW_BY_USER)){
                replace = "User";
                with = "SlideshowsByUser";
            }else if (URL.equals(ADD_FAVORITE)){
                replace = "SlideShow";
                with = "SlideShowFavorited";
            }else if (URL.equals(CHECK_FAVORITE)){
                replace = "Slideshow";
                with = "SlideShowIsFavorite";
            }

            if(replace != null && with != null){
                BufferedReader reader = new BufferedReader(
						new InputStreamReader(is));
				String line;
				StringBuffer buffer = new StringBuffer();
				boolean replaced = false;
				while ((line = reader.readLine()) != null) {
					if (line.indexOf(replace) >= 0 && !replaced) {
						buffer.append(line.replaceFirst(replace, with));
						replaced = true;
					} else {
						buffer.append(line);
					}
				}
				buffer.replace(buffer.lastIndexOf(replace), buffer
						.lastIndexOf(replace)
						+ replace.length(), with);
				is = new ByteArrayInputStream(buffer.toString().getBytes());
            }

            SlideShare4jUnmarshaller unmarshaller = new SlideShare4jUnmarshaller(URL);

            Object response = unmarshaller.unmarshal(is);

            if (response instanceof SlideShareServiceError) {
                logger.warning("Got SlideShareServiceError for " + URL);
                SlideShareServiceError error = (SlideShareServiceError) response;
                Message message = error.getMessage();
                throw new SlideShareServiceException(message.getId(), message.getValue());
            }
            return response;
        } catch (SlideShare4jException e) {
            logger.warning("SlideShare4j exception occurred: " + e);
            e.printStackTrace();
            throw new SlideShareServiceException(-1, e.getMessage());
        } catch (Exception e) {
            logger.severe("Exception occurred: " + e);
            e.printStackTrace();
            throw new SlideShareServiceException(-1, e.getMessage());
        }
    }

    public Slideshows searchSlideshows(String queryString,
                                       SlideshowsSearchParams params) throws SlideShareServiceException {
        params = (SlideshowsSearchParams) getOptionalParams(params);
        params.put(PARAM_QUERY, queryString);
        return (Slideshows) process(SEARCH_SLIDESHOWS, params, ITransport.METHOD_GET);
    }

    public void setProxy(Proxy proxy) {
        this.transport.setProxy(proxy);
    }

    public SlideShowEdited editSlideshow(String userName, String password,
                                         String slideshowId, SlideshowUpdateParams params)
            throws SlideShareServiceException {
        params = (SlideshowUpdateParams) getOptionalParams(params);
        params.put(PARAM_USERNAME, userName);
        params.put(PARAM_PASSWORD, password);
        params.put(PARAM_SLIDESHOW_ID, slideshowId);
        return (SlideShowEdited) process(EDIT_SLIDESHOW, params, ITransport.METHOD_GET);
    }

    public SlideShowUploaded uploadSlideshow(String userName, String password,
                                             String title, String srcFile, String fromUrl,
                                             SlideshowUploadParams params) throws SlideShareServiceException {
        params = (SlideshowUploadParams) getOptionalParams(params);
        params.put(PARAM_USERNAME, userName);
        params.put(PARAM_PASSWORD, password);
        params.put(PARAM_SLIDESHOW_TITLE, title);
        params.put(PARAM_SLIDESHOW_SRCFILE, srcFile);
        params.put(PARAM_SLIDESHOW_UPLOAD_URL, fromUrl);
        return (SlideShowUploaded) process(UPLOAD_SLIDESHOW, params, srcFile != null ? ITransport.METHOD_UPLOAD : ITransport.METHOD_GET);
    }

}
