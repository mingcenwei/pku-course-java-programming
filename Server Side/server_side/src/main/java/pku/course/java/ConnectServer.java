package pku.course.java;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

public class ConnectServer
{
    public static void main(String args[])
    {
        System.out.println(sendAndReceicveData("[\"createNewAccount\", \"sdffff\", \"sdfsdfsdfdsf\", \"sdfsdfsdfdfssdfds\"]"));
    }
    public static String sendAndReceicveData(String jsonString)
    {
        try
        {
            String urlString = "http://117.78.34.79:8080/new/query";
            URL url = new URL(urlString);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // conn.setReadTimeout(20000);
            // conn.setConnectTimeout(20000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(jsonString);
            writer.flush();
            writer.close();
            os.close();

            int responseCode=conn.getResponseCode(); // To Check for 200
            if (responseCode == HttpsURLConnection.HTTP_OK) {

                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuffer sb = new StringBuffer("");
                String line="";
                while((line = in.readLine()) != null) {
                    sb.append(line);
                    break;
                }
                in.close();
                return sb.toString();
            }
        }
        catch (Exception e)
        {
            System.out.println();
            e.printStackTrace();
        }

        return null;
    }
}

