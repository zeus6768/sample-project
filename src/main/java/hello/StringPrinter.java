package hello;

public class StringPrinter implements Printer {
    @Override
    public void print(final String message) {
        System.out.println(message);
    }
}
