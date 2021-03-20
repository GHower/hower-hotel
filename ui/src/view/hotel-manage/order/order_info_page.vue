<template>
  <div>
    <Card>
      <div slot="title">
        订单管理
      </div>
      <div class="">
        <div class="search-con search-con-top">
          <Select v-model="searchKey" class="search-col">
            <Option v-for="item in columns" v-if="item.key !== 'action'" :value="item.key"
                    :key="item.key">{{ item.title }}
            </Option>
          </Select>
          <Input @on-change="handleClear" placeholder="输入关键字搜索" class="search-input" v-model="searchValue"/>
          <Button @click="handleSearch" class="search-btn" type="primary">
            <Icon type="search"/>&nbsp;&nbsp;搜索
          </Button>
        </div>

        <Table :loading="loading" :columns="columns" :data="orders" stripe>
          <template slot-scope="{ row }" slot="id">
            <span>{{row.id}}</span>
          </template>
          <template slot-scope="{ row }" slot="status">
            {{statusToCN[row.status]}}
          </template>
          <template slot-scope="{ row,index }" slot="action">
            <Button type="primary" size="small" style="margin-right: 5px" @click="show(index)">修改</Button>
            <Button type="error" size="small" @click="remove(index)">删除</Button>
          </template>
        </Table>
        <div style="margin: 10px;overflow: hidden">
          <div style="float: right;">
            <Page :total="100" :current="1"
                  show-elevator
                  @on-change="changePage"></Page>
          </div>
        </div>
      </div>
    </Card>
  </div>
</template>

<script>
  export default {
    name: 'order_info',
    components: {},
    data() {
      return {
        columns: [
          {
            key:'id',
            slot: 'id',
            title: '订单号',
            width: 160
          },
          {
            key: 'source',
            title: '来源',
            width: 80
          },
          {
            key: 'rType',
            title: '房型',
            width: 160
          },
          {
            key: 'price',
            title: '房价',
            width: 60
          },
          {
            key: 'cName',
            title: '预订人',
            width: 80
          },
          {
            key: 'inTime',
            title: '入住时间',
            width: 160
          },
          {
            key: 'outTime',
            title: '离店时间',
            width: 160
          },
          {
            key: 'contact',
            title: '联系方式',
          },
          {
            key:'status',
            slot: 'status',
            title: '状态',
            width: 80
          },
          {
            key:'action',
            title: '操作',
            slot: 'action',
            width: 140,
            align: 'center'
          }
        ],
        orders: [
          {
            id: '2021031200000202',
            source: '美团',
            rType: '豪华大床房',
            price: 288,
            cName: '张三',
            inTime: '2021-03-12 12:00:00',
            outTime: '2021-03-14 13:00:00',
            contact: 13612345678,
            status: 1
          }
        ],
        statusToCN: {
          1: '有效',
          2: '待入住',
          3: '入住中',
          4: '已取消'
        },
        openModal: false,
        loading: false,
        searchKey: '',
        searchValue: '',

      }
    },
    methods: {
      changePage(p) {
        //TODO: p；页码
        console.log(p, '页')
      },
      show(index) {
        console.log(index)
      },
      remove(index) {

      },
      handleSearch() {
        console.log(this.value)
      },
      handleClear(e) {

      },
      setDefaultSearchKey(){
        this.searchKey = this.columns[0].key !== 'action' ? this.columns[0].key : (this.columns.length > 1 ? this.columns[1].key : '')
      }
    },
    watch: {
      columns (columns) {
        // this.handleColumns(columns)
        this.setDefaultSearchKey()
      },
      value (val) {
        // this.handleTableData()
        // if (this.searchable) this.handleSearch()
      },
    },
    mounted() {
      // todo:获取订单的信息
      this.setDefaultSearchKey()
    }
  }
</script>

<style lang="less">
  .ivu-table-overflowX::-webkit-scrollbar {
    height: 0;
  }

  .search-con {
    padding: 10px 0;

    .search {
      &-col {
        display: inline-block;
        width: 200px;
      }

      &-input {
        display: inline-block;
        width: 200px;
        margin-left: 2px;
      }

      &-btn {
        margin-left: 2px;
      }
    }
  }
</style>
