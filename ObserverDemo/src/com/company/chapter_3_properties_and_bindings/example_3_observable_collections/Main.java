package com.company.chapter_3_properties_and_bindings.example_3_observable_collections;

import javafx.collections.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Main {
    public static void main(String[] args) {



        // Factory and Utility Methods in FXCollections
        // Change Listeners for Observable Collections
                //  Change Listeners for Observable Collections
                //  Change Events in MapChangeListener
                //  Change Events in SetChangeListener
                //  Change Events in ArrayChangeListener
        // Create Bindings  for Observable collections


        //////////////////////////////////////  Factory and Utility Methods in FXCollections \\\\\\\\\\\\\\\\\\\\\\\\\\\
        // copy(), fill(), replaceAll(), reverse(), rotate(), shuffle(), and sort()
        ObservableList<String> strings = FXCollections.observableArrayList();
        ObservableMap<String, String> map = FXCollections.observableHashMap();
        ObservableSet<Integer> set = FXCollections.observableSet();
        ObservableFloatArray array = FXCollections.observableFloatArray();

        /*list.addListener((ListChangeListener<String>) c -> {
            System.out.println("\tlist = " +
                    c.getList());
        });
        map.addListener((MapChangeListener<String, String>) c -> {
            System.out.println("\tmap = " +
                    c.getMap());
        });
        set.addListener((SetChangeListener<Integer>) c -> {
            System.out.println("\tset = " +
                    c.getSet());
        });
        array.addListener((observableArray,
                           sizeChanged, from, to) -> {
            System.out.println("\tarray = " +
                    observableArray);
        });
        manipulateList(list);
        manipulateMap(map);
        manipulateSet(set);
        manipulateArray(array);*/
        //////////////////////////////////////////////// Change Listeners for Observable Collections \\\\\\\\\\\\\\\\\\
        //////////////////////////////////////////////// Change Events in MapChangeListener          \\\\\\\\\\\\\\\\\\
        //////////////////////////////////////////////// Change Events in SetChangeListener          \\\\\\\\\\\\\\\\\\
        //////////////////////////////////////////////// Change Events in ArrayChangeListener        \\\\\\\\\\\\\\\\\\

        // addListener()
        // removeListener()
        // ListChangeListener, MapChangeListener, and SetChangeListener interfaces each have an onChanged()
        // callback method whose parameter is a nested Change class
        /*strings.addListener((Observable observable) -> {
            System.out.println("\tlist invalidated");
        });
        strings.addListener((ListChangeListener.Change<? extends String> change) -> {
            System.out.println("\tstrings = " + change.getList());
        });

        System.out.println("Calling add(\"First\"): ");
        strings.add("First");

        System.out.println("Calling add(0, \"Zeroth\"): ");
        strings.add(0, "Zeroth");

        System.out.println("Calling addAll(\"Second\"," + " \"Third\"): ");
        strings.addAll("Second", "Third");

        System.out.println("Calling set(1," + " \"New First\"): ");
        strings.set(1, "New First");

        final List<String> list = Arrays.asList("Second_1", "Second_2");
        System.out.println("Calling addAll(3, list): ");
        strings.addAll(3, list);
        System.out.println("Calling remove(2, 4): ");
        strings.remove(2, 4);

        final Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()) {
            final String next = iterator.next();
            if (next.contains("t")) {
                System.out.println("Calling remove()" +
                        " on iterator: ");
                iterator.remove();
            }
        }
        System.out.println("Calling removeAll(" + "\"Third\", \"Fourth\"): ");
        strings.removeAll("Third", "Fourth");*/
        /////////////////////////////// Change Listeners for Observable Collections - ListChangeListener \\\\\\\\\\\\\\\
            // It represents one or more discrete changes, each of which can be
                        // elements added
                        // elements removed
                        // elements replaced
                        // elements permuted

            //        next() and reset(): control a cursor that iterates through the discrete changes
            //        The cursor is positioned before the first discrete change when
            //          onChange() is calle
            //        List of discrete change -> wasAdded(), wasRemoved(),  wasReplaced() and wasPermuted() what kind of event is
            //        strings.addListener(new MyListener());
            //        System.out.println("Calling addAll(\"Zero\"," +
            //                " \"One\", \"Two\", \"Three\"): ");
            //        strings.addAll("Zero", "One", "Two", "Three");
            //        System.out.println("Calling" +
            //                " FXCollections.sort(strings): ");



                strings.addListener(new MyListener());
                System.out.println("Calling addAll(\"Zero\"," + " \"One\", \"Two\", \"Three\"): ");
                strings.addAll("Zero", "One", "Two", "Three");

                System.out.println("Calling" + " FXCollections.sort(strings): ");
                FXCollections.sort(strings);

                System.out.println("Calling set(1, \"Three_1\"): ");
                strings.set(1, "Three_1");


        // Create Bindings for Observable Collections
                //  Eagerly Instantiated Property
                    //  Each property is backed by an appropriate property type that is instantiated at construction time
                    // The property constructor with full context, including the bean, the property’s name, and the initial value to initialize the properties

                // Half-Lazily Instantiated Property
                    // The property is instantiated only if the setter is called with a value different from the default value or if the property getter is called
                    // UC: with many properties, only a few of which are set
                // Fully Lazily Instantiated Property
                    //  In this strategy, the property is instantiated only if the property getter is called.
                    //  UC: We don't need to instantiate the property when the setter is called.
                    //  We only need the property is instantiated only if the
                    //  property getter is called



    }

    static class  MyListener implements ListChangeListener<String> {

        @Override
        public void onChanged(Change<? extends String> change) {
            System.out.println("\tlist = " + change.getList());
            System.out.println(prettyPrint(change));
        }

        private String prettyPrint(Change<? extends String> change) {
            StringBuilder sb = new StringBuilder("\tChange event data:\n");
            int i = 0;
            while (change.next()) {
                sb.append("\t\tcursor = ")
                        .append(i++)
                        .append("\n");

                final String kind = change.wasPermutated() ? "permutated"
                        : change.wasReplaced() ? "replaced"
                        : change.wasRemoved() ? "removed"
                        : change.wasAdded() ? "added" : "none";

                sb.append("\t\tKind of change: ").append(kind).append("\n");
                sb.append("\t\tAffected range: [").append(change.getFrom()).append(", ").append(change.getTo()).append("]\n");

                if (kind.equals("added") || kind.equals("replaced")) {
                    sb.append("\t\tAdded size: ").append(change.getAddedSize()).append("\n");
                    sb.append("\t\tAdded sublist: ").append(change.getAddedSubList()).append("\n");
                }

                if (kind.equals("permutated")) {
                    StringBuilder permutationSB = new StringBuilder("[");
                    int from = change.getFrom();
                    int to = change.getTo();
                    for (int k = from; k < to; k++) {
                        int permutation = change.getPermutation(k);
                        permutationSB.append(k).append("->").append(permutation);
                        if (k < change.getTo() - 1) {
                            permutationSB.append(", ");
                        }
                    }
                    permutationSB.append("]");
                    String permutation =
                            permutationSB.toString();
                    sb.append("\t\tPermutation: ")
                            .append(permutation).append("\n");

                }
            }
            return sb.toString();
        }
    }



    private static void manipulateList(ObservableList<String> list) {
        System.out.println("Calling list.addAll(\"Zero\"," + " \"One\", \"Two\", \"Three\"):");
        list.addAll("Zero", "One", "Two", "Three");

        System.out.println("Calling copy(list," + " Arrays.asList(\"Four\", \"Five\")):");
        FXCollections.copy(list, Arrays.asList("Four", "Five"));

        System.out.println("Calling replaceAll(list," + " \"Two\", \"Two_1\"):");
        FXCollections.replaceAll(list, "Two", "Two_1");

        System.out.println("Calling reverse(list):");
        FXCollections.reverse(list);

        System.out.println("Calling rotate(list, 2):");
        FXCollections.rotate(list, 2);

        System.out.println("Calling shuffle(list):");
        FXCollections.shuffle(list);

        System.out.println("Calling shuffle(list," + " new Random(0L)):");
        FXCollections.shuffle(list, new Random(0L));

        System.out.println("Calling sort(list):");
        FXCollections.sort(list);


        System.out.println("Calling sort(list, c)" + " with custom comparator: ");
        FXCollections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String lhs, String rhs) {
                // Reverse the order
                return rhs.compareTo(lhs);
            }
        });
        System.out.println("Calling fill(list," + " \"Ten\"): ");
        FXCollections.fill(list, "Ten");
    }

    private static void manipulateMap(ObservableMap<String, String> map) {
        System.out.println("Calling map.put(\"Key\"," +
                " \"Value\"):");
        map.put("Key", "Value");
    }

    private static void manipulateSet(ObservableSet<Integer> set) {
        System.out.println("Calling set.add(1024):");
        set.add(1024);
    }

    private static void manipulateArray(ObservableFloatArray array) {
        System.out.println("Calling  array.addAll(3.14159f," +
                " 2.71828f):");
        array.addAll(3.14159f, 2.71828f);
    }
}
