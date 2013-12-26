package model.mailer;

import model.constants.Component;
import model.constants.MailConsts;
import model.logger.LoggerMessage;

import javax.mail.MessagingException;
import java.util.HashMap;


/**
 * Created with IntelliJ IDEA.
 * User: Servcer
 * Date: 26.12.13
 * Time: 14:54
 * To change this template use File | Settings | File Templates.
 */
public class ErrorMailer {
    private static final String sender = "petersburgonlineerrorsystem";
    private static final String password = "djqysdrjcvjct";
    private static final HashMap<Component, String[]> responsibilities = setResp();

    private static HashMap<Component, String[]> setResp() {
        HashMap<Component, String[]> responsibilities = new HashMap<Component, String[]>();
        responsibilities.put(Component.Admin, MailConsts.AdminDevMailList);
        responsibilities.put(Component.Database, MailConsts.DatabaseDevMailList);
        responsibilities.put(Component.Global, MailConsts.GlobalDevMailList);
        responsibilities.put(Component.Parser, MailConsts.ParserDevMailList);
        responsibilities.put(Component.Phone, MailConsts.PhoneDevMailList);
        return responsibilities;
    }

    private static void sendMail(String mail, String text, String title) {
        try {
            GmailSender.Send(sender, password, mail, title, text);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private static void sendMail(String[] mails, String text, String title) {
        try {
            for (String mail : mails) {
                GmailSender.Send(sender, password, mail, title, text);
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void sendError(LoggerMessage loggerMessage) {
        String[] mails=responsibilities.get(loggerMessage.getComponent());
        sendMail(mails,loggerMessage.getMessage(),loggerMessage.getComponent()+" error");
    }
}
