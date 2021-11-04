package VanquishP2.Application.Beans.Service;

import org.springframework.stereotype.Service;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * This class is used to record every request made in a single session.
 */
@Service
public abstract class ServiceRequests {
    // The request count for this session
    private static int requestCount = 0;
    private static ArrayList<String> requestLog = new ArrayList<>();

    public static void writeSummary(){
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());

        try{
            File file = new File("requestLog.log");
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter out = new BufferedWriter(fileWriter);

            out.write("There were " + requestCount + " requests in session: " + formatter.format(date));
            out.newLine();
            for (String request : requestLog) {
                out.write(request);
                out.newLine();
            }

            System.out.println("Request Summary has been written to designated to file.");
        } catch (IOException e) {
            Logger.getFileLogger().writeLog(e.getMessage(), 2);
        }
    }

    public static void addRequest(String requestInfo){
        SimpleDateFormat formatter= new SimpleDateFormat("HH:mm:ss z");

        requestCount++;
        requestLog.add(requestInfo + " " + formatter.format(new Date(System.currentTimeMillis())));
    }
}
