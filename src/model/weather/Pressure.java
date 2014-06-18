package model.weather;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * Created by Илья on 13.05.14.
 */
@Root(name = "PRESSURE")
public class Pressure {
    @Attribute(name = "max")
    public int max;
    @Attribute(name = "min")
    public int min;
}
