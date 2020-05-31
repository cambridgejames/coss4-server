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
            <el-card style="margin-top: 40px;">{{competitionInfo}}</el-card>
        </div>
        <div v-else>
            scoring
        </div>
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
                isScoring: false
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
    }
}

</style>
