/* 
 * Copyright (C) 2014 rafa
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

var publicacionControl = function (strClase) {
    this.clase = strClase;
};
publicacionControl.prototype = new control('publicacion');
publicacionControl.prototype.getClassNamePublicacion = function () {
    return this.getClassName() + "Control";
};
publicacionControl.prototype.duplicate = function (place, id, oModel, oView) {
    var thisObject = this;
    $(place).empty();
    var oDocumentoModel = oModel;
    oDocumentoModel.loadAggregateViewOne(id);
    $(place).append(oView.getPanel("Borrado de " + this.clase, oView.getObjectTable(oDocumentoModel.getCachedPrettyFieldNames(), oDocumentoModel.getCachedOne(), oDocumentoModel.getCachedFieldNames())));
    $(place).append('<div id=\"result\">¿Seguro que desea duplicar el registro?</div>');
    $(place).append('<a class="btn btn-danger" id="btnDuplicarSi" href="#">Sí, duplicar</a>');
    $('#btnDuplicarSi').unbind('click');
    $('#btnDuplicarSi').click(function (event) {
        resultado = oModel.duplicateOne(id);
        oView.doResultOperationNotifyToUser(place, resultado["status"], resultado["message"], resultado["message"], false);
        return false;
    });
};
var oPublicacionControl = new publicacionControl('publicacion');