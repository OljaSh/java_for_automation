public class MyFirstProgram {

    public static void main(String[] args) {

        Square s = new Square(5);
        System.out.println("Square area " + s.l + " = " + s.area());

        Rectangle r = new Rectangle(2, 3);
        System.out.println("Rectangle area  = " + r.area());

        Point p = new Point(10, 80, 15, 90);

        Point p1 = new Point();
        Point p2 = new Point();
        p1.x1 = 10;
        p1.y1 = 80;
        p2.x2 = 15;
        p2.y2 = 90;

        System.out.println("Distance between points 1st option = " + distance(p1, p2));


        System.out.println("Distance between points 2nd option = " + p.distance());

    }

    public static double distance(Point p1, Point p2) {
        return Math.sqrt((p2.x2 - p1.x1) * (p2.x2 - p1.x1) + (p2.y2 - p1.y1) * (p2.y2 - p1.y1));
    }

}
