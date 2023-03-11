package sealedtypes;

// A sealed type must have children
// those children will normally be declared with "permits"
// permits comes AFTER extends/implements

// permits is optional if all the child types are
// declared in the same source file as the parent
// (for development convenience)

// all subtypes of a sealed type must be one of
// sealed, non-sealed, final (or record, or enum)

// if using the JPMS, all members of a sealed hierarchy
// must be in the same module
// if NOT using JPMS, all members of a sealed hierarchy
// must be in the same package
sealed interface X /*permits Y, Z*/ {}
final class Y implements X {}
sealed class Z implements X permits A, B {}

final class B extends Z {}
non-sealed class A extends Z {}

public class Example {
  public static void main(String[] args) {
    var var = 99;
  }
}
