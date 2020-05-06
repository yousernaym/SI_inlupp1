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
        float temp = rt.getForObject("http://localhost:8080/current/temperature", float.class);
        System.out.println(temp);
//        LogValue[] log = rt.getForObject("http://localhost:8080/log/temperature/3", LogValue[].class);
//        for (LogValue value : log)
//        {
//            System.out.print(value.getValue() + " | ");
//            System.out.println(value.getCreated());
//        }
//        Response res = rt.postForObject("http://localhost:8080/current/temperature/"+13, "", Response.class);
//        printResponse(res);

    }

    static void printResponse(Response res)
    {
        System.out.println("Status: " + res.getStatus());
        System.out.println("Message: " + res.getMessage());
    }
}
