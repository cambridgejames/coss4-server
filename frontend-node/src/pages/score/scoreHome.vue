<template>
    <div id="scoring-home-container" class="scoring-home-container">
        <div class="scoring-home-box">
            <div v-if="!isScoring" class="index-container">
                <div style="text-align: center;">
                    <h1>{{competitionInfo.compName}}</h1>
                </div>
                <div class="holder-time-box">
                    <span><i class="el-icon-location-outline"></i>&nbsp;{{$route.params.id.toUpperCase()}}</span>
                    <span><i class="el-icon-user"></i>&nbsp;{{competitionInfo.nickname}}</span>
                    <span><i class="el-icon-time"></i>&nbsp;{{formatDateTime(new Date(competitionInfo.startTime))}}</span>
                </div>
                <div style="margin-top: 20px; text-align: center;">
                    <el-button type="primary" @click="startScoring">进入评分</el-button>
                    <el-button @click="logoutImpl">退出</el-button>
                </div>
                <el-card class="tips">
                    <div style="margin-bottom: 20px;">
                        <p>
                            <span style="font-weight: bold;">用&ensp;户&ensp;名：</span>test_judge_1
                            <span style="color: #999;">(jid:1)</span>
                        </p>
                        <p><span style="font-weight: bold;">已评数量：</span>0</p>
                    </div>
                    <ul>
                        <li>请确认以上信息是否准确。如有问题，请联系竞赛管理员。</li>
                        <li>若要测试系统网络连接，可以<el-link type="primary" @click="networkTest">点击此处</el-link> 。</li>
                    </ul>
                </el-card>
            </div>

            <!-- 评分页面 -->
            <div v-else class="judge-container">
                <el-button @click="logoutImpl" style="position: fixed; top: 20px; right: 20px;">退出系统</el-button>
                <el-card>
                    <div slot="header">作品信息</div>
                    <div>
                        <el-row style="margin-bottom: 10px;" :gutter="20">
                            <el-col :span="12">
                                <span style="font-weight: bold;">参赛人/队名称：</span>{{currentJudge.playerInfo.userName}}
                            </el-col>
                            <el-col :span="12">
                                <span style="font-weight: bold;">当前分组：</span>{{currentJudge.playerInfo.groupName}}
                            </el-col>
                        </el-row>
                        <el-row>
                            <span style="font-weight: bold;">作品名称：</span>
                            {{!!currentJudge.playerInfo.imageUrl ? currentJudge.playerInfo.workName : '未上传作品'}}
                            <el-button v-if="!!currentJudge.playerInfo.imageUrl" style="margin-left: 10px;"
                                       @click="previewShow = true">查看作品</el-button>
                        </el-row>
                    </div>
                </el-card>
                <el-card class="judge-form-box">
                    <div slot="header">详细评分</div>
                    <el-form label-position="left" label-width="80px" :model="currentJudge">
                        <el-row :gutter="20">
                            <el-col :xs="24" :sm="12" :md="12" :lg="12" :xl="12"
                                    v-for="(item, index) in currentJudge.details" :key="index">
                                <el-form-item :label="item.compRubricName">
                                    <el-input-number v-model="item.value" label="描述文字" style="width: 100%;"
                                                     :min="item.minValue" :max="item.maxValue" :step="item.accuracy"></el-input-number>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <div style="text-align: center;">
                            <el-button @click="clearScore">清空</el-button>
                            <el-button type="primary" @click="commitScore">提交</el-button>
                        </div>
                    </el-form>
                </el-card>
            </div>
            <!-- END 评分页面 -->

            <el-dialog title="系统测试" :visible.sync="dialogVisible" :close-on-click-modal="false"
                       :close-on-press-escape="false" :show-close="false"
                       width="100%" style="max-width: 600px; margin-left: auto; margin-right: auto;">
                <el-timeline :reverse="reverse">
                    <el-timeline-item v-for="(activity, index) in activities" :key="index"
                                      :type="activity.type" size="large" :icon="activity.icon">
                        {{activity.content}}
                    </el-timeline-item>
                </el-timeline>
                <span slot="footer" class="dialog-footer">
                <el-button @click="cancelNetworkTest">取消</el-button>
                <el-button type="primary" @click="dialogVisible = false"
                           :disabled="dialogConfirmDisabled">确定</el-button>
            </span>
            </el-dialog>
            <el-image-viewer v-if="previewShow" :on-close="closePreview" :url-list="formatVarToArray(currentJudge.playerInfo.imageUrl)"/>
        </div>
    </div>
</template>

<script>
    import screenfull from 'screenfull';
    import baseInformation from "../../assets/js/api/competitionManagement/baseInformation";
    import loginAndLogout from "../../assets/js/api/userManagement/loginAndLogout";
    import message from "../../assets/js/message";
    import ElImageViewer from 'element-ui/packages/image/src/image-viewer'
    import format from "../../assets/js/format";

    export default {
        components:{
            ElImageViewer
        },
        name: "scoreHome",
        mixins: [baseInformation, loginAndLogout, message, format],
        data() {
            return {
                competitionInfo: {
                    tagMode: {}
                },
                currentJudge: {
                    playerInfo: {
                        id: 3,
                        userName: '用户组测试用户',
                        groupName: '班旗组',
                        workName: '无名称',
                        imageUrl: 'https://i.loli.net/2020/06/01/CEsW6GB7atrqz4u.png',
                        compId: '5'
                    },
                    details: [
                        {minValue: 0, compRubricName: '创意特色', maxValue: 10, accuracy: 1, weight: 1.0, id: 0, value: 0},
                        {minValue: 0, compRubricName: '色彩搭配', maxValue: 10, accuracy: 1, weight: 1.0, id: 0, value: 0},
                        {minValue: 0, compRubricName: '寓意内涵', maxValue: 15, accuracy: 1, weight: 1.0, id: 0, value: 0},
                        {minValue: 0, compRubricName: '现场展示', maxValue: 15, accuracy: 1, weight: 1.0, id: 0, value: 0}
                    ],
                    score: []
                },
                isScoring: true,
                dialogVisible: false,
                networkTestTimeout: null,
                dialogConfirmDisabled: true,
                reverse: false,
                activities: [],
                previewShow: false
            }
        },
        mounted() {
            let that = this;
            that.queryCompetitionImpl();
        },
        methods: {
            queryCompetitionImpl() {
                let that = this;
                that.queryCompetition({cid: this.$route.params.id.substr(2)}, function(data) {
                    that.competitionInfo = data;
                }, that.warningMessage);
            },
            startScoring() {
                let that = this;
                if (screenfull.isEnabled) {
                    screenfull.toggle();
                    this.isScoring = true;
                }
            },
            logoutImpl() {
                let that = this;
                that.$confirm('确定要退出系统吗？', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    that.$router.push({path: '/login'});
                    that.logout(function () {
                        that.successMessage('退出成功');
                    }, that.errorMessage);
                }).catch(() => {});
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
                that.networkTestTimeout = setTimeout(function() {
                    that.activities[0].icon = 'el-icon-check';
                    that.activities[0].type = 'success';
                    that.$set(that.activities, 0, that.activities[0]);
                    that.activities[1].icon = 'el-icon-loading';
                    that.activities[1].type = 'primary';
                    that.$set(that.activities, 1, that.activities[1]);
                    that.networkTestTimeout = setTimeout(function() {
                        that.activities[1].icon = 'el-icon-check';
                        that.activities[1].type = 'success';
                        that.$set(that.activities, 1, that.activities[1]);
                        that.activities[2].icon = 'el-icon-loading';
                        that.activities[2].type = 'primary';
                        that.$set(that.activities, 2, that.activities[2]);
                        that.networkTestTimeout = setTimeout(function() {
                            that.activities[2].icon = 'el-icon-check';
                            that.activities[2].type = 'success';
                            that.$set(that.activities, 2, that.activities[2]);
                            that.activities[3].icon = 'el-icon-check';
                            that.activities[3].type = 'success';
                            that.$set(that.activities, 3, that.activities[3]);
                            that.networkTestTimeout = null;
                            that.dialogConfirmDisabled = false;
                            that.successMessage('系统测试已完成');
                        }, 3000);
                    }, 3000);
                }, 3000);
            },
            cancelNetworkTest() {
                let that = this;
                if (that.networkTestTimeout === null) {
                    return;
                }
                that.warningMessage('正在取消系统测试');
                clearTimeout(that.networkTestTimeout);
                that.networkTestTimeout = null;
                setTimeout(function () {
                    for (let index = 0; index < that.activities.length; index++) {
                        if (that.activities[index].type !== 'success') {
                            that.activities[index].type = 'warning';
                            that.activities[index].icon = 'el-icon-minus';
                            that.$set(that.activities, index, that.activities[index]);
                        }
                    }
                    that.dialogConfirmDisabled = false;
                    that.successMessage('系统测试已取消');
                }, 1500);
            },
            clearScore() {
                for (let index = 0; index < this.currentJudge.details.length; index++) {
                    this.currentJudge.details[index].value = this.currentJudge.details[index].minValue;
                }
            },
            commitScore() {
                let that = this;
                let allMin = true;
                let allMax = true;
                for (let index = 0; index < that.currentJudge.details.length; index++) {
                    allMin &= that.currentJudge.details[index].value === that.currentJudge.details[index].minValue;
                    allMax &= that.currentJudge.details[index].value === that.currentJudge.details[index].maxValue;
                }
                if (allMin || allMax) {
                    that.warningMessage('不能全为最低分或全为最高分');
                } else {
                    that.successMessage("提交成功");
                    that.clearScore();
                }
            },
            closePreview() {
                this.previewShow = false;
            }
        }
    }
</script>

<style lang="scss" scoped>

.scoring-home-container {
    width: 100vw;
    height: 100vh;
    min-width: 700px;
    background-color: #fafafa;
    overflow: auto;

    .scoring-home-box {
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

                ul {
                    list-style-type: disc;
                    padding-left: 20px;
                    li {
                        margin-top: 10px;
                        font-size: 16px !important;
                        list-style: disc;

                        &:first-child {
                            margin-top: 0;
                        }

                        /deep/ .el-link {
                            font-size: 16px;
                            vertical-align: inherit;
                        }
                    }
                }
            }
        }

        .judge-container {
            margin-top: 100px;

            .judge-form-box {
                margin-top: 40px;
            }
        }
    }
}

</style>
