
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 医生预约挂号订单
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/yishengOrder")
public class YishengOrderController {
    private static final Logger logger = LoggerFactory.getLogger(YishengOrderController.class);

    @Autowired
    private YishengOrderService yishengOrderService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private HuanzheService huanzheService;
    @Autowired
    private YishengService yishengService;



    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("患者".equals(role))
            params.put("huanzheId",request.getSession().getAttribute("userId"));
        else if("医生".equals(role))
            params.put("yishengId",request.getSession().getAttribute("userId"));
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = yishengOrderService.queryPage(params);

        //字典表数据转换
        List<YishengOrderView> list =(List<YishengOrderView>)page.getList();
        for(YishengOrderView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        YishengOrderEntity yishengOrder = yishengOrderService.selectById(id);
        if(yishengOrder !=null){
            //entity转view
            YishengOrderView view = new YishengOrderView();
            BeanUtils.copyProperties( yishengOrder , view );//把实体数据重构到view中

                //级联表
                HuanzheEntity huanzhe = huanzheService.selectById(yishengOrder.getHuanzheId());
                if(huanzhe != null){
                    BeanUtils.copyProperties( huanzhe , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setHuanzheId(huanzhe.getId());
                }
                //级联表
                YishengEntity yisheng = yishengService.selectById(yishengOrder.getYishengId());
                if(yisheng != null){
                    BeanUtils.copyProperties( yisheng , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYishengId(yisheng.getId());
                }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody YishengOrderEntity yishengOrder, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,yishengOrder:{}",this.getClass().getName(),yishengOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("医生".equals(role))
            yishengOrder.setYishengId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        else if("患者".equals(role))
            yishengOrder.setHuanzheId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        yishengOrder.setInsertTime(new Date());
        yishengOrder.setCreateTime(new Date());
        yishengOrderService.insert(yishengOrder);
        return R.ok();
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody YishengOrderEntity yishengOrder, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,yishengOrder:{}",this.getClass().getName(),yishengOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("医生".equals(role))
//            yishengOrder.setYishengId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
//        else if("患者".equals(role))
//            yishengOrder.setHuanzheId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<YishengOrderEntity> queryWrapper = new EntityWrapper<YishengOrderEntity>()
            .eq("id",0)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        YishengOrderEntity yishengOrderEntity = yishengOrderService.selectOne(queryWrapper);
        if(yishengOrderEntity==null){
            yishengOrderService.updateById(yishengOrder);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        yishengOrderService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<YishengOrderEntity> yishengOrderList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            YishengOrderEntity yishengOrderEntity = new YishengOrderEntity();
//                            yishengOrderEntity.setYishengOrderUuidNumber(data.get(0));                    //预约挂号编号 要改的
//                            yishengOrderEntity.setYishengId(Integer.valueOf(data.get(0)));   //医生 要改的
//                            yishengOrderEntity.setHuanzheId(Integer.valueOf(data.get(0)));   //患者 要改的
//                            yishengOrderEntity.setYishengOrderTruePrice(data.get(0));                    //实付价格 要改的
//                            yishengOrderEntity.setYishengOrderTypes(Integer.valueOf(data.get(0)));   //挂号状态 要改的
//                            yishengOrderEntity.setKanbingTime(sdf.parse(data.get(0)));          //预约看病日期 要改的
//                            yishengOrderEntity.setShijianduanTypes(Integer.valueOf(data.get(0)));   //看病时间段 要改的
//                            yishengOrderEntity.setYishengOrderPaymentTypes(Integer.valueOf(data.get(0)));   //支付类型 要改的
//                            yishengOrderEntity.setInsertTime(date);//时间
//                            yishengOrderEntity.setCreateTime(date);//时间
                            yishengOrderList.add(yishengOrderEntity);


                            //把要查询是否重复的字段放入map中
                                //预约挂号编号
                                if(seachFields.containsKey("yishengOrderUuidNumber")){
                                    List<String> yishengOrderUuidNumber = seachFields.get("yishengOrderUuidNumber");
                                    yishengOrderUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> yishengOrderUuidNumber = new ArrayList<>();
                                    yishengOrderUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("yishengOrderUuidNumber",yishengOrderUuidNumber);
                                }
                        }

                        //查询是否重复
                         //预约挂号编号
                        List<YishengOrderEntity> yishengOrderEntities_yishengOrderUuidNumber = yishengOrderService.selectList(new EntityWrapper<YishengOrderEntity>().in("yisheng_order_uuid_number", seachFields.get("yishengOrderUuidNumber")));
                        if(yishengOrderEntities_yishengOrderUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(YishengOrderEntity s:yishengOrderEntities_yishengOrderUuidNumber){
                                repeatFields.add(s.getYishengOrderUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [预约挂号编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        yishengOrderService.insertBatch(yishengOrderList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }





    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        // 没有指定排序字段就默认id倒序
        if(StringUtil.isEmpty(String.valueOf(params.get("orderBy")))){
            params.put("orderBy","id");
        }
        PageUtils page = yishengOrderService.queryPage(params);

        //字典表数据转换
        List<YishengOrderView> list =(List<YishengOrderView>)page.getList();
        for(YishengOrderView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        YishengOrderEntity yishengOrder = yishengOrderService.selectById(id);
            if(yishengOrder !=null){


                //entity转view
                YishengOrderView view = new YishengOrderView();
                BeanUtils.copyProperties( yishengOrder , view );//把实体数据重构到view中

                //级联表
                    HuanzheEntity huanzhe = huanzheService.selectById(yishengOrder.getHuanzheId());
                if(huanzhe != null){
                    BeanUtils.copyProperties( huanzhe , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setHuanzheId(huanzhe.getId());
                }
                //级联表
                    YishengEntity yisheng = yishengService.selectById(yishengOrder.getYishengId());
                if(yisheng != null){
                    BeanUtils.copyProperties( yisheng , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYishengId(yisheng.getId());
                }
                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody YishengOrderEntity yishengOrder, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,yishengOrder:{}",this.getClass().getName(),yishengOrder.toString());
            YishengEntity yishengEntity = yishengService.selectById(yishengOrder.getYishengId());
            if(yishengEntity == null){
                return R.error(511,"查不到该医生");
            }
            // Double yishengNewMoney = yishengEntity.getYishengNewMoney();

            if(false){
            }

            //计算所获得积分
            Double buyJifen =0.0;
            Integer userId = (Integer) request.getSession().getAttribute("userId");
            HuanzheEntity huanzheEntity = huanzheService.selectById(userId);
            if(huanzheEntity == null)
                return R.error(511,"用户不能为空");
            if(huanzheEntity.getNewMoney() == null)
                return R.error(511,"用户金额不能为空");
            double balance = huanzheEntity.getNewMoney() - yishengEntity.getGuahaofei();//余额
            if(balance<0)
                return R.error(511,"余额不够支付");
            yishengOrder.setYishengOrderTypes(1); //设置订单状态为已支付
            yishengOrder.setYishengOrderTruePrice(yishengEntity.getGuahaofei()); //设置实付价格
            yishengOrder.setHuanzheId(userId); //设置订单支付人id
            yishengOrder.setYishengOrderUuidNumber(String.valueOf(new Date().getTime()));
            yishengOrder.setYishengOrderPaymentTypes(1);
            yishengOrder.setInsertTime(new Date());
            yishengOrder.setCreateTime(new Date());
                yishengOrderService.insert(yishengOrder);//新增订单
            huanzheEntity.setNewMoney(balance);//设置金额
            huanzheService.updateById(huanzheEntity);
            return R.ok();
    }

    /**
    * 取消预约
    */
    @RequestMapping("/refund")
    public R refund(Integer id, HttpServletRequest request){
        logger.debug("refund方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        String role = String.valueOf(request.getSession().getAttribute("role"));

            YishengOrderEntity yishengOrder = yishengOrderService.selectById(id);
            Integer yishengOrderPaymentTypes = yishengOrder.getYishengOrderPaymentTypes();
            Integer yishengId = yishengOrder.getYishengId();
            if(yishengId == null)
                return R.error(511,"查不到该医生");
            YishengEntity yishengEntity = yishengService.selectById(yishengId);
            if(yishengEntity == null)
                return R.error(511,"查不到该医生");
            Double yishengNewMoney = yishengEntity.getGuahaofei();
            if(yishengNewMoney == null)
                return R.error(511,"医生价格不能为空");

            Integer userId = (Integer) request.getSession().getAttribute("userId");
            HuanzheEntity huanzheEntity = huanzheService.selectById(userId);
            if(huanzheEntity == null)
                return R.error(511,"用户不能为空");
            if(huanzheEntity.getNewMoney() == null)
                return R.error(511,"用户金额不能为空");



            //判断是什么支付方式 1代表余额 2代表积分
            if(yishengOrderPaymentTypes == 1){//余额支付
                //计算金额
                Double money = yishengEntity.getGuahaofei();
                huanzheEntity.setNewMoney(huanzheEntity.getNewMoney() + money); //设置金额


            }

            yishengOrder.setYishengOrderTypes(2);//设置订单状态为取消预约
            yishengOrderService.updateById(yishengOrder);//根据id更新
            huanzheService.updateById(huanzheEntity);//更新用户信息
            yishengService.updateById(yishengEntity);//更新订单中医生的信息
            return R.ok();
    }


    /**
     * 使用
     */
    @RequestMapping("/deliver")
    public R deliver(Integer id ){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        YishengOrderEntity  yishengOrderEntity = new  YishengOrderEntity();;
        yishengOrderEntity.setId(id);
        yishengOrderEntity.setYishengOrderTypes(3);
        boolean b =  yishengOrderService.updateById( yishengOrderEntity);
        if(!b){
            return R.error("使用出错");
        }
        return R.ok();
    }

















}
