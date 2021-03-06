import org.dashevo.Client;
import org.dashevo.dapiclient.DapiClient;

public class Connect {

    public static void main(String[] args) {
        Client sdk = new Client("testnet");
        DapiClient client = sdk.getPlatform().client;

        connect(client);
    }

    private static void connect(DapiClient client) {
        try {
            System.out.printf("Connected. Best block hash: %s%n", client.getBestBlockHash());
        } catch (Exception e) {
            System.out.printf("\nSomething went wrong:\n%s%n", e.getLocalizedMessage());
        }
    }
}
