package server.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import server.models.Status;
import server.repositories.StatusLogDao;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("/")
    public String index()
    {
        return "Hej";
    }
}
