//Simple program for OTP generator through Gmail 

import java.util.Scanner;
import java.util.Random;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;


public class EmailOTP {
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the email: ");
        String email=sc.nextLine();

        Random rand=new Random();
        int otp=100000+rand.nextInt(900000);
        //int otp=123456;
        sendOtpEmail(email, otp);


        System.out.print("Enter the otp: ");
        int userOpt=sc.nextInt();

        if(otp==userOpt)
        {
            System.out.print("OTP Verification successful");
        }
        else {
            System.out.print("Invalid OTP ");
        }
        sc.close();
    }
    static void sendOtpEmail(String toEmail, int otp) {

        final String fromEmail = "Your gmail";   // sender gmail
        final String password = "password ";      // 16-digit app password

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(toEmail)
            );
            message.setSubject("Your OTP Verification Code");
            message.setText("Your otp is: " + otp + "\nDo not share it with anyone.");

            Transport.send(message);
            System.out.println("📧 OTP sent to email successfully!");

        } catch (Exception e) {
            System.out.println("❌ Failed to send OTP");
            e.printStackTrace();
        }
    }

}
