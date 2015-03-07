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

package com.googlecode.slideshare4j.test.mocks;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

import com.googlecode.slideshare4j.api.impl.SlideShare4jV2Impl;
import com.googlecode.slideshare4j.exceptions.SlideShare4jException;
import com.googlecode.slideshare4j.transport.AbstractTransport;

public class MockTransportImpl extends AbstractTransport {

	public MockTransportImpl(String apiKey, String sharedSecret) {
		super(apiKey, sharedSecret);
	}

	public InputStream get(String URL, Map<String, String> params)
			throws SlideShare4jException {
		
		return getResponseForUrl(URL,params);
		
	}

	public InputStream post(String URL, Map<String, String> params)
			throws SlideShare4jException {
		return get(URL,params);
	}

	public InputStream upload(String URL, Map<String, String> params)
			throws SlideShare4jException {
		return get(URL,params);
	}
	
	private InputStream getResponseForUrl(String URL, Map<String,String> params) {
        
        if(params.containsKey("create_error_response")){
            return this.getClass().getResourceAsStream("resources/slideshare_service_error.xml");
        }else if(SlideShare4jV2Impl.GET_SLIDESHOW.equals(URL)){

            if(params.containsKey("slideshow_id")){
            	return this.getClass().getResourceAsStream("resources/get_slideshow.xml");
	        }else if(params.containsKey("slideshow_url")){
	        	return this.getClass().getResourceAsStream("resources/get_slideshow_by_url.xml");
	        }
            return null;

        }else{

            return this.getClass().getResourceAsStream("resources/"+URL.replace(SlideShare4jV2Impl.API_BASE_URL+"/","")+".xml");
        }

    }
}
