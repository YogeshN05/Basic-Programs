import java.util.Scanner;
public class Even_Or_Odd {
    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        System.out.println("Enter a number to check if it is even or odd");
        int n=s.nextInt();


        if(n%2==0){
            System.out.println("The number is even");
        }
        else{
            System.out.println("The number is odd");
        }
        s.close();

    }
    
}
