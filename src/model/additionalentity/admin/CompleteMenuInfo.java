package model.additionalentity.admin;

import model.constants.Component;
import model.logger.LoggerFactory;

public class CompleteMenuInfo {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Global, CompleteMenuInfo.class);
    private Long menuID;
    private String name;
    private Long textGroupID;
    private Long parentID;
    private String parentName;
    private Long iconID;
    private int number;

    public static LoggerFactory getLoggerFactory() {
        return loggerFactory;
    }

    public static void setLoggerFactory(LoggerFactory loggerFactory) {
        CompleteMenuInfo.loggerFactory = loggerFactory;
    }

    public Long getMenuID() {
        return menuID;
    }

    public void setMenuID(Long menuID) {
        this.menuID = menuID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTextGroupID() {
        return textGroupID;
    }

    public void setTextGroupID(Long textGroupID) {
        this.textGroupID = textGroupID;
    }

    public Long getParentID() {
        return parentID;
    }

    public void setParentID(Long parentID) {
        this.parentID = parentID;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Long getIconID() {
        return iconID;
    }

    public void setIconID(Long iconID) {
        this.iconID = iconID;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
