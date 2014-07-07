package model.constants;


public class ServerConsts {
    //ip
    public static final int port = 8090;
    public static final String globalIP = "148.251.42.117";
    public static final String loopbackIP = "127.0.0.1";
    public static final String LocalServerURL = "http://" + loopbackIP + ":" + port + "/";
    public static final String GlobalServerURL = "http://" + globalIP + ":" + port + "/";
    public static final String worldOnlineModule = "worldOnline/";
    public static final String mobileModule = "mobile/";
    //storage
    public static final String home = "/home/";
    public static final String newBase = home + "base/";
    public static final String newFileBase = newBase + "fileBase/";
    public static final String imageFolder = newFileBase + "imageData/";
    public static final String tempFileStore = newFileBase + "tmpFileStore/";
    public static final String oldBase = home + "oldBase/";
    public static final String oldImageRoot = oldBase + "imageData/";
    public static final String oldIconsRoot = oldBase + "icon/";
    public static final String root = oldBase + "base/";
    public static final String globalLogDir = "logs/";
    public static final String staticFilesDirectory = newFileBase + "staticFiles/";
}
