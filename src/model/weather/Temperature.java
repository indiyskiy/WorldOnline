package model.weather;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name = "TEMPERATURE")
public class Temperature {
    @Attribute(name = "max")
    public int max;
    @Attribute(name = "min")
    public int min;
}
