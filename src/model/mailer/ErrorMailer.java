package model.mailer;

import model.constants.Component;
import model.constants.MailConsts;
import model.logger.LogLevel;
import model.logger.LoggerMessage;

import javax.mail.MessagingException;
import java.util.HashMap;


public class ErrorMailer {
    private static final String sender = "worldOnLineError";
    private static final String password = "djqysdrjcvjct111";
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
            MailSender.send(sender, password, mail, title, text, model.mailer.MailConsts.Yandex);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private static void sendMail(String[] mails, String text, String title) {
        try {
            for (String mail : mails) {
                MailSender.send(sender, password, mail, title, text, model.mailer.MailConsts.Yandex);
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void sendError(LoggerMessage loggerMessage) {
        String[] mails = responsibilities.get(loggerMessage.getComponent());
        sendMail(mails, loggerMessage.getMessage(), loggerMessage.getComponent() + " error");
    }

}
