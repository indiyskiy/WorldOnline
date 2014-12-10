package model.additionalentity.admin;

public class SimpleCardParameterType {
    private long cardParameterTypeID;
    //    long textGroupID;
    private boolean isTranslatable;
    private boolean isMultiply;

    public long getCardParameterTypeID() {
        return cardParameterTypeID;
    }

    public void setCardParameterTypeID(long cardParameterTypeID) {
        this.cardParameterTypeID = cardParameterTypeID;
    }

    public boolean isTranslatable() {
        return isTranslatable;
    }

    public void setTranslatable(boolean isTranslatable) {
        this.isTranslatable = isTranslatable;
    }

    public boolean isMultiply() {
        return isMultiply;
    }

    public void setMultiply(boolean isMultiply) {
        this.isMultiply = isMultiply;
    }
}
