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


public class SlideshowsSearchParams extends RequestParams {

    public static final String SORT_BY_RELEVANCE = "relevance";

    public static final String SORT_BY_MOST_VIEWED = "mostviewed";

    public static final String SORT_BY_MOST_DOWNLOADED = "mostdownloaded";

    public static final String SORT_BY_MOST_LATEST = "latest";

    public static final String UPLOAD_PERIOD_ANY = "any";

    public static final String UPLOAD_PERIOD_WEEK = "week";

    public static final String UPLOAD_PERIOD_MONTH = "month";

    public static final String UPLOAD_PERIOD_YEAR = "year";

    public static final String SEARCH_TYPE_TEXT = "text";

    public static final String SEARCH_TYPE_TAG = "tag";

    public static final String FILE_FORMAT_ALL = "all";

    public static final String FILE_FORMAT_PDF = "pdf";

    public static final String FILE_FORMAT_PPT = "ppt";

    public static final String FILE_FORMAT_ODP = "odp";

    public static final String FILE_FORMAT_PPS = "pps";

    public static final String FILE_FORMAT_POT = "pot";

    public static final String FILE_TYPE_ALL = "all";

    public static final String FILE_TYPE_PRESENTATIONS = "presentations";

    public static final String FILE_TYPE_DOCUMENTS = "documents";

    public static final String FILE_TYPE_WEBINARS = "webinars";

    public static final String FILE_TYPE_VIDEOS = "videos";


    private static final long serialVersionUID = 1L;

    public static final String PARAM_PAGE = "page";

    public static final String PARAM_ITEMS_PER_PAGE = "items_per_page";

    public static final String PARAM_LANGUAGE = "lang";

    public static final String PARAM_SORT_BY = "sort";

    public static final String PARAM_UPLOAD_PERIOD = "upload_date";

    public static final String PARAM_SEARCH_TYPE = "what";

    public static final String PARAM_DOWNLOADABLE = "download";

    public static final String PARAM_FILE_FORMAT = "format";

    public static final String PARAM_FILE_TYPE = "file_type";

    public static final String PARAM_CC = "cc";

    public static final String PARAM_CC_ADAPT = "cc_adapt";

    public static final String PARAM_CC_COMMERCIAL = "cc_commercial";

    public static final String PARAM_DETAILED = "detailed";

    public int getPage() {
        return Integer.parseInt(this.get(PARAM_PAGE));
    }

    public void setPage(int page) {
        this.put(PARAM_PAGE, "" + page);
    }

    public int getItemsPerPage() {
        return Integer.parseInt(this.get(PARAM_ITEMS_PER_PAGE));
    }

    public void setItemsPerPage(int itemsPerPage) {
        this.put(PARAM_ITEMS_PER_PAGE, "" + itemsPerPage);
    }

    public String getLanguage() {
        return this.get(PARAM_LANGUAGE);
    }

    public void setLanguage(String language) {
        this.put(PARAM_LANGUAGE, language);
    }

    public String getSortBy() {
        return this.get(PARAM_SORT_BY);
    }

    public void setSortBy(String sort) {
        this.put(PARAM_SORT_BY, sort);
    }

    public String getUploadPeriod() {
        return this.get(PARAM_UPLOAD_PERIOD);
    }

    public void setUploadPeriod(String uploadPeriod) {
        this.put(PARAM_UPLOAD_PERIOD, uploadPeriod);
    }

    public String getSearchType() {
        return this.get(PARAM_SEARCH_TYPE);
    }

    public void setSearchType(String searchType) {
        this.put(PARAM_SEARCH_TYPE, searchType);
    }

    public boolean isDownloadable() {
        return "1".equals(this.get(PARAM_DOWNLOADABLE));
    }

    public void setDownloadable(boolean downloadable) {
        this.put(PARAM_DOWNLOADABLE, (downloadable ? "1" : "0"));
    }

    public String getFileFormat() {
        return this.get(PARAM_FILE_FORMAT);
    }

    public void setFileFormat(String fileFormat) {
        this.put(PARAM_FILE_FORMAT, fileFormat);
    }

    public String getFileType() {
        return this.get(PARAM_FILE_TYPE);
    }

    public void setFileType(String fileType) {
        this.put(PARAM_FILE_TYPE, fileType);
    }

    public boolean isCreativeCommons() {
        return "1".equals(this.get(PARAM_CC));
    }

    public void setCreativeCommons(boolean creativeCommons) {
        this.put(PARAM_CC, (creativeCommons ? "1" : "0"));
    }

    public boolean isCreativeCommonsAdapt() {
        return "1".equals(this.get(PARAM_CC_ADAPT));
    }

    public void setCreativeCommonsAdapt(boolean creativeCommonsAdapt) {
        this.put(PARAM_CC_ADAPT, (creativeCommonsAdapt ? "1" : "0"));
    }

    public boolean isCreativeCommonsCommercial() {
        return "1".equals(this.get(PARAM_CC_COMMERCIAL));
    }

    public void setCreativeCommonsCommercial(boolean creativeCommonsCommercial) {
        this.put(PARAM_CC_COMMERCIAL, (creativeCommonsCommercial ? "1" : "0"));
    }

    public boolean isDetailed() {
        return "1".equals(this.get(PARAM_DETAILED));
    }

    public void setDetailed(boolean detailed) {
        this.put(PARAM_DETAILED, (detailed ? "1" : "0"));
    }


}
