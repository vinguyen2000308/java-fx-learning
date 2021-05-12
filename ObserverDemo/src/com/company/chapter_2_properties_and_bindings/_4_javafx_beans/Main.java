package com.company.chapter_2_properties_and_bindings._4_javafx_beans;

import com.company.chapter_2_properties_and_bindings._4_javafx_beans.adapting_java_Beans.Person;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.adapter.JavaBeanStringProperty;
import javafx.beans.property.adapter.JavaBeanStringPropertyBuilder;

import java.beans.PropertyVetoException;

public class Main {

    // Eagerly Instantiated Property
        //  An appropriate property type that is instantiated at construction time
    // Half-Lazily Instantiated Property
        // The property is instantiated only if the setter is called with a value different
        // from the default value or if the property getter is called
    // Full-Lazily Instantiated Property
        //  the property is instantiated only if the property getter is called
            // We don't need to instantiate the property when the setter is called. -> We need something to store set value


    // select() -> not okay


    // name is not a bound property -> calling person.setName() does not automatically propagate the new value to the adapted nameProperty we must call fireValueChangedEvent()
    // For the bound property -> calling person.setAddress() propagates the new value to addressProperty automatically.
                        // Not fire a event but value is still change
     // For the constrained property phoneNumber, after we bound the adapted phoneNumberProperty to another stringProperty, calling person. setPhoneNumber() throws a PropertyVetoException, and the new value is rejected
                        // After we bound this property to a another property. Calling set on original bean will throw exception
                        // We can change constrained property by calling set() on new bind or delete "Another property"

    public static void main(String[] args) throws  NoSuchMethodException {
        adaptJavaBeansProperty();
        adaptBoundProperty();
        adaptConstrainedProperty();
    }


    private static void adaptJavaBeansProperty() throws NoSuchMethodException {
        Person person = new Person();
        JavaBeanStringProperty nameProperty = JavaBeanStringPropertyBuilder.create()
                        .bean(person)
                        .name("name")
                        .build();

        nameProperty.addListener((observable, oldValue, newValue) -> {
            System.out.println("JavaFX property "+ observable+" changed:");
            System.out.println("\toldValue = "+ oldValue+", newValue = "+newValue);
        });

        System.out.println("Setting name on the"+ " JavaBeans property");
        person.setName("Weiqi Gao");
        System.out.println("Calling fireValueChange");
        nameProperty.fireValueChangedEvent();
        System.out.println("nameProperty.get() = "+ nameProperty.get());


        System.out.println("Setting value on the"+ " JavaFX property");
        person.setName("Johan Vos");
//        nameProperty.fireValueChangedEvent();
        System.out.println("person.getName() = "+ person.getName());
        System.out.println("nameProperty.getName() = " + nameProperty.get());

    }

    private static void adaptBoundProperty() throws NoSuchMethodException {
        System.out.println();
        Person person = new Person();
        JavaBeanStringProperty addressProperty =
                JavaBeanStringPropertyBuilder.create()
                        .bean(person)
                        .name("address")
                        .build();
        addressProperty.addListener((observable, oldValue, newValue) -> {
            System.out.println("JavaFX property "+ observable+" changed:");
            System.out.println("\toldValue = "+ oldValue+", newValue = "+newValue);
        });
        System.out.println("Setting address on the"+ " JavaBeans property");
        person.setAddress("12345 main Street");
        System.out.println(person.getAddress());
    }

    private static void adaptConstrainedProperty() throws NoSuchMethodException {
        System.out.println();
        Person person = new Person();
        JavaBeanStringProperty phoneNumberProperty = JavaBeanStringPropertyBuilder.create()
                        .bean(person)
                        .name("phoneNumber")
                        .build();
        phoneNumberProperty.addListener((observable, oldValue, newValue) -> {
            System.out.println("JavaFX property "+ observable+" changed:");
            System.out.println("\toldValue = "+oldValue+", newValue = "+newValue);
        });

        System.out.println("Setting phoneNumber on the"+" JavaBeans property");
        try {
            person.setPhoneNumber("800-555-1212");
        } catch (PropertyVetoException e) {
            System.out.println("A JavaBeans property"+ " change is vetoed.");
        }
        System.out.println("Bind phoneNumberProperty"+ " to another property");
        SimpleStringProperty stringProperty = new SimpleStringProperty("866-555-1216");
        phoneNumberProperty.bind(stringProperty);

        System.out.println("Setting phoneNumber on the"+ " JavaBeans property");
        System.out.println("Phone Number " + phoneNumberProperty.get());
        stringProperty.set("888-555-12167");

        System.out.println("person.getPhoneNumber() = "+person.getPhoneNumber());
    }
}

