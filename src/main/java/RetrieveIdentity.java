import org.dashevo.Client;
import org.dashevo.client.ClientOptions;
import org.dashevo.dapiclient.DapiClient;
import org.dashevo.dpp.identity.Identity;
import org.json.JSONObject;

public class RetrieveIdentity {
    static Client sdk;
    static String id = "BSNxbs99zCFvEtK8qrewYTVxNKt4DnFQ8sPbsb8nrDuf";

    public static void main(String[] args) {
        sdk = new Client(new ClientOptions("testnet"));
        // Uncomment the following line to request from a local node running platform services
        // sdk.getPlatform().setClient(new DapiClient("127.0.0.1", false));

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
