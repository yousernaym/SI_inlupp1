package server.repositories;

import server.models.Climate;
import server.models.LogValue;
import server.models.Status;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatusLogDao extends SqlDao
{
    public StatusLogDao() throws ClassNotFoundException, IOException
    {
        super();
    }

    public LogValue[] getClimateLog(int days, String climateProperty) throws SQLException
    {
        try
        {
            connect();

            PreparedStatement statement = prepareStatement(
                    "select " + climateProperty + ", Created from Conditioner.StatusLog " +
                            "where datediff(curdate(), Created) <= ? " +
                            "order by Created asc");

            return getLog(days, statement);
        } finally
        {
            disconnect();
        }
    }

    public float getEnergyCost(int days, float kwhCost) throws SQLException
    {
        try
        {
            connect();

            PreparedStatement statement = prepareStatement(
                    "select max(EnergySinceMidnight), max(Created) " +
                            "from Conditioner.StatusLog " +
                            " where datediff(curdate(), Created) <= ? " +
                            "group by date(Created)");

            LogValue[] kwhList = getLog(days, statement);
            float totalCost = 0;
            for (LogValue kwh : kwhList)
                totalCost += kwh.getValue();
            totalCost *= kwhCost;
            return totalCost;
        } finally
        {
            disconnect();
        }
    }

    private LogValue[] getLog(int days, PreparedStatement statement) throws SQLException
    {
        statement.setInt(1, days);
        ResultSet rs = statement.executeQuery();
        List<LogValue> values = new ArrayList<>(days);

        while (rs.next())
        {
            LogValue logValue = new LogValue(
                    rs.getFloat(1),
                    rs.getTimestamp(2).toInstant().toString());
            values.add(logValue);
        }
        return values.toArray(new LogValue[values.size()]);
    }

    public void log(Status status) throws SQLException
    {
        try
        {
            connect();
            PreparedStatement statement = prepareStatement(
                    "insert into Conditioner.StatusLog(Temperature, Humidity, Lumen, EnergySinceMidnight) " +
                            "values(?, ?, ?, ?)");
            statement.setFloat(1,
                    status.getClimate().getProperty(Climate.TEMPERATURE));
            statement.setFloat(2,
                    status.getClimate().getProperty((Climate.HUMIDITY)));
            statement.setFloat(3,
                    status.getClimate().getProperty(Climate.LUMEN));
            statement.setFloat(4,
                    status.getUsedEnergySinceMidnight());
            //statement.setTimestamp(5, new Timestamp(LocalDateTime.now().toEpochSecond(ZoneOffset.ofHours(0)) * 1000));

            statement.executeUpdate();
        } finally
        {
            disconnect();
        }
    }
}