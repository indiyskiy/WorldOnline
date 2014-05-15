package model.mailer;

import model.additionalentity.admin.ParsedRegistrationRequest;
import model.constants.Component;
import model.constants.ServerConsts;
import model.database.requests.AdminUserRequest;
import model.database.worldonlinedb.AdminUserEntity;
import model.logger.LoggerFactory;
import model.textparser.DESSecretKey;
import model.textparser.StringCrypter;

import javax.mail.MessagingException;

public class RegistrationMailer {
    private static final String servletAddress = ServerConsts.GlobalServerURL + ServerConsts.worldOnlineModule + "mailConfirm/";
    private static final String sender = "petersburgInYourHand";
    private static final String password = "djqysdrjcjvct111";
    private static final String title = "Registration on \"petersburg on your hand\"";
    private static final String text = "To confirm your registration you should go to the link. \n ";
    private static final LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, RegistrationMailer.class);
    private static final StringCrypter stringCrypter = getCrypter();
    private static String splitter = "OlOlOtRoLoLo";

    private static StringCrypter getCrypter() {
        try {
            StringCrypter stringCrypter = new StringCrypter(new DESSecretKey(new byte[]{-17, -1, 101, 15, -99, -122, -68, 87}));
            return stringCrypter;
        } catch (Exception e) {
            loggerFactory.error(e);
        }
        return null;
    }

    public static void sendRegistrationMail(ParsedRegistrationRequest parsedRegistrationRequest) {
        try {
            String registrationText = text + getLink(parsedRegistrationRequest);
            MailSender.send(sender, password, parsedRegistrationRequest.getEmail(), title, registrationText, model.mailer.MailConsts.Yandex);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private static String getLink(ParsedRegistrationRequest parsedRegistrationRequest) {
        String str = parsedRegistrationRequest.getLogin() + splitter + parsedRegistrationRequest.getHashMd5();
        return servletAddress + stringCrypter.encrypt(str);
    }

    public static boolean confirmAccount(String urlPart) {
        String decrypted = stringCrypter.decrypt(urlPart);
        String[] parts = decrypted.split(splitter);
        String login = parts[0];
        String hash = parts[1];
        AdminUserEntity userEntity = AdminUserRequest.getUserByLogin(login);
        if (userEntity == null) {
            loggerFactory.error(login + " (userEntity == null)");
            return false;
        }
        String adminUserLogin = userEntity.getAdminUserName();
        String adminUserEmail = userEntity.getAdminUserAdditionalInfo().getAdminUserEmail();
        String adminUserPassword = userEntity.getAdminUserPassword();
        String adminUserFirstName = userEntity.getAdminUserAdditionalInfo().getAdminUserFirstName();
        String adminUserSecondName = userEntity.getAdminUserAdditionalInfo().getAdminUserSecondName();
        String hash2 = AdminUserRequest.getMd5Hash(adminUserLogin, adminUserEmail, adminUserPassword, adminUserFirstName, adminUserSecondName);
        if (hash != null && hash.equals(hash2) && !userEntity.getConfirmed()) {
            userEntity.setConfirmed(true);
            AdminUserRequest.editAdminUser(userEntity);
            return true;
        } else {
            return false;
        }
    }

}
