<template>
    <div class="addEdit-block">
        <el-form
                class="detail-form-content"
                ref="ruleForm"
                :model="ruleForm"
                :rules="rules"
                label-width="auto"
                :style="{backgroundColor:addEditForm.addEditBoxColor}">
            <el-row>
                <el-col :span="12"  v-if="sessionTable !='huanzhe'">
                    <el-form-item class="select" v-if="type!='info'"  label="患者" prop="huanzheId">
                        <el-select v-model="ruleForm.huanzheId" :disabled="ro.huanzheId" filterable placeholder="请选择患者" @change="huanzheChange">
                            <el-option
                                    v-for="(item,index) in huanzheOptions"
                                    v-bind:key="item.id"
                                    :label="item.huanzheName"
                                    :value="item.id">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>

                <el-col :span="12"  v-if="sessionTable !='huanzhe' ">
                    <el-form-item class="input" v-if="type!='info'"  label="患者姓名" prop="huanzheName">
                        <el-input v-model="huanzheForm.huanzheName"
                                  placeholder="患者姓名" clearable readonly></el-input>
                    </el-form-item>
                    <div v-else-if="type=='info'">
                        <el-form-item class="input" label="患者姓名" prop="huanzheName">
                            <el-input v-model="ruleForm.huanzheName"
                                      placeholder="患者姓名" readonly></el-input>
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="12"  v-if="sessionTable !='huanzhe' ">
                    <el-form-item class="input" v-if="type!='info'"  label="患者手机号" prop="huanzhePhone">
                        <el-input v-model="huanzheForm.huanzhePhone"
                                  placeholder="患者手机号" clearable readonly></el-input>
                    </el-form-item>
                    <div v-else-if="type=='info'">
                        <el-form-item class="input" label="患者手机号" prop="huanzhePhone">
                            <el-input v-model="ruleForm.huanzhePhone"
                                      placeholder="患者手机号" readonly></el-input>
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="12"  v-if="sessionTable !='huanzhe' ">
                    <el-form-item class="input" v-if="type!='info'"  label="患者身份证号" prop="huanzheIdNumber">
                        <el-input v-model="huanzheForm.huanzheIdNumber"
                                  placeholder="患者身份证号" clearable readonly></el-input>
                    </el-form-item>
                    <div v-else-if="type=='info'">
                        <el-form-item class="input" label="患者身份证号" prop="huanzheIdNumber">
                            <el-input v-model="ruleForm.huanzheIdNumber"
                                      placeholder="患者身份证号" readonly></el-input>
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="12" v-if="sessionTable !='huanzhe' ">
                    <el-form-item class="upload" v-if="type!='info' && !ro.huanzhePhoto" label="患者头像" prop="huanzhePhoto">
                        <img style="margin-right:20px;" v-bind:key="index" v-for="(item,index) in (huanzheForm.huanzhePhoto || '').split(',')" :src="item" width="100" height="100">
                    </el-form-item>
                    <div v-else-if="type=='info'">
                        <el-form-item v-if="ruleForm.huanzhePhoto" label="患者头像" prop="huanzhePhoto">
                            <img style="margin-right:20px;" v-bind:key="index" v-for="(item,index) in (ruleForm.huanzhePhoto || '').split(',')" :src="item" width="100" height="100">
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="12"  v-if="sessionTable !='huanzhe' ">
                    <el-form-item class="input" v-if="type!='info'"  label="电子邮箱" prop="huanzheEmail">
                        <el-input v-model="huanzheForm.huanzheEmail"
                                  placeholder="电子邮箱" clearable readonly></el-input>
                    </el-form-item>
                    <div v-else-if="type=='info'">
                        <el-form-item class="input" label="电子邮箱" prop="huanzheEmail">
                            <el-input v-model="ruleForm.huanzheEmail"
                                      placeholder="电子邮箱" readonly></el-input>
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="12"  v-if="sessionTable !='yisheng'">
                    <el-form-item class="select" v-if="type!='info'"  label="医生" prop="yishengId">
                        <el-select v-model="ruleForm.yishengId" :disabled="ro.yishengId" filterable placeholder="请选择医生" @change="yishengChange">
                            <el-option
                                    v-for="(item,index) in yishengOptions"
                                    v-bind:key="item.id"
                                    :label="item.yishengName"
                                    :value="item.id">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>

                <el-col :span="12"  v-if="sessionTable !='yisheng' ">
                    <el-form-item class="input" v-if="type!='info'"  label="医生工号" prop="yishengUuidNumber">
                        <el-input v-model="yishengForm.yishengUuidNumber"
                                  placeholder="医生工号" clearable readonly></el-input>
                    </el-form-item>
                    <div v-else-if="type=='info'">
                        <el-form-item class="input" label="医生工号" prop="yishengUuidNumber">
                            <el-input v-model="ruleForm.yishengUuidNumber"
                                      placeholder="医生工号" readonly></el-input>
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="12"  v-if="sessionTable !='yisheng' ">
                    <el-form-item class="input" v-if="type!='info'"  label="医生姓名" prop="yishengName">
                        <el-input v-model="yishengForm.yishengName"
                                  placeholder="医生姓名" clearable readonly></el-input>
                    </el-form-item>
                    <div v-else-if="type=='info'">
                        <el-form-item class="input" label="医生姓名" prop="yishengName">
                            <el-input v-model="ruleForm.yishengName"
                                      placeholder="医生姓名" readonly></el-input>
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="12"  v-if="sessionTable !='yisheng' ">
                    <el-form-item class="input" v-if="type!='info'"  label="医生手机号" prop="yishengPhone">
                        <el-input v-model="yishengForm.yishengPhone"
                                  placeholder="医生手机号" clearable readonly></el-input>
                    </el-form-item>
                    <div v-else-if="type=='info'">
                        <el-form-item class="input" label="医生手机号" prop="yishengPhone">
                            <el-input v-model="ruleForm.yishengPhone"
                                      placeholder="医生手机号" readonly></el-input>
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="12"  v-if="sessionTable !='yisheng' ">
                    <el-form-item class="input" v-if="type!='info'"  label="医生身份证号" prop="yishengIdNumber">
                        <el-input v-model="yishengForm.yishengIdNumber"
                                  placeholder="医生身份证号" clearable readonly></el-input>
                    </el-form-item>
                    <div v-else-if="type=='info'">
                        <el-form-item class="input" label="医生身份证号" prop="yishengIdNumber">
                            <el-input v-model="ruleForm.yishengIdNumber"
                                      placeholder="医生身份证号" readonly></el-input>
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="12" v-if="sessionTable !='yisheng' ">
                    <el-form-item class="upload" v-if="type!='info' && !ro.yishengPhoto" label="医生头像" prop="yishengPhoto">
                        <img style="margin-right:20px;" v-bind:key="index" v-for="(item,index) in (yishengForm.yishengPhoto || '').split(',')" :src="item" width="100" height="100">
                    </el-form-item>
                    <div v-else-if="type=='info'">
                        <el-form-item v-if="ruleForm.yishengPhoto" label="医生头像" prop="yishengPhoto">
                            <img style="margin-right:20px;" v-bind:key="index" v-for="(item,index) in (ruleForm.yishengPhoto || '').split(',')" :src="item" width="100" height="100">
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="12"  v-if="sessionTable !='yisheng' ">
                    <el-form-item class="input" v-if="type!='info'"  label="医生擅长" prop="yishengShanchang">
                        <el-input v-model="yishengForm.yishengShanchang"
                                  placeholder="医生擅长" clearable readonly></el-input>
                    </el-form-item>
                    <div v-else-if="type=='info'">
                        <el-form-item class="input" label="医生擅长" prop="yishengShanchang">
                            <el-input v-model="ruleForm.yishengShanchang"
                                      placeholder="医生擅长" readonly></el-input>
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="12"  v-if="sessionTable !='yisheng' ">
                    <el-form-item class="input" v-if="type!='info'"  label="科室" prop="keshiValue">
                        <el-input v-model="yishengForm.keshiValue"
                                  placeholder="科室" clearable readonly></el-input>
                    </el-form-item>
                    <div v-else-if="type=='info'">
                        <el-form-item class="input" label="科室" prop="keshiValue">
                            <el-input v-model="ruleForm.keshiValue"
                                      placeholder="科室" readonly></el-input>
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="12"  v-if="sessionTable !='yisheng' ">
                    <el-form-item class="input" v-if="type!='info'"  label="职位" prop="zhiweiValue">
                        <el-input v-model="yishengForm.zhiweiValue"
                                  placeholder="职位" clearable readonly></el-input>
                    </el-form-item>
                    <div v-else-if="type=='info'">
                        <el-form-item class="input" label="职位" prop="zhiweiValue">
                            <el-input v-model="ruleForm.zhiweiValue"
                                      placeholder="职位" readonly></el-input>
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="12"  v-if="sessionTable !='yisheng' ">
                    <el-form-item class="input" v-if="type!='info'"  label="挂号费" prop="guahaofei">
                        <el-input v-model="yishengForm.guahaofei"
                                  placeholder="挂号费" clearable readonly></el-input>
                    </el-form-item>
                    <div v-else-if="type=='info'">
                        <el-form-item class="input" label="挂号费" prop="guahaofei">
                            <el-input v-model="ruleForm.guahaofei"
                                      placeholder="挂号费" readonly></el-input>
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="12"  v-if="sessionTable !='yisheng' ">
                    <el-form-item class="input" v-if="type!='info'"  label="赞" prop="zanNumber">
                        <el-input v-model="yishengForm.zanNumber"
                                  placeholder="赞" clearable readonly></el-input>
                    </el-form-item>
                    <div v-else-if="type=='info'">
                        <el-form-item class="input" label="赞" prop="zanNumber">
                            <el-input v-model="ruleForm.zanNumber"
                                      placeholder="赞" readonly></el-input>
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="12"  v-if="sessionTable !='yisheng' ">
                    <el-form-item class="input" v-if="type!='info'"  label="踩" prop="caiNumber">
                        <el-input v-model="yishengForm.caiNumber"
                                  placeholder="踩" clearable readonly></el-input>
                    </el-form-item>
                    <div v-else-if="type=='info'">
                        <el-form-item class="input" label="踩" prop="caiNumber">
                            <el-input v-model="ruleForm.caiNumber"
                                      placeholder="踩" readonly></el-input>
                        </el-form-item>
                    </div>
                </el-col>
                <input id="updateId" name="id" type="hidden">
            <input id="huanzheId" name="huanzheId" type="hidden">
            <input id="yishengId" name="yishengId" type="hidden">
               <el-col :span="12">
                   <el-form-item class="input" v-if="type!='info'"  label="病例编号" prop="bingliUuidNumber">
                       <el-input v-model="ruleForm.bingliUuidNumber"
                                 placeholder="病例编号" clearable  :readonly="ro.bingliUuidNumber"></el-input>
                   </el-form-item>
                   <div v-else-if="type=='info'">
                       <el-form-item class="input" label="病例编号" prop="bingliUuidNumber">
                           <el-input v-model="ruleForm.bingliUuidNumber"
                                     placeholder="病例编号" readonly></el-input>
                       </el-form-item>
                   </div>
               </el-col>
               <el-col :span="12">
                   <el-form-item class="input" v-if="type!='info'"  label="病例名称" prop="bingliName">
                       <el-input v-model="ruleForm.bingliName"
                                 placeholder="病例名称" clearable  :readonly="ro.bingliName"></el-input>
                   </el-form-item>
                   <div v-else-if="type=='info'">
                       <el-form-item class="input" label="病例名称" prop="bingliName">
                           <el-input v-model="ruleForm.bingliName"
                                     placeholder="病例名称" readonly></el-input>
                       </el-form-item>
                   </div>
               </el-col>
               <el-col :span="12">
                   <el-form-item class="input" v-if="type!='info'"  label="现住址" prop="bingliAddress">
                       <el-input v-model="ruleForm.bingliAddress"
                                 placeholder="现住址" clearable  :readonly="ro.bingliAddress"></el-input>
                   </el-form-item>
                   <div v-else-if="type=='info'">
                       <el-form-item class="input" label="现住址" prop="bingliAddress">
                           <el-input v-model="ruleForm.bingliAddress"
                                     placeholder="现住址" readonly></el-input>
                       </el-form-item>
                   </div>
               </el-col>
               <el-col :span="12">
                   <el-form-item class="input" v-if="type!='info'"  label="籍贯" prop="bingliJiguan">
                       <el-input v-model="ruleForm.bingliJiguan"
                                 placeholder="籍贯" clearable  :readonly="ro.bingliJiguan"></el-input>
                   </el-form-item>
                   <div v-else-if="type=='info'">
                       <el-form-item class="input" label="籍贯" prop="bingliJiguan">
                           <el-input v-model="ruleForm.bingliJiguan"
                                     placeholder="籍贯" readonly></el-input>
                       </el-form-item>
                   </div>
               </el-col>
               <el-col :span="12">
                   <el-form-item class="input" v-if="type!='info'"  label="婚姻状况" prop="bingliHunyin">
                       <el-input v-model="ruleForm.bingliHunyin"
                                 placeholder="婚姻状况" clearable  :readonly="ro.bingliHunyin"></el-input>
                   </el-form-item>
                   <div v-else-if="type=='info'">
                       <el-form-item class="input" label="婚姻状况" prop="bingliHunyin">
                           <el-input v-model="ruleForm.bingliHunyin"
                                     placeholder="婚姻状况" readonly></el-input>
                       </el-form-item>
                   </div>
               </el-col>
                <el-col :span="24">
                    <el-form-item v-if="type!='info'"  label="主诉" prop="zhusuContent">
                        <editor style="min-width: 200px; max-width: 600px;"
                                v-model="ruleForm.zhusuContent"
                                class="editor"
                                action="file/upload">
                        </editor>
                    </el-form-item>
                    <div v-else-if="type=='info'">
                        <el-form-item v-if="ruleForm.zhusuContent" label="主诉" prop="zhusuContent">
                            <span v-html="ruleForm.zhusuContent"></span>
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="24">
                    <el-form-item v-if="type!='info'"  label="既往史" prop="jiwangshiContent">
                        <editor style="min-width: 200px; max-width: 600px;"
                                v-model="ruleForm.jiwangshiContent"
                                class="editor"
                                action="file/upload">
                        </editor>
                    </el-form-item>
                    <div v-else-if="type=='info'">
                        <el-form-item v-if="ruleForm.jiwangshiContent" label="既往史" prop="jiwangshiContent">
                            <span v-html="ruleForm.jiwangshiContent"></span>
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="24">
                    <el-form-item v-if="type!='info'"  label="个人史" prop="gerenContent">
                        <editor style="min-width: 200px; max-width: 600px;"
                                v-model="ruleForm.gerenContent"
                                class="editor"
                                action="file/upload">
                        </editor>
                    </el-form-item>
                    <div v-else-if="type=='info'">
                        <el-form-item v-if="ruleForm.gerenContent" label="个人史" prop="gerenContent">
                            <span v-html="ruleForm.gerenContent"></span>
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="24">
                    <el-form-item v-if="type!='info'"  label="家族遗传史" prop="yichuanContent">
                        <editor style="min-width: 200px; max-width: 600px;"
                                v-model="ruleForm.yichuanContent"
                                class="editor"
                                action="file/upload">
                        </editor>
                    </el-form-item>
                    <div v-else-if="type=='info'">
                        <el-form-item v-if="ruleForm.yichuanContent" label="家族遗传史" prop="yichuanContent">
                            <span v-html="ruleForm.yichuanContent"></span>
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="24">
                    <el-form-item v-if="type!='info'"  label="医生诊断" prop="zhenduanContent">
                        <editor style="min-width: 200px; max-width: 600px;"
                                v-model="ruleForm.zhenduanContent"
                                class="editor"
                                action="file/upload">
                        </editor>
                    </el-form-item>
                    <div v-else-if="type=='info'">
                        <el-form-item v-if="ruleForm.zhenduanContent" label="医生诊断" prop="zhenduanContent">
                            <span v-html="ruleForm.zhenduanContent"></span>
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="24">
                    <el-form-item v-if="type!='info'"  label="开具药方" prop="yaofangContent">
                        <editor style="min-width: 200px; max-width: 600px;"
                                v-model="ruleForm.yaofangContent"
                                class="editor"
                                action="file/upload">
                        </editor>
                    </el-form-item>
                    <div v-else-if="type=='info'">
                        <el-form-item v-if="ruleForm.yaofangContent" label="开具药方" prop="yaofangContent">
                            <span v-html="ruleForm.yaofangContent"></span>
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="12">
                    <el-form-item v-if="type!='info'"  class="input" label="看病时间" prop="kanbingTime">
                        <el-date-picker
                                value-format="yyyy-MM-dd HH:mm:ss"
                                v-model="ruleForm.kanbingTime"
                                type="datetime"
                                placeholder="看病时间"
                                :disabled="ro.kanbingTime">
                        </el-date-picker>
                    </el-form-item>
                    <div v-else-if="type=='info'">
                        <el-form-item v-if="ruleForm.kanbingTime" label="看病时间" prop="kanbingTime">
                            <span v-html="ruleForm.kanbingTime"></span>
                        </el-form-item>
                    </div>
                </el-col>

               <el-col :span="12">
                   <el-form-item class="input" v-if="type!='info'"  label="缴费金额" prop="jiaofeiJine">
                       <el-input v-model="ruleForm.jiaofeiJine"
                                 placeholder="缴费金额" clearable  :readonly="ro.jiaofeiJine"></el-input>
                   </el-form-item>
                   <div v-else-if="type=='info'">
                       <el-form-item class="input" label="缴费金额" prop="jiaofeiJine">
                           <el-input v-model="ruleForm.jiaofeiJine"
                                     placeholder="缴费金额" readonly></el-input>
                       </el-form-item>
                   </div>
               </el-col>
                <el-col :span="12" v-if="!(sessionTable=='yisheng' && type!='info')">
                    <el-form-item class="select" v-if="type!='info'"  label="是否缴费" prop="jiaofeiTypes">
                        <el-select v-model="ruleForm.jiaofeiTypes" :disabled="ro.jiaofeiTypes" placeholder="请选择是否缴费">
                            <el-option
                                v-for="(item,index) in jiaofeiTypesOptions"
                                v-bind:key="item.codeIndex"
                                :label="item.indexName"
                                :value="item.codeIndex">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <div v-else-if="type=='info'">
                        <el-form-item class="input" label="是否缴费" prop="jiaofeiValue">
                        <el-input v-model="ruleForm.jiaofeiValue"
                            placeholder="是否缴费" readonly></el-input>
                        </el-form-item>
                    </div>
                </el-col>
            </el-row>
            <el-form-item class="btn">
                <el-button v-if="type!='info'" type="primary" class="btn-success" @click="onSubmit">提交</el-button>
                <el-button v-if="type!='info'" class="btn-close" @click="back()">取消</el-button>
                <el-button v-if="type=='info'" class="btn-close" @click="back()">返回</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>
<script>
    import styleJs from "../../../utils/style.js";
    // 数字，邮件，手机，url，身份证校验
    import { isNumber,isIntNumer,isEmail,isPhone, isMobile,isURL,checkIdCard } from "@/utils/validate";
    export default {
        data() {
            return {
                addEditForm:null,
                id: '',
                type: '',
                sessionTable : "",//登录账户所在表名
                role : "",//权限
                userId:"",//当前登录人的id
                huanzheForm: {},
                yishengForm: {},
                ro:{
                    huanzheId: false,
                    yishengId: false,
                    bingliUuidNumber: false,
                    bingliName: false,
                    bingliAddress: false,
                    bingliJiguan: false,
                    bingliHunyin: false,
                    zhusuContent: false,
                    jiwangshiContent: false,
                    gerenContent: false,
                    yichuanContent: false,
                    zhenduanContent: false,
                    yaofangContent: false,
                    kanbingTime: false,
                    jiaofeiJine: false,
                    insertTime: false,
                    jiaofeiTypes: false,
                },
                ruleForm: {
                    huanzheId: '',
                    yishengId: '',
                    bingliUuidNumber: new Date().getTime(),
                    bingliName: '',
                    bingliAddress: '',
                    bingliJiguan: '',
                    bingliHunyin: '',
                    zhusuContent: '',
                    jiwangshiContent: '',
                    gerenContent: '',
                    yichuanContent: '',
                    zhenduanContent: '',
                    yaofangContent: '',
                    kanbingTime: '',
                    jiaofeiJine: '',
                    insertTime: '',
                    jiaofeiTypes: '',
                },
                jiaofeiTypesOptions : [],
                huanzheOptions : [],
                yishengOptions : [],
                rules: {
                   huanzheId: [
                              { required: true, message: '患者不能为空', trigger: 'blur' },
                              {  pattern: /^[0-9]*$/,
                                  message: '只允许输入整数',
                                  trigger: 'blur'
                              }
                          ],
                   yishengId: [
                              { required: true, message: '医生不能为空', trigger: 'blur' },
                              {  pattern: /^[0-9]*$/,
                                  message: '只允许输入整数',
                                  trigger: 'blur'
                              }
                          ],
                   bingliUuidNumber: [
                              { required: true, message: '病例编号不能为空', trigger: 'blur' },
                          ],
                   bingliName: [
                              { required: true, message: '病例名称不能为空', trigger: 'blur' },
                          ],
                   bingliAddress: [
                              { required: true, message: '现住址不能为空', trigger: 'blur' },
                          ],
                   bingliJiguan: [
                              { required: true, message: '籍贯不能为空', trigger: 'blur' },
                          ],
                   bingliHunyin: [
                              { required: true, message: '婚姻状况不能为空', trigger: 'blur' },
                          ],
                   zhusuContent: [
                              { required: true, message: '主诉不能为空', trigger: 'blur' },
                          ],
                   jiwangshiContent: [
                              { required: true, message: '既往史不能为空', trigger: 'blur' },
                          ],
                   gerenContent: [
                              { required: true, message: '个人史不能为空', trigger: 'blur' },
                          ],
                   yichuanContent: [
                              { required: true, message: '家族遗传史不能为空', trigger: 'blur' },
                          ],
                   zhenduanContent: [
                              { required: true, message: '医生诊断不能为空', trigger: 'blur' },
                          ],
                   yaofangContent: [
                              { required: true, message: '开具药方不能为空', trigger: 'blur' },
                          ],
                   kanbingTime: [
                              { required: true, message: '看病时间不能为空', trigger: 'blur' },
                          ],
                   jiaofeiJine: [
                              { required: true, message: '缴费金额不能为空', trigger: 'blur' },
                              {  pattern: /^[0-9]{0,6}(\.[0-9]{1,2})?$/,
                                  message: '只允许输入整数6位,小数2位的数字',
                                  trigger: 'blur'
                              }
                          ],
                   insertTime: [
                              { required: true, message: '录入时间不能为空', trigger: 'blur' },
                          ],
                   jiaofeiTypes: [
                              { required: true, message: '是否缴费不能为空', trigger: 'blur' },
                              {  pattern: /^[0-9]*$/,
                                  message: '只允许输入整数',
                                  trigger: 'blur'
                              }
                          ],
                }
            };
        },
        props: ["parent"],
        computed: {
        },
        created() {
            //获取当前登录用户的信息
            this.sessionTable = this.$storage.get("sessionTable");
            this.role = this.$storage.get("role");
            this.userId = this.$storage.get("userId");


            if (this.role != "管理员"){
            }
            this.addEditForm = styleJs.addStyle();
            this.addEditStyleChange()
            this.addEditUploadStyleChange()
            //获取下拉框信息
                this.$http({
                    url:`dictionary/page?page=1&limit=100&sort=&order=&dicCode=jiaofei_types`,
                    method: "get"
                }).then(({ data }) => {
                    if (data && data.code === 0) {
                        this.jiaofeiTypesOptions = data.data.list;
                    }
                });

         this.$http({
             url: `huanzhe/page?page=1&limit=100`,
             method: "get"
         }).then(({ data }) => {
             if (data && data.code === 0) {
                this.huanzheOptions = data.data.list;
            }
         });
         this.$http({
             url: `yisheng/page?page=1&limit=100`,
             method: "get"
         }).then(({ data }) => {
             if (data && data.code === 0) {
                this.yishengOptions = data.data.list;
            }
         });

        },
        mounted() {
        },
        methods: {
            // 下载
            download(file){
                window.open(`${file}`)
            },
            // 初始化
            init(id,type) {
                if (id) {
                    this.id = id;
                    this.type = type;
                }
                if(this.type=='info'||this.type=='else'){
                    this.info(id);
                }
                // 获取用户信息
                this.$http({
                    url:`${this.$storage.get("sessionTable")}/session`,
                    method: "get"
                }).then(({ data }) => {
                    if (data && data.code === 0) {
                        var json = data.data;
                    } else {
                        this.$message.error(data.msg);
                    }
                });
            },
            huanzheChange(id){
                this.$http({
                    url: `huanzhe/info/`+id,
                    method: "get"
                }).then(({ data }) => {
                    if (data && data.code === 0) {
                        this.huanzheForm = data.data;
                    }
                });
            },
            yishengChange(id){
                this.$http({
                    url: `yisheng/info/`+id,
                    method: "get"
                }).then(({ data }) => {
                    if (data && data.code === 0) {
                        this.yishengForm = data.data;
                    }
                });
            },
            // 多级联动参数
            info(id) {
                let _this =this;
                _this.$http({
                    url: `bingli/info/${id}`,
                    method: 'get'
                }).then(({ data }) => {
                    if (data && data.code === 0) {
                        _this.ruleForm = data.data;
                        _this.huanzheChange(data.data.huanzheId)
                        _this.yishengChange(data.data.yishengId)
                    } else {
                        _this.$message.error(data.msg);
                    }
                });
            },
            // 提交
            onSubmit() {
                this.$refs["ruleForm"].validate(valid => {
                    if (valid) {
                        this.$http({
                            url:`bingli/${!this.ruleForm.id ? "save" : "update"}`,
                            method: "post",
                            data: this.ruleForm
                        }).then(({ data }) => {
                            if (data && data.code === 0) {
                                this.$message({
                                    message: "操作成功",
                                    type: "success",
                                    duration: 1500,
                                    onClose: () => {
                                        this.parent.showFlag = true;
                                        this.parent.addOrUpdateFlag = false;
                                        this.parent.bingliCrossAddOrUpdateFlag = false;
                                        this.parent.search();
                                        this.parent.contentStyleChange();
                                    }
                                });
                            } else {
                                this.$message.error(data.msg);
                            }
                        });
                    }
                });
            },
            // 获取uuid
            getUUID () {
                return new Date().getTime();
            },
            // 返回
            back() {
                this.parent.showFlag = true;
                this.parent.addOrUpdateFlag = false;
                this.parent.bingliCrossAddOrUpdateFlag = false;
                this.parent.contentStyleChange();
            },
            //图片

            addEditStyleChange() {
                this.$nextTick(()=>{
                    // input
                    document.querySelectorAll('.addEdit-block .input .el-input__inner').forEach(el=>{
                        el.style.height = this.addEditForm.inputHeight
                        el.style.color = this.addEditForm.inputFontColor
                        el.style.fontSize = this.addEditForm.inputFontSize
                        el.style.borderWidth = this.addEditForm.inputBorderWidth
                        el.style.borderStyle = this.addEditForm.inputBorderStyle
                        el.style.borderColor = this.addEditForm.inputBorderColor
                        el.style.borderRadius = this.addEditForm.inputBorderRadius
                        el.style.backgroundColor = this.addEditForm.inputBgColor
                    })
                    document.querySelectorAll('.addEdit-block .input .el-form-item__label').forEach(el=>{
                        el.style.lineHeight = this.addEditForm.inputHeight
                        el.style.color = this.addEditForm.inputLableColor
                        el.style.fontSize = this.addEditForm.inputLableFontSize
                    })
                    // select
                    document.querySelectorAll('.addEdit-block .select .el-input__inner').forEach(el=>{
                        el.style.height = this.addEditForm.selectHeight
                        el.style.color = this.addEditForm.selectFontColor
                        el.style.fontSize = this.addEditForm.selectFontSize
                        el.style.borderWidth = this.addEditForm.selectBorderWidth
                        el.style.borderStyle = this.addEditForm.selectBorderStyle
                        el.style.borderColor = this.addEditForm.selectBorderColor
                        el.style.borderRadius = this.addEditForm.selectBorderRadius
                        el.style.backgroundColor = this.addEditForm.selectBgColor
                    })
                    document.querySelectorAll('.addEdit-block .select .el-form-item__label').forEach(el=>{
                        el.style.lineHeight = this.addEditForm.selectHeight
                        el.style.color = this.addEditForm.selectLableColor
                        el.style.fontSize = this.addEditForm.selectLableFontSize
                    })
                    document.querySelectorAll('.addEdit-block .select .el-select__caret').forEach(el=>{
                        el.style.color = this.addEditForm.selectIconFontColor
                        el.style.fontSize = this.addEditForm.selectIconFontSize
                    })
                    // date
                    document.querySelectorAll('.addEdit-block .date .el-input__inner').forEach(el=>{
                        el.style.height = this.addEditForm.dateHeight
                        el.style.color = this.addEditForm.dateFontColor
                        el.style.fontSize = this.addEditForm.dateFontSize
                        el.style.borderWidth = this.addEditForm.dateBorderWidth
                        el.style.borderStyle = this.addEditForm.dateBorderStyle
                        el.style.borderColor = this.addEditForm.dateBorderColor
                        el.style.borderRadius = this.addEditForm.dateBorderRadius
                        el.style.backgroundColor = this.addEditForm.dateBgColor
                    })
                    document.querySelectorAll('.addEdit-block .date .el-form-item__label').forEach(el=>{
                        el.style.lineHeight = this.addEditForm.dateHeight
                        el.style.color = this.addEditForm.dateLableColor
                        el.style.fontSize = this.addEditForm.dateLableFontSize
                    })
                    document.querySelectorAll('.addEdit-block .date .el-input__icon').forEach(el=>{
                        el.style.color = this.addEditForm.dateIconFontColor
                        el.style.fontSize = this.addEditForm.dateIconFontSize
                        el.style.lineHeight = this.addEditForm.dateHeight
                    })
                    // upload
                    let iconLineHeight = parseInt(this.addEditForm.uploadHeight) - parseInt(this.addEditForm.uploadBorderWidth) * 2 + 'px'
                    document.querySelectorAll('.addEdit-block .upload .el-upload--picture-card').forEach(el=>{
                        el.style.width = this.addEditForm.uploadHeight
                        el.style.height = this.addEditForm.uploadHeight
                        el.style.borderWidth = this.addEditForm.uploadBorderWidth
                        el.style.borderStyle = this.addEditForm.uploadBorderStyle
                        el.style.borderColor = this.addEditForm.uploadBorderColor
                        el.style.borderRadius = this.addEditForm.uploadBorderRadius
                        el.style.backgroundColor = this.addEditForm.uploadBgColor
                    })
                    document.querySelectorAll('.addEdit-block .upload .el-form-item__label').forEach(el=>{
                        el.style.lineHeight = this.addEditForm.uploadHeight
                        el.style.color = this.addEditForm.uploadLableColor
                        el.style.fontSize = this.addEditForm.uploadLableFontSize
                    })
                    document.querySelectorAll('.addEdit-block .upload .el-icon-plus').forEach(el=>{
                        el.style.color = this.addEditForm.uploadIconFontColor
                        el.style.fontSize = this.addEditForm.uploadIconFontSize
                        el.style.lineHeight = iconLineHeight
                        el.style.display = 'block'
                    })
                    // 多文本输入框
                    document.querySelectorAll('.addEdit-block .textarea .el-textarea__inner').forEach(el=>{
                        el.style.height = this.addEditForm.textareaHeight
                        el.style.color = this.addEditForm.textareaFontColor
                        el.style.fontSize = this.addEditForm.textareaFontSize
                        el.style.borderWidth = this.addEditForm.textareaBorderWidth
                        el.style.borderStyle = this.addEditForm.textareaBorderStyle
                        el.style.borderColor = this.addEditForm.textareaBorderColor
                        el.style.borderRadius = this.addEditForm.textareaBorderRadius
                        el.style.backgroundColor = this.addEditForm.textareaBgColor
                    })
                    document.querySelectorAll('.addEdit-block .textarea .el-form-item__label').forEach(el=>{
                        // el.style.lineHeight = this.addEditForm.textareaHeight
                        el.style.color = this.addEditForm.textareaLableColor
                        el.style.fontSize = this.addEditForm.textareaLableFontSize
                    })
                    // 保存
                    document.querySelectorAll('.addEdit-block .btn .btn-success').forEach(el=>{
                        el.style.width = this.addEditForm.btnSaveWidth
                        el.style.height = this.addEditForm.btnSaveHeight
                        el.style.color = this.addEditForm.btnSaveFontColor
                        el.style.fontSize = this.addEditForm.btnSaveFontSize
                        el.style.borderWidth = this.addEditForm.btnSaveBorderWidth
                        el.style.borderStyle = this.addEditForm.btnSaveBorderStyle
                        el.style.borderColor = this.addEditForm.btnSaveBorderColor
                        el.style.borderRadius = this.addEditForm.btnSaveBorderRadius
                        el.style.backgroundColor = this.addEditForm.btnSaveBgColor
                    })
                    // 返回
                    document.querySelectorAll('.addEdit-block .btn .btn-close').forEach(el=>{
                        el.style.width = this.addEditForm.btnCancelWidth
                        el.style.height = this.addEditForm.btnCancelHeight
                        el.style.color = this.addEditForm.btnCancelFontColor
                        el.style.fontSize = this.addEditForm.btnCancelFontSize
                        el.style.borderWidth = this.addEditForm.btnCancelBorderWidth
                        el.style.borderStyle = this.addEditForm.btnCancelBorderStyle
                        el.style.borderColor = this.addEditForm.btnCancelBorderColor
                        el.style.borderRadius = this.addEditForm.btnCancelBorderRadius
                        el.style.backgroundColor = this.addEditForm.btnCancelBgColor
                    })
                })
            },
            addEditUploadStyleChange() {
                this.$nextTick(()=>{
                    document.querySelectorAll('.addEdit-block .upload .el-upload-list--picture-card .el-upload-list__item').forEach(el=>{
                        el.style.width = this.addEditForm.uploadHeight
                        el.style.height = this.addEditForm.uploadHeight
                        el.style.borderWidth = this.addEditForm.uploadBorderWidth
                        el.style.borderStyle = this.addEditForm.uploadBorderStyle
                        el.style.borderColor = this.addEditForm.uploadBorderColor
                        el.style.borderRadius = this.addEditForm.uploadBorderRadius
                        el.style.backgroundColor = this.addEditForm.uploadBgColor
                    })
                })
            },
        }
    };
</script>
<style lang="scss">
.editor{
  height: 500px;

  & /deep/ .ql-container {
	  height: 310px;
  }
}
.amap-wrapper {
  width: 100%;
  height: 500px;
}
.search-box {
  position: absolute;
}
.addEdit-block {
	margin: -10px;
}
.detail-form-content {
	padding: 12px;
}
.btn .el-button {
  padding: 0;
}</style>

