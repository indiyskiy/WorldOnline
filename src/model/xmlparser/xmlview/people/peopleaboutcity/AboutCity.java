package model.xmlparser.xmlview.people.peopleaboutcity;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Aboutcity")
public class AboutCity {
    @Attribute(name = "id", required = true)
    public String id;

    @Element(name = "ParentMenuID", required = false)
    public String parentMenuID;
    @Element(name = "BiographyRu", required = false)
    public String biographyRu;
    @Element(name = "BiographyEn", required = false)
    public String biographyEn;
    @Element(name = "Cards", required = false)
    public String cards;
    @Element(name = "NameEn", required = false)
    public String nameEn;
    @Element(name = "NameRu", required = false)
    public String nameRu;
    @Element(name = "Order", required = false)
    public String order;
    @Element(name = "Photo", required = false)
    public String photo;
    @Element(name = "StoryEn", required = false)
    public String storyEn;
    @Element(name = "StoryRu", required = false)
    public String storyRu;
    @Element(name = "RecommendEn", required = false)
    public String recommendEn;
    @Element(name = "RecommendRu", required = false)
    public String recommendRu;
    @Element(name = "Phone", required = false)
    public String phone;
    @Element(name = "Site", required = false)
    public String site;
    @Element(name = "Email", required = false)
    public String email;
    @Element(name = "Vkcom", required = false)
    public String vkCom;
    @Element(name = "Fbcom", required = false)
    public String fbCom;
    @Element(name = "Twitter", required = false)
    public String twitter;
    @Element(name = "Frsqr", required = false)
    public String frsqr;
    @Element(name = "CardImage", required = false)
    public String cardImage;
    @Element(name = "LiveJournal", required = false)
    public String liveJournal;
}
