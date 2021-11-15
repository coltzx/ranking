package pojo;

public class Model{
    int id,c,d;
    String name;
    double a,b,g,h,i;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getG() {
        return g;
    }

    public void setG(double g) {
        this.g = g;
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }

    public double getI() {
        return i;
    }

    public void setI(double i) {
        this.i = i;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", c=" + c +
                ", d=" + d +
                ", name='" + name + '\'' +
                ", a=" + a +
                ", b=" + b +
                ", g=" + g +
                ", h=" + h +
                ", i=" + i +
                '}';
    }
}