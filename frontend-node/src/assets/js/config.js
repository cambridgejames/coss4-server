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
      console.log(this.user);
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
      this.user = null;
      this.flushUserInfo();
    }
  }
}
