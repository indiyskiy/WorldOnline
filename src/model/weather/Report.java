package model.weather;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Илья on 13.05.14.
 */
@Root(name = "REPORT")
public class Report {
    @Element(name = "TOWN")
    Town town;
}
