import com.googlecode.jsonrpc4j.JsonRpcHttpClient;

import java.net.URL;
import java.util.HashMap;

public class JavaMain {
    public static void main(String[] args) throws Throwable {
        JsonRpcHttpClient client = new JsonRpcHttpClient(new URL("http://127.0.0.1:" + 47545 + "/jsonrpc.api"));
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("login", "myNewLogin1");
        params.put("password", "myNewPassword");
        String result = client.invoke("user.create", params, String.class);
        System.out.println(result);
    }
}
