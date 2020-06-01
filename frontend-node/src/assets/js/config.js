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
    clearUserInfo(targetUrl) {
        this.user = null;
        this.flushUserInfo();
        if (!!targetUrl) {
            this.$router.push({path: targetUrl});
        } else {
            this.$router.go(0);
        }
    }
  }
}
