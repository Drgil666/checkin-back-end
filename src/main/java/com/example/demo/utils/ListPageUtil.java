package com.example.demo.utils;

import com.example.demo.pojo.vo.ReturnPage;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.Data;

/**
 * @author Gilbert
 * @date 2020/11/14 16:48
 */
@Data
public class ListPageUtil {
    /**
     * 分页上限
     */
    public static Integer INT_LIMIT = Integer.MAX_VALUE;

    /**
     * 创建分页
     *
     * @param current  当前页面
     * @param pageSize 页面大小
     * @param sorter   排序方式
     */
    public static void paging(Integer current, Integer pageSize, String sorter) throws Exception {
        if (current == null && pageSize == null) {
            current = 1;
            pageSize = INT_LIMIT;
        }
        assert current != null : "current参数错误!";
        assert pageSize != null : "pageSize参数错误!";
        if (sorter != null) {
            sorter = OrderToolUtil.toOrderString(sorter);
            PageHelper.startPage(current, pageSize, sorter);
        } else {
            PageHelper.startPage(current, pageSize);
        }
    }

    /**
     * 封装页面
     *
     * @param data 要封装的数据
     * @return 封装好的页面类
     */
    public static <T> ReturnPage<T> returnPage(PageInfo<T> data) {
        ReturnPage<T> returnPage = new ReturnPage<>();
        returnPage.setPageSize(data.getPageSize());
        returnPage.setCurrent(data.getPageNum());
        returnPage.setTotal(data.getTotal());
        returnPage.setData(data.getList());
        return returnPage;
    }
}
