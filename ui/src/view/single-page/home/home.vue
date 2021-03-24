<template>
  <div>
<!--    <Row :gutter="20">-->
<!--      <i-col :xs="12" :md="8" :lg="6" v-for="(infor, i) in inforCardData" :key="`infor-${i}`" style="height: 120px;padding-bottom: 10px;">-->
<!--        <infor-card shadow :color="infor.color" :icon="infor.icon" :icon-size="36">-->
<!--          <count-to :end="infor.count" count-class="count-style"/>-->
<!--          <p>{{ infor.title }}</p>-->
<!--        </infor-card>-->
<!--      </i-col>-->
<!--    </Row>-->
    <Row :gutter="20" style="margin-top: 10px;">
      <i-col :md="24" :lg="8" style="margin-bottom: 20px;">
        <Card shadow>
          <chart-pie style="height: 300px;" :value="pieData" text="订单收入来源"></chart-pie>
        </Card>
      </i-col>
      <i-col :md="24" :lg="16" style="margin-bottom: 20px;">
        <Card shadow>
          <chart-bar style="height: 300px;" :value="barData" text="房型销量"/>
        </Card>
      </i-col>
    </Row>
    <Row>
      <Card shadow>
        <example style="height: 310px;" text="近半年收入情况"/>
      </Card>
    </Row>
  </div>
</template>

<script>
import InforCard from '_c/info-card'
import CountTo from '_c/count-to'
import { ChartPie, ChartBar } from '_c/charts'
import Example from './example.vue'
export default {
  name: 'home',
  components: {
    InforCard,
    CountTo,
    ChartPie,
    ChartBar,
    Example
  },
  data () {
    return {
      inforCardData: [
        { title: '新增用户', icon: 'md-person-add', count: 803, color: '#2d8cf0' },
        { title: '累计点击', icon: 'md-locate', count: 232, color: '#19be6b' },
        { title: '新增问答', icon: 'md-help-circle', count: 142, color: '#ff9900' },
        { title: '分享统计', icon: 'md-share', count: 657, color: '#ed3f14' },
        // { title: '新增互动', icon: 'md-chatbubbles', count: 12, color: '#E46CBB' },
        // { title: '新增页面', icon: 'md-map', count: 14, color: '#9A66E4' }
      ],
      pieData: [
        { value: 310, name: '到店现付' },
        { value: 234, name: '携程旅行' },
        { value: 135, name: '去哪儿旅行' },
        { value: 1548, name: '美团' }
      ],
      barData: {
        '豪华大床房': 13253,
        '豪华双床房': 34235,
        '浪漫情侣房': 26321,
        '豪华圆床房': 12340,
        '豪华大床房': 24643,
        '舒适三人房': 1322,
        '豪华海景房': 1324
      }
    }
  },
  mounted () {
    //
  }
}
</script>

<style lang="less">
.count-style{
  font-size: 50px;
}
</style>
