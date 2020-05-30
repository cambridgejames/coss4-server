import config from "../../config";
import message from "../../message";

export default {
    name: 'loginAndLogout',
    methods: {
        loginWithUsualInfo(data, success, failed) {
            this.$axios.post('/api/user-management/entry/loginWithUsualInfo', data).then(result => {
                if (result.data.code === 0) {
                    config.isLogin = true;
                    config.user = result.data.data;
                    message.methods.successMessage('登陆成功');
                    success(result.data.data);
                } else if ('function' === typeof failed) {
                    failed(result.data.msg);
                }
            }).catch(err => {
                console.log(err);
                message.methods.errorMessage('请求失败');
            });
        }
    }
}
