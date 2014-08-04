package model.weather;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "MMWEATHER")
public class Mmweather {
    @Element(name = "REPORT")
    Report report;
}
