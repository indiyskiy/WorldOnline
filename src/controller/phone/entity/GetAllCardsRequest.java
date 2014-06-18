package controller.phone.entity;

/**
 * Created by Илья on 18.04.14.
 */
public class GetAllCardsRequest extends MobileRequest {
    private Integer limit = null;
    private Integer offset = null;

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
