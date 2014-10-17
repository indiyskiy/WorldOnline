package model.exchangerates;


import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "ValCurs")
public class ValCurs {
    @Attribute(name = "Date", required = true)
    public String date;

    @Attribute(name = "name", required = true)
    public String name;

    @ElementList(inline = true, name = "Valute")
    public List<Valute> valutes;
}
