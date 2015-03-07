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

package com.googlecode.slideshare4j.api.response.handlers;

import com.googlecode.slideshare4j.api.impl.SlideShare4jV2Impl;
import com.googlecode.slideshare4j.api.response.generated.Message;
import com.googlecode.slideshare4j.api.response.generated.SlideShareServiceError;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractResponseHandler extends DefaultHandler {

    private static final String TAG_SLIDESHARE_SERVICE_ERROR = "SlideShareServiceError";

    private static final String TAG_MESSAGE = "Message";

    private static final String ATTR_ID = "ID";

    private static final String RESPONSE_DATE_PATTERN = "EEE MMM dd HH:mm:ss Z yyyy";

    private Message message;

    private SlideShareServiceError slideShareServiceError;

    protected String value = "";

    private static final Logger logger = Logger.getLogger(AbstractResponseHandler.class
            .getName());

    // this is called when document is not well-formed:
    public void fatalError(SAXParseException ex) throws SAXException {
        logger.severe("FATAL_ERROR: [at " +
                ex.getLineNumber() + "] " + ex);
    }

    public void warning(SAXParseException ex) throws SAXException {
        logger.warning("WARNING: [at " +
                ex.getLineNumber() + "] " + ex);
    }

    @Override
    public void skippedEntity(String name) throws SAXException {
        logger.warning("WARNING: [Skipped entity " +
                name + "] ");
    }

    @Override
    public void ignorableWhitespace(char[] ch, int start, int length)
            throws SAXException {
        characters(ch, start, length);
    }

    public final Object getResponse() {
        if (slideShareServiceError != null) {
            return slideShareServiceError;
        } else {
            return getResult();
        }
    }

    public boolean isServiceError() {
        return slideShareServiceError != null;
    }

    protected abstract void handleStartOf(String uri, String localName, String qName, Attributes attrs) throws SAXException;

    protected abstract void handleEndOf(String uri, String localName, String qName) throws SAXException;

    public final void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (localName.equals(TAG_SLIDESHARE_SERVICE_ERROR)) {
            slideShareServiceError = new SlideShareServiceError();
        } else if (slideShareServiceError != null && localName.equals(TAG_MESSAGE)) {
            message = new Message();
            message.setId(parseInteger(attributes.getValue(ATTR_ID)));
        } else {
            handleStartOf(uri, localName, qName, attributes);
        }
    }

    public final void endElement(String uri, String localName, String qName) throws SAXException {
        if (slideShareServiceError == null) {
            handleEndOf(uri, localName, qName);
        } else if (localName.equals(TAG_MESSAGE) && message != null) {
            message.setValue(value);
            slideShareServiceError.setMessage(message);
        }
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        value += new String(ch, start, length);
    }

    abstract protected Object getResult();

    protected void setValue(Object obj, String name, String value) {
        //use java reflection to call setter
        Class<?> cls = obj.getClass();
        value = value.trim();
        if (value != null && value.length() <= 30 && value.length() > 1) {
            //try in this sequence, Date, Boolean, Long, Integer and then String
            Map<Class, Object> typeAndValues = new HashMap<Class, Object>();
            typeAndValues.put(Date.class, parseDate(value));
            typeAndValues.put(boolean.class, parseBoolean(value));
            typeAndValues.put(Boolean.class, parseBoolean(value));
            typeAndValues.put(long.class, parseLong(value));
            typeAndValues.put(Long.class, parseLong(value));
            typeAndValues.put(int.class, parseInteger(value));
            typeAndValues.put(Integer.class, parseInteger(value));

            for (Class type : typeAndValues.keySet()) {
                try {
                    Method toCall = cls.getMethod("set" + name, type);
                    toCall.invoke(obj, typeAndValues.get(type));
                    logger.log(Level.FINEST, "Found set" + name + "(" + type + ") on " + obj.getClass().getName());
                    return;
                } catch (Exception ex) {
                    logger.log(Level.FINEST, "Not Found set" + name + "(" + type + ") on " + obj.getClass().getName(), ex);
                }
            }
        }
        try {
            Method toCall = cls.getMethod("set" + name, String.class);
            toCall.invoke(obj, value);
        } catch (Exception ex) {
            logger.log(Level.FINEST, "Not Found set" + name + "(" + String.class + ") on " + obj.getClass().getName(), ex);
        }
    }

    public static AbstractResponseHandler getHandlerFor(String URL) {
        if (SlideShare4jV2Impl.GET_SLIDESHOW.equals(URL)) {
            return new GetSlideShowResponseHandler();
        } else if (SlideShare4jV2Impl.GET_SLIDESHOW_BY_GROUP.equals(URL)) {
            return new GetSlideShowsByGroupResponseHandler();
        } else if (SlideShare4jV2Impl.GET_SLIDESHOW_BY_TAG.equals(URL)) {
            return new GetSlideShowsByTagResponseHandler();
        } else if (SlideShare4jV2Impl.GET_SLIDESHOW_BY_USER.equals(URL)) {
            return new GetSlideShowsByUserResponseHandler();
        } else if (SlideShare4jV2Impl.GET_USER_CAMPAIGN_LEADS.equals(URL)) {
            return new GetUserLeadsResponseHandler();
        } else if (SlideShare4jV2Impl.GET_USER_CAMPAIGNS.equals(URL)) {
            return new GetUserCampaignsResponseHandler();
        } else if (SlideShare4jV2Impl.GET_USER_CONTACTS.equals(URL)) {
            return new GetUserContactsResponseHandler();
        } else if (SlideShare4jV2Impl.GET_USER_GROUPS.equals(URL)) {
            return new GetUserGroupsResponseHandler();
        } else if (SlideShare4jV2Impl.GET_USER_LEADS.equals(URL)) {
            return new GetUserLeadsResponseHandler();
        } else if (SlideShare4jV2Impl.GET_USER_TAGS.equals(URL)) {
            return new GetUserTagsResponseHandler();
        } else if (SlideShare4jV2Impl.SEARCH_SLIDESHOWS.equals(URL)) {
            return new SearchSlideShowsResponseHandler();
        } else if (SlideShare4jV2Impl.EDIT_SLIDESHOW.equals(URL)) {
            return new SlideshowEditedResponseHandler();
        } else if (SlideShare4jV2Impl.DELETE_SLIDESHOW.equals(URL)) {
            return new SlideshowDeletedResponseHandler();
        } else if (SlideShare4jV2Impl.UPLOAD_SLIDESHOW.equals(URL)) {
            return new SlideshowUploadedResponseHandler();
        } else if (SlideShare4jV2Impl.ADD_FAVORITE.equals(URL)) {
            return new SlideshowFavoritedResponseHandler();
        } else if (SlideShare4jV2Impl.CHECK_FAVORITE.equals(URL)) {
            return new SlideshowCheckFavoriteResponseHandler();
        }
        return null;
    }

    public static Date parseDate(String string) {
        SimpleDateFormat sdf = new SimpleDateFormat(RESPONSE_DATE_PATTERN);
        string = string.trim();
        try {
            return sdf.parse(string);
        } catch (Exception e) {
            logger.log(Level.FINEST, "String \"" + string + "\" is not valid date." + e);
            return null;
        }
    }

    public static Integer parseInteger(String string) {
        if (string == null)
            return null;
        string = string.trim();
        if (string.matches("[0-9]+")) {
            return new Integer(string);
        } else {
            logger.log(Level.FINEST, "String \"" + string + "\" is not valid integer.");
            return null;
        }
    }

    public static Long parseLong(String string) {
        if (string == null)
            return null;
        string = string.trim();
        if (string.matches("[0-9]+")) {
            return new Long(string);
        } else {
            logger.log(Level.FINEST, "String \"" + string + "\" is not valid long.");
            return null;
        }
    }

    public static Double parseDouble(String string) {
        if (string == null)
            return null;
        string = string.trim();
        if (string.replaceAll(",", "").matches("[0-9]+.?[0-9]+")) {
            return new Double(string);
        } else {
            logger.log(Level.FINEST, "String \"" + string + "\" is not valid double.");
            return null;
        }
    }

    public static Boolean parseBoolean(String string) {
        if (string == null)
            return null;
        string = string.trim();
        if (string.trim().toLowerCase().matches("y|yes|true|1")) {
            return true;
        } else {
            logger.log(Level.FINEST, "String \"" + string
                    + "\" is not valid boolean. Treating false.");
            return false;
        }
    }
}
