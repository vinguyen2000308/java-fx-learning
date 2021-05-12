package com.company.chapter_2_properties_and_bindings._1_key_concepts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ReportChange implements PropertyChangeListener {
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String textChangeProperty = evt.getPropertyName();
        if ("textChange".equals(textChangeProperty)) {
            System.out.println(evt.getOldValue() + " Changes to " + evt.getNewValue());
        }
    }
}
