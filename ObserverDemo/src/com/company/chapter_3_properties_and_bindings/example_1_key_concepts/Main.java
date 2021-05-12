package com.company.chapter_3_properties_and_bindings.example_1_key_concepts;

import java.util.Scanner;

public class Main  {

    // Observable and InvalidationListener
    // Observable and ChangeListener
    // Writeable Value and ReadOnlyValue
    // JavaFX Properties
        // bind(ObservableValue<? extends T> observable)
        // unbind()
        // isBound()
        // bindBidirectional(Property<T> other)
        // unbindBidirectional(Property<T> other)
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
