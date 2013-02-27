/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tracktranslator;

import com.vitriol.Props;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Iterator;

/**
 *
 * @author DRahamim
 */
public class TrackRestObj {

    private String request;

    public TrackRestObj(String postUrl, Map<String, String> parameters, String charSet) {
        this.request = formatRequest(postUrl, parameters, charSet);
    }
    
    private String formatRequest(String url, Map<String, String> parameters, String charSet) {
        String request = url;
        String query = formatQuery(parameters, charSet);
        if(query.length() > 0){
            request = request + "?" + query;
        }
        return request;
    }

    /**
     * Iterates through a map of parameters, encodes them against a char set,
     *  and strings concatenates them according to HTTP specifications
     * 
     * ex:
     * parameters
     *     {param1: value1, param2: value2}
     * return
     *     "?param1=value1&param2=value2&"
     * 
     * @param parameters
     * @return 
     */
    private String formatQuery(Map<String, String> parameters, String charSet) {
        String query = "";
        try {
            Iterator<String> iter = parameters.keySet().iterator();
            while(iter.hasNext()) {
                String key = iter.next();
                String value = parameters.get(key);
                String encodedValue = URLEncoder.encode(value, charSet);
                query = query + key + "=" + encodedValue;
                if(iter.hasNext()) {
                    query = query + "&";
                }
            }
        } catch (UnsupportedEncodingException e) {
        }
        return query;
    }
    
    public String getRequest() {
        return request;
    }
}
