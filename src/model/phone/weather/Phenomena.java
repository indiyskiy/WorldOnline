package model.phone.weather;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * Created by Илья on 13.05.14.
 */
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
