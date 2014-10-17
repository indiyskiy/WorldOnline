package helper;

import model.constants.Component;
import model.constants.databaseenumeration.DataType;
import model.logger.LoggerFactory;

import java.sql.Timestamp;

public class ParameterValidator {
    static LoggerFactory loggerFactory = new LoggerFactory(Component.Global, ParameterValidator.class);

    public static boolean isValidParameter(String parameter, DataType cardParameterDataType) throws Exception {
        if (parameter.isEmpty()) {
            return true;
        }
        try {
            if (parameter == null) {
                throw new InvalidDataTypeException(null, cardParameterDataType);
            }
            switch (cardParameterDataType) {
                case DoubleType: {
                    try {
                        Double.parseDouble(parameter);
                        return true;
                    } catch (Exception e) {
                        throw new InvalidDataTypeException(parameter, cardParameterDataType);
                    }
                }
                case EmailType: {
                    try {
                        //todo validator
//                    InternetAddress emailAddr = new InternetAddress(parameter);
//                    emailAddr.validate();
                        return true;
                    } catch (Exception e) {
                        throw new InvalidDataTypeException(parameter, cardParameterDataType);
                    }
                }
                case IntegerType: {
                    try {
                        Integer.parseInt(parameter);
                        return true;
                    } catch (Exception e) {
                        throw new InvalidDataTypeException(parameter, cardParameterDataType);
                    }
                }
                case LinkType: {
                    try {
                        //todo validator
                        return true;
                    } catch (Exception e) {
                        throw new InvalidDataTypeException(parameter, cardParameterDataType);
                    }
                }
                case PhoneNumberType: {
                    try {
                        Long.parseLong(parameter);
                        return true;
                    } catch (Exception e) {
                        throw new InvalidDataTypeException(parameter, cardParameterDataType);
                    }
                }
                case StringType: {
                    try {
                        return true;
                    } catch (Exception e) {
                        throw new InvalidDataTypeException(parameter, cardParameterDataType);
                    }
                }
                case UnknownType: {
                    return true;
                }
                case Cost: {
                    try {
                        Double.parseDouble(parameter);
                        return true;
                    } catch (Exception e) {
                        throw new InvalidDataTypeException(parameter, cardParameterDataType);
                    }
                }
                case Header: {
                    return true;
                }
                case Boolean: {
                    try {
                        Boolean.parseBoolean(parameter);
                        return true;
                    } catch (Exception e) {
                        throw new InvalidDataTypeException(parameter, cardParameterDataType);
                    }
                }
                case Percent: {
                    try {
                        Double.parseDouble(parameter);
                        return true;
                    } catch (Exception e) {
                        throw new InvalidDataTypeException(parameter, cardParameterDataType);
                    }
                }
                case TimestampType: {
                    try {
                        Timestamp.valueOf(parameter);
                    } catch (Exception e) {
                        throw new InvalidDataTypeException(parameter, cardParameterDataType);
                    }
                }
            }
        } catch (Exception e) {
            loggerFactory.error("invalid parameter " + parameter);
            loggerFactory.error(e);
            throw e;
        }
        throw new Exception("unknown data type");
    }

}


