package model.constants;

import java.util.HashSet;

public enum AdminRule {
    All,
    Registered,
    Moderator,
    HeightModerator,
    AdminOnly,
    Nobody,
    Unregistered,
    Client;

    public HashSet<ProtectAdminLevel> getAccessStatusMap() {
        HashSet<ProtectAdminLevel> protectAdminLevels = new HashSet<ProtectAdminLevel>();
        switch (this) {
            case All: {
                protectAdminLevels.add(ProtectAdminLevel.Administrator);
                protectAdminLevels.add(ProtectAdminLevel.HeightModerator);
                protectAdminLevels.add(ProtectAdminLevel.LowModerator);
                protectAdminLevels.add(ProtectAdminLevel.Client);
                protectAdminLevels.add(ProtectAdminLevel.Registered);
                protectAdminLevels.add(ProtectAdminLevel.Unregistered);
                break;
            }
            case Registered: {
                protectAdminLevels.add(ProtectAdminLevel.Administrator);
                protectAdminLevels.add(ProtectAdminLevel.HeightModerator);
                protectAdminLevels.add(ProtectAdminLevel.LowModerator);
                protectAdminLevels.add(ProtectAdminLevel.Client);
                protectAdminLevels.add(ProtectAdminLevel.Registered);
                break;
            }
            case Client: {
                protectAdminLevels.add(ProtectAdminLevel.Administrator);
                protectAdminLevels.add(ProtectAdminLevel.HeightModerator);
                protectAdminLevels.add(ProtectAdminLevel.LowModerator);
                protectAdminLevels.add(ProtectAdminLevel.Client);
                break;
            }
            case Moderator: {
                protectAdminLevels.add(ProtectAdminLevel.Administrator);
                protectAdminLevels.add(ProtectAdminLevel.HeightModerator);
                protectAdminLevels.add(ProtectAdminLevel.LowModerator);
                break;
            }
            case HeightModerator: {
                protectAdminLevels.add(ProtectAdminLevel.Administrator);
                protectAdminLevels.add(ProtectAdminLevel.HeightModerator);
                break;
            }
            case AdminOnly: {
                protectAdminLevels.add(ProtectAdminLevel.Administrator);
                break;
            }
            case Nobody: {
                protectAdminLevels.add(ProtectAdminLevel.Administrator);
                break;
            }
            case Unregistered: {
                protectAdminLevels.add(ProtectAdminLevel.Unregistered);
                break;
            }
            default: {
                break;
            }
        }
        return protectAdminLevels;
    }
}
