package model.weather;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name = "PHENOMENA")
public class Phenomena {
    @Attribute(name = "cloudiness")
    public int cloudiness;
    @Attribute(name = "precipitation")
    public int precipitation;
    @Attribute(name = "rpower")
    public int rainPower;
    @Attribute(name = "spower")
    public int lightningPower;
}
