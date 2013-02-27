/*
 * 
 */
package tracktranslator;

import com.vitriol.Props;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

import java.net.Socket;
import java.net.ServerSocket;

/**
 *
 * @author DRahamim
 */
public class TrackThread
        implements Runnable {

    private ServerSocket serverSocket;
    private boolean debug;
    private String postUrl;
    private String charSet;

    // constructor
    public TrackThread(
            ServerSocket socket,
            String postUrl,
            String charset,
            boolean debug) {
        this.serverSocket = socket;
        this.postUrl = postUrl;
        this.charSet = charset;
        this.debug = debug;
    }

    // thread entry point
    public void run() {
        try {
            // loop forever waiting for connections
            for (;;) {
                Socket clientSocket = serverSocket.accept();

                if (debug) {
                    System.out.println("got connection on port "
                            + serverSocket.getLocalPort());
                }

                handle(clientSocket);

                clientSocket.close();
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    // handle connection from client
    private void handle(
            Socket clientSocket) 
            throws IOException {
        // got client connection, get reader
        BufferedReader in = getReader(clientSocket);

        // process request
        process(in);

        // clean up
        in.close();
    }

    // gather request and return response
    private void process(
            BufferedReader browserIn) // input from browser
            throws IOException {
        TrackParser trackParser = new TrackParser(postUrl, charSet);
        TrackRestObj restObj = trackParser.parse(browserIn);

        TrackPublisher trackPublisher = new TrackPublisher();
        trackPublisher.publish(restObj);
    }

    // get a Reader for a Socket
    private BufferedReader getReader(
            Socket socket)
            throws IOException {
        return (getStreamReader(socket.getInputStream()));
    }

    // get a Reader for a stream
    private BufferedReader getStreamReader(
            InputStream inputStream) {
        return (new BufferedReader(new InputStreamReader(inputStream)));
    }
}