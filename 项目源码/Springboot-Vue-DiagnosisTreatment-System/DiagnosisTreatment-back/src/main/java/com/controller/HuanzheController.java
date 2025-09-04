
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
 * 患者
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/huanzhe")
public class HuanzheController {
    private static final Logger logger = LoggerFactory.getLogger(HuanzheController.class);

    @Autowired
    private HuanzheService huanzheService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service

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
        PageUtils page = huanzheService.queryPage(params);

        //字典表数据转换
        List<HuanzheView> list =(List<HuanzheView>)page.getList();
        for(HuanzheView c:list){
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
        HuanzheEntity huanzhe = huanzheService.selectById(id);
        if(huanzhe !=null){
            //entity转view
            HuanzheView view = new HuanzheView();
            BeanUtils.copyProperties( huanzhe , view );//把实体数据重构到view中

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
    public R save(@RequestBody HuanzheEntity huanzhe, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,huanzhe:{}",this.getClass().getName(),huanzhe.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<HuanzheEntity> queryWrapper = new EntityWrapper<HuanzheEntity>()
            .eq("username", huanzhe.getUsername())
            .or()
            .eq("huanzhe_phone", huanzhe.getHuanzhePhone())
            .or()
            .eq("huanzhe_id_number", huanzhe.getHuanzheIdNumber())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        HuanzheEntity huanzheEntity = huanzheService.selectOne(queryWrapper);
        if(huanzheEntity==null){
            huanzhe.setCreateTime(new Date());
            huanzhe.setPassword("123456");
            huanzheService.insert(huanzhe);
            return R.ok();
        }else {
            return R.error(511,"账户或者患者手机号或者患者身份证号已经被使用");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody HuanzheEntity huanzhe, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,huanzhe:{}",this.getClass().getName(),huanzhe.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        //根据字段查询是否有相同数据
        Wrapper<HuanzheEntity> queryWrapper = new EntityWrapper<HuanzheEntity>()
            .notIn("id",huanzhe.getId())
            .andNew()
            .eq("username", huanzhe.getUsername())
            .or()
            .eq("huanzhe_phone", huanzhe.getHuanzhePhone())
            .or()
            .eq("huanzhe_id_number", huanzhe.getHuanzheIdNumber())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        HuanzheEntity huanzheEntity = huanzheService.selectOne(queryWrapper);
        if("".equals(huanzhe.getHuanzhePhoto()) || "null".equals(huanzhe.getHuanzhePhoto())){
                huanzhe.setHuanzhePhoto(null);
        }
        if(huanzheEntity==null){
            huanzheService.updateById(huanzhe);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"账户或者患者手机号或者患者身份证号已经被使用");
        }
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        huanzheService.deleteBatchIds(Arrays.asList(ids));
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
            List<HuanzheEntity> huanzheList = new ArrayList<>();//上传的东西
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
                            HuanzheEntity huanzheEntity = new HuanzheEntity();
//                            huanzheEntity.setUsername(data.get(0));                    //账户 要改的
//                            //huanzheEntity.setPassword("123456");//密码
//                            huanzheEntity.setHuanzheName(data.get(0));                    //患者姓名 要改的
//                            huanzheEntity.setHuanzhePhone(data.get(0));                    //患者手机号 要改的
//                            huanzheEntity.setHuanzheIdNumber(data.get(0));                    //患者身份证号 要改的
//                            huanzheEntity.setHuanzhePhoto("");//详情和图片
//                            huanzheEntity.setSexTypes(Integer.valueOf(data.get(0)));   //性别 要改的
//                            huanzheEntity.setHuanzheEmail(data.get(0));                    //电子邮箱 要改的
//                            huanzheEntity.setNewMoney(data.get(0));                    //余额 要改的
//                            huanzheEntity.setCreateTime(date);//时间
                            huanzheList.add(huanzheEntity);


                            //把要查询是否重复的字段放入map中
                                //账户
                                if(seachFields.containsKey("username")){
                                    List<String> username = seachFields.get("username");
                                    username.add(data.get(0));//要改的
                                }else{
                                    List<String> username = new ArrayList<>();
                                    username.add(data.get(0));//要改的
                                    seachFields.put("username",username);
                                }
                                //患者手机号
                                if(seachFields.containsKey("huanzhePhone")){
                                    List<String> huanzhePhone = seachFields.get("huanzhePhone");
                                    huanzhePhone.add(data.get(0));//要改的
                                }else{
                                    List<String> huanzhePhone = new ArrayList<>();
                                    huanzhePhone.add(data.get(0));//要改的
                                    seachFields.put("huanzhePhone",huanzhePhone);
                                }
                                //患者身份证号
                                if(seachFields.containsKey("huanzheIdNumber")){
                                    List<String> huanzheIdNumber = seachFields.get("huanzheIdNumber");
                                    huanzheIdNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> huanzheIdNumber = new ArrayList<>();
                                    huanzheIdNumber.add(data.get(0));//要改的
                                    seachFields.put("huanzheIdNumber",huanzheIdNumber);
                                }
                        }

                        //查询是否重复
                         //账户
                        List<HuanzheEntity> huanzheEntities_username = huanzheService.selectList(new EntityWrapper<HuanzheEntity>().in("username", seachFields.get("username")));
                        if(huanzheEntities_username.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(HuanzheEntity s:huanzheEntities_username){
                                repeatFields.add(s.getUsername());
                            }
                            return R.error(511,"数据库的该表中的 [账户] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //患者手机号
                        List<HuanzheEntity> huanzheEntities_huanzhePhone = huanzheService.selectList(new EntityWrapper<HuanzheEntity>().in("huanzhe_phone", seachFields.get("huanzhePhone")));
                        if(huanzheEntities_huanzhePhone.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(HuanzheEntity s:huanzheEntities_huanzhePhone){
                                repeatFields.add(s.getHuanzhePhone());
                            }
                            return R.error(511,"数据库的该表中的 [患者手机号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //患者身份证号
                        List<HuanzheEntity> huanzheEntities_huanzheIdNumber = huanzheService.selectList(new EntityWrapper<HuanzheEntity>().in("huanzhe_id_number", seachFields.get("huanzheIdNumber")));
                        if(huanzheEntities_huanzheIdNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(HuanzheEntity s:huanzheEntities_huanzheIdNumber){
                                repeatFields.add(s.getHuanzheIdNumber());
                            }
                            return R.error(511,"数据库的该表中的 [患者身份证号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        huanzheService.insertBatch(huanzheList);
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
    * 登录
    */
    @IgnoreAuth
    @RequestMapping(value = "/login")
    public R login(String username, String password, String captcha, HttpServletRequest request) {
        HuanzheEntity huanzhe = huanzheService.selectOne(new EntityWrapper<HuanzheEntity>().eq("username", username));
        if(huanzhe==null || !huanzhe.getPassword().equals(password))
            return R.error("账号或密码不正确");
        //  // 获取监听器中的字典表
        // ServletContext servletContext = ContextLoader.getCurrentWebApplicationContext().getServletContext();
        // Map<String, Map<Integer, String>> dictionaryMap= (Map<String, Map<Integer, String>>) servletContext.getAttribute("dictionaryMap");
        // Map<Integer, String> role_types = dictionaryMap.get("role_types");
        // role_types.get(.getRoleTypes());
        String token = tokenService.generateToken(huanzhe.getId(),username, "huanzhe", "患者");
        R r = R.ok();
        r.put("token", token);
        r.put("role","患者");
        r.put("username",huanzhe.getHuanzheName());
        r.put("tableName","huanzhe");
        r.put("userId",huanzhe.getId());
        return r;
    }

    /**
    * 注册
    */
    @IgnoreAuth
    @PostMapping(value = "/register")
    public R register(@RequestBody HuanzheEntity huanzhe){
//    	ValidatorUtils.validateEntity(user);
        Wrapper<HuanzheEntity> queryWrapper = new EntityWrapper<HuanzheEntity>()
            .eq("username", huanzhe.getUsername())
            .or()
            .eq("huanzhe_phone", huanzhe.getHuanzhePhone())
            .or()
            .eq("huanzhe_id_number", huanzhe.getHuanzheIdNumber())
            ;
        HuanzheEntity huanzheEntity = huanzheService.selectOne(queryWrapper);
        if(huanzheEntity != null)
            return R.error("账户或者患者手机号或者患者身份证号已经被使用");
        huanzhe.setNewMoney(0.0);
        huanzhe.setCreateTime(new Date());
        huanzheService.insert(huanzhe);
        return R.ok();
    }

    /**
     * 重置密码
     */
    @GetMapping(value = "/resetPassword")
    public R resetPassword(Integer  id){
        HuanzheEntity huanzhe = new HuanzheEntity();
        huanzhe.setPassword("123456");
        huanzhe.setId(id);
        huanzheService.updateById(huanzhe);
        return R.ok();
    }


    /**
     * 忘记密码
     */
    @IgnoreAuth
    @RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request) {
        HuanzheEntity huanzhe = huanzheService.selectOne(new EntityWrapper<HuanzheEntity>().eq("username", username));
        if(huanzhe!=null){
            huanzhe.setPassword("123456");
            boolean b = huanzheService.updateById(huanzhe);
            if(!b){
               return R.error();
            }
        }else{
           return R.error("账号不存在");
        }
        return R.ok();
    }


    /**
    * 获取用户的session用户信息
    */
    @RequestMapping("/session")
    public R getCurrHuanzhe(HttpServletRequest request){
        Integer id = (Integer)request.getSession().getAttribute("userId");
        HuanzheEntity huanzhe = huanzheService.selectById(id);
        if(huanzhe !=null){
            //entity转view
            HuanzheView view = new HuanzheView();
            BeanUtils.copyProperties( huanzhe , view );//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }
    }


    /**
    * 退出
    */
    @GetMapping(value = "logout")
    public R logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return R.ok("退出成功");
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
        PageUtils page = huanzheService.queryPage(params);

        //字典表数据转换
        List<HuanzheView> list =(List<HuanzheView>)page.getList();
        for(HuanzheView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        HuanzheEntity huanzhe = huanzheService.selectById(id);
            if(huanzhe !=null){


                //entity转view
                HuanzheView view = new HuanzheView();
                BeanUtils.copyProperties( huanzhe , view );//把实体数据重构到view中

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
    public R add(@RequestBody HuanzheEntity huanzhe, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,huanzhe:{}",this.getClass().getName(),huanzhe.toString());
        Wrapper<HuanzheEntity> queryWrapper = new EntityWrapper<HuanzheEntity>()
            .eq("username", huanzhe.getUsername())
            .or()
            .eq("huanzhe_phone", huanzhe.getHuanzhePhone())
            .or()
            .eq("huanzhe_id_number", huanzhe.getHuanzheIdNumber())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        HuanzheEntity huanzheEntity = huanzheService.selectOne(queryWrapper);
        if(huanzheEntity==null){
            huanzhe.setCreateTime(new Date());
        huanzhe.setPassword("123456");
        huanzheService.insert(huanzhe);
            return R.ok();
        }else {
            return R.error(511,"账户或者患者手机号或者患者身份证号已经被使用");
        }
    }


}
