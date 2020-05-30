<template>
    <div id="certification-container" class="certification-container">
        <div class="background"></div>
        <div id="main-header" class="main-header">
            <div style="max-width: 1200px; margin: 0 auto;">
                <div id="main-icon" class="main-icon"></div>
                <el-menu default-active="/certification" style="float: right;" mode="horizontal" :router="true">
                    <el-menu-item index="/">主页</el-menu-item>
                    <el-menu-item index="/certification">认证中心</el-menu-item>
                    <el-menu-item :index="'/login?from=' + this.$route.path" v-if="!isLogin" target="_blank">登录/注册</el-menu-item>
                    <el-submenu index="/user" v-else>
                        <template slot="title">
                            <el-avatar shape="square" size="small" :src="user.imageUrl" style="margin-right: 6px;"></el-avatar>
                            {{user.nickname}}
                        </template>
                        <el-menu-item index="/info">用户资料</el-menu-item>
                        <el-menu-item index="/settings">设置</el-menu-item>
                        <el-menu-item index="/help">帮助</el-menu-item>
                        <el-menu-item @click="logoutImpl">退出</el-menu-item>
                    </el-submenu>
                </el-menu>
            </div>
        </div>
        <div id="main-container" class="main-container">
            <router-view/>
        </div>
    </div>
</template>

<script>
    import config from "../../assets/js/config";
    import loginAndLogout from "../../assets/js/api/userManagement/loginAndLogout";

    export default {
        name: "certification",
        mixins: [config, loginAndLogout],
        methods: {
            logoutImpl() {
                this.$confirm('确定要退出系统吗？', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.logout(function () {
                        message.successMessage('退出成功');
                    }, this.errorMessage);
                }).catch(() => {});
            }
        }
    }
</script>

<style lang="scss" scoped>

.certification-container {
    .background {
        width: 100vw;
        height: 100vh;
        position: fixed;
        top: 0;
        left: 0;
        background-color: #fafafa;
        z-index: -10000;
    }
    .main-header {
        position: fixed;
        top: 0;
        left: 0;
        right: 0;
        min-width: 600px;
        height: 60px;
        line-height: 60px;
        padding: 0 50px;
        background-color: #fff;
        border-bottom: 1px solid #dcdfe6;
        z-index: 100;
        .main-icon {
            float: left;
            height: 60px;
            width: 140px;
            background-image: url("/static/imgs/logo_with_name.svg");
            background-size: contain;
            background-position: center;
            background-repeat: no-repeat;
        }
    }
    .main-container {
        width: 100%;
        padding-top: 60px;
        background-color: #fafafa;
        overflow: hidden;
    }
}

</style>
