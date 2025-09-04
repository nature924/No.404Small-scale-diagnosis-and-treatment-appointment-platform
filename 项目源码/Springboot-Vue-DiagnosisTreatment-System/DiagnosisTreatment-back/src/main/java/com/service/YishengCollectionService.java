package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.YishengCollectionEntity;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 * 医生收藏 服务类
 */
public interface YishengCollectionService extends IService<YishengCollectionEntity> {

    /**
    * @param params 查询参数
    * @return 带分页的查询出来的数据
    */
     PageUtils queryPage(Map<String, Object> params);
}