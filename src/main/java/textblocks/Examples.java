package textblocks;

public class Examples {
  public static void main(String[] args) {
    // opening triple quotes must be followed by whitespace-newline
    String multiLines = """       
        hello, this is a multi-line           
        piece of text,           
            I can put some special\ncharacters in here
        if I want to say ""Hello""\"""\""" she said!
      """.indent(-10);
    System.out.println(multiLines);
    String more = """
        x       
        y       
        z""";
    System.out.println(more.length());
  }
}
