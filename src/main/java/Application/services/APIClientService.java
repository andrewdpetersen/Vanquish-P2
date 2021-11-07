package Application.services;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class APIClientService {
    private static OkHttpClient apiClient = new OkHttpClient();

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
/**
 * Search by album, artist, track
 * Search-type comes from front end
 * Marshall the request String based on that information
 * Check if model exists, and add information as necessary
 * If query is for an Artist, query Deezer by default
 * (When we query an Artist, we get top 5 tracks, then albums... if they want a specific track,
 * ...search by Track or album)
 * If not query Deezer
 * Return results as a List of whichever model is requested (just for data transfer, no persistence)
 * When the front end makes a selection from results, we send that model back
 * Find what information from Deezer we need to persist a consistent model in our database
 */
