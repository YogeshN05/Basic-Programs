import java.util.Scanner;
public class switchh {
    public static void main(String[] args) {
        for(int i=0;i<7;i++)
        {

            Scanner a=new Scanner(System.in);
            System.out.print("Enter the Day: ");
            String day=a.nextLine();
            String b=day.toUpperCase();
            //String result="";
            
            //result=switch(day)
            switch(b)
            {
                case "SATURDAY","SUNDAY"->System.out.println(b+" Wake Up at 9AM");
                case "MONDAY"->System.out.println(b+" Wake Up at 8AM");
                case "TUESDAY","WEDNESDAY","THURSDAY","FRIDAY"->System.out.println(b+" Wake Up at 7AM");
                default->System.out.println("Invalid Input");
            }
        }
        //System.out.println(result);
    }
    
}
