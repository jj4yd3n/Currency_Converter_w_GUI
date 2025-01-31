//Packages to deal with sending HTTP requests and handling JSON data.
import java.net.URL;
import java.net.http.*;
import java.net.URI;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class currencyConvert {
    private Double val;
    private Double cadRate;
    private Double eurRate;
    private Double phpRate;
    private String[] currency;
    private Double result;

    public currencyConvert(Double val) {
        this.val = val;
    }

    public Double convertUSDToCAD(Double val){
        /*Old hardcoded exchange rate
        return val * cadRate;
       //ORIGINAL COMMENT: USD to CAD keeps changing wtf so this is a placeholder
         */
        return Math.round(val * getCAD() * 100) / 100.0;
    }
    public Double convertUSDToPHP(Double val){
        /*Old hardcoded exchange rate
        return val * 58.25;
         */
        return Math.round(val * getPHP() * 100) / 100.0;
    }
    public Double convertCADtoUSD(Double val){
        /*Old hardcoded exchange rate
        return val / cadRate;
         */
        return Math.round(val / getCAD() * 100) / 100.0;
    }
    public Double convertCADtoPHP(Double val){
        return Math.round((val * (getPHP() / getCAD())) * 100) / 100.0;
    }

    public String getError(){
        return "Please enter capitalized 3 character currency or connect to the internet.";
    }

    //msgAPI method calls FxRatesAPI and gathers whatever currency is in the parameter and stores it in the "rate" variable. USD is the base currency. Method will throw an exception if API cannot be called.
    private Double getRate(String cur) throws Exception{
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
        Double rate = jsonObject.getAsJsonObject("rates").get(cur.toUpperCase()).getAsDouble();
        return rate;
    }

    public String[] getCur() throws Exception{
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
        return currency;
    }

    //Call FxRates API to convert currency fro parameters.
    public Double convert(String from, String to, Double amount) throws Exception{
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
        return result;
    }


    public Double getCAD(){
        try {
            cadRate = getRate("CAD");
        } catch (Exception e) {
            System.out.println("Please enter capitalized 3 character currency or connect to the internet.");
        }
        return cadRate;
    } //Retrieve CAD exchange rate from API

    public Double getEUR(){
        try{
             eurRate = getRate("EUR");
        }
        catch(Exception e){
            System.out.println("Please enter capitalized 3 character currency or connect to the internet.");
        }
        return eurRate;
    } //Retrieve EUR exchange rate from API

    public Double getPHP(){
        try{
            phpRate = getRate("PHP");
        }
        catch(Exception e){
            System.out.println("Please enter capitalized 3 character currency or connect to the internet.");
        }
        return phpRate;
    } //Retrieve PHP exchange rate from API


}
