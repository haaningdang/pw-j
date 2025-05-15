package com.pw.mbp.page;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pw.mbp.page.dto.PwPage;
import com.pw.mbp.page.request.PwPageRequest;

public class PwPageUtil {

    // 计算当前页码
    private static int current(PwPageRequest request) {
        return (request == null || request.getPage() == null) ? 1 : request.getPage().getCurrent();
    }

    // 计算每页限制
    private static int limit(PwPageRequest request) {
        return (request == null || request.getPage() == null) ? 20 : request.getPage().getLimit();
    }

    // 请求对象转page对象
    public static <T> Page<T> wrapper(PwPageRequest request) {
        return new Page<>(current(request), limit(request));
    }

    // 请求对象转page对象
    public static <T> Page<T> wrapper(PwPageRequest request, String count) {
        Page<T> page = wrapper(request);
        page.setCountId(count);
        return page;
    }

    // 计算分页
    public static int page(int total, int limit) {
        if(limit == 0) return 0;
        return total % limit == 0 ? (total / limit) : (total / limit + 1);
    }

    // 构造返回对象
    public static <T> PwPage<T> response(IPage<T> page){
        PwPage<T> result = new PwPage<>();
        result.setLimit(Convert.toInt(page.getSize(), 20));
        result.setCurrent(Convert.toInt(page.getCurrent(), 1));
        result.setTotal(Convert.toInt(page.getTotal(), 0));
        result.setPage(page(result.getTotal(), result.getLimit()));
        result.setRows(page.getRecords());
        return result;
    }


}
