import org.bitcoinj.core.ECKey;
import org.bitcoinj.crypto.DeterministicKey;
import org.dashevo.Client;
import org.dashevo.client.ClientOptions;
import org.dashevo.client.WalletOptions;
import org.dashevo.dapiclient.model.DocumentQuery;
import org.dashevo.dpp.document.Document;
import org.dashevo.dpp.identity.Identity;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SubmitDocument {
    static Client sdk;
    static String mnemonic = "harbor loan abuse time decide smooth craft cherry strategy rain cream disagree"; // AX7q38cKWZqx6ffTtXBCecx7VYb546JhkGGN6yJJAjxv

    public static void main(String[] args) {
        ClientOptions options = ClientOptions.builder()
                .network("testnet")
                // .dapiAddress("127.0.0.1") // Uncomment to request from a local node running platform services
                .walletOptions(new WalletOptions(mnemonic, 0))
                .app("tutorialContract", "A1TPQcpJPhKbaX3bRSsG7gHJQSEYMqtf1BgBnAkkgJAu")
                .build();
        sdk = new Client(options);
        submitDocument();
    }

    private static void submitDocument() {
        try {
            DeterministicKey identityKey = sdk.getWallet().getBlockchainIdentityKeyChain().getWatchingKey();
            Identity identity = sdk.getPlatform().getIdentities().getByPublicKeyHash(identityKey.getPubKeyHash());

            JSONObject docJson = new JSONObject("{\"message\": \"Java test\"}");
            Map<String, Object> docProperties = docJson.toMap();

            // Create the note document
            Document noteDocument = sdk.getPlatform().getDocuments().create("tutorialContract.note", identity.getId(), docProperties);
            System.out.println(new JSONObject(noteDocument.toJSON()).toString());

            try {
                System.out.println("---- Broadcasting transition");
                // Sign and submit the document(s)
                sdk.getPlatform().getDocuments().broadcast(identity, identityKey, Arrays.asList(noteDocument), null, null);
                System.out.println("---- Transition broadcast successfully");
            } catch (Exception e) {
                System.out.printf("\nSomething went wrong while broadcasting ST:\n%s%n", e);
            }

            Thread.sleep(10000L);
            System.out.println("---- Retrieving new document");
            DocumentQuery queryOpts = new DocumentQuery.Builder().startAt(0).limit(10).build();
            List<Document> docs = sdk.getPlatform().getDocuments().get("tutorialContract.note", queryOpts);
            for (Document doc : docs) {
                System.out.printf("%s%n", new JSONObject(doc.toJSON()).toString(2));
            }
        } catch (Exception e) {
            System.out.printf("\nSomething went wrong:\n%s%n", e);
        }
    }
}
