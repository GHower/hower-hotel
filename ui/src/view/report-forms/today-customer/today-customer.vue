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
  export default {
    name: "today-customer",
    components: {},
    data(){
      return{
        tableTitle:[

        ],
        tableData:[

        ],
        exportLoading:false,

      }
    },
    methods:{
      exportExcel () {
        if (this.tableData.length) {
          this.exportLoading = true;
          const params = {
            title: ['一级分类', '二级分类', '三级分类'],
            key: ['category1', 'category2', 'category3'],
            data: this.tableData,
            autoWidth: true,
            filename: '分类列表'
          };
          excel.export_array_to_excel(params)
          this.exportLoading = false
        } else {
          this.$Message.info('打印报表为空！')
        }
      },
      getTodayCustomer(){

      }
    },
    watch:{

    },
    mounted() {
    },

  }
</script>


