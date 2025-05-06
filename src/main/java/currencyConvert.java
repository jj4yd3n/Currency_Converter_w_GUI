//Packages to deal with sending HTTP requests and handling JSON data.
import java.net.URL;
import java.net.http.*;
import java.net.URI;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import javax.swing.JOptionPane;

public class currencyConvert {
    private Double val;
    private String[] currency;
    private Double result;
    private Double rate;

    public currencyConvert(Double val) {
        this.val = val;
    }

    public String getError(){
        return "Please enter capitalized 3 character currency or connect to the internet.";
    }

    //msgAPI method calls FxRatesAPI and gathers whatever currency is in the parameter and stores it in the "rate" variable. USD is the base currency. Method will throw an exception if API cannot be called.
    private Double getRate(String cur) throws Exception{
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
             rate = jsonObject.getAsJsonObject("rates").get(cur.toUpperCase()).getAsDouble();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please connect to the internet!");
        }
        return rate;
    }

    public String[] getCur() throws Exception{
        try {
            String url = "https://api.fxratesapi.com/currencies";
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
            currency = jsonObject.keySet().toArray(new String[0]);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please connect to the internet!");
        }
        return currency;
    }

    //Call FxRates API to convert currency from parameters.
    public Double convert(String from, String to, Double amount) throws Exception{
        try{
        String url = "https://api.fxratesapi.com/convert?from=" + from + "&to=" + to + "&amount=" + amount + "&format=json";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
         result = jsonObject.get("result").getAsDouble();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"Please connect to the internet!");
        }
        return result;
    }
}
