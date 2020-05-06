package server.models;

public class LogValue
{
    private float value;
    private String created;

    public LogValue()
    {

    }
    public LogValue(float value, String created)
    {
        this.value = value;
        this.created = created;
    }

    public String getCreated()
    {
        return created;
    }

    public void setCreated(String created)
    {
        this.created = created;
    }

    public float getValue()
    {
        return value;
    }

    public void setValue(float value)
    {
        this.value = value;
    }
}
