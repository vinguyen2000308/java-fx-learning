package com.company.chapter_3_properties_and_bindings.example_4_javafx_beans.JavaFXBeanModelExample;

import javafx.beans.property.*;
import javafx.scene.paint.Color;

public class JavaFXBeanModelEagerlyInstantiated {

    private final IntegerProperty i = new SimpleIntegerProperty(this, "i", 0);
    private final StringProperty str = new SimpleStringProperty(this, "str", "Hello");
    private final ObjectProperty<Color> color = new SimpleObjectProperty<Color>(this, "color", Color.BLACK);

    public final int getI() {
        return i.get();
    }

    public final void setI(int i) {
        this.i.set(i);
    }

    public IntegerProperty iProperty() {
        return i;
    }

    public final String getStr() {
        return str.get();
    }

    public final void setStr(String str) {
        this.str.set(str);
    }

    public StringProperty strProperty() {
        return str;
    }

    public final Color getColor() {
        return color.get();
    }

    public final void setColor(Color color) {
        this.color.set(color);
    }

    public ObjectProperty<Color> colorProperty() {
        return color;
    }
}
