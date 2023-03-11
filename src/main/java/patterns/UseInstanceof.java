package patterns;

import java.util.Collection;
import java.util.List;

final class MyPoint {
  private int x, y;

  @Override
  public boolean equals(Object other) {
    return other instanceof MyPoint mp
        && this.x == mp.x
        && this.y == mp.y;
  }
}
public class UseInstanceof {
  public static void main(String[] args) {
    Object thing = "Hello";

    if (thing instanceof String) {
      String s = (String)thing;
      System.out.println("It's a string of length " + s.length());
    }
    if (thing instanceof String s) {
      System.out.println("It's a string of length " + s.length());
    }

    if (thing instanceof String s && s.length() > 3) {
      System.out.println("It's a long String");
    } else {
//      System.out.println("s is " + s); // nope, s is not valid!
    }

    if (!(thing instanceof String s) || s.length() > 3) {}

    do {
//      if (Math.random() > -1) break;
    } while (!(thing instanceof String s));
    System.out.println("s is " + s);

    if (thing instanceof List) {}
    if (thing instanceof List<?>) {}
//    if (thing instanceof List<String>) {}

    Collection<String> cs = null;
    if (cs instanceof List<String> ls) {
      String itemZero = ls.get(0);
    }

  }
}
