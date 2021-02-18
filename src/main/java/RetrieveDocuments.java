import org.dashevo.Client;
import org.dashevo.dapiclient.DapiClient;
import org.dashevo.dapiclient.model.DocumentQuery;
import org.dashevo.dpp.document.Document;
import org.dashevo.platform.ContractInfo;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

public class RetrieveDocuments {

    public static void main(String[] args) {
        Client client = new Client("testnet");
        client.getPlatform().setClient(new DapiClient("174.34.233.123", false));

        // Add a named contract to the app list
        String contractId = "6Ti3c7nvD1gDf4gFi8a3FfZVhVLiRsGLnQ7nCAF74osi";
        ((Map)client.getPlatform().getApps()).put("tutorialContract", new ContractInfo(contractId, null));

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
