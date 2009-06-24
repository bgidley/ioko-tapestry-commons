/*
 * Copyright (c) 2009 ioko365 Ltd
 *
 * This file is part of ioko tapestry-commons.
 *
 *     Foobar is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Foobar is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with ioko tapestry-commons.  If not, see <http://www.gnu.org/licenses/>.
 */

package uk.co.ioko.tapestry.caching.services;

import org.apache.tapestry5.RenderSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.ioko.tapestry.caching.services.support.MethodCall;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class RenderSupportPlayer {

	private static final Logger logger = LoggerFactory.getLogger(RenderSupportPlayer.class);

	private RenderSupport renderSupport;

	public RenderSupportPlayer(RenderSupport renderSupport) {
		this.renderSupport = renderSupport;
	}

	public void playbackMethodCalls(List<MethodCall> methodCalls) {
		if (methodCalls == null) {
			// WOO-HOO! nothing to playback
		} else {
			// iterate through the method calls and use reflection to call them on RenderSupport
			for (MethodCall methodCall : methodCalls) {
				Class<RenderSupport> renderSupportType = RenderSupport.class;
				Object[] methodParams = methodCall.getParams();
				try {
					Method method = renderSupportType.getMethod(methodCall.getMethodName(), methodCall.getParamTypes());
					// Consider adding a (Object) here.
					// It is getting confused between Object[] and Object...
					method.invoke(renderSupport, methodParams);
				}
				catch (NoSuchMethodException e) {
					logger.error("{}", e);
					throw new RuntimeException(e);
				}
				catch (IllegalAccessException e) {
					logger.error("{}", e);
					throw new RuntimeException(e);
				}
				catch (InvocationTargetException e) {
					logger.error("{}", e);
					throw new RuntimeException(e);
				}
			}
		}
	}
}