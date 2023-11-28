public class Circle {
    private double radius;

    public Circle(double radius){
        this.radius = radius;
    }

    public double getRadius(){
        return this.radius;
    }

    public double getArea(){
        return Math.PI * this.radius * this.radius;
    }

    public String displayInfo(){
        return "Radius: " + this.radius;
    }
}
