package com.example.seekerpool_springboot.marc.util;


import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

public class SendMailUtil {

    public static Boolean sendMail(String authWord, String targetMail, String memName) {

        //SMTP設定
        String host = "smtp.gmail.com";
        String port = "587";
        String username = "xxx";
        String password = "xxx";

        //信件內容
        String content = memName + " 您好，請使用該驗證碼進行會員註冊驗證 : " + authWord;
        String subject = "Seeker's Pool 網站 會員註冊驗證碼信件";

        //設定屬性
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.debug",true);
        props.put("mail.smtp.ssl.protocols","TLSv1.2");
        props.put("mail.smtp.ssl.trust","smtp.gmail.com");

        //建立Session物件
        Session session = Session.getDefaultInstance(props, new jakarta.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });


        try {
            //設定信件內容
            MimeMessage message = new MimeMessage(session);
            message.setSubject(subject);
            message.setFrom(new InternetAddress(username));
            message.setContent(content, "text/html;charset=utf-8");
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(targetMail));

            Transport.send(message);
            System.out.println("信件傳送成功");
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("信件傳送失敗");
            return false;
        }
    }

}
