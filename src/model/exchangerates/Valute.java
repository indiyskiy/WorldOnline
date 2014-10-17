package model.exchangerates;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Valute")
public class Valute {

    @Attribute(name = "ID", required = true)
    public String id;

    @Element(name = "NumCode", required = true)
    public int numCode;

    @Element(name = "CharCode", required = true)
    public String charCode;

    @Element(name = "Nominal", required = true)
    public String nominal;

    @Element(name = "Name", required = true)
    public String name;

    @Element(name = "Value", required = true)
    public String value;

}
