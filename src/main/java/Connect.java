import org.dashevo.Client;
import org.dashevo.client.ClientOptions;
import org.dashevo.dapiclient.DapiClient;

public class Connect {

    public static void main(String[] args) {
        ClientOptions options = ClientOptions.builder()
                .network("testnet")
                // .dapiAddress("35.163.152.74")
                .build();
        Client sdk = new Client(options);
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
