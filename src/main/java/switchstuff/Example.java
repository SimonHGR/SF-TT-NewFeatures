package switchstuff;

public class Example {
  public static void main(String[] args) {
    int x = 3;
    switch (x) {
      case 3:
        System.out.println("it's three");
      case 4:
        int val = 99;
      case 5:
        System.out.println("smallish");
        break;
      default:
        System.out.println("something else");
        break;
    }
    System.out.println("-------------------------");
    switch (x) {
      // commas are legal in colon form too, but why would you ever use
      // colon form again!?
      case 3, 4, 5 -> {
        int b = 99; // variable scope makes sense :)
        System.out.println("smallish");
        if (Math.random() > 0.5) break; // ugly but perhaps necessary
        System.out.println("yes, really, it's pretty small");
      }
      default ->
        System.out.println("something else");
    }
    System.out.println("--------------------");
    // Switch EXPRESSIONS
    // must cover all possible input values
    // Future versions will allow pattern matching on types
    // if the base type is a sealed type, you'll only have to
    // provide cases for all the known types
//    x = 4;
    var message = switch(x) {
      case 3 -> "Hello, it's a three";
      case 4 -> {
        int v1 = 99;
        yield v1 + 12;
      }
      default ->
          throw new IllegalArgumentException("uh oh, wasn't expecting that");
    };
    System.out.println(message);

    System.out.println("------------------------------");
    var m2 = switch (x) {
      case 2: // colon forms still fall through!
        System.out.println("got a two :)");
      case 3:
        int v1 = 12;
        yield v1 + 50; // always use yield, even in single expression mode
      case 4:
        yield 99;
      default:
        throw new IllegalArgumentException("uh, oh!");
    };
    System.out.println("case expression produced " + m2);
  }
}
