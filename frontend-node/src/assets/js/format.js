export default {
    data() {
        return {}
    },
    methods: {
        formatDate(currentDate) {
            let date = new Date(currentDate);
            let year = date.getFullYear();
            let month = date.getMonth() + 1;
            month = month < 10 ? ('0' + month) : month;
            let day = date.getDate();
            day = day < 10 ? ('0' + day) : day;
            return year + "-" + month + "-" + day;
        },
        formatDateTime(currentDate) {
            let Y = currentDate.getFullYear() + '-';
            let M = (currentDate.getMonth()+1 < 10 ? '0'+(currentDate.getMonth()+1) : currentDate.getMonth()+1) + '-';
            let D = currentDate.getDate() + ' ';
            let h = (currentDate.getHours() < 10 ? '0'+currentDate.getHours() : currentDate.getHours()) + ':';
            let m = currentDate.getMinutes() + ':';
            let s = currentDate.getSeconds();
            return Y+M+D+h+m+s; 
        }
    }
}
