<template>
  <div>
    <Card>
      <div slot="title">
        订单管理
      </div>
      <div class="">
<!--        <div class="search-con search-con-top">-->
<!--          <Select v-model="searchKey" class="search-col">-->
<!--            <Option v-for="item in columns" v-if="item.key !== 'action'" :value="item.key"-->
<!--                    :key="item.key">{{ item.title }}-->
<!--            </Option>-->
<!--          </Select>-->
<!--          <Input @on-change="handleClear" placeholder="输入关键字搜索" class="search-input" v-model="searchValue"/>-->
<!--          <Button @click="handleSearch" class="search-btn" type="primary">-->
<!--            <Icon type="search"/>&nbsp;&nbsp;搜索-->
<!--          </Button>-->
<!--        </div>-->

        <Table :loading="loading" :columns="columns" :data="orders" stripe>
          <template slot-scope="{ row }" slot="id">
            <span>{{row.orderInfo.id}}</span>
          </template>
          <template slot-scope="{ row }" slot="source">
            <span>{{row.orderInfo.source}}</span>
          </template>
          <template slot-scope="{ row }" slot="rType">
            <span>{{row.roomType}}</span>
          </template>
          <template slot-scope="{ row }" slot="status">
            {{statusToCN[row.orderInfo.status]}}
          </template>
          <template slot-scope="{ row }" slot="cName">
            {{row.customerInfo.name}}
          </template>
          <template slot-scope="{ row }" slot="contact">
            {{row.customerInfo.contact}}
          </template>
          <template slot-scope="{ row }" slot="inTime">
            {{convertTimeFormat(row.orderInfo.inTime)}}
          </template>
          <template slot-scope="{ row }" slot="outTime">
            {{convertTimeFormat(row.orderInfo.outTime)}}
          </template>
          <template slot-scope="{ row,index }" slot="action">
            <Button type="primary" size="small" @click="show(row,index)">查看详情</Button>
          </template>
        </Table>
        <div style="margin: 10px;overflow: hidden">
          <div style="float: right;">
            <Page :total="page.total" :current="page.current"
                  show-elevator
                  @on-change="changePage"></Page>
          </div>
        </div>
      </div>
      <Modal v-model="Modal.open" title="订单详情">
        <div class="order_info">
          <Row>
            <Col span="12">
              <span class="order_label">订单号</span><span>{{Modal.detail.orderInfo.id}}</span>
            </Col>
            <Col span="12">
              <span class="order_label">房型</span><span>{{Modal.detail.roomType}}</span>
            </Col>
          </Row>
          <Row>
            <Col span="12">
              <span class="order_label">预定方</span><span>{{Modal.detail.customerInfo.name}}</span>
            </Col>
            <Col span="12">
              <span class="order_label">订单状态</span><span>{{Modal.detail.price}}元</span>
            </Col>
          </Row>
          <Row>
            <Col span="12">
              <span class="order_label">订单来源</span><span>{{Modal.detail.orderInfo.source}}</span>
            </Col>
            <Col span="12">
              <span class="order_label">联系方式</span><span>{{Modal.detail.customerInfo.contact}}</span>
            </Col>
          </Row>
          <Row>
            <Col span="12">
              <span class="order_label">入住时间</span><span>{{convertTimeFormat(Modal.detail.orderInfo.inTime)}}</span>
            </Col>
            <Col span="12">
              <span class="order_label">离店时间</span><span>{{convertTimeFormat(Modal.detail.orderInfo.outTime)}}</span>
            </Col>
          </Row>
          <Row>
            <Col span="4">
              <span class="order_label">备注信息</span>
            </Col>
            <Col span="20">
              <Input v-model="Modal.detail.orderInfo.remarks" row="5" type="textarea" :autosize="true" readonly/>
            </Col>
          </Row>
        </div>
      </Modal>
    </Card>
  </div>
</template>

<script>
  import {
    getOrderPage
  } from "@/api/data"

  export default {
    name: 'order_info',
    components: {},
    data() {
      return {
        columns: [
          {
            key: 'id',
            slot: 'id',
            title: '订单号',
            width: 140
          },
          {
            key: 'source',
            slot: 'source',
            title: '来源',
          },
          {
            key: 'rType',
            slot: 'rType',
            title: '房型',
          },
          // {
          //   key: 'price',
          //   title: '房价',
          //   width: 60
          // },
          {
            key: 'cName',
            slot: 'cName',
            title: '预订人',
          },
          {
            key: 'inTime',
            slot: 'inTime',
            title: '入住时间',
            width: 160
          },
          {
            key: 'outTime',
            slot: 'outTime',
            title: '离店时间',
            width: 160
          },
          {
            key: 'contact',
            slot: 'contact',
            title: '联系方式',
            width: 120
          },
          {
            key: 'status',
            slot: 'status',
            title: '状态',
            width: 80
          },
          {
            key: 'action',
            slot: 'action',
            title: '操作',
          }
        ],
        orders: [],
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
        page:{
          current: 1,
          pageSize: 10,
          total: 10,
          isSearch: false,
        },
        Modal: {
          open: false,
          detail: {
            orderInfo: {},
            customerInfo: {},
            roomInfo: {},
            roomType: '',
          },
        }
      }
    },
    methods: {
      changePage(p) {
        //TODO: p；页码
        console.log(p, '页')
      },
      show(row, index) {
        this.Modal.open = true;
        this.Modal.detail = row;
      },
      handleSearch() {
        console.log(this.searchKey);
        console.log(this.searchValue);
        this.getOrderPage({

        }).then(res=>{
          console.log()
        })
      },
      handleClear(e) {

      },
      convertTimeFormat(timeArray) {
        if (timeArray && timeArray.length === 6) {
          return timeArray[0] + "-"
            + timeArray[1] + "-"
            + timeArray[2] + " "
            + timeArray[3] + ":"
            + timeArray[4] + ":"
            + timeArray[5];
        }else{
          return "暂无信息";
        }
      },
      setDefaultSearchKey() {
        this.searchKey = this.columns[0].key !== 'action' ? this.columns[0].key : (this.columns.length > 1 ? this.columns[1].key : '')
      },
      getOrderPage(option) {
        getOrderPage({
          ...option
        }).then(res => {
          this.orders = res.data.result["records"];
          this.page.current= res.data.result["current"];
          this.page.total= res.data.result["total"];
        })
      },
    },
    watch: {
      columns(columns) {
        this.setDefaultSearchKey()
      },
      value(val) {
      },
    },
    mounted() {
      this.setDefaultSearchKey();
      this.getOrderPage();
    }
  }
</script>

<style lang="less">
  .ivu-table-overflowX::-webkit-scrollbar {
    height: 0;
  }

  .order_info {
    .order_label {
      margin: 0 10px;
    }

    .ivu-col {
      margin-bottom: 10px;
    }
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
