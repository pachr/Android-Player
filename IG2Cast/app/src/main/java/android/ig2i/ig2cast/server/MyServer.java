package android.ig2i.ig2cast.server;

import android.ig2i.ig2cast.MainActivity;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Paul-Alexandre on 02/06/2016.
 */
public class MyServer extends NanoHTTPD {
    private final static int PORT = 8080;

    public MyServer() throws IOException {
        super(PORT);
        start();
        System.out.println("\nRunning! Point your browers to http://localhost:8080/ \n");
    }

    @Override
    public Response serve(String uri, Method method,
                          Map<String, String> header, Map<String, String> parameters,
                          Map<String, String> files) {

        File file = null;
        FileInputStream fis = null;
        try {
            if (MainActivity.DataSource != null) {
                file = new File(MainActivity.DataSource);
                fis = new FileInputStream(MainActivity.DataSource);
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new NanoHTTPD.Response(Response.Status.OK, "audio/mpeg", fis, file.length());
    }
}
