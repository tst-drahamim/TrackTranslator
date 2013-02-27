/*
 * 
 */
package tracktranslator;

import java.io.IOException;
import java.net.ServerSocket;
import com.vitriol.Props;

/**
 *
 * @author DRahamim
 */
public class TrackListener {

    private int port;
    private int nthreads;
    private String postUrl;
    private String charset;
    private boolean debug;

    // constructor
    public TrackListener(
            int port,
            int nthreads,
            String postUrl,
            String charSet,
            boolean debug) //properties handler
    {
        this.port = port;
        this.nthreads = nthreads;
        this.postUrl = postUrl;
        this.charset = charset;
        this.debug = debug;
    }

    // non static action code (called from main after creating instance)
    public void run(String[] args) // command line arguments
    {
        try {
            // listen on sockets
            ServerSocket socket = new ServerSocket(port);

            if (debug) {
                System.out.println(
                        "listening for browser connection on port " + port);
            }

            // build thread service objects using the sockets
            TrackThread thread = new TrackThread(socket, postUrl, charset, debug);

            // light off the requested number of threads to handle connections

            for (int count = nthreads; count > 0; --count) {
                new Thread(thread).start();
            }

        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}