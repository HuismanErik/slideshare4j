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

import com.googlecode.slideshare4j.api.impl.SlideShare4jV2Impl;
import com.googlecode.slideshare4j.transport.ITransport;

public class MockAPI2Impl extends SlideShare4jV2Impl{
	
	public MockAPI2Impl() {}
    
	public void setTransport(ITransport transport){
		this.transport =  transport;
	}
}
