<template>
  <div id="login-container" class="login-container">
    <div id="login-icon" class="login-icon">
      <div style="width: 200px; height: 100%; margin: 0 auto;">
        <a href="/"><div style="height: 100%;"></div></a>
      </div>
    </div>
    <el-tabs type="border-card" :stretch="true">

      <!-- 用户密码登录 -->
      <el-tab-pane label="用户密码登录">
        <el-form ref="userLoginForm" :model="form.userLogin" status-icon label-width="92px" :rules="rules.userRule">
          <el-form-item label="用户名" prop="username" :error="form.errorCode.userLoginError">
            <el-input v-model="form.userLogin.username" placeholder="用户名/邮箱"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password" :error="form.errorCode.userLoginError">
            <el-input v-model="form.userLogin.password" placeholder="密码" show-password></el-input>
          </el-form-item>
          <el-form-item label="图片验证码" prop="verification">
            <el-col :span="16" style="padding-right: 14px;">
              <el-input v-model="form.userLogin.verification" placeholder="验证码不区分大小写"></el-input>
            </el-col>
            <el-col :span="8" style="height: 40px;" title="点击刷新验证码">
              <img class="image-code" :src="form.image" alt="点击刷新验证码" @click="getImageVerification">
            </el-col>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="loginWithUserLogin">登录</el-button>
            <el-button type="primary">注册</el-button>
            <el-button @click="goBack">返回</el-button>
            <el-link type="primary" :underline="false" style="float: right;">忘记密码？</el-link>
          </el-form-item>
        </el-form>
        <el-divider></el-divider>
        <div class="third-part-login">
          <p>第三方平台登录：</p>
          <ul>
            <li><img width="30" src="/static/imgs/login/qq.svg" alt="QQ登录"></li>
            <li><img width="30" src="/static/imgs/login/wechat.svg" alt="微信登录"></li>
            <li><img width="30" src="/static/imgs/login/weibo.svg" alt="微博登录"></li>
          </ul>
        </div>
      </el-tab-pane>

      <!-- 短信验证码登录 -->
      <el-tab-pane label="短信验证码登录">
        <el-form ref="phoneLoginForm" :model="form.phoneLogin" status-icon label-width="92px" :rules="rules.phoneRule">
          <el-form-item label="手机" prop="number">
            <el-input v-model.number="form.phoneLogin.number" placeholder="手机号码"
                      maxlength="11" type="tel"></el-input>
          </el-form-item>
          <el-form-item label="图片验证码" prop="verification">
            <el-col :span="16" style="padding-right: 14px;">
              <el-input v-model="form.phoneLogin.verification" placeholder="验证码不区分大小写"></el-input>
            </el-col>
            <el-col :span="8" style="height: 40px;" title="点击刷新验证码">
              <img class="image-code" :src="form.image" alt="点击刷新验证码" @click="getImageVerification">
            </el-col>
          </el-form-item>
          <el-form-item label="短信验证码" prop="message">
            <el-col :span="16" style="padding-right: 14px;">
              <el-input v-model="form.phoneLogin.message" placeholder="短信验证码"></el-input>
            </el-col>
            <el-col :span="8" style="height: 40px;">
              <el-button style="width: 100%;" type="primary"
                         :disabled="form.phoneLogin.buttonText !== '发送验证码'">
                {{form.phoneLogin.buttonText}}
              </el-button>
            </el-col>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="">登录</el-button>
            <el-button type="primary">注册</el-button>
            <el-button @click="goBack">返回</el-button>
          </el-form-item>
        </el-form>
      </el-tab-pane>

      <!-- 竞赛账号登录 -->
      <el-tab-pane label="竞赛账号登录">
        <el-form ref="playerLoginForm" :model="form" status-icon label-width="92px" :rules="rules.playerRule">
          <el-form-item label="账号类型" prop="mode">
            <el-radio-group v-model="form.playerLogin.mode">
              <el-radio :label="false">参赛账号</el-radio>
              <el-radio :label="true">评委账号</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="账号" prop="username">
            <el-input v-model="form.playerLogin.username"
                      :placeholder="(form.playerLogin.mode ? '评委' : '参赛') + '账号'"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input v-model="form.playerLogin.password" placeholder="密码" show-password></el-input>
          </el-form-item>
          <el-form-item label="图片验证码" prop="verification">
            <el-col :span="16" style="padding-right: 14px;">
              <el-input v-model="form.playerLogin.verification" placeholder="验证码不区分大小写"></el-input>
            </el-col>
            <el-col :span="8" style="height: 40px;" title="点击刷新验证码">
              <img class="image-code" :src="form.image" alt="点击刷新验证码" @click="getImageVerification">
            </el-col>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="loginWithCompetitionAccount">登录</el-button>
            <el-button @click="goBack">返回</el-button>
            <el-link type="primary" :underline="false" style="float: right;">忘记密码？</el-link>
          </el-form-item>
        </el-form>
      </el-tab-pane>

      <!-- 二维码登录 -->
      <el-tab-pane label="二维码安全登录">
        <div id="qr-code" class="qr-code"></div>
        <p style="font-size: 14px; text-align: center;">{{form.qrCodeLogin.message}}</p>
        <div style="margin-top: 10px; text-align: center;">
          <el-button @click="goBack">返回</el-button>
        </div>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
  import config from "../../assets/js/config";
  import message from "../../assets/js/message";
  import loginAndLogout from "../../assets/js/api/userManagement/loginAndLogout";

  export default {
    mixins: [config, message, loginAndLogout],
    data() {
      let imageVerificationRule = (rule, value, callback) => {
        let that = this;
        if (value === '' || value === undefined) {
          callback(new Error('图片验证码不能为空'));
        } else {
          let data = {code: value};
          that.$axios.put('/api/verification-code/imageCode/checkCaseInsensitive', data).then(result => {
            if (result.data.code !== 0) {
              callback(new Error('图片验证码不正确'));
            } else {
              callback();
            }
          }).catch(err => {});
        }
      };
      let phoneRule = (rule, value, callback) => {
        if (String(value).length !== 11) {
          callback('请输入正确的手机号码');
        } else {
          callback();
        }
      };
      return {
        form: {
          image: '',
          userLogin: {
            username: '',
            password: '',
            verification: ''
          },
          phoneLogin: {
            number: '',
            message: '',
            verification: '',
            buttonText: '发送验证码'//重新发送 (60s)
          },
          playerLogin: {
            mode: false,
            username: '',
            password: '',
            verification: ''
          },
          qrCodeLogin: {
            message: '请使用竞赛在线评分手机客户端扫描二维码登录'
          },
          errorCode: {
            userLoginError: ''
          }
        },
        rules: {
          userRule: {
            username: {required: true, message: '用户名/邮箱不能为空', trigger: 'blur'},
            password: {required: true, message: '密码不能为空', trigger: 'blur'},
            verification: {required: true, validator: imageVerificationRule, trigger: 'blur'}
          },
          phoneRule: {
            number: [
              {required: true, message: '手机号码不能为空', trigger: 'blur'},
              {type: 'number', message: '请输入正确的手机号码', trigger: 'blur'},
              {validator: phoneRule, trigger: 'blur'}
            ],
            verification: {required: true, validator: imageVerificationRule, trigger: 'blur'},
            message: {required: true, message: '短信验证码不能为空', trigger: 'blur'}
          },
          playerRule: {
            mode: [
              {required: true, message: '账号类型不能为空', trigger: 'blur'},
              {type: 'boolean',  message: '请选择正确的账号类型'}
            ],
            username: {required: true, message: '账号不能为空', trigger: 'blur'},
            password: {required: true, message: '密码不能为空', trigger: 'blur'},
            verification: {required: true, validator: imageVerificationRule, trigger: 'blur'}
          }
        }
      }
    },
    mounted() {
      let that = this;
      if (that.getUserInfo() !== null) {
        that.goBack();
      }
      that.getImageVerification();
    },
    methods: {
      /**
       * 获取图片验证码
       */
      getImageVerification() {
        let that = this;
        that.form.verification = '';
        that.$axios.get('/api/verification-code/imageCode/getVerificationCode').then(result => {
          if (result.data.code === 0) {
            that.form.image = result.data.data;
          }
        }).catch(err => {});
      },
      /**
       * 用户密码登录
       */
      loginWithUserLogin() {
        let that = this;
        let data = {
          username: that.form.userLogin.username,
          password: that.form.userLogin.password
        };
        that.form.errorCode.userLoginError = '';
        that.$refs['userLoginForm'].validate((valid) => {
          if (valid) {
            that.loginWithUsualInfo(data, function() {
              that.goBack();
            }, function(msg) {
              that.form.errorCode.userLoginError = msg;
              that.getImageVerification();
              return false;
            });
          } else {
            return false;
          }
        });
      },
      loginWithCompetitionAccount() {
        let that = this;
        if (that.form.playerLogin.mode) {
          // 评委账号
          that.$router.push({path: '/scoring/cm5'});
        }
      },
      goBack() {
        let that = this;
        let from = that.$route.query.from;
        if (!!from) {
            that.$router.push({path: from});
            console.log(from);
        } else {
            that.$router.push({path: '/'});
            console.log('/');
        }
      }
    }
  }
</script>

<style lang="scss" scoped>

  .login-container {
    width: 530px;
    max-width: 96%;
    margin: 50px auto;
    .login-icon {
      height: 60px;
      margin: 20px auto;
      background-image: url("/static/imgs/logo_with_name.svg");
      background-size: contain;
      background-position: center;
      background-repeat: no-repeat;
    }
    .third-part-login {
      display: flex;
      height: 30px;
      font-size: 14px;
      line-height: 30px;
      p {
        display: inline-block;
        margin-right: 10px;
      }
      ul {
        display: inline-block;
        li {
          width: 30px;
          height: 30px;
          border-radius: 30px;
          float: left;
          margin-right: 10px;
          cursor: pointer;
          &:hover {
            box-shadow: 0 0 5px 0 #999;
          }
        }
      }
    }
    .image-code {
      width: 100%;
      height: 100%;
      text-align: right;
      cursor: pointer;
      &:hover {
        color: #6495ed;
      }
    }
    .qr-code {
      width: 150px;
      height: 150px;
      margin: 0 auto 10px auto;
      background-image: url("../../assets/qrcode.png");
      background-size: contain;
      background-position: center;
      background-repeat: no-repeat;
    }
  }

</style>
