package client.models;

import java.time.Instant;

public class LogValue
{
    private float value;
    private Instant created;

    public Instant getCreated()
    {
        return created;
    }

    public void setCreated(Instant created)
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
