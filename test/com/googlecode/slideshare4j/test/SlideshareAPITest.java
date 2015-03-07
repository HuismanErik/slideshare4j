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

package com.googlecode.slideshare4j.test;

import com.googlecode.slideshare4j.api.request.*;
import com.googlecode.slideshare4j.api.response.generated.*;
import com.googlecode.slideshare4j.exceptions.SlideShareServiceException;
import com.googlecode.slideshare4j.test.mocks.MockAPI2Impl;
import junit.framework.TestCase;

import com.googlecode.slideshare4j.test.mocks.MockTransportImpl;
import com.googlecode.slideshare4j.transport.ITransport;

public class SlideshareAPITest extends TestCase {

	private final ITransport transport = new MockTransportImpl("DUMMY_KEY","DUMMY_SECRET");

    private final MockAPI2Impl ss4jTestApi = new MockAPI2Impl();

    @Override
	protected void setUp() throws Exception {
		ss4jTestApi.setTransport(transport);
	}


    public void testGetSlideShowById() throws Exception{

        String id = "5417186";
        SlideshowParams params = new SlideshowParams();
        params.setDetailed(true);
        Slideshow slideshow = ss4jTestApi.getSlideshow(id,params);
        assertNotNull(slideshow);
        assertEquals(slideshow.getID(),id);

    }

    public void testGetSlideShowByUrl() throws Exception{

        String url = "http://www.slideshare.net/hajjflemings/the-workplace-of-the-future-where-does-your-personal-brand-fit";
        SlideshowParams params = new SlideshowParams();
        params.setDetailed(true);
        Slideshow slideshow = ss4jTestApi.getSlideshowByUrl(url,params);
        assertNotNull(slideshow);
        assertEquals(slideshow.getURL(),url);

    }

    public void testGetSlideShowsByTag() throws Exception{

        String tag = "personal";
        SlideshowsByTagParams params = new SlideshowsByTagParams();
        params.setDetailed(true);
        SlideshowsByTag slideshowsByTag = ss4jTestApi.getSlideshowsByTag(tag,params);
        assertNotNull(slideshowsByTag);
        assertEquals(tag,slideshowsByTag.getName());
        assertFalse(slideshowsByTag.getSlideshow().size() == 0);

    }

    public void testGetSlideShowsByGroup() throws Exception{

        String group = "javascript";
        SlideshowsByGroupParams params = new SlideshowsByGroupParams();
        params.setDetailed(true);
        SlideshowsByGroup slideshowsByGroups = ss4jTestApi.getSlideshowsByGroup(group,params);
        assertNotNull(slideshowsByGroups);
        assertEquals(group,slideshowsByGroups.getName());
        assertFalse(slideshowsByGroups.getSlideshow().size() == 0);

    }

    public void testGetSlideShowsByUser() throws Exception{

        String username = "douglascrockford";
        SlideshowsByUserParams params = new SlideshowsByUserParams();
        params.setDetailed(true);
        SlideshowsByUser slideshowsByUser = ss4jTestApi.getSlideshowsByUser(username,params);
        assertNotNull(slideshowsByUser);
        assertEquals(username,slideshowsByUser.getName());
        assertFalse(slideshowsByUser.getSlideshow().size() == 0);

    }

    public void testSearchSlideshows() throws Exception{

        String query = "javascript";
        SlideshowsSearchParams params = new SlideshowsSearchParams();
        params.setDetailed(true);
        Slideshows slideshows = ss4jTestApi.searchSlideshows(query, params);
        assertNotNull(slideshows);
        assertNotNull(slideshows.getMeta());
        assertEquals(slideshows.getMeta().getQuery(),query);
        assertFalse(slideshows.getMeta().getTotalResults() == 0);
        assertFalse(slideshows.getSlideshow().size() == 0);

    }

    public void testGetUserGroups() throws Exception{

        String userName = "jboutelle";
        UserGroupsParams params = new UserGroupsParams();
        Groups groups = ss4jTestApi.getUserGroups(userName, params);

        assertNotNull(groups);
        assertFalse(groups.getGroup().size() == 0);

    }

    public void testGetUserContacts() throws Exception{

        String userName = "jboutelle";
        UserContactsParams params = new UserContactsParams();
        Contacts contacts = ss4jTestApi.getUserContacts(userName, params);

        assertNotNull(contacts);
        assertFalse(contacts.getContact().size() == 0);

    }

    public void testGetUserTags() throws Exception{

        String userName = "rpatil82";
        UserTagsParams params = new UserTagsParams();
        Tags tags = ss4jTestApi.getUserTags(userName, "",params);
        assertNotNull(tags);
        assertFalse(tags.getTag().size() == 0);

    }

    public void testEditSlideShow() throws Exception{
        String id = "123456";
        String userName = "rpatil82";
        SlideshowUpdateParams params = new SlideshowUpdateParams();
        SlideShowEdited result = ss4jTestApi.editSlideshow(userName,"",id,params);
        assertNotNull(result);
        assertEquals(result.getSlideShowID(),id);
    }

    public void testDeleteSlideShow() throws Exception{
        String id = "123456";
        String userName = "rpatil82";
        SlideShowDeleted result = ss4jTestApi.deleteSlideshow(userName,"",id);
        assertNotNull(result);
        assertEquals(result.getSlideShowID(),id);
    }

    public void testAddFavorite() throws Exception{
        String id = "123456";
        String userName = "rpatil82";
        SlideShowFavorited result = ss4jTestApi.addFavorite(userName,"",id);
        assertNotNull(result);
        assertEquals(result.getSlideShowID(),id);
    }

    public void testCheckFavorite() throws Exception{
        String id = "5417186";
        String userName = "rpatil82";
        SlideShowIsFavorite result = ss4jTestApi.checkFavorite(userName,"",id);
        assertNotNull(result);
        assertEquals(result.getSlideShowID(),id);
        assertEquals(result.isFavorited(),Boolean.FALSE);
    }

    public void testUploadSlideShow() throws Exception{
        String id = "123456";
        String userName = "rpatil82";
        String title = "Uploaded";
        String srcFile = "/tmp/abc.ppt";
        String fromUrl = "http://www.clemson.edu/ces/powsys/2002/ppt/TB3/JS.ppt";
        SlideshowUploadParams params = new SlideshowUploadParams();
        params.setAllowEmbeds(true);
        SlideShowUploaded result = ss4jTestApi.uploadSlideshow(userName,"",title, srcFile, fromUrl, params);
        assertNotNull(result);
        assertEquals(result.getSlideShowID(),id);
    }

    public void testSlideShareServiceError() throws Exception{
        String id = "123456";
        SlideshowParams params = new SlideshowParams();
        params.put("create_error_response","1");
        try{
            Slideshow slideshow =  ss4jTestApi.getSlideshow(id,params);
            assertNull(slideshow);
        }catch (SlideShareServiceException exception){
            assertNotNull(exception.getMessage());
        }
    }
}
