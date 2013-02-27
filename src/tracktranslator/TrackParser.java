/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tracktranslator;

import com.vitriol.Props;
import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author DRahamim
 */
public class TrackParser {

    private String postUrl;
    private String charSet;

    public TrackParser(
            String postUrl,
            String charset) {
        this.postUrl = postUrl;
        this.charSet = charSet;
    }

    public TrackRestObj parse(
            BufferedReader browserIn) {
        Map<String,String> parameters = new HashMap();
        parameters.put("param1", "value1");
        
        TrackRestObj resultRest = new TrackRestObj(postUrl, parameters, charSet);
        return resultRest;
    }
}
