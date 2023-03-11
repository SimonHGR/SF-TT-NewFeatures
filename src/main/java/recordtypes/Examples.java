package recordtypes;

import java.lang.reflect.Field;

// Canonical constructor arguments declare "components"
// components are stored in auto-generated final fields of the same type
// varargs (only one, last), stored as array
// auto-generated accessors:
// - zero args
// - return type of the field type
// - throw no exceptions
// ATTEMPT at immutability
// -- implicitly final type AND parent type is implicitly java.lang.Record
// NO extends clause even for Record
// implementing interface is good and encouraged :)
// not legal to name fields after the non-private methods of Object
record Car(String color, int seatCount) { // these are "components"
  // not permitted to declare ANY instance fields
  private static int x; // static fields (and methods) are fine
  public void showThyself() {
    System.out.println("I'm a car, color: "
        + this.color + " seats: " + seatCount);
  }

  @Override
  public String toString() {
//    return super.toString() + " new version of method"; // NOPE, no super...
    return "I'm a car, vrooom vroom, color is " + color + " seats " + seatCount;
  }

  // if no coded constructor has the same argument list as the component list
  // above... Then the compiler generates the "canonical constructor" for us
  // args as component list, accessibility same as type
  // BUT if we code a constructor with that arg list, we must initialize
  // the fields (not surprising)
//  Car(String color, int s) {} // NOT allowed, compoent names must be matched
    // LEGAL but ... why?
//  Car(String color, int seatCount) {
//    this.color = color;
//    this.seatCount = seatCount;
//  }
// well, here's why, maybe...
//  Car(String color, int seatCount) {
//    if (color == null || color.length() == 0) {
//      throw new IllegalArgumentException();
//    }
//    this.color = color;
//    this.seatCount = seatCount;
//  }

  // but, here's a "compact" constructor
  // NOT treated as a constructor, but is copied onto the front
  // of the auto-generated constructor
  // assignment to the fields must be done by canonical constructor
  // therefore we MUST NOT REFER TO THOSE FIELDS
  Car {
    if (color == null || color.length() == 0) {
      throw new IllegalArgumentException();
    }
  }

  // all additional constructors MUST END UP at the canonical constructor
  Car(String color) {
    this(color, 5); // delegate as usual
  }
}

public class Examples {
  public static void main(String[] args) throws Throwable {
    Car c = new Car("Red", 5);
    Car c2 = new Car("Red", 5);
    Car c3 = new Car("Blue", 7);

    System.out.println(c);
    System.out.println(c.equals(c2));
    System.out.println(c.equals(c3));
    System.out.println(c.hashCode());
    System.out.println(c2.hashCode());
    System.out.println(c3.hashCode());

    System.out.println("Car " + c + " is " + c.color());

    Class<?> cl = Car.class;
    Field colorField = cl.getDeclaredField("color");
    colorField.setAccessible(true);
    Object colorVal = colorField.get(c);
    System.out.println("colorVal is " + colorVal);

    c.showThyself();
  }
}
