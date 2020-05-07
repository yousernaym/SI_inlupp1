package client;

import org.springframework.web.client.RestTemplate;
import server.models.Climate;
import server.models.LogValue;
import server.models.Response;
import java.util.*;

public class Main
{
    private static  RestTemplate restTemplate = new RestTemplate();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args)
    {
        showMenu();
        while (true)
        {
            System.out.print("Välj åtgärd: ");
            String choice = scanner.nextLine();
            if (choice.equals("1"))
                showClimate(Climate.TEMPERATURE);
            else if (choice.equals("2"))
                showClimate(Climate.HUMIDITY);
            else if (choice.equals("3"))
                showClimate(Climate.LUMEN);
            else if (choice.equals("4"))
                setClimate(Climate.TEMPERATURE);
            else  if (choice.equals("5"))
                setClimate(Climate.HUMIDITY);
            else if (choice.equals("6"))
                setClimate(Climate.LUMEN);
            else if (choice.equals("7"))
                showClimate();
            else if (choice.equals("8"))
                showUsedEnergy();
            else if (choice.equals("9"))
                showClimateLog(Climate.TEMPERATURE);
            else if (choice.equals("10"))
                showClimateLog(Climate.HUMIDITY);
            else if (choice.equals("11"))
                showClimateLog(Climate.LUMEN);
            else if (choice.equals("12"))
                showEnergyCost();
            else if (choice.equals("13"))
                logCurrentStatus();
            else
                showMenu();
        }

    }

    private static void logCurrentStatus()
    {
        Response res = restTemplate.postForObject(
                "http://localhost:8080/logCurrentStatus", "", Response.class);
        printResponse(res);
    }

    private static void showEnergyCost()
    {
        System.out.println("Ange kostnad per kWh: ");
        String line = scanner.nextLine();
        float kwhCost = Float.parseFloat(line);
        Map<String, Float> arg = new HashMap<>();
        arg.put("kwhCost", kwhCost);
        float totalCost = restTemplate.getForObject(
                "http://localhost:8080/log/energyCost/7/{kwhCost}", float.class, arg);
        System.out.println(totalCost + " kr");
    }

    private static void showClimateLog(String propertyType)
    {
        LogValue[] log = restTemplate.getForObject(
                "http://localhost:8080/log/climate/" + propertyType + "/7", LogValue[].class);
        for (LogValue value : log)
        {
            System.out.print(value.getValue() + " | ");
            System.out.println(value.getCreated());
        }

    }

    private static void showUsedEnergy()
    {
        float energy = restTemplate.getForObject(
                "http://localhost:8080/energy", float.class);
        System.out.println(energy + " kWh");
    }

    private static void showMenu()
    {
        System.out.println(" 1. Visa temperatur");
        System.out.println(" 2. Visa luftfuktighet");
        System.out.println(" 3. Visa belysning");
        System.out.println(" 4. Ändra temperatur");
        System.out.println(" 5. Ändra luftfuktighet");
        System.out.println(" 6. Ändra belysning");
        System.out.println(" 7. Visa alla klimatvärden");
        System.out.println(" 8. Visa elförbrukning för senaste dygnet");
        System.out.println(" 9. Visa temperatur för senaste veckan");
        System.out.println("10. Visa luftfuktighet för senaste veckan");
        System.out.println("11. Visa belysning för senaste veckan");
        System.out.println("12. Visa elkostnad för senaste veckan");
        System.out.println("13. Logga status");
    }

    private static void setClimate(String propertyType)
    {
        System.out.print("Ange värde: ");
        String line = scanner.nextLine();
        float value = Float.parseFloat(line);
        Map<String, Float> arg = new HashMap<>();
        arg.put("value", value);
        Response res = restTemplate.postForObject(
                "http://localhost:8080/current/climate/" + propertyType + "/{value}", "", Response.class, arg);
        printResponse(res);
    }

    private static void showClimate(String propertyType)
    {
        float value = restTemplate.getForObject(
                "http://localhost:8080/current/climate/" + propertyType, float.class);
        System.out.println(propertyType + ": " + value);
    }

    private static void showClimate()
    {
        Climate climate = restTemplate.getForObject(
                "http://localhost:8080/current/climate", Climate.class);
        for (Map.Entry<String, Float> property : climate.getProperties())
            System.out.println(property.getKey() + ": " + property.getValue());
    }

    static void printResponse(Response res)
    {
        System.out.println("Status: " + res.getStatus());
        System.out.println("Message: " + res.getMessage());
    }
}
