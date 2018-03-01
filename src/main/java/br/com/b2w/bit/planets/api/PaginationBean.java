package br.com.b2w.bit.planets.api;

import br.com.b2w.bit.planets.service.Pagination;

import javax.validation.constraints.Max;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;
import java.io.Serializable;

public class PaginationBean implements Pagination, Serializable {

    private static final long serialVersionUID = 6447602044750129683L;

    private static final int  LIMIT_MAX = 100;

    private static final String LIMIT = "limit";
    private static final String DEFAULT_LIMIT = "10";

    private static final String OFFSET = "offset";
    private static final String DEFAULT_OFFSET = "0";

    @Max(LIMIT_MAX)
    @DefaultValue(DEFAULT_LIMIT)
    @QueryParam(LIMIT)
    private Integer limit;

    @DefaultValue(DEFAULT_OFFSET)
    @QueryParam(OFFSET)
    private Integer offset;

    @Override
    public Integer getLimit() {
        return limit;
    }

    @Override
    public Integer getOffset() {
        return offset;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
