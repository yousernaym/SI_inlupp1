package server.controllers;

import org.springframework.web.bind.annotation.*;
import server.models.Climate;
import server.models.LogValue;
import server.models.Response;
import server.models.Status;
import server.repositories.StatusLogDao;

import java.io.IOException;
import java.sql.SQLException;

@RestController
public class StatusController
{
    private Status currentStatus = new Status();
    private StatusLogDao statusLogDao = new StatusLogDao();

    public StatusController() throws IOException, ClassNotFoundException
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

    @GetMapping(value = "/energy", headers="Accept=application/json")
    public float getCurrentEnergy()
    {
        return currentStatus.getUsedEnergySinceMidnight();
    }

    @PostMapping("/current/climate/{property}/{value}")
    public Response setCurrentClimate(@PathVariable String property, @PathVariable float value)
    {
        currentStatus.getClimate().setProperty(property, value);
        return new Response(200, property + " set to " + value);
    }

    @GetMapping("/log/climate/{property}/{days}")
    public LogValue[] getClimateLog(@PathVariable String property, @PathVariable int days) throws SQLException
    {
        return statusLogDao.getClimateLog(days, property);
    }

    @PostMapping("/logCurrentStatus")
    public Response logCurrentStatus()
    {
        try
        {
            statusLogDao.log(currentStatus);
        } catch (SQLException e)
        {
            return new Response(500, e.getMessage());
        }
        return new Response(200, "Log entry created");
    }

    @GetMapping(value="/log/energyCost/{days}/{kwhCost}", headers="Accept=application/json")
    public float getEnergyCost(@PathVariable int days, @PathVariable float kwhCost) throws SQLException
    {
        return statusLogDao.getEnergyCost(days, kwhCost);
    }
}

