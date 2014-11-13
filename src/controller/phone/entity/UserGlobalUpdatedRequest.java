package controller.phone.entity;


import model.database.worldonlinedb.UserEntity;

public class UserGlobalUpdatedRequest extends MobileRequest {
    private UserEntity user;

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
