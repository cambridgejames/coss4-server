<template>
    <div id="scoring-home-container" class="scoring-home-container">
        <div v-if="!isScoring" class="index-container">
            <div style="text-align: center;">
                <h1>{{competitionInfo.compName}}</h1>
            </div>
            <div class="holder-time-box">
                <span><i class="el-icon-user"></i>&nbsp;{{competitionInfo.nickname}}</span>
                <span><i class="el-icon-time"></i>&nbsp;{{formatDateTime(new Date(competitionInfo.startTime))}}</span>
            </div>
            <div style="margin-top: 20px; text-align: center;">
                <el-button type="primary" @click="startScoring">进入评分</el-button>
                <el-button @click="logoutImpl">退出</el-button>
            </div>
            <el-card class="tips">
                <p>{{competitionInfo}}</p>
                <p>若要测试系统网络连接，可以<el-link type="primary" @click="networkTest">点击此处</el-link>。</p>
            </el-card>
        </div>
        <div v-else>
            scoring
        </div>
        <el-dialog title="系统测试" :visible.sync="dialogVisible" :close-on-click-modal="false" :close-on-press-escape="false" :show-close="false"
                   width="100%" style="max-width: 600px; margin-left: auto; margin-right: auto;">
            <el-timeline :reverse="reverse">
                <el-timeline-item v-for="(activity, index) in activities" :key="index" :type="activity.type" size="large" :icon="activity.icon">
                    {{activity.content}}
                </el-timeline-item>
            </el-timeline>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false" :disabled="true">取 消</el-button>
                <el-button type="primary" @click="dialogVisible = false" :disabled="dialogConfirmDisabled">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
    import screenfull from 'screenfull';
    import baseInformation from "../../assets/js/api/competitionManagement/baseInformation";
    import loginAndLogout from "../../assets/js/api/userManagement/loginAndLogout";
    import message from "../../assets/js/message";

    export default {
        name: "scoreHome",
        mixins: [baseInformation, loginAndLogout, message],
        data() {
            return {
                competitionInfo: {
                    tagMode: {}
                },
                isScoring: false,
                dialogVisible: false,
                dialogConfirmDisabled: true,
                reverse: false,
                activities: []
            }
        },
        mounted() {
            this.queryCompetitionImpl();
        },
        methods: {
            queryCompetitionImpl() {
                let that = this;
                that.queryCompetition({cid: this.$route.params.id.substr(2)}, function(data) {
                    that.competitionInfo = data;
                }, that.warningMessage);
            },
            startScoring() {
                if (screenfull.isEnabled) {
                    screenfull.toggle();
                    this.isScoring = true;
                }
            },
            logoutImpl() {
                this.logout(this.successMessage, this.errorMessage);
                this.$router.push({path: '/login'});
            },
            networkTest() {
                let that = this;
                that.activities = [
                    {content: '检查系统环境'}, {content: '测试网络连通性'}, {content: '模拟成绩提交'}, {content: '成功'}
                ];
                that.dialogConfirmDisabled = true;
                that.dialogVisible = true;
                that.activities[0].icon = 'el-icon-loading';
                that.activities[0].type = 'primary';
                that.$set(that.activities, 0, that.activities[0]);
                setTimeout(function() {
                    that.activities[0].icon = 'el-icon-check';
                    that.activities[0].type = 'success';
                    that.$set(that.activities, 0, that.activities[0]);
                    that.activities[1].icon = 'el-icon-loading';
                    that.activities[1].type = 'primary';
                    that.$set(that.activities, 1, that.activities[1]);
                    setTimeout(function() {
                        that.activities[1].icon = 'el-icon-check';
                        that.activities[1].type = 'success';
                        that.$set(that.activities, 1, that.activities[1]);
                        that.activities[2].icon = 'el-icon-loading';
                        that.activities[2].type = 'primary';
                        that.$set(that.activities, 2, that.activities[2]);
                        setTimeout(function() {
                            that.activities[2].icon = 'el-icon-check';
                            that.activities[2].type = 'success';
                            that.$set(that.activities, 2, that.activities[2]);
                            that.activities[3].icon = 'el-icon-check';
                            that.activities[3].type = 'success';
                            that.$set(that.activities, 3, that.activities[3]);
                            that.dialogConfirmDisabled = false;
                        }, 3000);
                    }, 3000);
                }, 3000);
            }
        }
    }
</script>

<style lang="scss" scoped>

.scoring-home-container {
    max-width: 1200px;
    margin: 0 auto;

    .index-container {
        margin-top: 100px;

        .holder-time-box {
            height: 20px;
            min-height: 20px;
            margin-top: 10px;
            text-align: center;
            span {
                display: inline-block;
                margin-right: 20px;
                font-size: 14px;
                color: #aaa;
                &:last-child {
                    margin-right: 0;
                }
            }
        }
        .tips {
            margin-top: 40px;
            p {
                margin-bottom: 10px;
            }
        }
    }
}

</style>
