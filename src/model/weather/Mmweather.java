package model.weather;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Илья on 13.05.14.
 */
@Root(name = "MMWEATHER")
public class Mmweather {
    @Element(name = "REPORT")
    Report report;
}
