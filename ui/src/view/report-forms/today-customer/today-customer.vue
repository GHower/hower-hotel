<style lang="less">
  @import url('../common.less');
</style>
<template>
  <Card>
    <div slot="title">
      <Row>
        <i-col span="20">
          <b style="font-size: 18px">今日住客报表</b>
        </i-col>
        <i-col span="4" style="text-align: right">
          <Button type="primary" icon="md-print" :loading="exportLoading" @click="exportExcel">打印</Button>
        </i-col>
      </Row>
    </div>
    <div class="">
      <Table :columns="tableTitle" :data="tableData">

      </Table>
    </div>
  </Card>
</template>

<script>
  import excel from '@/libs/excel'
  import {getOrderToday} from '@/api/data'

  export default {
    name: "today-customer",
    components: {},
    data() {
      return {
        tableTitle: [
          {
            key: 'roomType',
            title: '房型'
          },
          {
            key: 'number',
            title: '房间号'
          },
          {
            key: 'name',
            title: '住客姓名',
          },
          {
            key: 'contact',
            title: '联系方式',
          },
          {
            key: 'sex',
            title: '性别',
          },
          {
            key: 'id',
            title: '证件号码',
          },
        ],
        tableData: [],
        exportLoading: false,
      }
    },
    methods: {
      exportExcel() {
        if (this.tableData.length) {
          this.exportLoading = true;
          let data_str = new Date().toLocaleDateString().replace(/\//g,"-")

          const params = {
            title: ['房型', '房间号', '住客姓名', '联系方式', '性别', '证件号码'],
            key: ['roomType', 'number', 'name', 'contact', 'sex', 'id'],
            data: this.tableData,
            autoWidth: true,
            filename: '住客报表('+data_str+')'
          };
          excel.export_array_to_excel(params)
          this.exportLoading = false
        } else {
          this.$Message.info('打印报表为空！')
        }
      },
      getTodayCustomer() {
        getOrderToday({}).then(res => {
          const result = res.data.result;
          console.log(result);
          let data = [];
          result.forEach(e => {
            e.guests.forEach(g => {
              data.push({
                ...g,
                roomType: e.roomType,
                number: e.roomInfo.number,
                sex: g.sex === 1 ? '男' : '女',
              })
            })
          });
          this.tableData = data;
        }).catch(err => {
        })
      }
    },
    watch: {},
    mounted() {
      this.getTodayCustomer();
    },

  }
</script>


