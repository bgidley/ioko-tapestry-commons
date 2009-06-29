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

Tapestry.ajaxRequest = function(url, successHandler)
{
    return new Ajax.Request(url, {
        method: "get",

        onSuccess: function(response, jsonResponse)
        {
            // When the page is unloaded, pending Ajax requests appear to terminate
            // as succesful (but with no reply value). Since we're trying to navigate
            // to a new page anyway, we just ignore those false success callbacks.
            // We have a listener for the window's "beforeunload" event that sets
            // this flag.

            if (Tapestry.windowUnloaded)
                return;

            if (! response.request.success())
            {
                Tapestry.error(Tapestry.Messages.ajaxRequestUnsuccessful);
                return;
            }

            try
            {
                // Re-invoke the success handler, capturing any exceptions.
                successHandler.call(this, response, jsonResponse);
            }
            catch (e)
            {
                Tapestry.error(Tapestry.Messages.clientException + e);
            }
        },
        onException: Tapestry.ajaxFailureHandler,
        onFailure: Tapestry.ajaxFailureHandler });
};