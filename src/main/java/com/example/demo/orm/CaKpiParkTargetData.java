package com.example.demo.orm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 园区指标数据表
 * </p>
 *
 * @author wesley
 * @since 2022-08-15
 */

@Data
@Accessors(chain = true)
@ApiModel("园区指标数据表")
public class CaKpiParkTargetData{

    /**
     * 主键ID
     */
    @ApiModelProperty("主键ID")
    private Integer id;
    /**
     * 园区ID
     */
    @ApiModelProperty("园区ID")
    private Integer parkId;
    /**
     * 年份
     */
    @ApiModelProperty("年份")
    private Integer year;
    /**
     * 园区类型
     */
    @ApiModelProperty("园区类型")
    private String parkType;
    /**
     * 园区内二氧化碳排放总量（吨CO2）
     */
    @ApiModelProperty("园区内二氧化碳排放总量（吨CO2）")
    private Double carBonEmission;
    /**
     * 园区工业增加总值（万元）
     */
    @ApiModelProperty("园区工业增加总值（万元）")
    private Double gdpRaise;
    /**
     * 上年度单位工业增加值的碳排放量（吨CO2）
     */
    @ApiModelProperty("上年度单位工业增加值的碳排放量（吨CO2）")
    private Double lastYearEmissionRatioStren;
    /**
     * 本年度单位工业增加值的碳排放量（吨CO2）
     */
    @ApiModelProperty("本年度单位工业增加值的碳排放量（吨CO2）")
    private Double emissionRatioStren;
    /**
     * 园区内一二三星级标准建筑面积总和（平方米）
     */
    @ApiModelProperty("园区内一二三星级标准建筑面积总和（平方米）")
    private Double oneTwoThreeLevelBuildingArea;
    /**
     * 园区内建筑总面积（平方米）
     */
    @ApiModelProperty("园区内建筑总面积（平方米）")
    private Double buildingArea;
    /**
     * 新能源公交车数量（辆）
     */
    @ApiModelProperty("新能源公交车数量（辆）")
    private Double newEnergyBus;
    /**
     * 园区公交车总量（辆）
     */
    @ApiModelProperty("园区公交车总量（辆）")
    private Double bus;
    /**
     * 热电联产比例（%）
     */
    @ApiModelProperty("热电联产比例（%）")
    private Double heatPowerCombinedRate;
    /**
     * 绿色电力购买量（kW·h）
     */
    @ApiModelProperty("绿色电力购买量（kW·h）")
    private Double greenPower;
    /**
     * 园区用电量（kW·h）
     */
    @ApiModelProperty("园区用电量（kW·h）")
    private Double power;
    /**
     * 园区内绿地面积（平方米）
     */
    @ApiModelProperty("园区内绿地面积（平方米）")
    private Double parkGreenArea;
    /**
     * 园区用地面积（平方米）
     */
    @ApiModelProperty("园区用地面积（平方米）")
    private Double parkArea;
    /**
     * 工业园区生产总值（万元）
     */
    @ApiModelProperty("工业园区生产总值（万元）")
    private Double industryGdp;
    /**
     * 能源综合消耗总量（吨标准煤）
     */
    @ApiModelProperty("能源综合消耗总量（吨标准煤）")
    private Double energyGenConsume;
    /**
     * 工业固体废物回收利用率
     */
    @ApiModelProperty("工业固体废物回收利用率")
    private Double solidGarbageRecycleRate;
    /**
     * 园区可再生能源消耗量（吨标煤）
     */
    @ApiModelProperty("园区可再生能源消耗量（吨标煤）")
    private Double renewableEnergyConsume;
    /**
     * 园区能源耗总量（吨标煤）
     */
    @ApiModelProperty("园区能源耗总量（吨标煤）")
    private Double energyConsume;
    /**
     * 循环经济产业链上的企业总产值（万元）
     */
    @ApiModelProperty("循环经济产业链上的企业总产值（万元）")
    private Double circleEconomicGdp;
    /**
     * 园区总产值（万元）
     */
    @ApiModelProperty("园区总产值（万元）")
    private Double gdp;

}
