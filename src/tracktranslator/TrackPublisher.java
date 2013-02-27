/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tracktranslator;

import com.vitriol.Props;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author DRahamim
 */
public class TrackPublisher {

    private final String propfile = "tracktranslator.properties";
    private Props props;
    private String url;
    private String charset;

    public TrackPublisher() {
    }

    public boolean publish(TrackRestObj restObj) {
        boolean success = true;
        
        this.url = props.getString("trackPath");
        this.charset = props.getString("charSet");
        String request = restObj.getRequest();
        
        URLConnection connection = buildConnection();
        InputStream response = doPost(connection, request);
        
        if(response == null) {
            success = false;
        }
        return success;
    }
    
    private URLConnection buildConnection() {
        URLConnection connection = null;
        try {
            connection = new URL(url).openConnection();
            connection.setDoOutput(true); // Triggers POST.
            connection.setRequestProperty("Accept-Charset", charset);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);
        } catch(IOException e) {
            
        }
        return connection;
    }
    
    private InputStream doPost(URLConnection connection, String request) {
        OutputStream output = null;
        InputStream response = null;
        try {
            output = connection.getOutputStream();
            output.write(request.getBytes(charset));
            response = connection.getInputStream();
        } catch(IOException e) {
            
        }
        return response;
    }
}
