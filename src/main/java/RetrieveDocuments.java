import org.dashevo.Client;
import org.dashevo.client.ClientOptions;
import org.dashevo.dapiclient.model.DocumentQuery;
import org.dashevo.dpp.document.Document;
import org.json.JSONObject;

import java.util.List;

public class RetrieveDocuments {

    public static void main(String[] args) {
        ClientOptions options = ClientOptions.builder()
                .network("testnet")
                // .dapiAddress("35.163.152.74")
                .app("tutorialContract", "GY9KLtcWGjaDtAqrfN85PWNEPNUNMABnyF1udsgMSLo8")
                .build();
        Client client = new Client(options);

        retrieveDocuments(client);
    }

    private static void retrieveDocuments(Client client) {
        try {
            // Note: this contract requires `limit` to be 1 greater than the number of documents desired (platform bug)
            DocumentQuery queryOpts = new DocumentQuery.Builder().startAt(0).limit(2).build();
            List<Document> docs = client.getPlatform().getDocuments().get("tutorialContract.note", queryOpts);
            for (Document doc : docs) {
                System.out.printf("%s%n", new JSONObject(doc.toJSON()).toString(2));
            }

            // Get DPNS domain document
//            queryOpts.setLimit(1);
//            List<Document> names = client.getPlatform().getDocuments().get("dpns.domain", queryOpts);
//            for (Document name : names) {
//                System.out.printf("%s%n", new JSONObject(name.toJSON()).toString(2));
//            }
        } catch (Exception e) {
            System.out.printf("\nSomething went wrong:\n%s%n", e);
        }
    }
}
