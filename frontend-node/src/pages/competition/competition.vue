<template>
    <div id="competition-index-container" class="competition-container">
        <div class="banner-container"></div>
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
                <div class="list-condition">
                    <span class="condition-span">排序</span>
                    <div class="condition-item">
                        <span v-for="item in sort">{{item.title}}</span>
                    </div>
                </div>
            </el-card>
            <el-card class="list-result"></el-card>
        </div>
    </div>
</template>

<script>
    export default {
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
                ]
            }
        }
    }
</script>

<style lang="scss" scoped>

@import "../../assets/scss/main-list";

.competition-container {
    .banner-container {
        height: 400px;
        background-color: cornflowerblue;
    }
    .list-container {
        max-width: 1200px;
        margin: -40px auto 40px auto;
    }
}

</style>
