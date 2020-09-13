package real.server.game;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class ServerMTGame extends Thread {
    private boolean isActive = true;
    private int nombreClient;
    List<Socket> clients = new ArrayList<>();
    boolean endGame;
    String IP_Gangant;


    public static void main(String[] args) {
        new ServerMTGame().start();  // run server
        System.out.println("running server");
        System.out.println("l'execution de l'application est continue....");
    }

    ServerSocket serverSocket;

    @Override
    public void run() {


        try {
            serverSocket = new ServerSocket(1234);

            while (isActive) {
                Socket socket = serverSocket.accept(); // bloquante, il attent qu'un client puisse se connecte
                clients.add(socket);
                nombreClient++;
                new Conversation(socket, nombreClient).start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    void onGangne(String IP_gangant, int reponse) {

        clients.forEach(socket -> {
            try {
                OutputStream outputStream = socket.getOutputStream();
                PrintWriter printWriter = new PrintWriter(outputStream);
                printWriter.println("end");
                if (socket.getInetAddress().getHostAddress().equals(IP_gangant)) {
                    printWriter.println("bravos vous etes le gangant, la reponse est " + reponse);
                } else {
                    printWriter.println("le gangant est IP= " + IP_gangant);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    class Conversation extends Thread {
        private Socket socket;
        private int numClient;
        public static final int nbRecherche = 12;

        public Conversation(Socket socket, int numClient) {
            this.socket = socket;
            this.numClient = numClient;
        }

        @Override
        public void run() {
            try {
                InputStream input = socket.getInputStream();
                OutputStream output = socket.getOutputStream();

                InputStreamReader reader = new InputStreamReader(input);
                BufferedReader buffered = new BufferedReader(reader);

                PrintWriter printWriter = new PrintWriter(output, true);

                System.out.println("connexion client numero: " + numClient);

                String IP = socket.getInetAddress().getHostAddress();
                printWriter.println("welcome you are client num: " + numClient + " IP: " + IP);
                //  System.out.println(buffered.readLine());
                //   System.out.println(input.read());
                while (true) {

                    int reponse = input.read();
                    //   System.out.println(reponse);
                    if (!endGame) {
                        if (reponse < nbRecherche) {
                            printWriter.println("superieur que " + reponse);
                        } else if (reponse > nbRecherche) {
                            printWriter.println("inferieur que " + reponse);
                        } else {
                            endGame =true;
                            IP_Gangant=IP;
                            printWriter.println("end");

                            printWriter.println("bravos vous etes le gangant, la reponse est " + reponse);

                            // onGangne(IP, nbRecherche);

                        }
                    } else {
                        printWriter.println("end...le gangant est IP= "+IP_Gangant);
                        System.out.println("game over");
                    }


                    //  System.out.println("le client " + IP + " a envoye une requete " + req);

                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
