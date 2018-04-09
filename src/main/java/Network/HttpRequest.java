package Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

/**
 * <p>HttpRequest can be used to get information from any open Web Service.
 * This implementation only can send request via GET method because of the
 * needs for this System.</p>
 *
 * @author Albertpv
 * @version 1.0
 */
public class HttpRequest {

    /**
     * Any class implementing this interface will be
     * able to act as the callback of the HttpRequest.
     */
    public interface HttpReply {

        /**
         * If the HTTP request can be done successfully, this method
         * is called with the response data from Web Server.
         *
         * @param data  The respnse data.
         */
        void onSuccess(String data);

        /**
         * If an error happens during the HTTP request this method
         * is called.
         *
         * @param message   An error message explaining what went wrong.
         */
        void onError(String message);
    }

    private String getInput(URLConnection conn) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder builder = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {

            builder.append(line);
            builder.append("\n");
        }

        return builder.toString();
    }

    private String urlEncode (String toEncode) {

        toEncode = toEncode.replaceAll("[èéêë]","e");
        toEncode = toEncode.replaceAll("[ûù]","u");
        toEncode = toEncode.replaceAll("[ïîíì]","i");
        toEncode = toEncode.replaceAll("[àâá]","a");
        toEncode = toEncode.replaceAll("[Ôóò]","o");

        toEncode = toEncode.replaceAll("[ÈÉÊË]","E");
        toEncode = toEncode.replaceAll("[ÛÙÚ]","U");
        toEncode = toEncode.replaceAll("[ÏÎÍÌ]","I");
        toEncode = toEncode.replaceAll("[ÀÂÁ]","A");
        toEncode = toEncode.replaceAll("[ÔÓÒ]","O");
        toEncode = toEncode.replaceAll("'","");
        toEncode = toEncode.replaceAll("[)(]", "");
        toEncode = toEncode.replaceAll(" ", "+");
        toEncode = toEncode.replaceAll("[çÇ]", "c");
        toEncode = toEncode.replaceAll("[^\\p{ASCII}]", "");
        toEncode = toEncode.replaceAll("\"", "%22");
        toEncode = toEncode.replaceAll("<", "%3C");
        toEncode = toEncode.replaceAll(">", "%3E");
        toEncode = toEncode.replaceAll("#", "%23");
        toEncode = toEncode.replaceAll("%", "%25");
        toEncode = toEncode.replaceAll("[|]", "%7c");


        return toEncode;
    }

    private static String getParameters(HashMap<String, String> parameters) {
        StringBuilder sb = new StringBuilder();

        for (String key : parameters.keySet()) {

            sb.append(key);
            sb.append("=");
            sb.append(parameters.get(key));
            sb.append("&");
        }

        return sb.toString();
    }

    /**
     * Sends a HTTP GET request returning via callback the data returned or
     * and error message if something went wrong.
     *
     * @param link          The access point of the Web Service.
     * @param parameters    The parameters to send with the request.
     * @param reply         Callback.
     */
    public void get(String link, HashMap<String, String> parameters, HttpReply reply) {

        try {
            link += getParameters(parameters);
            URL url = new URL(link);
            URLConnection conn = url.openConnection();

            String data = getInput(conn);
            reply.onSuccess(data);

        } catch (MalformedURLException e) {

            reply.onError("Malformed URL: " + link);
            e.printStackTrace();

        } catch (IOException e) {

            reply.onError(e.getMessage());
            e.printStackTrace();
        }
    }
}
