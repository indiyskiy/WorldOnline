package helper;

import model.constants.Component;
import model.constants.databaseenumeration.DataType;
import model.logger.LoggerFactory;

public class ParameterValidator {
    static LoggerFactory loggerFactory = new LoggerFactory(Component.Global, ParameterValidator.class);

    public static String isValidParameter(String parameter, DataType cardParameterDataType) {
        try {
            if (parameter == null || parameter.replaceAll(" ", "").isEmpty()) {
                return null;
            }
            switch (cardParameterDataType) {
                case DoubleType: {
                    parameter = parameter.replaceAll("\"", "");
                    parameter = parameter.replaceAll(",", "");
                    parameter = parameter.replaceAll(" ", "");
                    parameter = parameter.replaceAll("в", "");
                    parameter = parameter.replaceAll("Ђ", "");
                    parameter = parameter.replaceAll("`", "");
//                    parameter = parameter.replaceAll("/", "");
                    return String.valueOf(Double.parseDouble(parameter));
                }
                case EmailType: {
                    //todo validator
//                    InternetAddress emailAddr = new InternetAddress(parameter);
//                    emailAddr.validate();
                    break;
                }
                case IntegerType: {
                    return String.valueOf(Integer.parseInt(parameter));
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
                    parameter = parameter.replaceAll("\"", "");
                    parameter = parameter.replaceAll(" ", "");
                    parameter = parameter.replaceAll("\\(", "");
                    parameter = parameter.replaceAll("\\)", "");
                    parameter = parameter.replaceAll("\\\\", "");
                    parameter = parameter.replaceAll("", "");
                    parameter = parameter.replaceAll("/", "");
                    if (parameter.length() < 3) {
                        return null;
                    }
                    return String.valueOf(Long.parseLong(parameter));
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
            loggerFactory.error("invalid parameter " + parameter);
            loggerFactory.error(e);
            return null;
        }
        return parameter;
    }

}


