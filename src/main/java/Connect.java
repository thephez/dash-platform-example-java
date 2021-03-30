import org.dashevo.Client;
import org.dashevo.client.ClientOptions;
import org.dashevo.dapiclient.DapiClient;

public class Connect {

    public static void main(String[] args) {
        Client sdk = new Client(new ClientOptions("testnet"));
        //sdk.getPlatform().setClient(new DapiClient("174.34.233.134", false));
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
