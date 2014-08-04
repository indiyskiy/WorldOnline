package model.mailer;

public enum MailConsts {
    GMail("smtp.gmail.com", 465, "gmail.com"),
    Yandex("smtp.yandex.ru", 465, "yandex.ru");
    private String host;
    private int port;
    private String postfix;

    MailConsts(String host, int port, String postfix) {
        this.host = host;
        this.port = port;
        this.postfix = postfix;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPostfix() {
        return postfix;
    }

    public void setPostfix(String postfix) {
        this.postfix = postfix;
    }
}
