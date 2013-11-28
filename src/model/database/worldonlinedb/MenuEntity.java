package model.database.worldonlinedb;

import model.constants.databaseenumeration.MenuType;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Servcer
 * Date: 28.11.13
 * Time: 17:42
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "Menu", schema = "", catalog = "worldonline")
@Entity
public class MenuEntity {
    @javax.persistence.Column(name = "MenuID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long menuID;

    public Long getMenuID() {
        return menuID;
    }

    public void setMenuID(Long menuID) {
        this.menuID = menuID;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ParentMenuID")
    private MenuEntity parentMenu;

    public MenuEntity getMenu() {
        return parentMenu;
    }

    public void setMenu(MenuEntity menu) {
        this.parentMenu = menu;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "NameTextGroupID")
    private TextGroupEntity nameTextGroup;

    public TextGroupEntity getNameTextGroup() {
        return nameTextGroup;
    }

    public void setNameTextGroup(TextGroupEntity nameTextGroup) {
        this.nameTextGroup = nameTextGroup;
    }

    @javax.persistence.Column(name = "Number")
    @Basic
    private Integer number;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "IconImage")
    private ImageEntity iconImage;

    public ImageEntity getIconImage() {
        return iconImage;
    }

    public void setIconImage(ImageEntity iconImage) {
        this.iconImage = iconImage;
    }


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "PushedIconImage")
    private ImageEntity pushedIconImage;

    public ImageEntity getPushedIconImage() {
        return pushedIconImage;
    }

    public void setPushedIconImage(ImageEntity pushedIconImage) {
        this.pushedIconImage = pushedIconImage;
    }

    @javax.persistence.Column(name = "MenuType")
    @Basic
    private Integer menuType;

    public Integer getMenuType() {
        return menuType;
    }

    public void setMenuType(Integer menuType) {
        this.menuType = menuType;
    }

    public MenuEntity() {
    }

    public MenuEntity(MenuEntity parentMenu, TextGroupEntity nameTextGroup, ImageEntity iconImage, ImageEntity pushedIconImage, MenuType menuType) {
        this.parentMenu = parentMenu;
        this.nameTextGroup = nameTextGroup;
        this.iconImage = iconImage;
        this.pushedIconImage = pushedIconImage;
        if(parentMenu!=null){
        this.menuType = menuType.getValue();
        } else {
            this.menuType= MenuType.RootMenu.getValue();
        }
    }
}
