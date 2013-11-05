package view.servlet;

import model.database.requests.UserRequests;
import model.database.worldonlinedb.UserEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 17.10.13
 * Time: 15:09
 * To change this template use File | Settings | File Templates.
 */
public class ExampleServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
   /*     UserEntity user1=new UserEntity();
        UserContentEntity userContent=new UserContentEntity();
        GlobalVersionEntity globalVersion=new GlobalVersionEntity();
        globalVersion.setActivationTimestamp(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        globalVersion.setCreationTimestamp(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        globalVersion.setDisableTimestamp(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        globalVersion.setGlobalVersionName("test version");
        userContent.setGlobalVersion(globalVersion);
        userContent.setLastConnectionTimestamp(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        userContent.setLastSynchronizeTimestamp(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        user1.setUserContent(userContent);
        user1.setUserHardware(new UserHardwareEntity());
        user1.setUserPersonalData(new UserPersonalDataEntity());
        UserRequests.addUser(user1);
        UserRequests.addUser(user1);
        if(UserRequests.contains(user1)){
            UserRequests.addUser(user1);
        }*/
        ArrayList<UserEntity> allUsers = UserRequests.getAllUsers();
        request.setAttribute("users", allUsers);
        request.setAttribute("size", allUsers.size());
        ServletHelper.sendForward("/example.jsp", this, request, response);
    }

}
