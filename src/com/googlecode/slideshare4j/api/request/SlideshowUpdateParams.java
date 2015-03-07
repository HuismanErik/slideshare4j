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


public class SlideshowUpdateParams extends RequestParams {

    private static final long serialVersionUID = 1L;

    public static final String PARAM_TITLE = "slideshow_title";

    public static final String PARAM_DESC = "slideshow_description";

    public static final String PARAM_SS_TAGS = "slideshow_tags";

    public static final String PARAM_MARK_SS_PRIVATE = "make_slideshow_private";

    public static final String PARAM_GENRATE_SECRET_URL = "generate_secret_url";

    public static final String PARAM_ALLOW_EMBED = "allow_embeds";

    public static final String PARAM_SHARE_WITH_CONTACTS = "share_with_contacts";

    public String getSlideshowDescription() {
        return this.get(PARAM_DESC);
    }

    public void setSlideshowDescription(String slideshowDescription) {
        this.put(PARAM_DESC, slideshowDescription);
    }

    public String getSlideshowTitle() {
        return this.get(PARAM_TITLE);
    }

    public void setSlideshowTitle(String title) {
        this.put(PARAM_TITLE, title);
    }

    public String getSlideshowTags() {
        return this.get(PARAM_SS_TAGS);
    }

    public void setSlideshowTags(String slideshowTags) {
        this.put(PARAM_SS_TAGS, slideshowTags);
    }

    public boolean isMarkSlideshowPrivate() {
        return "Y".equals(this.get(PARAM_MARK_SS_PRIVATE));
    }

    public void setMarkSlideshowPrivate(boolean markSlideshowPrivate) {
        this.put(PARAM_MARK_SS_PRIVATE, markSlideshowPrivate ? "Y" : "N");
    }

    public boolean isGenerateSecretUrl() {
        return "1".equals(this.get(PARAM_GENRATE_SECRET_URL));
    }

    public void setGenerateSecretUrl(boolean generateSecretUrl) {
        this.put(PARAM_GENRATE_SECRET_URL, generateSecretUrl ? "1" : "0");
    }

    public boolean isAllowEmbeds() {
        return "1".equals(this.get(PARAM_ALLOW_EMBED));
    }

    public void setAllowEmbeds(boolean allowEmbeds) {
        this.put(PARAM_ALLOW_EMBED, allowEmbeds ? "1" : "0");
    }

    public boolean isShareWithContacts() {
        return "1".equals(this.get(PARAM_SHARE_WITH_CONTACTS));
    }

    public void setShareWithContacts(boolean shareWithContacts) {
        this.put(PARAM_SHARE_WITH_CONTACTS, shareWithContacts ? "1" : "0");
    }


}
