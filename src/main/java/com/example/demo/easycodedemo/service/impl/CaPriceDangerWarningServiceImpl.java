package com.example.demo.easycodedemo.service.impl;

import com.example.demo.easycodedemo.entity.CaPriceDangerWarning;
import com.example.demo.easycodedemo.dao.CaPriceDangerWarningDao;
import com.example.demo.easycodedemo.service.CaPriceDangerWarningService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (CaPriceDangerWarning)表服务实现类
 *
 * @author makejava
 * @since 2022-12-12 14:45:17
 */
@Service("caPriceDangerWarningService")
public class CaPriceDangerWarningServiceImpl implements CaPriceDangerWarningService {
    @Resource
    private CaPriceDangerWarningDao caPriceDangerWarningDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public CaPriceDangerWarning queryById(Integer id) {
        return this.caPriceDangerWarningDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param caPriceDangerWarning 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<CaPriceDangerWarning> queryByPage(CaPriceDangerWarning caPriceDangerWarning, PageRequest pageRequest) {
        long total = this.caPriceDangerWarningDao.count(caPriceDangerWarning);
        return new PageImpl<>(this.caPriceDangerWarningDao.queryAllByLimit(caPriceDangerWarning, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param caPriceDangerWarning 实例对象
     * @return 实例对象
     */
    @Override
    public CaPriceDangerWarning insert(CaPriceDangerWarning caPriceDangerWarning) {
        this.caPriceDangerWarningDao.insert(caPriceDangerWarning);
        return caPriceDangerWarning;
    }

    /**
     * 修改数据
     *
     * @param caPriceDangerWarning 实例对象
     * @return 实例对象
     */
    @Override
    public CaPriceDangerWarning update(CaPriceDangerWarning caPriceDangerWarning) {
        this.caPriceDangerWarningDao.update(caPriceDangerWarning);
        return this.queryById(caPriceDangerWarning.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.caPriceDangerWarningDao.deleteById(id) > 0;
    }
}
