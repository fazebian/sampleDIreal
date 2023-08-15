package org.example;

/**
 * Hello world!
 *
 */
public class App 
{
    record Point(int x, int y) {}

    class Circle {
        private final Point center;
        private String name;

        @Inject
        public Circle(Point center) {
            this.center = center;
        }

        @Inject
        public void setName(String name) {
            this.name = name;
        }
    }

    public static void main( String[] args )
    {
        var registry = new InjectorRegistry();
        registry.registerInstance(Point.class, new Point(0, 0));
        registry.registerProvider(String.class, () -> "hello");

    }
}
