package model.weather;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "REPORT")
public class Report {
    @Element(name = "TOWN")
    Town town;
}
