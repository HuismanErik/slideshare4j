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

package com.googlecode.slideshare4j.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public final class SlideShare4jUtils {

    public static final String SLIDESHARE_DATE_PATTERN = "EEE MMM dd HH:mm:ss Z yyyy";

    public static String buildUrl(String URL, Map<String, String> params) throws UnsupportedEncodingException {
        if (URL == null) return null;
        String qs = buildQueryString(params);
        return URL.indexOf("?") < 0 ? URL + "?" + qs : URL + "&" + qs;
    }

    public static String buildQueryString(Map<String, String> params) throws UnsupportedEncodingException {
        String qs = "";
        if (params == null) return qs;
        int i = 0;
        for (String param : params.keySet()) {
            qs += param + "=" + URLEncoder.encode(params.get(param), "UTF-8") + (i++ < (params.size() - 1) ? "&" : "");
        }
        return qs;
    }

    public static String getSlideshareHash(String ts, String secret) throws NoSuchAlgorithmException {
        MessageDigest algorithm = MessageDigest.getInstance("SHA1");
        return bytes2String(algorithm.digest((secret + ts).getBytes()));
    }

    public static String bytes2String(byte[] bytes) {
        StringBuilder string = new StringBuilder();
        for (byte b : bytes) {
            String hexString = Integer.toHexString(0x00FF & b);
            string.append(hexString.length() == 1 ? "0" + hexString : hexString);
        }
        return string.toString();
    }

    public static Date parseDate(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(SLIDESHARE_DATE_PATTERN);
        try {
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
            return null;
        }

    }

    public static String printDate(Date dt) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(SLIDESHARE_DATE_PATTERN);
        return simpleDateFormat.format(dt);
    }

    public static Boolean parseBoolean(String string) {
        return string.trim().toLowerCase().matches("y|yes|true|1");
    }

    public static String printBoolean(Boolean val) {
        return val ? "1" : "0";
    }

}
