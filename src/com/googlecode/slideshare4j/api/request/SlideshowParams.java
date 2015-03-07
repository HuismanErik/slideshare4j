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

package com.googlecode.slideshare4j.api.request;

import com.googlecode.slideshare4j.api.SlideShareAPI;

public class SlideshowParams extends RequestParams {
             
    private static final long serialVersionUID = 1L;

    public static final String PARAM_EXCLUDE_TAGS = "exclude_tags";

    public static final String PARAM_DETAILED = "detailed";

    public String getUsername() {
        return this.get(SlideShareAPI.PARAM_USERNAME);
    }

    public void setUsername(String username) {
        this.put(SlideShareAPI.PARAM_USERNAME, username);
    }

    public String getPassword() {
        return this.get(SlideShareAPI.PARAM_PASSWORD);
    }

    public void setPassword(String password) {
        this.put(SlideShareAPI.PARAM_PASSWORD, password);
    }

    public boolean isExcludeTags() {
        return "1".equals(this.get(PARAM_EXCLUDE_TAGS));
    }

    public void setExcludeTags(boolean excludeTags) {
        this.put(PARAM_EXCLUDE_TAGS, excludeTags ? "1" : "0");
    }

    public boolean isDetailed() {
        return "1".equals(this.get(PARAM_DETAILED));
    }

    public void setDetailed(boolean detailed) {
        this.put(PARAM_DETAILED, detailed ? "1" : "0");
    }
}
