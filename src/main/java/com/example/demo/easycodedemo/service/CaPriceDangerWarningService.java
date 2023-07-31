package com.example.demo.easycodedemo.service;

import com.example.demo.easycodedemo.entity.CaPriceDangerWarning;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (CaPriceDangerWarning)表服务接口
 *
 * @author makejava
 * @since 2022-12-12 14:45:17
 */
public interface CaPriceDangerWarningService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CaPriceDangerWarning queryById(Integer id);

    /**
     * 分页查询
     *
     * @param caPriceDangerWarning 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<CaPriceDangerWarning> queryByPage(CaPriceDangerWarning caPriceDangerWarning, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param caPriceDangerWarning 实例对象
     * @return 实例对象
     */
    CaPriceDangerWarning insert(CaPriceDangerWarning caPriceDangerWarning);

    /**
     * 修改数据
     *
     * @param caPriceDangerWarning 实例对象
     * @return 实例对象
     */
    CaPriceDangerWarning update(CaPriceDangerWarning caPriceDangerWarning);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
