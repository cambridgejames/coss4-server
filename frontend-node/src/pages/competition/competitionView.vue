<template>
    <div id="competition-view-container" class="competition-view-container">
        <div style="background-color: white; border-bottom: 1px solid #eee;">
            <el-page-header style="padding: 24px; margin: 0 auto; max-width: 1200px;"
                            @back="goBack" :content="competitionInfo.compName"></el-page-header>
        </div>
        <div class="competition-detail-box">
            <el-card>
                {{competitionInfo}}
            </el-card>
            <el-tabs class="detail-detail-box" type="border-card" v-model="activeName">
                <!-- 简介 -->
                <el-tab-pane label="简介" name="detail">
                    <div v-html="competitionInfo.detailedInformation"></div>
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

    export default {
        name: "competitionView",
        mixins: [message],
        data() {
            return {
                activeName: 'detail',
                competitionInfo: {}
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
                    } else {
                        that.warningMessage(result.data.msg);
                    }
                }).catch(err => {
                    that.errorMessage('请求失败');
                });
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

    .detail-detail-box {
        margin-top: 40px;

        .replay-slot-badge {
            margin-left: 2px;
        }
    }
}

</style>
