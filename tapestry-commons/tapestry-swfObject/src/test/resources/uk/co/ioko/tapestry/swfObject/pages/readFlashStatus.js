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

Tapestry.Initializer.readFlashStatus = function (flashId, outputId) {

    new PeriodicalExecuter(function(pe) {
        if ($(flashId).getFlashVars) {
            pe.stop();
            updateFlashVars(flashId, outputId);
        }
    }, 1);


};

function updateFlashVars(flashId, outputId) {
    var intro = new Element("p").update("Flashvars");
    $(outputId).insert(intro);

    Tapestry.debug("Inside updateFlashVars");
    // Need to wait until flash is loaded

    if ($(flashId).getFlashVars) {
        var flashVars = $(flashId).getFlashVars();


        var dl = new Element("dl");

        for (var item in flashVars) {
            var dt = new Element("dt").update(item);
            var dd = new Element("dd").update(flashVars[item]);

            Tapestry.debug("Item is "+ item);
            Tapestry.debug("Item value is " + flashVars[item]);

            $(dl).insert(dt);
            $(dl).insert(dd);
        }
        $(outputId).insert(dl);

    }
}

