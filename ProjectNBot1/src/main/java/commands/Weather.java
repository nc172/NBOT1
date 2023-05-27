package commands;
import java.net.URL;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;



public class Weather {

    public static Map<String, Object>jsonToMap(String str){
        Map<String,Object> map = new Gson().fromJson(
                str, new TypeToken<HashMap<String,Object>>() {}.getType()
        );
        return map;
    }

    public static double[] getCurentWeather(String location){
        double[] curWeather = new double[2];
        String API_KEY = "1dd6798b4dfc9b7856d1663605165417";
        String urlString = "https://api.openweathermap.org/data/2.5/weather?q=" + location + "&units=imperial&appid=" + API_KEY;

        try{

            StringBuilder result = new StringBuilder();
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while((line = rd.readLine()) != null){
                result.append(line);
            }
            rd.close();


            //// Parsing the JSON response and extracting specific data
            Map<String,Object> respMap = jsonToMap(result.toString());
            Map<String,Object> mainMap = jsonToMap(respMap.get("main").toString());


            //current weather call
            double temp = (double) mainMap.get("temp");
            double humidity = (double) mainMap.get("humidity");
            // System.out.println("Atmospheric pressure measured in hPa (hectopascals): " + mainMap.get("pressure"));
            
            //add 2 info to array
            curWeather[0] = temp;
            curWeather[1] = humidity;

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return curWeather;
    }

    
}
