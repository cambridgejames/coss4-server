<template>
    <div id="competition-index-container" class="competition-container">
        <div class="banner-container">
            <span>创建竞赛</span>
        </div>
        <div class="list-container">
            <el-card>
                <div class="list-input-box">
                    <el-input placeholder="请输入内容" prefix-icon="el-icon-search" class="input-input"></el-input>
                    <el-button type="primary" icon="el-icon-search"></el-button>
                </div>
                <div class="list-condition">
                    <span class="condition-span">类型</span>
                    <div class="condition-item">
                        <span v-for="item in list">{{item}}</span>
                    </div>
                </div>
            </el-card>
            <el-card class="list-result-box">
                <div class="list-condition">
                    <span class="condition-span">类型</span>
                    <div class="condition-item">
                        <span v-for="item in sort">{{item.title}}</span>
                    </div>
                </div>
                <div v-if="!items.list" class="result-none">
                    暂无数据
                </div>
                <div v-else v-for="item in items.list" class="result-item-box">
                    <div class="result-item-inner-box">
                        <div class="item-image-box" @click="toCompetitionView(item.id)">
                            <el-image class="item-image" :src="item.imageUrl" fit="cover"></el-image>
                        </div>
                        <div class="item-info-box">
                            <div class="info-title-box">
                                <el-tag size="small" :type="item.tagMode.type"
                                        style="width: 54px; min-width: 54px;">{{item.tagMode.content}}</el-tag>
                                <span class="info-title" @click="toCompetitionView(item.id)">{{item.compName}}</span>
                                <span class="info-time">{{formatDate(new Date(item.createTime))}}</span>
                            </div>
                            <div class="holder-time-box">
                                <span><i class="el-icon-user"></i>&nbsp;{{item.nickname}}</span>
                                <span><i class="el-icon-time"></i>&nbsp;{{formatDateTime(new Date(item.startTime))}}</span>
                            </div>
                            <div class="item-detail-box">{{item.detailedInformation}}</div>
                            <div class="item-function-box">
                                <span style="height: 28px; line-height: 28px;">浏览量：<span style="color: #f00;">{{item.viewCount}}</span></span>
                                <ul>
                                    <li><i class="el-icon-thumb"></i>{{item.topCount}}</li>
                                    <li><i class="el-icon-star-off"></i>{{item.likeCount}}</li>
                                    <li><i class="el-icon-chat-dot-round"></i>{{item.replyCount}}</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="result-pagination-box" v-if="Math.ceil(items.total / 15) !== 1">
                    <el-pagination background layout="prev, pager, next" :hide-on-single-page="false"
                                   :total="Math.ceil(items.total / 15)">
                    </el-pagination>
                </div>
            </el-card>
        </div>
    </div>
</template>

<script>
    import message from "../../assets/js/message";
    import format from "../../assets/js/format";

    export default {
        mixins: [message, format],
        name: "competition",
        data() {
            return {
                list: ['竞赛类型A', '竞赛类型B', '竞赛类型C', '竞赛类型D', '竞赛类型E', '竞赛类型F', '竞赛类型G',
                    '竞赛类型H', '竞赛类型I', '竞赛类型J', '竞赛类型K', '竞赛类型L', '竞赛类型M', '竞赛类型N',
                    '竞赛类型O', '竞赛类型P', '竞赛类型Q', '竞赛类型R', '竞赛类型S', '竞赛类型T', '竞赛类型U',
                    '竞赛类型V', '竞赛类型W', '竞赛类型X', '竞赛类型Y', '竞赛类型Z'],
                sort: [
                    {name: 'time', title: '时间', mode: 'DESC'},
                    {name: 'name', title: '名称', mode: 'NONE'},
                    {name: 'type', title: '类型', mode: 'NONE'}
                ],
                src: '/static/imgs/cover/competition-default-cover-1.png',
                items: {
                    total: 0
                }
            }
        },
        mounted() {
            this.queryCompetitionList();
        },
        methods: {
            /**
             * 查询竞赛列表
             */
            queryCompetitionList() {
                let that = this;
                let data = {
                    sortType: "START_TIME"
                };
                that.$axios.put('/api/competition-management/base/queryCompetitionList', data).then(result => {
                    if (result.data.code === 0) {
                        that.items = result.data.data;
                        for (let index = 0; index < that.items.list.length; index++) {
                            // 添加默认封面
                            if (!that.items.list[index].imageUrl) {
                                let hashCode = that.getHashCode(that.items.list[index].compName) % 6 + 1;
                                that.items.list[index].imageUrl = '/static/imgs/cover/competition-default-cover-' + hashCode + '.png';
                            }
                            // 判断竞赛状态
                            if (that.items.list[index].endingSign === true) {
                                that.items.list[index].tagMode = {type: 'danger', content: '已结束'};
                            } else if (new Date(that.items.list[index].startTime) > new Date()) {
                                that.items.list[index].tagMode = {type: '', content: '未开始'};
                            } else {
                                that.items.list[index].tagMode = {type: 'success', content: '进行中'};
                            }
                            // 互动信息
                            that.items.list[index].viewCount = 0;
                            that.items.list[index].topCount = 0;
                            that.items.list[index].likeCount = 0;
                            that.items.list[index].replyCount = 0;
                        }
                    }
                }).catch(err => {
                    that.errorMessage('请求失败');
                });
            },
            /**
             * 跳转到竞赛详情页面
             * @param id 竞赛ID
             */
            toCompetitionView(id) {
                this.$router.push({path : '/competition/cm' + id});
            },
            /**
             * 获取字符串的哈希值
             * @param str 字符串
             * @returns {number} 哈希值
             */
            getHashCode(str){
                let hash = 1315423911, i, ch;
                for (i = str.length - 1; i >= 0; i--) {
                    ch = str.charCodeAt(i);
                    hash ^= ((hash << 5) + ch + (hash >> 2));
                }
                return  (hash & 0x7FFFFFFF);
            }
        }
    }
</script>

<style lang="scss" scoped>

@import "../../assets/scss/main-list";

.competition-container {
    .banner-container {
        height: 400px;
        text-align: center;
        background-color: cornflowerblue;

        span {
            width: 162px;
            height: 44px;
            display:inline-block;
            margin-top: 200px;
            border: 1px solid #fff;
            border-radius: 50px;
            color: #fff;
            line-height: 44px;
            font-size: 20px;
            font-weight: bold;
            cursor: pointer;
        }
        span:hover {
            background-color: rgba(255, 255, 255, 0.2);
        }
    }
    .list-container {
        max-width: 1200px;
        margin: -40px auto 40px auto;
    }
}

</style>
