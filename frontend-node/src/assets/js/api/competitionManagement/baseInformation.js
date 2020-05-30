import message from "../../message";

export default {
    name: 'baseInformation',
    mixins: [message],
    methods: {
        queryCompetition(data, success, failed) {
            let that = this;
            this.$axios.put('/api/competition-management/base/queryCompetition', data).then(result => {
                if (result.data.code === 0) {
                    if (!result.data.data.imageUrl) {
                        let hashCode = getHashCode(result.data.data.compName) % 6 + 1;
                        result.data.data.imageUrl = '/static/imgs/cover/competition-default-cover-' + hashCode + '.png';
                    }
                    success(result.data.data);
                } else if ('function' === typeof failed) {
                    failed(result.data.msg);
                }
            }).catch(err => {
                that.errorMessage('请求失败');
            });
        },
        queryCompetitionList(data, success, failed) {
            let that = this;
            this.$axios.put('/api/competition-management/base/queryCompetitionList', data).then(result => {
                if (result.data.code === 0) {
                    let items = result.data.data;
                    for (let index = 0; index < items.list.length; index++) {
                        // 添加默认封面
                        if (!items.list[index].imageUrl) {
                            let hashCode = getHashCode(items.list[index].compName) % 6 + 1;
                            items.list[index].imageUrl = '/static/imgs/cover/competition-default-cover-' + hashCode + '.png';
                        }
                        // 判断竞赛状态
                        if (items.list[index].endingSign === true) {
                            items.list[index].tagMode = {type: 'danger', content: '已结束'};
                        } else if (new Date(items.list[index].startTime) > new Date()) {
                            items.list[index].tagMode = {type: '', content: '未开始'};
                        } else {
                            items.list[index].tagMode = {type: 'success', content: '进行中'};
                        }
                        // 互动信息
                        items.list[index].viewCount = 0;
                        items.list[index].topCount = 0;
                        items.list[index].likeCount = 0;
                        items.list[index].replyCount = 0;
                    }
                    success(items);
                } else if ('function' === typeof failed) {
                    failed(result.data.msg);
                }
            }).catch(err => {
                that.errorMessage('请求失败');
            });
        }
    }
}

/**
 * 获取字符串的哈希值
 * @param str 字符串
 * @returns {number} 哈希值
 */
function getHashCode(str){
    let hash = 1315423911, i, ch;
    for (i = str.length - 1; i >= 0; i--) {
        ch = str.charCodeAt(i);
        hash ^= ((hash << 5) + ch + (hash >> 2));
    }
    return  (hash & 0x7FFFFFFF);
}
