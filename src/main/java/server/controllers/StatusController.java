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

    @GetMapping(value = "/current/climate/{property}", headers = "Accept=application/json")
    public float getCurrentClimate(@PathVariable String property)
    {
        return currentStatus.getClimate().getProperty(property);
    }

    @GetMapping("/current/climate")
    public Climate getCurrentClimate()
    {
        return currentStatus.getClimate();
    }

    @PostMapping("/current/climate/{property}/{value}")
    public Response setCurrentClimate(@PathVariable String property, @PathVariable float value)
    {
        currentStatus.getClimate().setProperty(property, value);
        return new Response(200, property + " set to " + value);
    }

    @GetMapping("/log/climate/{property}/{days}")
    public LogValue[] getClimateLog(@PathVariable String property, @PathVariable int days) throws SQLException, IOException, ClassNotFoundException
    {
        return statusLogDao.getClimateLog(days, property);
    }


}
