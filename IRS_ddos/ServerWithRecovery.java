
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

public class ServerWithRecovery {

    private ServerSocket serverSocket;
    private boolean running;
    private static final int ATTACK_THRESHOLD = 50; // Requests per second
    private AtomicInteger requestCount = new AtomicInteger(0);
    private boolean underAttack = false;

    public void startServer(int port) {
        try {
            serverSocket = new ServerSocket(port);
            running = true;
            System.out.println("Server started and listening on port " + port);

            // Start a thread to monitor incoming requests and detect DDoS attacks
            new Thread(this::detectDDoS).start();

            while (running) {
                if (underAttack) {
                    System.out.println("Server under attack! Temporarily halting request processing...");
                    Thread.sleep(5000); // Simulate halting server for recovery
                    underAttack = false;
                    System.out.println("Server recovery complete. Resuming normal operations.");
                }

                Socket clientSocket = serverSocket.accept();
                requestCount.incrementAndGet();

                // Simulate request handling
                handleClientRequest(clientSocket);
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Error starting server: " + e.getMessage());
        }
    }

    public void stopServer() {
        try {
            running = false;
            serverSocket.close();
            System.out.println("Server stopped.");
        } catch (IOException e) {
            System.out.println("Error stopping server: " + e.getMessage());
        }
    }

    private void handleClientRequest(Socket clientSocket) throws IOException {
        try {
            Thread.sleep(100); // simulate request handling delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        clientSocket.close();
    }

    private void detectDDoS() {
        while (running) {
            try {
                Thread.sleep(1000); // Check request count every second
                int currentRequestCount = requestCount.getAndSet(0);

                if (currentRequestCount > ATTACK_THRESHOLD) {
                    System.out.println("ALERT: Potential DDoS attack detected! Requests: " + currentRequestCount);
                    underAttack = true;
                } else {
                    System.out.println("Normal traffic. Requests: " + currentRequestCount);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ServerWithRecovery server = new ServerWithRecovery();
        server.startServer(8080);
    }
}
