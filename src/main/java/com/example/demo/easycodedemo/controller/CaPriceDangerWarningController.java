package com.example.demo.easycodedemo.controller;

import com.example.demo.easycodedemo.entity.CaPriceDangerWarning;
import com.example.demo.easycodedemo.service.CaPriceDangerWarningService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (CaPriceDangerWarning)表控制层
 *
 * @author makejava
 * @since 2022-12-12 14:45:16
 */
@RestController
@RequestMapping("caPriceDangerWarning")
public class CaPriceDangerWarningController {
    /**
     * 服务对象
     */
    @Resource
    private CaPriceDangerWarningService caPriceDangerWarningService;

    /**
     * 分页查询
     *
     * @param caPriceDangerWarning 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<CaPriceDangerWarning>> queryByPage(CaPriceDangerWarning caPriceDangerWarning, PageRequest pageRequest) {
        return ResponseEntity.ok(this.caPriceDangerWarningService.queryByPage(caPriceDangerWarning, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<CaPriceDangerWarning> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.caPriceDangerWarningService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param caPriceDangerWarning 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<CaPriceDangerWarning> add(CaPriceDangerWarning caPriceDangerWarning) {
        return ResponseEntity.ok(this.caPriceDangerWarningService.insert(caPriceDangerWarning));
    }

    /**
     * 编辑数据
     *
     * @param caPriceDangerWarning 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<CaPriceDangerWarning> edit(CaPriceDangerWarning caPriceDangerWarning) {
        return ResponseEntity.ok(this.caPriceDangerWarningService.update(caPriceDangerWarning));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.caPriceDangerWarningService.deleteById(id));
    }

}

