import org.dashevo.Client;
import org.dashevo.client.ClientOptions;
import org.dashevo.client.WalletOptions;
import org.dashevo.dpp.contract.DataContract;
import org.dashevo.dpp.contract.DataContractCreateTransition;
import org.dashevo.dpp.identity.Identity;
import org.json.JSONObject;

import java.util.Map;

public class RegisterDataContract {
    static Client sdk;
    static String mnemonic = "harbor loan abuse time decide smooth craft cherry strategy rain cream disagree"; // AX7q38cKWZqx6ffTtXBCecx7VYb546JhkGGN6yJJAjxv

    public static void main(String[] args) {
        ClientOptions options = ClientOptions.builder()
                .network("testnet")
                // .dapiAddress("127.0.0.1") // Uncomment to request from a local node running platform services
                .walletOptions(new WalletOptions(mnemonic, 0))
                .build();
        sdk = new Client(options);
        registerContract();
    }

    private static void registerContract() {
        try {
            Identity identity = sdk.getPlatform().getIdentities().getByPublicKeyHash(sdk.getWallet().getBlockchainIdentityKeyChain().getWatchingKey().getPubKeyHash());

            // Tutorial contract
            String ownerId = identity.getId().toString();
            String jsonstring = "{\"protocolVersion\": 0, \"$schema\": \"https://schema.dash.org/dpp-0-4-0/meta/data-contract\", \"ownerId\": \"" + ownerId + "\", \"documents\": {\"note\": {\"properties\": {\"message\": {\"type\": \"string\"}}, \"additionalProperties\": false }}}";
            JSONObject contractDocs = new JSONObject(jsonstring);

            Map<String, Object> contractDocuments = contractDocs.toMap();
            DataContract tutorialContract = sdk.getPlatform().getContracts().create(contractDocuments, identity);
            System.out.println(tutorialContract.getId());
            System.out.println(new JSONObject(tutorialContract.toJSON()).toString());

            try {
                System.out.println("---- Broadcasting transition");
                DataContractCreateTransition transition = sdk.getPlatform().getContracts().broadcast(tutorialContract, identity, sdk.getWallet().getBlockchainIdentityKeyChain().getWatchingKey(), 0);
                System.out.println("---- Transition broadcast");
                System.out.println(new JSONObject(transition.toJSON()).toString());
            } catch (Exception e) {
                System.out.printf("\nSomething went wrong while broadcasting ST:\n%s%n", e);
            }

            Thread.sleep(10000L);
            System.out.println("---- Retrieving new contract");
            DataContract publishedContract = sdk.getPlatform().getContracts().get(tutorialContract.getId());
            System.out.println("DataContract: -----------------------------------");
            System.out.println(new JSONObject((publishedContract != null) ? publishedContract.toJSON() : null).toString(2));
        } catch (Exception e) {
            System.out.printf("\nSomething went wrong:\n%s%n", e);
        }
    }
}
