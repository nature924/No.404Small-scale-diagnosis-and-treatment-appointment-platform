<template>
  <div>
    <el-form
      class="detail-form-content"
      ref="ruleForm"
      :model="ruleForm"
      label-width="80px"
    >
     <el-row>
                    <el-col :span="12">
           <el-form-item v-if="flag=='huanzhe'"  label='患者姓名' prop="huanzheName">
               <el-input v-model="ruleForm.huanzheName"  placeholder='患者姓名' clearable></el-input>
           </el-form-item>
         </el-col>

         <el-col :span="12">
           <el-form-item v-if="flag=='huanzhe'"  label='患者手机号' prop="huanzhePhone">
               <el-input v-model="ruleForm.huanzhePhone"  placeholder='患者手机号' clearable></el-input>
           </el-form-item>
         </el-col>

         <el-col :span="12">
           <el-form-item v-if="flag=='huanzhe'"  label='患者身份证号' prop="huanzheIdNumber">
               <el-input v-model="ruleForm.huanzheIdNumber"  placeholder='患者身份证号' clearable></el-input>
           </el-form-item>
         </el-col>

         <el-col :span="12">
             <el-form-item v-if="flag=='huanzhe'" label='患者头像' prop="huanzhePhoto">
                 <file-upload
                         tip="点击上传照片"
                         action="file/upload"
                         :limit="3"
                         :multiple="true"
                         :fileUrls="ruleForm.huanzhePhoto?ruleForm.huanzhePhoto:''"
                         @change="huanzhePhotoUploadChange"
                 ></file-upload>
             </el-form-item>
         </el-col>
         <el-col :span="12">
           <el-form-item v-if="flag=='huanzhe'"  label='电子邮箱' prop="huanzheEmail">
               <el-input v-model="ruleForm.huanzheEmail"  placeholder='电子邮箱' clearable></el-input>
           </el-form-item>
         </el-col>

         <el-col :span="12">
             <el-form-item v-if="flag=='huanzhe'"  label='余额' prop="newMoney">
                 <el-input v-model="ruleForm.newMoney"  placeholder='余额' disabled style="width: 100px"></el-input>
                 <!--<a id="btn-recharge" @click="chongzhi" href="javascript:void(0)">点我充值</a>-->
             </el-form-item>
         </el-col>
         <el-col :span="12">
           <el-form-item v-if="flag=='yisheng'"  label='医生工号' prop="yishengUuidNumber">
               <el-input v-model="ruleForm.yishengUuidNumber"  placeholder='医生工号' clearable></el-input>
           </el-form-item>
         </el-col>

         <el-col :span="12">
           <el-form-item v-if="flag=='yisheng'"  label='医生姓名' prop="yishengName">
               <el-input v-model="ruleForm.yishengName"  placeholder='医生姓名' clearable></el-input>
           </el-form-item>
         </el-col>

         <el-col :span="12">
           <el-form-item v-if="flag=='yisheng'"  label='医生手机号' prop="yishengPhone">
               <el-input v-model="ruleForm.yishengPhone"  placeholder='医生手机号' clearable></el-input>
           </el-form-item>
         </el-col>

         <el-col :span="12">
           <el-form-item v-if="flag=='yisheng'"  label='医生身份证号' prop="yishengIdNumber">
               <el-input v-model="ruleForm.yishengIdNumber"  placeholder='医生身份证号' clearable></el-input>
           </el-form-item>
         </el-col>

         <el-col :span="12">
             <el-form-item v-if="flag=='yisheng'" label='医生头像' prop="yishengPhoto">
                 <file-upload
                         tip="点击上传照片"
                         action="file/upload"
                         :limit="3"
                         :multiple="true"
                         :fileUrls="ruleForm.yishengPhoto?ruleForm.yishengPhoto:''"
                         @change="yishengPhotoUploadChange"
                 ></file-upload>
             </el-form-item>
         </el-col>
         <el-col :span="12">
           <el-form-item v-if="flag=='yisheng'"  label='医生擅长' prop="yishengShanchang">
               <el-input v-model="ruleForm.yishengShanchang"  placeholder='医生擅长' clearable></el-input>
           </el-form-item>
         </el-col>

         <el-col :span="12">
             <el-form-item v-if="flag=='yisheng'"  label='科室' prop="keshiTypes">
                 <el-select v-model="ruleForm.keshiTypes"  placeholder='请选择科室'>
                     <el-option
                             v-for="(item,index) in keshiTypesOptions"
                             v-bind:key="item.codeIndex"
                             :label="item.indexName"
                             :value="item.codeIndex">
                     </el-option>
                 </el-select>
             </el-form-item>
         </el-col>
         <el-col :span="12">
             <el-form-item v-if="flag=='yisheng'"  label='职位' prop="zhiweiTypes">
                 <el-select v-model="ruleForm.zhiweiTypes"  placeholder='请选择职位'>
                     <el-option
                             v-for="(item,index) in zhiweiTypesOptions"
                             v-bind:key="item.codeIndex"
                             :label="item.indexName"
                             :value="item.codeIndex">
                     </el-option>
                 </el-select>
             </el-form-item>
         </el-col>
         <el-col :span="12">
           <el-form-item v-if="flag=='yisheng'"  label='挂号费' prop="guahaofei">
               <el-input v-model="ruleForm.guahaofei"  placeholder='挂号费' clearable></el-input>
           </el-form-item>
         </el-col>

         <el-col :span="12">
           <el-form-item v-if="flag=='yisheng'"  label='赞' prop="zanNumber">
               <el-input v-model="ruleForm.zanNumber" disabled  placeholder='赞' clearable></el-input>
           </el-form-item>
         </el-col>

         <el-col :span="12">
           <el-form-item v-if="flag=='yisheng'"  label='踩' prop="caiNumber">
               <el-input v-model="ruleForm.caiNumber" disabled  placeholder='踩' clearable></el-input>
           </el-form-item>
         </el-col>

         <el-col :span="24">
             <el-form-item v-if="flag=='yisheng'"  label="医生详细介绍" prop="yishengContent">
                 <editor
                         style="min-width: 200px; max-width: 600px;"
                         v-model="ruleForm.yishengContent"
                         class="editor"
                         action="file/upload">
                 </editor>
             </el-form-item>
         </el-col>
         <el-form-item v-if="flag=='users'" label="用户名" prop="username">
             <el-input v-model="ruleForm.username"
                       placeholder="用户名"></el-input>
         </el-form-item>
         <el-col :span="12">
             <el-form-item v-if="flag!='users'"  label="性别" prop="sexTypes">
                 <el-select v-model="ruleForm.sexTypes" placeholder="请选择性别">
                     <el-option
                             v-for="(item,index) in sexTypesOptions"
                             v-bind:key="item.codeIndex"
                             :label="item.indexName"
                             :value="item.codeIndex">
                     </el-option>
                 </el-select>
             </el-form-item>
         </el-col>
         <el-col :span="24">
             <el-form-item>
                 <el-button type="primary" @click="onUpdateHandler">修 改</el-button>
             </el-form-item>
         </el-col>
     </el-row>
    </el-form>
  </div>
</template>
<script>
// 数字，邮件，手机，url，身份证校验
import { isNumber,isIntNumer,isEmail,isMobile,isPhone,isURL,checkIdCard } from "@/utils/validate";

export default {
  data() {
    return {
        ruleForm: {},
        flag: '',
        usersFlag: false,
        // sexTypesOptions : [],
// 注册表 患者
    // 注册的类型字段 患者
        // 性别
        sexTypesOptions : [],
// 注册表 医生
    // 注册的类型字段 医生
        // 性别
        sexTypesOptions : [],
        // 科室
        keshiTypesOptions : [],
        // 职位
        zhiweiTypesOptions : [],
    };
  },
  mounted() {
    //获取当前登录用户的信息
    var table = this.$storage.get("sessionTable");
    this.sessionTable = this.$storage.get("sessionTable");
    this.role = this.$storage.get("role");
    if (this.role != "管理员"){
    }

    this.flag = table;
    this.$http({
      url: `${this.$storage.get("sessionTable")}/session`,
      method: "get"
    }).then(({ data }) => {
      if (data && data.code === 0) {
        this.ruleForm = data.data;
// 注册表 患者
// 注册表 医生
      } else {
        this.$message.error(data.msg);
      }
    });

// 注册表 患者 的级联表
// 注册表 医生 的级联表

      this.$http({
          url: `dictionary/page?page=1&limit=100&sort=&order=&dicCode=sex_types`,
          method: "get"
      }).then(({ data }) => {
          if (data && data.code === 0) {
              this.sexTypesOptions = data.data.list;
          } else {
              this.$message.error(data.msg);
          }
      });
   this.$http({
       url: `dictionary/page?page=1&limit=100&sort=&order=&dicCode=keshi_types`,
       method: "get"
   }).then(({ data }) => {
       if (data && data.code === 0) {
          this.keshiTypesOptions = data.data.list;
      } else {
          this.$message.error(data.msg);
      }
    });
   this.$http({
       url: `dictionary/page?page=1&limit=100&sort=&order=&dicCode=zhiwei_types`,
       method: "get"
   }).then(({ data }) => {
       if (data && data.code === 0) {
          this.zhiweiTypesOptions = data.data.list;
      } else {
          this.$message.error(data.msg);
      }
    });
  },
  methods: {
    chongzhi() {
      this.$router.replace({ path: "/pay" });
    },
    huanzhePhotoUploadChange(fileUrls) {
        this.ruleForm.huanzhePhoto = fileUrls;
    },
    yishengPhotoUploadChange(fileUrls) {
        this.ruleForm.yishengPhoto = fileUrls;
    },


    onUpdateHandler() {
                         if((!this.ruleForm.huanzheName)&& 'huanzhe'==this.flag){
                             this.$message.error('患者姓名不能为空');
                             return
                         }

                             if( 'huanzhe' ==this.flag && this.ruleForm.huanzhePhone&&(!isMobile(this.ruleForm.huanzhePhone))){
                                 this.$message.error(`手机应输入手机格式`);
                                 return
                             }
                         if((!this.ruleForm.huanzheIdNumber)&& 'huanzhe'==this.flag){
                             this.$message.error('患者身份证号不能为空');
                             return
                         }

                         if((!this.ruleForm.huanzhePhoto)&& 'huanzhe'==this.flag){
                             this.$message.error('患者头像不能为空');
                             return
                         }

                             if( 'huanzhe' ==this.flag && this.ruleForm.huanzheEmail&&(!isEmail(this.ruleForm.huanzheEmail))){
                                 this.$message.error(`邮箱应输入邮箱格式`);
                                 return
                             }
                         if((!this.ruleForm.yishengUuidNumber)&& 'yisheng'==this.flag){
                             this.$message.error('医生工号不能为空');
                             return
                         }

                         if((!this.ruleForm.yishengName)&& 'yisheng'==this.flag){
                             this.$message.error('医生姓名不能为空');
                             return
                         }

                             if( 'yisheng' ==this.flag && this.ruleForm.yishengPhone&&(!isMobile(this.ruleForm.yishengPhone))){
                                 this.$message.error(`手机应输入手机格式`);
                                 return
                             }
                         if((!this.ruleForm.yishengIdNumber)&& 'yisheng'==this.flag){
                             this.$message.error('医生身份证号不能为空');
                             return
                         }

                         if((!this.ruleForm.yishengPhoto)&& 'yisheng'==this.flag){
                             this.$message.error('医生头像不能为空');
                             return
                         }

                         if((!this.ruleForm.yishengShanchang)&& 'yisheng'==this.flag){
                             this.$message.error('医生擅长不能为空');
                             return
                         }

                         if((!this.ruleForm.keshiTypes)&& 'yisheng'==this.flag){
                             this.$message.error('科室不能为空');
                             return
                         }

                         if((!this.ruleForm.zhiweiTypes)&& 'yisheng'==this.flag){
                             this.$message.error('职位不能为空');
                             return
                         }

                         if((!this.ruleForm.guahaofei)&& 'yisheng'==this.flag){
                             this.$message.error('挂号费不能为空');
                             return
                         }

                         if((!this.ruleForm.zanNumber)&& 'yisheng'==this.flag){
                             this.$message.error('赞不能为空');
                             return
                         }

                         if((!this.ruleForm.caiNumber)&& 'yisheng'==this.flag){
                             this.$message.error('踩不能为空');
                             return
                         }

                         if((!this.ruleForm.yishengContent)&& 'yisheng'==this.flag){
                             this.$message.error('医生详细介绍不能为空');
                             return
                         }

        if((!this.ruleForm.sexTypes) && this.flag!='users'){
            this.$message.error('性别不能为空');
            return
        }
      if('users'==this.flag && this.ruleForm.username.trim().length<1) {
        this.$message.error(`用户名不能为空`);
        return
      }
      this.$http({
        url: `${this.$storage.get("sessionTable")}/update`,
        method: "post",
        data: this.ruleForm
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.$message({
            message: "修改信息成功",
            type: "success",
            duration: 1500,
            onClose: () => {
            }
          });
        } else {
          this.$message.error(data.msg);
        }
      });
    }
  }
};
</script>
<style lang="scss" scoped>
</style>
