package model.database.worldonlinedb;

import model.constants.databaseenumeration.MenuType;

import javax.persistence.*;

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

    public MenuEntity getParentMenu() {
        return parentMenu;
    }

    public void setParentMenu(MenuEntity menu) {
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
    @JoinColumn(name = "IconImageID")
    private ImageEntity iconImage;

    public ImageEntity getIconImage() {
        return iconImage;
    }

    public void setIconImage(ImageEntity iconImage) {
        this.iconImage = iconImage;
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

    public MenuEntity(MenuEntity parentMenu, TextGroupEntity nameTextGroup, ImageEntity iconImage, MenuType menuType) {
        this.parentMenu = parentMenu;
        this.nameTextGroup = nameTextGroup;
        this.iconImage = iconImage;
        if (parentMenu != null) {
            this.menuType = menuType.getValue();
        } else {
            this.menuType = MenuType.RootMenu.getValue();
        }
    }

    public void setAll(MenuEntity tmp) {
        this.menuID = tmp.menuID;
        this.iconImage = tmp.iconImage;
        this.menuType = tmp.menuType;
        this.nameTextGroup = tmp.nameTextGroup;
        this.number = tmp.number;
        this.parentMenu = tmp.parentMenu;
    }
}
