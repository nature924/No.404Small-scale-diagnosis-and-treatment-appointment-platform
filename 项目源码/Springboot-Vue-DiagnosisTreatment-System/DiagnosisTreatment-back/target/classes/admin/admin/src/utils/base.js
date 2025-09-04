const base = {
    get() {
        return {
            url : "http://localhost:8080/xiaoxingzhenliaoyuyuepingtai/",
            name: "xiaoxingzhenliaoyuyuepingtai",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/xiaoxingzhenliaoyuyuepingtai/front/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "小型诊疗预约平台"
        } 
    }
}
export default base
