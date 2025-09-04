
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
 * 病例
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/bingli")
public class BingliController {
    private static final Logger logger = LoggerFactory.getLogger(BingliController.class);

    @Autowired
    private BingliService bingliService;


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
        PageUtils page = bingliService.queryPage(params);

        //字典表数据转换
        List<BingliView> list =(List<BingliView>)page.getList();
        for(BingliView c:list){
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
        BingliEntity bingli = bingliService.selectById(id);
        if(bingli !=null){
            //entity转view
            BingliView view = new BingliView();
            BeanUtils.copyProperties( bingli , view );//把实体数据重构到view中

                //级联表
                HuanzheEntity huanzhe = huanzheService.selectById(bingli.getHuanzheId());
                if(huanzhe != null){
                    BeanUtils.copyProperties( huanzhe , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setHuanzheId(huanzhe.getId());
                }
                //级联表
                YishengEntity yisheng = yishengService.selectById(bingli.getYishengId());
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
    public R save(@RequestBody BingliEntity bingli, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,bingli:{}",this.getClass().getName(),bingli.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("患者".equals(role))
            bingli.setHuanzheId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        else if("医生".equals(role)){
            bingli.setJiaofeiTypes(1);
            bingli.setYishengId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        }

        Wrapper<BingliEntity> queryWrapper = new EntityWrapper<BingliEntity>()
            .eq("huanzhe_id", bingli.getHuanzheId())
            .eq("yisheng_id", bingli.getYishengId())
            .eq("bingli_uuid_number", bingli.getBingliUuidNumber())
            .eq("bingli_name", bingli.getBingliName())
            .eq("bingli_address", bingli.getBingliAddress())
            .eq("bingli_jiguan", bingli.getBingliJiguan())
            .eq("bingli_hunyin", bingli.getBingliHunyin())
            .eq("jiaofei_types", bingli.getJiaofeiTypes())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        BingliEntity bingliEntity = bingliService.selectOne(queryWrapper);
        if(bingliEntity==null){
            bingli.setInsertTime(new Date());
            bingli.setCreateTime(new Date());
            bingliService.insert(bingli);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody BingliEntity bingli, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,bingli:{}",this.getClass().getName(),bingli.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("患者".equals(role))
//            bingli.setHuanzheId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
//        else if("医生".equals(role))
//            bingli.setYishengId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<BingliEntity> queryWrapper = new EntityWrapper<BingliEntity>()
            .notIn("id",bingli.getId())
            .andNew()
            .eq("huanzhe_id", bingli.getHuanzheId())
            .eq("yisheng_id", bingli.getYishengId())
            .eq("bingli_uuid_number", bingli.getBingliUuidNumber())
            .eq("bingli_name", bingli.getBingliName())
            .eq("bingli_address", bingli.getBingliAddress())
            .eq("bingli_jiguan", bingli.getBingliJiguan())
            .eq("bingli_hunyin", bingli.getBingliHunyin())
            .eq("jiaofei_types", bingli.getJiaofeiTypes())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        BingliEntity bingliEntity = bingliService.selectOne(queryWrapper);
        if(bingliEntity==null){
            bingliService.updateById(bingli);//根据id更新
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
        bingliService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }



    /**
    *  缴费
    */
    @RequestMapping("/jiaofei")
    public R jiaofei(@RequestParam("id") Integer id){
        logger.debug("jiaofei:,,Controller:{},,id:{}",this.getClass().getName(),id.toString());

        BingliEntity bingliEntity = bingliService.selectById(id);
        if(bingliEntity == null)
            return R.error("查不到缴费清单");
        if(bingliEntity.getJiaofeiTypes()==2)
            return R.error("已经缴过费用了");

        HuanzheEntity huanzheEntity = huanzheService.selectById(bingliEntity.getHuanzheId());
        double balance = huanzheEntity.getNewMoney() - bingliEntity.getJiaofeiJine();

        if(balance<0)
            return R.error("当前余额不够支付,请去前台充值后再支付");


        bingliEntity.setJiaofeiTypes(2);
        bingliService.updateById(bingliEntity);


        huanzheEntity.setNewMoney(balance);
        huanzheService.updateById(huanzheEntity);

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
            List<BingliEntity> bingliList = new ArrayList<>();//上传的东西
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
                            BingliEntity bingliEntity = new BingliEntity();
//                            bingliEntity.setHuanzheId(Integer.valueOf(data.get(0)));   //患者 要改的
//                            bingliEntity.setYishengId(Integer.valueOf(data.get(0)));   //医生 要改的
//                            bingliEntity.setBingliUuidNumber(data.get(0));                    //病例编号 要改的
//                            bingliEntity.setBingliName(data.get(0));                    //病例名称 要改的
//                            bingliEntity.setBingliAddress(data.get(0));                    //现住址 要改的
//                            bingliEntity.setBingliJiguan(data.get(0));                    //籍贯 要改的
//                            bingliEntity.setBingliHunyin(data.get(0));                    //婚姻状况 要改的
//                            bingliEntity.setZhusuContent("");//详情和图片
//                            bingliEntity.setJiwangshiContent("");//详情和图片
//                            bingliEntity.setGerenContent("");//详情和图片
//                            bingliEntity.setYichuanContent("");//详情和图片
//                            bingliEntity.setZhenduanContent("");//详情和图片
//                            bingliEntity.setYaofangContent("");//详情和图片
//                            bingliEntity.setKanbingTime(sdf.parse(data.get(0)));          //看病时间 要改的
//                            bingliEntity.setJiaofeiJine(data.get(0));                    //缴费金额 要改的
//                            bingliEntity.setInsertTime(date);//时间
//                            bingliEntity.setJiaofeiTypes(Integer.valueOf(data.get(0)));   //是否缴费 要改的
//                            bingliEntity.setCreateTime(date);//时间
                            bingliList.add(bingliEntity);


                            //把要查询是否重复的字段放入map中
                                //病例编号
                                if(seachFields.containsKey("bingliUuidNumber")){
                                    List<String> bingliUuidNumber = seachFields.get("bingliUuidNumber");
                                    bingliUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> bingliUuidNumber = new ArrayList<>();
                                    bingliUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("bingliUuidNumber",bingliUuidNumber);
                                }
                        }

                        //查询是否重复
                         //病例编号
                        List<BingliEntity> bingliEntities_bingliUuidNumber = bingliService.selectList(new EntityWrapper<BingliEntity>().in("bingli_uuid_number", seachFields.get("bingliUuidNumber")));
                        if(bingliEntities_bingliUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(BingliEntity s:bingliEntities_bingliUuidNumber){
                                repeatFields.add(s.getBingliUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [病例编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        bingliService.insertBatch(bingliList);
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
        PageUtils page = bingliService.queryPage(params);

        //字典表数据转换
        List<BingliView> list =(List<BingliView>)page.getList();
        for(BingliView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        BingliEntity bingli = bingliService.selectById(id);
            if(bingli !=null){


                //entity转view
                BingliView view = new BingliView();
                BeanUtils.copyProperties( bingli , view );//把实体数据重构到view中

                //级联表
                    HuanzheEntity huanzhe = huanzheService.selectById(bingli.getHuanzheId());
                if(huanzhe != null){
                    BeanUtils.copyProperties( huanzhe , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setHuanzheId(huanzhe.getId());
                }
                //级联表
                    YishengEntity yisheng = yishengService.selectById(bingli.getYishengId());
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
    public R add(@RequestBody BingliEntity bingli, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,bingli:{}",this.getClass().getName(),bingli.toString());
        Wrapper<BingliEntity> queryWrapper = new EntityWrapper<BingliEntity>()
            .eq("huanzhe_id", bingli.getHuanzheId())
            .eq("yisheng_id", bingli.getYishengId())
            .eq("bingli_uuid_number", bingli.getBingliUuidNumber())
            .eq("bingli_name", bingli.getBingliName())
            .eq("bingli_address", bingli.getBingliAddress())
            .eq("bingli_jiguan", bingli.getBingliJiguan())
            .eq("bingli_hunyin", bingli.getBingliHunyin())
            .eq("jiaofei_types", bingli.getJiaofeiTypes())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        BingliEntity bingliEntity = bingliService.selectOne(queryWrapper);
        if(bingliEntity==null){
            bingli.setInsertTime(new Date());
            bingli.setCreateTime(new Date());
        bingliService.insert(bingli);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }


}
