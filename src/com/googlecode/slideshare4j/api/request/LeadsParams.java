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


public class LeadsParams extends RequestParams {

    private static final long serialVersionUID = 1L;

    public static final String PARAM_BEGIN = "begin";

    public static final String PARAM_END = "end";

    public String getBegin() {
        return this.get(PARAM_BEGIN);
    }

    public void setBegin(String begin) {
        this.put(PARAM_BEGIN, begin);
    }

    public String getEnd() {
        return this.get(PARAM_END);
    }

    public void setEnd(String end) {
        this.put(PARAM_END, end);
    }

}
