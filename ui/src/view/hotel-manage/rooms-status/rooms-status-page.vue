<template>
  <div>
    <Card>
      <div slot="title">
        <div class="type-tip">
          <span v-for="item in rooms_status_type">
            <Icon type="ios-radio-button-on" :color="item.color" size="22"/> {{item.name}}
          </span>
        </div>
      </div>
      <div class="room_ul">
        <div class="room_li" v-for="item in room_infos">
          <p class="room_type_name">
            {{item.room_type_name}}
          </p>
          <Button class="room_info" v-for="_item in item.datas"
               :style="{backgroundColor:getBgColor(_item.status)}"
               @click="showDrawer"
          >
            <span class="room_number">{{_item.room_number}}</span>
          </Button>
        </div>
      </div>
    </Card>
    <Drawer width="600" v-model="openDrawer">
      <p slot="header">{{drawerInfo.header}}</p>
      <div class="order_info">
        <Row>
          <Col span="12">
            <span class="order_label">订单号</span><span>{{drawerInfo.order_info.id}}</span>
          </Col>
          <Col span="12">
            <span class="order_label">房型</span><span>{{drawerInfo.order_info.room_type}}</span>
          </Col>
        </Row>
        <Row>
          <Col span="12">
            <span class="order_label">预定方</span><span>{{drawerInfo.order_info.user}}</span>
          </Col>
          <Col span="12">
            <span class="order_label">房价</span><span>{{drawerInfo.order_info.price}}元</span>
          </Col>
        </Row>
        <Row>
          <Col span="12">
            <span class="order_label">订单来源</span><span>{{drawerInfo.order_info.order_type}}</span>
          </Col>
          <Col span="12">
            <span class="order_label">联系方式</span><span>{{drawerInfo.order_info.contact}}</span>
          </Col>
        </Row>
        <Row>
          <Col span="12">
            <span class="order_label">入住时间</span><span>{{drawerInfo.order_info.in_time}}</span>
          </Col>
          <Col span="12">
            <span class="order_label">离店时间</span><span>{{drawerInfo.order_info.out_time}}</span>
          </Col>
        </Row>
        <Row>
          <Col span="4">
            <span class="order_label">备注信息</span>
          </Col>
          <Col span="20">
            <Input v-model="drawerInfo.order_info.remarks" type="textarea" :autosize="true" readonly />
          </Col>
        </Row>
      </div>
      <Divider/>
      <div class="user_info">
        <Row>
          <Col span="12">
            <span style="font-size: 15px;font-weight: bold">住客信息</span>
          </Col>
          <Col span="12">
          </Col>
        </Row>
        <Row>
          <Table size="small" :columns="drawerInfo.users_info.columns" :data="drawerInfo.users_info.datas">
            <template slot-scope="{ row }" slot="name">
              <strong>{{ row.name }}</strong>
            </template>
            <template slot-scope="{ row }" slot="sex">
              {{ row.sex===1 ? '男':'女'}}
            </template>
            <template slot-scope="{ row, index }" slot="action">
              <Button type="primary" size="small" style="margin-right: 5px" @click="show(index)">View</Button>
              <Button type="error" size="small" @click="remove(index)">Delete</Button>
            </template>
          </Table>
        </Row>
      </div>
      <Divider/>
      <div class="consume_info">
        <Row>
          <Col span="12">
            <span style="font-size: 15px;font-weight: bold">消费明细</span>
          </Col>
          <Col span="12">
          </Col>
        </Row>
        <Row>
          <Table size="small" :columns="drawerInfo.pay_info.columns" :data="drawerInfo.pay_info.datas">
            <template slot-scope="{ row }" slot="operator">
              {{row.operator.name}}
            </template>
          </Table>
        </Row>
      </div>
    </Drawer>
  </div>
</template>

<script>
  import {getTableData} from '@/api/data'

  export default {
    name: 'rooms_status',
    components: {},
    data() {
      return {
        openDrawer:false,
        rooms_status_type: [
          {id: 1, color: '#229453', name: '净房'},
          {id: 2, color: '#f1dfcb', name: '脏房'},
          {id: 3, color: '#ed5a65', name: '租住中'},
          {id: 4, color: '#858585', name: '已停售'},
        ],
        room_infos: [
          {
            room_type_id: 1001,
            room_type_name: '豪华大床房',
            datas: [
              {
                room_number: 201,
                status: 1,
              },
              {
                room_number: 202,
                status: 2,
              },
              {
                room_number: 203,
                status: 3,
              },
              {
                room_number: 204,
                status: 4,
              }
            ]
          },
          {
            room_type_id: 1002,
            room_type_name: '豪华双人大床房',
            datas: [
              {
                room_number: 301,
                status: 1,
              },
              {
                room_number: 302,
                status: 2,
              },
              {
                room_number: 303,
                status: 3,
              },
              {
                room_number: 304,
                status: 3,
              }
            ]
          },
        ],
        drawerInfo:{
          header:'房间；',
          order_info:{
            // 订单号构成，下单日期+4位酒店编号+4位当天订单数
            id:2021031200000001,
            user:'张三',//下单方
            price:288,
            contact:13612345678,
            room_type:'豪华大床房',
            order_type:'美团',//订单类型
            in_time:'2021-03-15 15:00:08',
            out_time:'2021-03-16 12:00:00',
            remarks:'这是订单备注',
          },
          // 住户信息
          users_info:{
            columns:[
              {
                title: '名称',
                slot: 'name'
              },
              {
                title: '证件号码',
                key: 'identity',
                width: 160
              },
              {
                title: '性别',
                slot: 'sex',
              },
              {
                title: '联系方式',
                key: 'contact',
                width: 110
              },
              {
                title: '操作',
                slot: 'action',
                width: 140,
                align: 'center'
              }
            ],
            datas:[
              {id:1,name:'张三',identity:440000200001010919,sex:1,contact:13612345678},
              {id:1,name:'李四',identity:440000200001010919,sex:0,contact:13612345678},
            ]
          },
          // 消费明细
          pay_info:{
            columns:[
              {
                title: '入账方式',
                key: 'pay_method'
              },
              {
                title: '金额',
                key: 'money',
              },
              {
                title: '时间',
                key: 'time',
                width:150
              },
              {
                title: '操作员',
                slot: 'operator',
              }
            ],
            datas:[
              {
                pay_method:'现金',
                money:200,
                time:'2021-03-15 15:00:08',
                operator:{
                  id:1001,
                  name:'邑大'
                },
                remarks:''
              }
            ]
          }
        }
      }
    },
    methods: {
      getBgColor(key){
        return this.rooms_status_type.filter(e=>e.id===key)[0].color
      },
      showDrawer(){
        this.openDrawer = true;
      },
      show(r){

      },
      remove(r){

      }
    },
    mounted() {
      //todo：获取房间类型等
    }
  }
</script>

<style>
  .type-tip {
    width: 500px;
  }

  .type-tip span {
    margin-right: 10px;
  }
  .room_type_name{
    font-size: 20px;
    margin: 10px 0;
  }
  .room_info{
    width: 128px;
    height: 70px;
    margin: 5px;
    display: inline-block;
  }
  .room_number{
    font-size: 16px;
    color: black;
    margin: 4px;
    padding: 0 3px;
  }
  .user_in_room{

  }
  .order_info .ivu-col{
    margin-bottom: 10px;
  }
  .order_label{
    margin: 0 10px;
  }

</style>
