import Vue from 'vue';
//配置路由
import VueRouter from 'vue-router'
Vue.use(VueRouter);
//1.创建组件
import Index from '@/views/index'
import Home from '@/views/home'
import Login from '@/views/login'
import NotFound from '@/views/404'
import UpdatePassword from '@/views/update-password'
import pay from '@/views/pay'
import register from '@/views/register'
import center from '@/views/center'

     import users from '@/views/modules/users/list'
    import bingli from '@/views/modules/bingli/list'
    import dictionary from '@/views/modules/dictionary/list'
    import gonggao from '@/views/modules/gonggao/list'
    import huanzhe from '@/views/modules/huanzhe/list'
    import singleSeach from '@/views/modules/singleSeach/list'
    import yaopin from '@/views/modules/yaopin/list'
    import yisheng from '@/views/modules/yisheng/list'
    import yishengCollection from '@/views/modules/yishengCollection/list'
    import yishengLiuyan from '@/views/modules/yishengLiuyan/list'
    import yishengOrder from '@/views/modules/yishengOrder/list'
    import config from '@/views/modules/config/list'
    import dictionaryGonggao from '@/views/modules/dictionaryGonggao/list'
    import dictionaryJiaofei from '@/views/modules/dictionaryJiaofei/list'
    import dictionaryKeshi from '@/views/modules/dictionaryKeshi/list'
    import dictionarySex from '@/views/modules/dictionarySex/list'
    import dictionaryShijianduan from '@/views/modules/dictionaryShijianduan/list'
    import dictionarySingleSeach from '@/views/modules/dictionarySingleSeach/list'
    import dictionaryYaopin from '@/views/modules/dictionaryYaopin/list'
    import dictionaryYishengCollection from '@/views/modules/dictionaryYishengCollection/list'
    import dictionaryYishengOrder from '@/views/modules/dictionaryYishengOrder/list'
    import dictionaryYishengOrderPayment from '@/views/modules/dictionaryYishengOrderPayment/list'
    import dictionaryZhiwei from '@/views/modules/dictionaryZhiwei/list'





//2.配置路由   注意：名字
const routes = [{
    path: '/index',
    name: '首页',
    component: Index,
    children: [{
      // 这里不设置值，是把main作为默认页面
      path: '/',
      name: '首页',
      component: Home,
      meta: {icon:'', title:'center'}
    }, {
      path: '/updatePassword',
      name: '修改密码',
      component: UpdatePassword,
      meta: {icon:'', title:'updatePassword'}
    }, {
      path: '/pay',
      name: '支付',
      component: pay,
      meta: {icon:'', title:'pay'}
    }, {
      path: '/center',
      name: '个人信息',
      component: center,
      meta: {icon:'', title:'center'}
    } ,{
        path: '/users',
        name: '管理信息',
        component: users
      }
    ,{
        path: '/dictionaryGonggao',
        name: '公告类型',
        component: dictionaryGonggao
    }
    ,{
        path: '/dictionaryJiaofei',
        name: '是否缴费',
        component: dictionaryJiaofei
    }
    ,{
        path: '/dictionaryKeshi',
        name: '科室',
        component: dictionaryKeshi
    }
    ,{
        path: '/dictionarySex',
        name: '性别类型',
        component: dictionarySex
    }
    ,{
        path: '/dictionaryShijianduan',
        name: '时间段',
        component: dictionaryShijianduan
    }
    ,{
        path: '/dictionarySingleSeach',
        name: '单页数据类型',
        component: dictionarySingleSeach
    }
    ,{
        path: '/dictionaryYaopin',
        name: '药品类型',
        component: dictionaryYaopin
    }
    ,{
        path: '/dictionaryYishengCollection',
        name: '收藏表类型',
        component: dictionaryYishengCollection
    }
    ,{
        path: '/dictionaryYishengOrder',
        name: '挂号状态',
        component: dictionaryYishengOrder
    }
    ,{
        path: '/dictionaryYishengOrderPayment',
        name: '订单支付类型',
        component: dictionaryYishengOrderPayment
    }
    ,{
        path: '/dictionaryZhiwei',
        name: '职位',
        component: dictionaryZhiwei
    }
    ,{
        path: '/config',
        name: '轮播图',
        component: config
    }


    ,{
        path: '/bingli',
        name: '病例',
        component: bingli
      }
    ,{
        path: '/dictionary',
        name: '字典',
        component: dictionary
      }
    ,{
        path: '/gonggao',
        name: '公告信息',
        component: gonggao
      }
    ,{
        path: '/huanzhe',
        name: '患者',
        component: huanzhe
      }
    ,{
        path: '/singleSeach',
        name: '单页数据',
        component: singleSeach
      }
    ,{
        path: '/yaopin',
        name: '药品',
        component: yaopin
      }
    ,{
        path: '/yisheng',
        name: '医生',
        component: yisheng
      }
    ,{
        path: '/yishengCollection',
        name: '医生收藏',
        component: yishengCollection
      }
    ,{
        path: '/yishengLiuyan',
        name: '医生留言',
        component: yishengLiuyan
      }
    ,{
        path: '/yishengOrder',
        name: '医生预约挂号订单',
        component: yishengOrder
      }


    ]
  },
  {
    path: '/login',
    name: 'login',
    component: Login,
    meta: {icon:'', title:'login'}
  },
  {
    path: '/register',
    name: 'register',
    component: register,
    meta: {icon:'', title:'register'}
  },
  {
    path: '/',
    name: '首页',
    redirect: '/index'
  }, /*默认跳转路由*/
  {
    path: '*',
    component: NotFound
  }
]
//3.实例化VueRouter  注意：名字
const router = new VueRouter({
  mode: 'hash',
  /*hash模式改为history*/
  routes // （缩写）相当于 routes: routes
})

export default router;
