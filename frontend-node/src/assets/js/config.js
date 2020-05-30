import message from "./message";

export default {
  data() {
    return {
      isLogin: false,
      user: null
    }
  },
  mounted() {
    this.getUserInfo();
  },
  methods: {
    flushUserInfo() {
      this.isLogin = this.user !== null;
      localStorage.setItem('userInfo', JSON.stringify(this.user));
    },
    getUserInfo() {
      if (this.user === null) {
        this.user = JSON.parse(localStorage.getItem('userInfo'));
      }
      this.isLogin = this.user !== null;
      return this.user;
    },
    clearUserInfo() {
      this.$confirm('确定要退出系统吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.user = null;
        this.flushUserInfo();
        this.$router.go(0);
        message.successMessage('退出成功');
      }).catch(() => {});
    }
  }
}
