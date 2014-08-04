package model.weather;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name = "HEAT")
public class Heat {
    @Attribute(name = "max")
    public
    int max;
    @Attribute(name = "min")
    public
    int min;
}
