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
    import baseInformation from "../../assets/js/api/competitionManagement/baseInformation";

    export default {
        mixins: [message, format, baseInformation],
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
            this.queryCompetitionListImpl();
        },
        methods: {
            /**
             * 查询竞赛列表
             */
            queryCompetitionListImpl() {
                let that = this;
                let data = {
                    sortType: "START_TIME"
                };
                that.queryCompetitionList(data, function (data) {
                    that.items = data;
                }, that.warningMessage);
            },
            /**
             * 跳转到竞赛详情页面
             * @param id 竞赛ID
             */
            toCompetitionView(id) {
                this.$router.push({path : '/competition/cm' + id});
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
