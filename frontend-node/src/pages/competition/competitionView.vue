<template>
    <div id="competition-view-container" class="competition-view-container">
        <div style="background-color: white; border-bottom: 1px solid #eee;">
            <el-page-header style="padding: 24px; margin: 0 auto; max-width: 1200px;"
                            @back="goBack" content="竞赛详情"></el-page-header>
        </div>
        <div class="competition-detail-box">
            <el-card>
                <div class="detail-main-box">
                    <el-image class="main-image-box" :src="competitionInfo.imageUrl" fit="cover" lazy></el-image>
                    <div class="main-info-box">
                        <div class="info-title">{{competitionInfo.compName}}</div>
                        <div style="font-size: 12px; color: #999; margin-bottom: 10px;">
                            <span style="margin-right: 20px;">CM{{competitionInfo.id}}</span>
                            <span><i class="el-icon-time"></i>&nbsp;{{formatDateTime(new Date(competitionInfo.startTime))}}</span>
                        </div>
                        <div v-if="!!user && !!user.id">
                            <el-button v-if="user.id === competitionInfo.userId || user.id === 1"
                                       type="primary">信息维护</el-button>
                            <el-button v-if="user.id !== competitionInfo.userId" type="primary">参赛人员入口</el-button>
                            <el-button type="primary" @click="certificationResult">成绩认证</el-button>
                        </div>
                    </div>
                </div>
                <!--<div>{{competitionInfo}}</div>-->
            </el-card>
            <el-tabs class="detail-detail-box" type="border-card" v-model="activeName">
                <!-- 简介 -->
                <el-tab-pane label="简介" name="detail">
                    <div style="padding: 5px;">
                        <div style="margin-bottom: 20px;" v-if="!!competitionInfo.notice && competitionInfo.notice.length !== 0">
                            <h2 style="margin-bottom: 10px;">主办方通知</h2>
                            <el-alert v-for="(item, index) in competitionInfo.notice" :key="index"
                                      :title="item.description" :type="item.type" :closable="false"
                                      style="margin-bottom: 10px;"></el-alert>
                        </div>
                        <div>
                            <h2 style="margin-bottom: 10px;">竞赛简介</h2>
                            <div v-html="competitionInfo.detailedInformation" style="text-align: justify;"></div>
                        </div>
                    </div>
                </el-tab-pane>
                <!-- END 简介 -->

                <!-- 参赛成员表 -->
                <el-tab-pane label="参赛成员" name="player">
                    参赛成员
                </el-tab-pane>
                <!-- END 参赛成员表 -->

                <!-- 评分结果表 -->
                <el-tab-pane label="评分结果" name="score">
                    评分结果
                </el-tab-pane>
                <!-- END 评分结果表 -->

                <!-- 评论 -->
                <el-tab-pane label="评论" name="replay">
                    <span slot="label">
                        评论<el-badge :value="100" :max="99" class="replay-slot-badge" type="primary"></el-badge>
                    </span>
                    评论
                </el-tab-pane>
                <!-- END 评论 -->
            </el-tabs>
        </div>
    </div>
</template>

<script>
    import message from "../../assets/js/message";
    import format from "../../assets/js/format";
    import config from "../../assets/js/config";

    export default {
        name: "competitionView",
        mixins: [message, format, config],
        data() {
            return {
                activeName: 'detail',
                competitionInfo: {
                    imageUrl: '/static/imgs/cover/competition-default-cover-1.png'
                },
                notice: [
                    {type: 'success', description: '这是一句绕口令：黑灰化肥会挥发发灰黑化肥挥发；灰黑化肥会挥发发黑灰化肥发挥。 黑灰化肥会挥发发灰黑化肥黑灰挥发化为灰……'},
                    {type: 'info', description: '这是一句绕口令：黑灰化肥会挥发发灰黑化肥挥发；灰黑化肥会挥发发黑灰化肥发挥。 黑灰化肥会挥发发灰黑化肥黑灰挥发化为灰……'},
                    {type: 'warning',  description: '这是一句绕口令：黑灰化肥会挥发发灰黑化肥挥发；灰黑化肥会挥发发黑灰化肥发挥。 黑灰化肥会挥发发灰黑化肥黑灰挥发化为灰……'},
                    {type: 'error',  description: '这是一句绕口令：黑灰化肥会挥发发灰黑化肥挥发；灰黑化肥会挥发发黑灰化肥发挥。 黑灰化肥会挥发发灰黑化肥黑灰挥发化为灰……'}
                ]
            }
        },
        mounted() {
            this.queryCompetition();
        },
        methods: {
            queryCompetition() {
                let that = this;
                let data = {cid: that.$route.params.id.substr(2)};
                this.$axios.put('/api/competition-management/base/queryCompetition', data).then(result => {
                    if (result.data.code === 0) {
                        that.competitionInfo = result.data.data;
                        that.competitionInfo.notice = that.notice;
                    } else {
                        that.warningMessage(result.data.msg);
                    }
                }).catch(err => {
                    that.errorMessage('请求失败');
                });
            },
            certificationResult() {
                let newPage = this.$router.resolve({path: '/certification/result/cm' + this.competitionInfo.id});
                window.open(newPage.href, '_blank');
            },
            goBack() {
                this.$router.push({path: '/competition'});
            }
        }
    }
</script>

<style lang="scss" scoped>

.competition-detail-box {
    max-width: 1200px;
    margin: 40px auto;

    .detail-main-box {
        width: 100%;
        height: 250px;
        display: flex;
        flex-direction: row;

        .main-image-box {
            width: 450px;
            min-width: 450px;
        }

        .main-info-box {
            width: 100%;
            margin-left: 20px;
            overflow: auto;

            .info-title {
                font-size: 24px;
                line-height: 1.2;
                margin-bottom: 10px;
                text-align: justify;
            }
        }
    }

    .detail-detail-box {
        margin-top: 40px;

        .replay-slot-badge {
            margin-left: 2px;
        }
    }
}

</style>
