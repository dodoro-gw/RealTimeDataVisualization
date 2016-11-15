import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

/**
 * Created by sunflower on 11/14/16.
 */
public class StreamingDataSimulation {
    private static final String HOSTNAME = "localhost";
    private static final int PORT_OUTPUT = 9999;
    public static void main(String args[]) throws IOException, InterruptedException {
        double mu = 0.5;
        double std = 0.15;
        int count = 0;
        long startTime = 1476000000;
        Random r = new Random();

        //send the detection results via a socket client PORT_OUTPUT
        Socket client;
        while(true) {
            try {
                client = new Socket(HOSTNAME, PORT_OUTPUT);
                break;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        final PrintWriter out = new PrintWriter(client.getOutputStream(), true);

        while(count < 10000){

            double value = r.nextGaussian()*std + mu;
            if(value > 1 || value < 0) continue;
            if(value > 0.95 || value < 0.05){
                out.println(startTime + "," + value + ",1");
            }else{
                out.println(startTime + "," + value + ",0");
            }
            startTime++;
            count++;
            Thread.sleep(600);
        }
    }
}
