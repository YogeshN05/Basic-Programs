class cons{
    cons(){System.out.println("Hello");} //default constructor
    int a,b;
    cons(int a,int b){                      //parameterized constructor
        System.out.println("Sum of a+b: "+(a+b));
    }
}

public class Constructor {
    public static void main(String[] args) {
        cons def=new cons();
        cons para=new cons(1, 02);
    }
}
