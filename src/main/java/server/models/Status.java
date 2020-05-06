package server.models;

public class Status
{
    private Climate climate = new Climate(30, 0.5f, 0.7f);

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
