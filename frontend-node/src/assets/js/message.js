export default {
    data() {
        return {}
    },
    methods: {
    infoMessage(content) {
        this.$message({showClose: true, type: 'info', message: content, duration: 1500});
    },
    successMessage(content) {
        this.$message({showClose: true, type: 'success', message: content, duration: 1500});
    },
    warningMessage(content) {
        this.$message({showClose: true, type: 'warning', message: content, duration: 1500});
    },
    errorMessage(content) {
        console.log(this.$message);
        this.$message({showClose: true, type: 'error', message: content, duration: 1500});
    }
    }
}
