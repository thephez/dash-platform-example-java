import org.dashevo.Client;
import org.dashevo.dapiclient.DapiClient;
import org.dashevo.dpp.document.Document;
import org.json.JSONObject;

import java.util.List;

public class RetrieveName {
    static Client sdk = new Client("testnet");
    static String nameUniqueIdentityId = "BSNxbs99zCFvEtK8qrewYTVxNKt4DnFQ8sPbsb8nrDuf";

    public static void main(String[] args) {
        sdk.getPlatform().setClient(new DapiClient("174.34.233.130", false));

        resolveByName();
        retrieveNameByRecord();
        retrieveNameBySearch();
    }

    private static void resolveByName() {
        try {
            Document name = sdk.getPlatform().getNames().get("user-0001");
            System.out.printf("Name retrieved: %s%n", new JSONObject(name != null ? name.toJSON() : null).toString(2));
        } catch (Exception e) {
            System.out.printf("\nSomething went wrong:\n%s%n", e);
        }
    }

    private static void retrieveNameByRecord() {
        try {
            List<Document> names = sdk.getPlatform().getNames().resolveByRecord("dashUniqueIdentityId", nameUniqueIdentityId);
            System.out.printf("Name retrieved: %s%n", new JSONObject(names.get(0).toJSON()).toString(2));
        } catch (Exception e) {
            System.out.printf("\nSomething went wrong:\n%s%n", e);
        }
    }

    private static void retrieveNameBySearch() {
        try {
            List<Document> names = sdk.getPlatform().getNames().search("user-0001", "dash", true, 1, 1);
            System.out.printf("Name retrieved: %s%n", new JSONObject(names.get(0).toJSON()).toString(2));
        } catch (Exception e) {
            System.out.printf("\nSomething went wrong:\n%s%n", e);
        }
    }
}
