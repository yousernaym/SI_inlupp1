package server.models;

import java.time.Instant;
import java.time.LocalDateTime;

public class LogValue
{
    private float value;
    private LocalDateTime created;

    public LocalDateTime getCreated()
    {
        return created;
    }

    public void setCreated(LocalDateTime created)
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
