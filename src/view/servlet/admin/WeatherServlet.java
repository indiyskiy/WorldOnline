package view.servlet.admin;

import model.constants.AdminRule;
import model.constants.Component;
import model.constants.databaseenumeration.Cloudiness;
import model.constants.databaseenumeration.Precipitation;
import model.constants.databaseenumeration.WindDirection;
import model.database.requests.WeatherRequest;
import model.database.worldonlinedb.WeatherEntity;
import model.logger.LoggerFactory;
import view.servlet.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WeatherServlet extends ProtectedServlet {
    private final int MAX_ITEMS = 50;
    private LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, AllUsersServlet.class);

    @Override
    protected AdminRule setAdminRule() {
        return AdminRule.All;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ServletHelper.setUTF8(request, response);
        try {
            WeatherEntity weatherEntity = WeatherRequest.getCurrentWeather();
            request.setAttribute("temperature", "от " + weatherEntity.getMinTemperature() + " до " + weatherEntity.getMaxTemperature() + " C&deg;");
            request.setAttribute("time", weatherEntity.getWeatherTimestamp());
            if (weatherEntity.getWindMin() != weatherEntity.getWindMax()) {
                request.setAttribute("wind", WindDirection.parseInt(weatherEntity.getWindDirection()).toString() + ", " + weatherEntity.getWindMin() + "-" + weatherEntity.getWindMax() + " м/с");
            } else {
                request.setAttribute("wind", WindDirection.parseInt(weatherEntity.getWindDirection()).toString() + ", " + weatherEntity.getWindMin() + " м/с");
            }
            request.setAttribute("cloudiness", Cloudiness.parseInt(weatherEntity.getCloudiness()).toStringHTML());
            request.setAttribute("precipitation", Precipitation.parseInt(weatherEntity.getPrecipitation()).toStringHTML(weatherEntity.getRainPower(), weatherEntity.getLightningPower()));
            ServletHelper.sendForward("/weather.jsp", this, request, response);
        } catch (Exception e) {
            ServletHelper.sendError(e, request, response, this, loggerFactory);
        }
    }

}
