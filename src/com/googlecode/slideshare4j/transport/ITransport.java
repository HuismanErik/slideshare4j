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

package com.googlecode.slideshare4j.transport;

import com.googlecode.slideshare4j.exceptions.SlideShare4jException;

import java.io.InputStream;
import java.net.Proxy;
import java.util.Map;

public interface ITransport {

    public static final int METHOD_GET = 0;

    public static final int METHOD_POST = 1;

    public static final int METHOD_UPLOAD = 2;

    public void setProxy(Proxy proxy);

    public InputStream get(String URL, Map<String, String> params) throws SlideShare4jException;

    public InputStream post(String URL, Map<String, String> params) throws SlideShare4jException;

    public InputStream upload(String URL, Map<String, String> params) throws SlideShare4jException;


}
