package server.repositories;

import server.models.Environment;
import server.models.LogValue;
import server.models.Status;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StatusLogDao extends SqlDao
{
    public StatusLogDao() throws ClassNotFoundException, IOException, SQLException
    {
        super();

        Status status = new Status();
        Environment env = new Environment();
        env.setHumidity(1);
        env.setLumen(0.85f);
        env.setTemperature(20);
        status.setEnvironment(env);

        //log(status);

        List<LogValue> values = getLog(7, StatusField.Temperature);
        for (LogValue value : values)
            System.out.println(value.getValue() + " " + value.getCreated());

    }

    public List<LogValue> getLog(int days, StatusField statusField) throws SQLException, IOException, ClassNotFoundException
    {
        List<LogValue> values = new ArrayList<>(days);
        try
        {
            connect();
            String aggregate = statusField == StatusField.EnergySinceMidnight
                    ? "max(" + statusField.toString() + ")"
                    : "avg(" + statusField.toString() + ")";

            PreparedStatement statement = prepareStatement(
                    "select " + aggregate + ", Created from Conditioner.StatusLog where datediff(curdate(), Created) < ? " +
                            "group by date(created) " +
                            "order by Created asc");

            statement.setInt(1, days);
            ResultSet rs = statement.executeQuery();

            while (rs.next())
            {
                LogValue logValue = new LogValue();
                logValue.setValue(rs.getFloat(1));
                logValue.setCreated(rs.getTimestamp(2).toLocalDateTime());
                values.add(logValue);
            }

            return values;
        }
        finally
        {
            disconnect();
        }
    }

    public void log(Status status) throws SQLException, IOException, ClassNotFoundException
    {
        try
        {
            connect();
            PreparedStatement statement =  prepareStatement(
                    "insert into Conditioner.StatusLog(Temperature, Humidity, Lumen, EnergySinceMidnight) " +
                            "values(?, ?, ?, ?)");
            statement.setFloat(1, status.getEnvironment().getTemperature());
            statement.setFloat(2, status.getEnvironment().getHumidity());
            statement.setFloat(3,status.getEnvironment().getLumen());
            statement.setFloat(4, status.getUsedEnergySinceMidnight());
            //statement.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            statement.executeUpdate();
        }
        finally
        {
            disconnect();
        }

    }

}
