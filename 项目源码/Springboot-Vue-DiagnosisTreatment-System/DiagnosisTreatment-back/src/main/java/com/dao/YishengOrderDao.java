package com.dao;

import com.entity.YishengOrderEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.YishengOrderView;

/**
 * 医生预约挂号订单 Dao 接口
 *
 * @author 
 */
public interface YishengOrderDao extends BaseMapper<YishengOrderEntity> {

   List<YishengOrderView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
