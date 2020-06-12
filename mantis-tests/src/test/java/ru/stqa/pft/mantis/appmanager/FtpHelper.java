package ru.stqa.pft.mantis.appmanager;

import org.apache.commons.net.ftp.FTPClient;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FtpHelper {

    private final ApplicationManager app;
    private FTPClient ftp;

    public FtpHelper(ApplicationManager app) {
        this.app = app;
        ftp = new FTPClient();
    }

    public void upload(File file, String target, String backup) throws IOException {// upload new file (old file rename)
        ftp.connect(app.getProperty("ftp.host"));//connection with remote system
        ftp.login(app.getProperty("ftp.login"), app.getProperty("ftp.password"));//login
        ftp.deleteFile(backup);//delete last backup
        ftp.rename(target, backup);//rename deleted file and backup
        ftp.enterLocalPassiveMode();//turn on passive mode for transfer file
        ftp.storeFile(target, new FileInputStream(file));//transfer file
        ftp.disconnect();//disconnection
    }

    public void restore(String backup, String target) throws IOException {//restore an old file  (source file of testing system)
        ftp.connect(app.getProperty("ftp.host"));//connection with remote system
        ftp.login(app.getProperty("ftp.login"), app.getProperty("ftp.password"));//login
        ftp.deleteFile(target);//deletion file
        ftp.rename(backup, target);//restore source file
        ftp.disconnect();//disconnection
    }
}
