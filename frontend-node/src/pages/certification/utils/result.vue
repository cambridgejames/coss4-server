<template>
    <div id="certification-result-container" class="certification-result-container">
        <el-card>
            <div slot="header">竞赛信息</div>
            <div style="line-height: 30px; font-size: 16px;">
                <p>
                    <span style="font-weight: bold;">竞赛名称：</span>{{competitionInfo.compName}}
                    <el-tag size="small" :type="competitionInfo.tagMode.type" style="margin-left: 10px;">
                        {{competitionInfo.tagMode.content}}
                    </el-tag>
                </p>
                <p>
                    <span style="font-weight: bold;">创&ensp;建&ensp;人：</span>{{competitionInfo.nickname}}
                    <span style="color: #999; margin-left: 20px;">UID:{{competitionInfo.userId}}</span>
                </p>
                <p><span style="font-weight: bold;">创建时间：</span>{{formatDateTime(new Date(competitionInfo.createTime))}}</p>
                <p><span style="font-weight: bold;">开始时间：</span>{{formatDateTime(new Date(competitionInfo.startTime))}}</p>
            </div>
        </el-card>
        <el-card style="margin-top: 40px;">
            <div slot="header">成绩信息</div>
            <div class="score-input-box">
                <el-input v-model="result.number" placeholder="请输入证书编号" :clearable="true"
                          @keyup.enter.native="scoreVerification"></el-input>
                <el-popover placement="bottom-end" width="150" trigger="hover">
                    <div class="qr-code-uploader" @click="qrCodeSelect">
                        <div v-if="!haveFile" class="uploader-choose-file">
                            <i class="el-icon-upload2 qr-code-uploader-icon"></i>
                            <span class="qr-code-uploader-title">点击上传二维码</span>
                        </div>
                        <div v-else class="uploader-loading">
                            <i class="el-icon-loading qr-code-uploader-icon"></i>
                        </div>
                        <input class="qr-code-uploader-input" ref="qrCode" type="file" accept="image/*"
                               @change="qrCodeUpload"/>
                    </div>
                    <el-button slot="reference" class="input-button"
                               :icon="haveFile ? 'el-icon-loading' : 'el-icon-full-screen'"></el-button>
                </el-popover>
                <el-button type="primary" icon="el-icon-search" class="input-button"
                           @click="scoreVerification"></el-button>
            </div>
            <div v-if="result.src.length !== 0 || pdfLoading" class="score-pdf-viewer" v-loading="pdfLoading"
                 element-loading-text="加载中" element-loading-spinner="el-icon-loading" element-loading-background="rgba(0, 0, 0, 0.6)">
                <iframe :src="result.src" frameborder="0"></iframe>
            </div>
            <div v-else class="score-none-box">暂无信息</div>
        </el-card>
    </div>
</template>

<script>
    import baseInformation from "../../../assets/js/api/competitionManagement/baseInformation";
    import message from "../../../assets/js/message";
    import  format from  "../../../assets/js/format";
    import jspdf from "../../../assets/js/pdf/jspdf";

    export default {
        name: "result",
        mixins: [message, format, baseInformation, jspdf],
        data() {
            return {
                competitionInfo: {
                    tagMode: {}
                },
                result: {
                    number: '',
                    src: '',
                    information: {}
                },
                haveFile: false,
                pdfLoading: false
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
            qrCodeSelect() {
                if (!this.haveFile) {
                    this.$refs.qrCode.dispatchEvent(new MouseEvent('click'));
                }
            },
            qrCodeUpload() {
                let that = this;
                that.haveFile = true;
                setTimeout(function () {
                    that.haveFile = false;
                    that.result.number = '2346DF72-13AF-47E3-89B9-9fE1F8E37A1D';
                    that.scoreVerification();
                }, 1000);
            },
            scoreVerification() {
                let that = this;
                if (!this.result.number || this.result.number.length === 0) {
                    this.warningMessage('请输入证书编号');
                    return;
                } else if (this.result.number.length !== 36) {
                    this.warningMessage('请输入正确的证书编号');
                    return;
                }
                let pattern = /\w{8}(-\w{4}){3}-\w{12}/g;   // 使用政策表达式校验编号是否是UUID格式
                if (pattern.test(this.result.number)) {
                    // TODO: 调接口查询成绩认证信息
                    this.pdfLoading = true;
                    that.result.src = '';
                    setTimeout(function () {
                        that.result.src = that.createPdf(that.competitionInfo) + '#view=fit';
                        that.pdfLoading = false;
                    }, 2000);
                } else {
                    this.warningMessage('请输入正确的证书编号');
                }
            }
        }
    }
</script>

<style lang="scss" scoped>

.certification-result-container {
    max-width: 1200px;
    margin: 40px auto;

    .score-input-box {
        display: flex;
        flex-direction: row;
        padding-bottom: 20px;
        border-bottom: 1px solid #eee;
        margin-bottom: 20px;
        .input-button {
            margin-left: 10px;
        }
    }

    .score-pdf-viewer {
        width: 100%;
        height: 1000px;
        iframe {
            width: 100%;
            height: 100%;
        }
    }

    .score-none-box {
        margin: 70px 0;
        text-align: center;
        font-size: 14px;
        color: #999;
    }
}

.qr-code-uploader {
    width: 150px;
    height: 150px;
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
    text-align: center;
    color: #ccc;
    .uploader-choose-file {
        margin-top: 40px;
        .qr-code-uploader-icon {
            display: block;
            font-size: 28px;
        }
        .qr-code-uploader-title {
            display: block;
            margin-top: 10px;
            font-size: 12px;
        }
    }
    .uploader-loading {
        margin-top: 56px;
        .qr-code-uploader-icon {
            display: block;
            font-size: 34px;
        }
    }
    .qr-code-uploader-input {
        display: none;
    }
    &:hover {
        border-color: #409EFF;
        color: #409EFF;
    }
}

</style>
