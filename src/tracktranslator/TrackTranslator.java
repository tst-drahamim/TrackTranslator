/*
 * 
 */
package tracktranslator;

import java.util.HashMap;
import com.vitriol.Props;

/**
 *
 * @author DRahamim
 */
public class TrackTranslator {

    private final String propfile = "tracktranslator.properties";
    private Props props;

    //constructor
    public TrackTranslator() {
        HashMap<String, String> defaults = new HashMap<>();
        defaults.put("port", "3000");
        defaults.put("nthreads", "1");
        defaults.put("debug", "false");        
        defaults.put("host", "localhost//core/");
        defaults.put("objectPath", "core/rest/track/device/create");
        defaults.put("trackPath", "core/rest/track/device/publish");
        defaults.put("kmlPath", "core/rest/geo/object/create");
        defaults.put("localPath", ".");
        defaults.put("title", "iSpatial_v2");
        defaults.put("model", "predator");
        defaults.put("data", "path");
        defaults.put("updateFreq", "1000");
        defaults.put("bufsize", "16384");
        props = new Props(propfile, defaults);
    }

    // non static action code (called from main after creating instance)
    public void run(
            String[] args) // command line arguments
    {
        int port = props.getInt("port");
        int nthreads = props.getInt("nthreads");
        boolean debug = props.getBool("debug");
        String postUrl = props.getString("trackPath");
        String charSet = props.getString("encoding");
        TrackListener trackListener = new TrackListener(port, nthreads, postUrl, charSet, debug);
        trackListener.run(args);
    }

    // main entry point
    public static void main(
            String[] args) // command line arguments
    {
        TrackTranslator trackTranslator = new TrackTranslator();
        trackTranslator.run(args);
    }
}