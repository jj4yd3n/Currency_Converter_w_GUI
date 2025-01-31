import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {
    public static void main(String[] args) throws Exception {
        GUI myGUI = new GUI();
        currencyConvert currencyConvert = new currencyConvert(25.0);
        System.out.println(currencyConvert.convert("USD", "CAD", 4500.0));
        //System.out.println(currencyConvert.getCur());


        /*Temp code to test calling FxRatesAPI
        try {
            String url = "https://api.fxratesapi.com/latest";
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
            Double audRate = jsonObject.getAsJsonObject("rates").get("AUD").getAsDouble();
            Boolean status = jsonObject.getAsJsonObject("success").getAsBoolean();
            System.out.println(status);
            System.out.println(audRate);
        }
        catch(Exception e){
            System.out.println("Could not reach a server! This program needs an internet connection.");
        }
        */

    }

}

