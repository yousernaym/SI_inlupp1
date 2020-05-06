package server.controllers;

import org.springframework.web.bind.annotation.*;
import server.models.Climate;
import server.models.LogValue;
import server.models.Response;
import server.models.Status;
import server.repositories.ClimateProperty;
import server.repositories.StatusLogDao;

import java.io.IOException;
import java.sql.SQLException;

@RestController
public class StatusController
{
    private Status currentStatus = new Status();
    private StatusLogDao statusLogDao = new StatusLogDao();

    public StatusController() throws SQLException, IOException, ClassNotFoundException
    {
    }

    @GetMapping(value = "/current/{climateProperty}", headers = "Accept=application/json")
    public float getCurrentClimate(@PathVariable String climateProperty)
    {
        return currentStatus.getClimate().getProperty(ClimateProperty.valueOf("Temperature"));
    }

    @GetMapping("/current/humidity")
    public float getCurrentHumidity()
    {
        return currentStatus.getClimate().getHumidity();
    }

    @GetMapping("/current/lumen")
    public float getCurrentLumen()
    {
        return currentStatus.getClimate().getLumen();
    }

    @GetMapping("/current/climate")
    public Climate getCurrentClimate() throws SQLException, IOException, ClassNotFoundException
    {
        return currentStatus.getClimate();
    }

    @PostMapping("/current/temperature/{value}")
    public Response setCurrentTemperature(@PathVariable float value)
    {
        currentStatus.getClimate().setTemperature(value);
        return new Response(200, "Temperature set to " + value);
    }

    @PostMapping("/current/humidity/{value}")
    public Response setCurrentHumidity(@PathVariable float value)
    {
        currentStatus.getClimate().setHumidity(value);
        return new Response(200, "Humidity set to " + value);
    }

    @PostMapping("/current/lumen/{value}")
    public Response setCurrentLumen(@PathVariable float value)
    {
        currentStatus.getClimate().setLumen(value);
        return new Response(200, "Lumen set to " + value);
    }

    @GetMapping("/log/{climateProperty}/{days}")
    public LogValue[] getClimateLog(@PathVariable String climateProperty, @PathVariable int days) throws SQLException, IOException, ClassNotFoundException
    {
        return statusLogDao.getClimateLog(days, ClimateProperty.valueOf(climateProperty));
    }



}
