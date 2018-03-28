/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

public class HttpRequest {

    public interface HttpReply {

        void onSuccess(String data);

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
