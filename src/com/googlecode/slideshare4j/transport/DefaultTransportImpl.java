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

import com.googlecode.slideshare4j.api.SlideShareAPI;
import com.googlecode.slideshare4j.exceptions.SlideShare4jException;
import com.googlecode.slideshare4j.util.SlideShare4jUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DefaultTransportImpl extends AbstractTransport {

    private final Logger logger = Logger.getLogger(DefaultTransportImpl.class
            .getName());

    private URLConnection getConnection(String URL, Map<String, String> params, int method) throws NoSuchAlgorithmException, IOException {
        if (method == ITransport.METHOD_GET) {
            return getProxy() == null ? new URL(getURL(URL,
                    params)).openConnection() : new URL(getURL(URL, params))
                    .openConnection(getProxy());
        } else {
            return getProxy() == null ? new URL(
                    getURL(URL, null)).openConnection()
                    : new URL(getURL(URL, null)).openConnection(getProxy());
        }
    }

    public DefaultTransportImpl(String apiKey, String sharedSecret) {
        super(apiKey, sharedSecret);
    }

    public InputStream get(String URL, Map<String, String> params)
            throws SlideShare4jException {

        try {
            URLConnection conn = getConnection(URL, params, ITransport.METHOD_GET);
            logger.info("Fetching: " + conn.getURL());
            return conn.getInputStream();
        } catch (MalformedURLException e) {
            throw new SlideShare4jException(e.getMessage());
        } catch (IOException e) {
            throw new SlideShare4jException(e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            throw new SlideShare4jException(e.getMessage());
        }
    }

    public InputStream post(String URL, Map<String, String> params)
            throws SlideShare4jException {
        try {
            URLConnection conn = getConnection(URL, params, ITransport.METHOD_POST);
            logger.log(Level.FINEST, "Posting to: " + conn.getURL());
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            conn.getOutputStream().write(
                    SlideShare4jUtils.buildQueryString(params).getBytes());
            return conn.getInputStream();
        } catch (MalformedURLException e) {
            throw new SlideShare4jException(e.getMessage());
        } catch (IOException e) {
            throw new SlideShare4jException(e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            throw new SlideShare4jException(e.getMessage());
        }
    }

    private void writeFileUplaod(File file, OutputStream output, String separator, String charset) throws IOException {
        output.write((separator + "\r\n").getBytes());
        output.write(("Content-Disposition: form-data; name=\"slideshow_srcfile\"; filename=\""
                + file.getName()
                + "\" charset="
                + charset + "\r\n").getBytes());
        String type = URLConnection.guessContentTypeFromName(file.getName());
        output.write(("Content-Type: "
                + (type == null ? "application/octet-stream" : type) + "; charset=" + charset + "\r\n").getBytes());
        output.write("\r\n".getBytes());
        InputStream input = null;
        try {
            input = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            for (int length; (length = input.read(buffer)) > 0;) {
                output.write(buffer, 0, length);
            }
            output.flush();
        } finally {
            if (input != null)
                try {
                    input.close();
                } catch (IOException ioex) {
                    logger
                            .warning("File input stream close failed for "
                                    + file.getName()
                                    + ": "
                                    + ioex);
                }
        }
        output.write("\r\n".getBytes());// end of content
    }

    public InputStream upload(String URL, Map<String, String> params)
            throws SlideShare4jException {
        try {
            URLConnection conn = getConnection(URL, params, ITransport.METHOD_UPLOAD);
            logger.log(Level.FINEST, "Uploading to: " + conn.getURL());
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            String boundary = "---------------------------" + Long.toHexString(System.currentTimeMillis());
            String separator = "--" + boundary;
            String charset = params.get("charset") != null ? params
                    .get("charset") : "utf-8";
            conn.setRequestProperty("Content-Type",
                    "multipart/form-data; boundary=" + boundary);
            OutputStream output = conn.getOutputStream();

            try {
                // add parameters
                for (String param : params.keySet()) {
                    String value = params.get(param);
                    if (value == null) continue;
                    if (param.equals(SlideShareAPI.PARAM_SLIDESHOW_SRCFILE)) {
                        File file = new File(value);
                        writeFileUplaod(file, output, separator, charset);
                    } else {
                        output.write((separator + "\r\n").getBytes());
                        output.write(("Content-Disposition: form-data; name=\""
                                + param + "\"\r\n").getBytes());
                        output.write("\r\n".getBytes());
                        output.write((value + "\r\n").getBytes());
                    }
                }

                output.write((separator + "--\r\n").getBytes());// End of request

            } finally {
                if (output != null)
                    output.close();
            }
            return conn.getInputStream();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new SlideShare4jException(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            throw new SlideShare4jException(e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new SlideShare4jException(e.getMessage());
        }
    }

}
