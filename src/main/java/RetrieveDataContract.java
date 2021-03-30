import org.dashevo.Client;
import org.dashevo.client.ClientOptions;
import org.dashevo.dpp.contract.DataContract;
import org.json.JSONObject;

public class RetrieveDataContract {
    static Client sdk;
    static String contractId = "6Ti3c7nvD1gDf4gFi8a3FfZVhVLiRsGLnQ7nCAF74osi";

    public static void main(String[] args) {
        ClientOptions options = ClientOptions.builder()
                .network("testnet")
                // .dapiAddress("127.0.0.1") // Uncomment to request from a local node running platform services
                .build();
        sdk = new Client(options);

        retrieveContract();
    }

    private static void retrieveContract() {
        try {
            DataContract contract = sdk.getPlatform().getContracts().get(contractId);
            System.out.printf("Retrieved contract: %s%n", new JSONObject(contract != null ? contract.toJSON() : null).toString(2));
        } catch (Exception e) {
            System.out.printf("\nSomething went wrong:\n%s%n", e);
        }
    }
}
