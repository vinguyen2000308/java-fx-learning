package com.company.chapter_3_properties_and_bindings.example_3_creating_bindings;

import javafx.beans.InvalidationListener;
import javafx.beans.binding.*;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;

public class Main {



    private static IntegerProperty i1;

    // Type Specific Specializations
    // Factory Method in Bindings
    // Creating Bindings with Fluent API



    // Java Binding
        // isValid()
        // invalidate()
        // ObservableList<?> getDependencies()
        // dispose() to clean up resource
        /*Each dependency can send invalidation events to the Binding,
        making it invalidated. When the value of the Binding is queried through the
        get() or getValue() call, if it is invalidated, its value is recalculated based on the
        dependencies’ values. This value will be cached and used in subsequent value queries,
        until the Binding becomes invalidated again. This lazy value evaluation is what makes
        the JavaFX properties and bindings framework efficient. Attaching a ChangeListener
        forces eager evaluation.*/

    ////////////////////////////////////////////////// Creating a custom binding \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    // All bindings are are custom bindings and there are several ways to create them:
        //  Extend Abstract Base Class such as Double binding
        //  Using factory methods in the utility class of bindings
        //  Using fluent API method in the property and binding class

    ///////////////////////////////////////////////////Type Specializations\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    // These are specialized classes of the generic types Property<T> and Bindings<T>
    // => Direct use of generic type is inefficients
    // ====> BooleanProperty, IntegerProperty, LongProperty, FloatProperty, DoubleProperty, StringProperty, and ObjectProperty



    public static void main(String[] args) {

        // Example 1: Create binding
         /* final DoubleProperty x = new SimpleDoubleProperty(null, "x", 2.0);
        final DoubleProperty y = new SimpleDoubleProperty(null, "y", 3.0);
        System.out.println("Creating a binding with X and Y ");
        DoubleBinding area = new DoubleBinding() {
            {
                super.bind(x,y);
            }
            @Override
            protected double computeValue() {
                return x.get() * y.get();
            }
        };
        System.out.println("X value " + x.get());
        System.out.println("Y value "  + y.get());
        System.out.println("area.get() = " + area.get());
        System.out.println("Setting x to 5");
        x.set(5);
        System.out.println("Setting y to 7");
        y.set(7);
        System.out.println("area.get() = " + area.get());*/

        // Example 2: Factory Method Binding
        // add(), subtract(), multiply(), and divide()
        // and(), or(), and not(), min(), max(), and negate();
        // String length(), isEmpty(), and isNotEmpty()
        // Relational operations: equal(), equalIgnoreCase(), greaterThan(), graterThanOrEqual(), lessThan(), lessThanOrEqual(),notEqualIgnoreCase() ,  notEqual()
        // There are a set of factory method like: createDoubleBinding()
        // DoubleBinding area = Bindings.createDoubleBinding(() -> {
        //      return x.get() * y.get();
        // }, x, y);
        // Example 3: Create Binding with the Fluent API
        // The fluent API for creating bindings is embodied in the IntegerExpression series of classes. These expression classes are superclasses
        //of both the property classes and the binding classes

        // Note: It should be noted that the fluent API has its limitations. As the relationship becomes
        //      more complicated or goes beyond the available operators, the direct extension method is
        //      preferred


        IntegerProperty x1 = new SimpleIntegerProperty(0);
        IntegerProperty y1 = new SimpleIntegerProperty(0);
        IntegerProperty x2 = new SimpleIntegerProperty(0);
        IntegerProperty y2 = new SimpleIntegerProperty(0);
        IntegerProperty x3 = new SimpleIntegerProperty(0);
        IntegerProperty y3 = new SimpleIntegerProperty(0);
        final NumberBinding area = x1.multiply(y2)
                .add(x2.multiply(y3))
                .add(x3.multiply(y1))
                .subtract(x1.multiply(y3))
                .subtract(x2.multiply(y1))
                .subtract(x3.multiply(y2))
                .divide(2.0D);
        StringExpression output = Bindings.format(
                "For A(%d,%d), B(%d,%d), C(%d,%d)," +
                        " the area of triangle ABC is %3.1f",
                x1, y1, x2, y2, x3, y3, area);
        x1.set(0);
        y1.set(0);
        x2.set(6);
        y2.set(0);
        x3.set(4);
        y3.set(3);
        System.out.println(output.get());
        x1.set(1);
        y1.set(0);
        x2.set(2);
        y2.set(2);
        x3.set(0);
        y3.set(1);
        System.out.println(output.get());

        // area = sqrt(s * (s – a) * (s – b) * (s – c))
        // s = (a + b + c) / 2

        DoubleProperty a = new SimpleDoubleProperty(0);
        DoubleProperty b = new SimpleDoubleProperty(0);
        DoubleProperty c = new SimpleDoubleProperty(0);


        // DirectBinding
        DoubleBinding area1 = new DoubleBinding() {
            @Override
            protected double computeValue() {
                double a0 = a.get();
                double b0 = b.get();
                double c0 = c.get();
                if ((a0 + b0 > c0) && (b0 + c0 > a0) && (c0 + a0 > b0)) {
                    double s = (a0 + b0 + c0) / 2.0D;
                    return Math.sqrt(s * (s - a0) * (s - b0) * (s - c0));
                } else {
                    return 0.0D;
                }
            }

        };
        // Fluent API
        DoubleBinding s = a.add(b).add(c).divide(2);
        final DoubleBinding areaSquared = new When(
                a.add(b).greaterThan(c)
                        .and(b.add(c).greaterThan(a))
                        .and(c.add(a).greaterThan(b)))
                .then(s.multiply(s.subtract(a))
                        .multiply(s.subtract(b))
                        .multiply(s.subtract(c)))
                .otherwise(0.0D);
        a.set(3);
        b.set(4);
        c.set(5);
        System.out.printf("Given sides a = %1.0f," +
                        " b = %1.0f, and c = %1.0f," +
                        " the area of the triangle is" +
                        " %3.2f\n", a.get(), b.get(), c.get(),
                Math.sqrt(areaSquared.get()));

        a.set(2);
        b.set(2);
        c.set(2);
        System.out.printf("Given sides a = %1.0f," +
                        " b = %1.0f, and c = %1.0f," +
                        " the area of the triangle is" +
                        " %3.2f\n", a.get(), b.get(), c.get(),
                Math.sqrt(areaSquared.get()));


        
        createProperty();
        addAndRemoveInvalidationListener();
        addAndRemoveChangeListener();
        bindAndUnbindOnePropertyToAnother();




    }


    //////////////////// Binding \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    private static void addAndRemoveInvalidationListener() {
        System.out.println();
        final InvalidationListener invalidationListener =
                observable -> {
                    System.out.println(
                            "The observable has been " +
                                    "invalidated: " +
                                    observable + ".");
                };
        i1.addListener(invalidationListener);
        System.out.println("Added invalidation listener.");
        System.out.println("Calling i1.set(2048).");
        i1.set(2048);
        System.out.println("Calling i1.setValue(3072).");
        i1.setValue(3072);
        i1.removeListener(invalidationListener);
        System.out.println("Removed invalidation listener.");
        System.out.println("Calling i1.set(4096).");
        i1.set(4096);


    }
    private static void addAndRemoveChangeListener() {
        System.out.println();
        final ChangeListener<Number> changeListener =
                (observableValue,
                 oldValue,
                 newValue) -> {
                    System.out.println(
                            "The observableValue has " +
                                    "changed: oldValue = " +
                                    oldValue +
                                    ", newValue = " +
                                    newValue);
                };
        i1.addListener(changeListener);
        System.out.println("Added change listener.");
        System.out.println("Calling i1.set(5120).");
        i1.set(5120);
        i1.removeListener(changeListener);
        System.out.println("Removed change listener.");
        System.out.println("Calling i1.set(6144).");
        i1.set(6144);

    }
    private static void bindAndUnbindOnePropertyToAnother() {
        System.out.println();
        IntegerProperty i2 = new SimpleIntegerProperty(0);
        System.out.println("i2.get() = " + i2.get());
        System.out.println("Binding i2 to i1.");
        i2.bind(i1);
        System.out.println("i2.get() = " + i2.get());
        System.out.println("Calling i1.set(7168).");
        i1.set(7168);
        System.out.println("i2.get() = " + i2.get());
        System.out.println("Unbinding i2 from i1.");
        i2.unbind();
        System.out.println("i2.get() = " + i2.get());
        System.out.println("Calling i1.set(8192).");
        i1.set(8192);
        System.out.println("i2.get() = " + i2.get());
    }

    public static void createProperty() {
        i1 = new SimpleIntegerProperty(1024);
        System.out.println("i1 = " + i1);
        System.out.println("i1.get() = " + i1.get());
        System.out.println("i1.getValue() = "
                + i1.getValue());

    }

}
