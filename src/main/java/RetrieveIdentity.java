import org.dashevo.Client;
import org.dashevo.client.ClientOptions;
import org.dashevo.dpp.identity.Identity;
import org.json.JSONObject;

public class RetrieveIdentity {
    static Client sdk;
    static String id = "BSNxbs99zCFvEtK8qrewYTVxNKt4DnFQ8sPbsb8nrDuf";

    public static void main(String[] args) {
        ClientOptions options = ClientOptions.builder()
                .network("testnet")
                // .dapiAddress("127.0.0.1") // Uncomment to request from a local node running platform services
                .build();
        sdk = new Client(options);

        getIdentity();
    }

    private static void getIdentity() {
        try {
            Identity identity = sdk.getPlatform().getIdentities().get(id);
            System.out.printf("Retrieved identity: %s%n", new JSONObject(identity != null ? identity.toJSON() : null).toString(2));
        } catch (Exception e) {
            System.out.printf("\nSomething went wrong:\n%s%n", e);
        }
    }
}
