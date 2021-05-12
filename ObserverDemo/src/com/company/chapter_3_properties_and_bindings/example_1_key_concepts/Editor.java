package com.company.chapter_3_properties_and_bindings.example_1_key_concepts;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Editor {
    private PropertyChangeSupport editorText = new PropertyChangeSupport(this);

    private String text;

    public void addPropertyChangeListener(PropertyChangeListener l) {
        editorText.addPropertyChangeListener(l);
    }
    public void removePropertyChangeListener(PropertyChangeListener l) {
        editorText.removePropertyChangeListener(l);
    }

    public void setEditorText(String newText)
    {
        String oldText = this.text;
        text = newText;
        editorText.firePropertyChange("textChange", oldText, newText);
    }

}
