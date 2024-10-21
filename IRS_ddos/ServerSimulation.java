import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSimulation {

    private ServerSocket serverSocket;
    private boolean running;

    public void startServer(int port) {
        try {
            serverSocket = new ServerSocket(port);
            running = true;
            System.out.println("Server started and listening on port " + port);

            while (running) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Accepted connection from client: " + clientSocket.getInetAddress());

                // Simulate request handling
                handleClientRequest(clientSocket);
            }
        } catch (IOException e) {
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
        // Simulating processing time for each request
        try {
            Thread.sleep(100); // simulate request handling delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        clientSocket.close();
    }

    public static void main(String[] args) {
        ServerSimulation server = new ServerSimulation();
        server.startServer(8080);
    }
}
