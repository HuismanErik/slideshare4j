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


public class SlideshowsByTagParams extends RequestParams {

    private static final long serialVersionUID = 1L;

    public static final String PARAM_LIMIT = "limit";

    public static final String PARAM_OFFSET = "offset";

    public static final String PARAM_DETALED = "detailed";

    public Long getLimit() {
        return Long.parseLong(this.get(PARAM_LIMIT));
    }

    public void setLimit(Long limit) {
        this.put(PARAM_LIMIT, limit.toString());
    }

    public Long getOffset() {
        return Long.parseLong(this.get(PARAM_OFFSET));
    }

    public void setOffset(Long offset) {
        this.put(PARAM_OFFSET, offset.toString());
    }

    public boolean isDetailed() {
        return "1".equals(this.get(PARAM_DETALED));
    }

    public void setDetailed(boolean detailed) {
        this.put(PARAM_DETALED, detailed ? "1" : "0");
    }
}
