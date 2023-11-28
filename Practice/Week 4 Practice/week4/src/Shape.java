public class Shape {
    private String color;

    public Shape(String color){
        this.color = color;
    }
    public String getColor(){
        return this.color;
    }

    public void setColor(String c){
        this.color = c;
    }

    public String displayInfo(){
        return "Color: " + this.color;
    }
}
