package controller.parser.adminparser;


import model.additionalentity.admin.CardParameter;
import model.database.requests.ParameterRequest;
import model.database.worldonlinedb.CardParameterEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;

public class EditCardParametersParser {

    ArrayList<CardParameterEntity> cardParameters = new ArrayList<>();

    public EditCardParametersParser(HttpServletRequest request) {
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            if (name.contains("parameter")) {
                Long id = Long.parseLong(name.replaceAll("parameter", ""));
                CardParameterEntity cardParameter = ParameterRequest.getCardParameter(id);
                String value = request.getParameter(name);
                if (cardParameter != null) {
                    cardParameter.setCardParameterValue(value);
                    cardParameters.add(cardParameter);
                }
            }
        }
    }

    public ArrayList<CardParameterEntity> getCardParameters() {
        return cardParameters;
    }
}
