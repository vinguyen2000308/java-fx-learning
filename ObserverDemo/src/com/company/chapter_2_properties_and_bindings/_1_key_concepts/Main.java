package com.company.chapter_2_properties_and_bindings._1_key_concepts;

import java.util.Scanner;

public class Main  {

    // Observable and InvalidationListener
        // Receive a notification when the property or binding becomes invalidated
            // Becomes invalidated if its set() and setValue() is called with different value from its current help value
    // Observable and ChangeListener
        // Receive a notification when the property or binding changes from  one value to different value.

    // WriteableValue and ReadOnlyValue
        // Writeable -> calling set() on this property
        // ReadOnlyValue -> get()
    // JavaFX Properties
        // bind(ObservableValue<? extends T> observable)
            // unidirectional binding between the Property and the ObservableValue.
        // unbind()
            // releases the binding
        // isBound()
            // Check whether a unidirectional binding is in effect.
            // Once in effect, the set() and setValue() -> RuntimeException
        // bindBidirectional(Property<T> other)
            // Establish a bidirectional binding between the two Property objects.
        // unbindBidirectional(Property<T> other)
            // Releases it

    public static void main(String[] args) {
        // write your code here
        ReportChange reportChange = new ReportChange();
        Scanner scanner = new Scanner(System.in);
        Editor editor = new Editor();
        editor.addPropertyChangeListener(reportChange);
        while (true) {

            System.out.println("Enter Text");
            editor.setEditorText(scanner.nextLine());


        }
    }

}
