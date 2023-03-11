package previewstuff;

sealed interface Transporter permits Car, Truck {}
record Car(String color, int seats) implements Transporter {}
record Truck(int payload) implements Transporter {}

public class Example {
  public static void main(String[] args) {
    Object obj = "Hello";
    // normal switch only permits int or smaller numeric types, String, enum
    switch (obj) {
      case String s -> System.out.println("It's text");
      case Number n -> System.out.println("It's a number");
      default -> System.out.println("Hmm, not sure how to handle that!");
    }
    String msg = switch (obj) {
      case String s -> "It's text";
      case Number n -> "It's a number";
      default -> "Hmm, not sure how to handle that!";
    };
    System.out.println("message is " + msg);

    Transporter t = new Car("Red", 5);
    int capacity = switch (t) {
      case null -> 0; // null match (null as switch argument) is permitted
      case Truck tr -> tr.payload();
      // watch out for logical overlaps, ORDER MATTERS
      case Car c when c.color().length() == 3 -> (c.seats() - 1) * 200;
      case Car c when c.color().equals("Red") -> (c.seats() - 1) * 100;
      case Car c -> (c.seats() - 1) * 170;
    };

  }
}
