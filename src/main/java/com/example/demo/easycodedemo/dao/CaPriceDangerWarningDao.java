package com.example.demo.easycodedemo.dao;

import com.example.demo.easycodedemo.entity.CaPriceDangerWarning;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (CaPriceDangerWarning)表数据库访问层
 *
 * @author makejava
 * @since 2022-12-12 14:45:17
 */
@Mapper
public interface CaPriceDangerWarningDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CaPriceDangerWarning queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param caPriceDangerWarning 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<CaPriceDangerWarning> queryAllByLimit(CaPriceDangerWarning caPriceDangerWarning, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param caPriceDangerWarning 查询条件
     * @return 总行数
     */
    long count(CaPriceDangerWarning caPriceDangerWarning);

    /**
     * 新增数据
     *
     * @param caPriceDangerWarning 实例对象
     * @return 影响行数
     */
    int insert(CaPriceDangerWarning caPriceDangerWarning);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<CaPriceDangerWarning> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<CaPriceDangerWarning> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<CaPriceDangerWarning> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<CaPriceDangerWarning> entities);

    /**
     * 修改数据
     *
     * @param caPriceDangerWarning 实例对象
     * @return 影响行数
     */
    int update(CaPriceDangerWarning caPriceDangerWarning);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

