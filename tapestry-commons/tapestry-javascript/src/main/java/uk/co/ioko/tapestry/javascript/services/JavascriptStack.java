/*
 * Copyright (c) 2009 ioko365 Ltd
 *
 * This file is part of ioko tapestry-commons.
 *
 *     ioko tapestry-commons is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     ioko tapestry-commons is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with ioko tapestry-commons.  If not, see <http://www.gnu.org/licenses/>.
 */

package uk.co.ioko.tapestry.javascript.services;

import org.apache.tapestry5.Asset;
import org.apache.tapestry5.ioc.Invocation;
import org.apache.tapestry5.ioc.MethodAdvice;
import org.apache.tapestry5.ioc.MethodAdviceReceiver;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by IntelliJ IDEA. User: ben Date: Jul 7, 2009 Time: 2:07:57 PM
 */
public class JavascriptStack {

	private List<Asset> libraries;

	public JavascriptStack(final List<Asset> libraries) {

		List<String> filenames = new ArrayList<String>();
		this.libraries = new ArrayList<Asset>();
		for (Asset asset : libraries) {
			String fileName = asset.getResource().getFile();
			if (!filenames.contains(fileName)) {
				this.libraries.add(asset);
				filenames.add(fileName);
			}
		}
	}

	public void adviseGetJavascriptStack(MethodAdviceReceiver receiver) throws NoSuchMethodException {
		MethodAdvice advice = new MethodAdvice() {
			public void advise(Invocation invocation) {
				invocation.proceed();
				List<Asset> assets = (List<Asset>) invocation.getResult();

				// The response is FINAL so we can't change it we can however use our own list
				List<Asset> result = new ArrayList<Asset>(assets.size() + libraries.size());
			    result.addAll(assets);
				result.addAll(libraries);
				invocation.overrideResult(result);

			}
		};
		
		Method getJavascript = receiver.getInterface().getMethod("getJavascriptStack");
		receiver.adviseMethod(getJavascript, advice);
	}

}
