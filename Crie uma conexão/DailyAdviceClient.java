import java.io.*;
import java.net.*;

public class DailyAdviceClient {
    String[] adviceList = {"Morda pedaços menores", "Use o jeans apertado. Não, ele NÃO faz você parecer gorda.", "Só vou dizer uma palavra: inapropriado.",
    "Pelo menos hoje, seja honesta. Diga a seu chefe o que realmente pensa", "Reconsidere esse corte de cabelo"};

    public void go() {
        try {
            ServerSocket serverSock = new ServerSocket(4242);

            while(true) {
                Socket sock = serverSock.accept();

                PrintWriter writer = new PrintWriter(sock.getOutputStream());
                String advice = getAdvice();
                writer.println(advice);
                writer.close();
                System.out.println(advice);
            }
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    private String getAdvice() {
        int random = (int) (Math.random() * adviceList.length);
        return adviceList[random];
    }
    public static void main(String[] args) {
        DailyAdviceClient client = new DailyAdviceClient();
        client.go();
    }
}