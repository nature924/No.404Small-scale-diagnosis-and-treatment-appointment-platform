package com.dao;

import com.entity.YishengLiuyanEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.YishengLiuyanView;

/**
 * 医生留言 Dao 接口
 *
 * @author 
 */
public interface YishengLiuyanDao extends BaseMapper<YishengLiuyanEntity> {

   List<YishengLiuyanView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
