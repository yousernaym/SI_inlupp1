package client;

import org.springframework.web.client.RestTemplate;
import server.models.LogValue;
import server.models.Response;

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        RestTemplate rt = new RestTemplate();
        float temp = rt.getForObject("http://localhost:8080/current/climate/lumen", float.class);
        System.out.println(temp);
        LogValue[] log = rt.getForObject("http://localhost:8080/log/climate/lumen/7", LogValue[].class);
        for (LogValue value : log)
        {
            System.out.print(value.getValue() + " | ");
            System.out.println(value.getCreated());
        }
        Response res = rt.postForObject("http://localhost:8080/current/climate/lumen", "0.27", Response.class);
        printResponse(res);

        float energy = rt.getForObject("http://localhost:8080/energy", float.class);
        System.out.println("Energy: " + energy);

        //res = rt.postForObject("http://localhost:8080/logCurrentStatus", "", Response.class);
        //printResponse(res);
    }

    static void printResponse(Response res)
    {
        System.out.println("Status: " + res.getStatus());
        System.out.println("Message: " + res.getMessage());
    }
}
