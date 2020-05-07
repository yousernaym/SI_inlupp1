package server.models;

public class Status
{
    private Climate climate = new Climate();
    public Status()
    {
        climate.setProperty(Climate.TEMPERATURE, 31);
        climate.setProperty(Climate.HUMIDITY, 50);
        climate.setProperty(Climate.LUMEN, 70);
    }

    public Climate getClimate()
    {
        return climate;
    }

    public float getUsedEnergySinceMidnight()
    {
        return 30;
    }
}
