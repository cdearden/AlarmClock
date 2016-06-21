/* Author:      Craig Dearden
 * Date:        Jun 21, 2016
 * Name:        Alarm.java
 * Description: Implements the methods to create and modify a simple alarm.
 */
package com.craigdearden.time;

import java.sql.Date;

public class Alarm
{
    private String _name;
    private long _time;
    private Date _repeat;
    private boolean _on;
//  private music _tone;
    
    Alarm()
    {
        _name = "";
        _time = 0;
        _repeat = null;
        _on = false;
    }

    Alarm(String name, long time, Date repeat, boolean on)
    {
        _name = name;
        _time = time;
        _repeat = repeat;
        _on = on;
    }
   

    /**
     * @return the _name
     */
    public String getName()
    {
        return _name;
    }

    /**
     * @param _name the _name to set
     */
    public void setName(String name)
    {
        this._name = name;
    }

    /**
     * @return the _time
     */
    public long getTime()
    {
        return _time;
    }

    /**
     * @param _time the _time to set
     */
    public void setTime(long time)
    {
        this._time = time;
    }

    /**
     * @return the _repeat
     */
    public Date getRepeat()
    {
        return _repeat;
    }

    /**
     * @param _repeat the _repeat to set
     */
    public void setRepeat(Date repeat)
    {
        this._repeat = repeat;
    }

    /**
     * @return the _on
     */
    public boolean isOn()
    {
        return _on;
    }

    /**
     * @param _on the _on to set
     */
    public void setOn(boolean on)
    {
        this._on = on;
    }

    @Override
    public String toString()
    {
        String status = isOn() ? "ON" : "OFF";
        String alarm = String.format("%1$20s, %2$50d, Repeat every: %3$50t, %4$10s", getName(), getTime(), getRepeat(), "Alarm: " + status);
        return alarm;
    }
}
