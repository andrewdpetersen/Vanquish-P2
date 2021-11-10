package VanquishP2.Application.Beans.Service;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class APIClientService {
    private static final OkHttpClient apiClient = new OkHttpClient();

    public static String get(String url) {
        Request req = new Request.Builder().url(url).build();

        try(Response response = apiClient.newCall(req).execute())
        {
            return response.body().string();
        }
        catch(IOException e)
        {
            //TODO: Set up file logger or AOP logging and invoke here instead of stack trace.
            e.printStackTrace();
        }
        return null;
    }
}
