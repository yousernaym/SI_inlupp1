package server.models;

public class Status
{
    private Environment environment;

    public Environment getEnvironment()
    {
        return environment;
    }

    public void setEnvironment(Environment environment)
    {
        this.environment = environment;
    }

    public float getUsedEnergySinceMidnight()
    {
        return 30;
    }
}
