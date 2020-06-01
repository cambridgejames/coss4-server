export default {
    name: 'format',
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
            let M = (currentDate.getMonth() + 1 < 10 ? '0' : '') + (currentDate.getMonth() + 1) + '-';
            let D = (currentDate.getDate() < 10 ? '0' : '') + currentDate.getDate() + ' ';
            let h = (currentDate.getHours() < 10 ? '0' : '') + currentDate.getHours() + ':';
            let m = (currentDate.getMinutes() < 10 ? '0' : '') + currentDate.getMinutes() + ':';
            let s = (currentDate.getSeconds() < 10 ? '0' : '') + currentDate.getSeconds();
            return Y + M + D + h + m + s;
        },
        formatImageUrl(imageUrl) {
            if (imageUrl.indexOf('https://') === 0) {
                imageUrl = imageUrl.substr(6);
            } else if (imageUrl.indexOf('http://') === 0) {
                imageUrl = imageUrl.substr(5);
            }
            return imageUrl;
        },
        formatVarToArray(stringOrArray) {
            if ('string' === typeof stringOrArray) {
                stringOrArray = [stringOrArray];
            }
            return stringOrArray;
        }
    }
}
