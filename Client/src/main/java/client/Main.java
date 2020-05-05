package client;

import org.springframework.web.client.RestTemplate;

public class Main
{
    public static void main(String[] args)
    {
        RestTemplate rt = new RestTemplate();
        String res = rt.getForObject("http://localhost:8080/", String.class);
        System.out.println(res);
    }
}
