package model.mailer;

import com.sun.mail.smtp.SMTPTransport;
import model.constants.Component;
import model.logger.LoggerFactory;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.Security;
import java.util.Date;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: Servcer
 * Date: 26.12.13
 * Time: 15:00
 * To change this template use File | Settings | File Templates.
 */
public class MailSender {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Global, MailSender.class);

    private MailSender() {
    }

    public static void send(final String username, final String password, String recipientEmail, String title, String message, MailConsts mailConsts) throws AddressException, MessagingException {
        MailSender.send(username, password, recipientEmail, "", title, message, mailConsts);
    }

    public static void send(final String username, final String password, String recipientEmail, String ccEmail, String title, String message, MailConsts mailConsts) throws AddressException, MessagingException {
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        Properties props = System.getProperties();
        props.setProperty("mail.smtps.host", mailConsts.getHost());
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", String.valueOf(mailConsts.getPort()));
        props.setProperty("mail.smtp.socketFactory.port", String.valueOf(mailConsts.getPort()));
        props.setProperty("mail.smtps.auth", "true");
        props.put("mail.smtps.quitwait", "false");
        Session session = Session.getInstance(props, null);
        final MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(username + "@" + mailConsts.getPostfix()));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail, false));
        if (ccEmail.length() > 0) {
            msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccEmail, false));
        }
        msg.setSubject(title);
        msg.setText(message, "utf-8");
        msg.setSentDate(new Date());
        SMTPTransport t = (SMTPTransport) session.getTransport("smtps");
        t.connect(mailConsts.getHost(), username, password);
        t.sendMessage(msg, msg.getAllRecipients());
        t.close();
    }

}
