<template>
  <div id="index-container" class="index-container">
    <div id="main-header" class="main-header">
      <div style="max-width: 1200px; margin: 0 auto;">
        <div id="main-icon" class="main-icon"></div>
        <el-menu :default-active="activeName" style="float: right;" mode="horizontal" :router="true">
          <el-menu-item index="/">主页</el-menu-item>
          <el-menu-item index="/competition">竞赛</el-menu-item>
          <el-menu-item index="/community">社区</el-menu-item>
          <el-menu-item index="/wiki">教程</el-menu-item>
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
      <el-backtop></el-backtop>
      <router-view style="min-height: 100vh;" />
    </div>
    <div id="main-footer" class="main-footer">
      <div style="max-width: 1200px; margin: 0 auto;">
        <div>竞赛在线评分系统</div>
        <div class="split-line"></div>
        <div id="copy" class="copy">
          Copyright &copy; {{copy.time}} {{copy.admin}}&nbsp;|
          <a :href="copy.record1.link">{{copy.record1.number}}</a>&nbsp;
          <a :href="copy.record2.link">{{copy.record2.number}}</a>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import config from "../assets/js/config";
  import loginAndLogout from "../assets/js/api/userManagement/loginAndLogout";

  export default {
    mixins: [config, loginAndLogout],
    data() {
      return {
        activeName: '1',
        copy: {
            time: '2017-2020',
            admin: '强力发明狂',
            record1: {
                number: '冀ICP备18027949号-2',
                link: 'http://beian.miit.gov.cn/'
            },
            record2: {
                number: '冀公网安备13092802000145号',
                link: 'http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=13092802000145'
            }
        }
      };
    },
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

  .index-container {
    position: relative;
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
    .main-footer {
      padding: 20px;
      color: #ddd;
      background-color: black;
      .split-line {
        width: 100%;
        height: 0;
        margin: 10px 0;
        border-bottom: 1px solid #666;
      }
      .copy {
        color: #999;
        font-size: 12px;
        line-height: 20px;
        text-align: center;
        a {
          color: inherit;
          &:hover {
            color: cornflowerblue;
          }
        }
      }
    }
  }

</style>
