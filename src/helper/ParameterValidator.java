package helper;

import model.constants.Component;
import model.constants.databaseenumeration.DataType;
import model.logger.LoggerFactory;

/**
 * Created by Илья on 07.04.14.
 */
public class ParameterValidator {
    static LoggerFactory loggerFactory = new LoggerFactory(Component.Global, ParameterValidator.class);

    public static boolean isValidParameter(String parameter, DataType cardParameterDataType) {
        try {
            if (parameter == null || parameter.isEmpty()) {
                return false;
            }
            switch (cardParameterDataType) {
                case DoubleType: {
                    Double.parseDouble(parameter);
                    break;
                }
                case EmailType: {
                    //todo validator
//                    InternetAddress emailAddr = new InternetAddress(parameter);
//                    emailAddr.validate();
                    break;
                }
                case IntegerType: {
                    Integer.parseInt(parameter);
                    break;
                }
                case LinkType: {
                    //todo validator
                    break;
                }
                case PhoneNumberType: {
                    if (parameter.startsWith("+")) {
                        parameter = parameter.substring(1, parameter.length());
                    }
                    parameter = parameter.replaceAll("-", "");
                    parameter = parameter.replaceAll(" ", "");
                    parameter = parameter.replaceAll("\\(", "");
                    parameter = parameter.replaceAll("\\)", "");
                    if (parameter.length() < 3) {
                        return false;
                    }
                    Long.parseLong(parameter);
                    break;
                }
                case StringType: {
                    break;
                }
                case UnknownType: {
                    break;
                }
                default: {
                    break;
                }
            }

        } catch (Exception e) {
            loggerFactory.error(e);
            return false;
        }
        return true;
    }
}
