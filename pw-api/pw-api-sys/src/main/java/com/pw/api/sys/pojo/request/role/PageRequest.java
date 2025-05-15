package com.pw.api.sys.pojo.request.role;

import com.pw.mbp.page.request.PwPageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PageRequest extends PwPageRequest {

    private String keyword;

}
