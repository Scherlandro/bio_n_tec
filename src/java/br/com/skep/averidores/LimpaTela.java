/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.skep.averidores;

import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;

/**
 *
 * @author Scherlandro
 */
public class LimpaTela {
    public void limpa(UIComponent component) {
    if (component instanceof EditableValueHolder) {
        EditableValueHolder evh = (EditableValueHolder) component;
        evh.setSubmittedValue(null);
        evh.setValue(null);
        evh.setLocalValueSet(false);
        evh.setValid(true);
    }
    if(component.getChildCount()>0){
        for (UIComponent child : component.getChildren()) {
            this.limpa(child);
        }
    }
}
}
