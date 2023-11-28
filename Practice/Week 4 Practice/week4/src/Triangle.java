public class Triangle {
    private double base;
    private double height;

    public Triangle(double base, double height){
        this.base = base;
        this.height = height;
    }

    public double getArea(){
        return this.base * this.height / 2;
    }

    public String displayInfo(){
        return "Base: " + this.base + ", Height: " + this.height; 
    }
}
