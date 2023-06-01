package util.logger;

import model.user.User;

import java.io.*;
import java.time.LocalDateTime;

public class Logger {
    private static final String path = "./log/list.log";
    private static final Logger logger = new Logger();
    public static Logger get() { return logger; }
    private Logger() {}

    public void log(String msg) {
        String message = "[" + LocalDateTime.now() + "] ";
        if (User.isLogged()) message += User.getName()
                + "(" + (User.isLoggedUserAdmin() ? "admin" : "customer") + "): ";
        else message += "Stranger: ";
        message += msg + "\n";

        try {
            PrintWriter pw = new PrintWriter(new FileWriter(path, true));
            pw.append(message);
            pw.close();

        } catch (IOException e) { e.printStackTrace(); }
    }
}
