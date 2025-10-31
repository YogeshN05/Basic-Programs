import java.util.Scanner;
public class Star {
    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        System.out.print("Input: ");
        int n=s.nextInt();
        int i,j,k;
        for(i=1;i<=n;i++)
        {
            for(j=1;j<=i;j++)
            {
                System.out.print(j+" ");
            }
            System.out.println();
        }
        for(i=n;i>=1;i--)
        {
            for(j=1;j<i;j++)
            {
                System.out.print(j+" ");
            }
            System.out.println();
        }
    }
}
