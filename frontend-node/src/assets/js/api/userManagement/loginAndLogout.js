import config from "../../config";
import message from "../../message";

export default {
    name: 'loginAndLogout',
    mixins: [config, message],
    methods: {
        loginWithUsualInfo(data, success, failed) {
            let that = this;
            this.$axios.post('/api/user-management/entry/loginWithUsualInfo', data).then(result => {
                if (result.data.code === 0) {
                    that.isLogin = true;
                    that.user = result.data.data;
                    that.flushUserInfo();
                    that.successMessage('登陆成功');
                    success(result.data.data);
                } else if ('function' === typeof failed) {
                    failed(result.data.msg);
                }
            }).catch(err => {
                that.errorMessage('请求失败');
            });
        },
        logout(success, failed, targetUrl) {
            let that = this;
            this.$axios.post('/api/user-management/entry/logout', {}).then(result => {
                if (result.data.code === 0) {
                    this.clearUserInfo(targetUrl);
                    success(result.data.msg);
                } else if ('function' === typeof failed) {
                    failed(result.data.msg);
                }
            }).catch(err => {
                that.errorMessage('请求失败');
            });
        }
    }
}
