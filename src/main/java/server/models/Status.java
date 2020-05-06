package server.models;

public class Status
{
    private Climate climate = new Climate();
    public Status()
    {
        climate.setProperty(Climate.TEMPERATURE, 31);
        climate.setProperty(Climate.HUMIDITY, 0.5f);
        climate.setProperty(Climate.LUMEN, 0.7f);
    }

    public Climate getClimate()
    {
        return climate;
    }

    public void setClimate(Climate climate)
    {
        this.climate = climate;
    }

    public float getUsedEnergySinceMidnight()
    {
        return 30;
    }
}
