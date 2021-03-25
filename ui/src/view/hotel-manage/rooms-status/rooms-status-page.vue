<template>
  <div>
    <Card>
      <div slot="title">
        <Row>
          <Col span="12">
            <span v-for="item in rooms_status_type">
            <Icon type="ios-radio-button-on" :color="item.color" size="22"/> {{item.name}}
          </span>
          </Col>
          <Col span="12" class="input_info_div">
              <Button type="primary" href="javascript:" @click="inputInfo">读卡录入</Button>
          </Col>
        </Row>
      </div>
      <div class="room_ul">
        <div class="room_li" v-for="item in room_infos">
          <p class="name">
            {{item.name}}
          </p>
          <Button class="room_info" v-for="_item in item.roomInfo"
               :style="{backgroundColor:getBgColor(_item.status)}"
               @click="showDrawer(_item.number)"
          >
            <p class="number">{{_item.number}}</p>
<!--            <p>张三</p>-->
          </Button>
        </div>
      </div>
    </Card>
    <Drawer width="600" v-model="drawer.open">
      <p slot="header">{{drawer.title}}</p>
      <div class="order_info" v-if="drawer.order_info.id">
        <Row>
          <Col span="12">
            <span class="order_label">订单号</span><span>{{drawer.order_info.id}}</span>
          </Col>
          <Col span="12">
            <span class="order_label">房型</span><span>{{drawer.order_info.room_type}}</span>
          </Col>
        </Row>
        <Row>
          <Col span="12">
            <span class="order_label">预定方</span><span>{{drawer.order_info.user}}</span>
          </Col>
          <Col span="12">
            <span class="order_label">房价</span><span>{{drawer.order_info.price}}元</span>
          </Col>
        </Row>
        <Row>
          <Col span="12">
            <span class="order_label">订单来源</span><span>{{drawer.order_info.order_type}}</span>
          </Col>
          <Col span="12">
            <span class="order_label">联系方式</span><span>{{drawer.order_info.contact}}</span>
          </Col>
        </Row>
        <Row>
          <Col span="12">
            <span class="order_label">入住时间</span><span>{{drawer.order_info.in_time}}</span>
          </Col>
          <Col span="12">
            <span class="order_label">离店时间</span><span>{{drawer.order_info.out_time}}</span>
          </Col>
        </Row>
        <Row>
          <Col span="4">
            <span class="order_label">备注信息</span>
          </Col>
          <Col span="20">
            <Input v-model="drawer.order_info.remarks" type="textarea" :autosize="true" readonly />
          </Col>
        </Row>
      </div>
      <div style="height: 100px;line-height: 80px;text-align: center" v-else>
        暂无订单信息
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
          <Table size="small" :columns="drawer.users_info.columns" :data="drawer.users_info.datas">
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
          <Table size="small" :columns="drawer.pay_info.columns" :data="drawer.pay_info.datas">
            <template slot-scope="{ row }" slot="operator">
              {{row.operator.name}}
            </template>
          </Table>
        </Row>
      </div>
    </Drawer>
    <Modal v-modal="inputInfoModal.open">

    </Modal>
  </div>
</template>

<script>
  import {
    getRoomInfoListDTO,
    getOrderByRoomId,

  } from '@/api/data'

  export default {
    name: 'rooms_status',
    components: {},
    data() {
      return {
        drawer:{
          open:false,
          title:'',// 房型+房号,如 豪华大床房-8201
          order_info:{
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
        },
        rooms_status_type: [
          {id: 1, color: '#229453', name: '净房'},
          {id: 2, color: '#f1dfcb', name: '脏房'},
          {id: 3, color: '#ed5a65', name: '租住中'},
          {id: 4, color: '#858585', name: '已停售'},
        ],
        room_infos: [],
        inputInfoModal:{
          open:false,
        }
      }
    },
    methods: {
      getBgColor(key){
        return this.rooms_status_type.filter(e=>e.id===key)[0].color
      },
      showDrawer(roomId){
        this.drawer.open = true;
        this.getOrderByRoomId(roomId);
        console.log(roomId)
      },
      inputInfo(){
        this.inputInfoModal=true;

      },
      show(r){

      },
      remove(r){

      },
      getRoomInfoListDTO(){
        getRoomInfoListDTO().then(res=>{
          this.room_infos = res.data.result
        })
      },
      getOrderByRoomId(roomId){
        //todo:通过房间ID查询订单信息、住客信息

      },
      getPayInfoByRoomId(){
        // todo: 流水账信息,做不来可删除
      }
    },
    mounted() {
      //todo：获取房间类型等
      this.getRoomInfoListDTO();

    }
  }
</script>

<style>

  .type-tip span {
    margin-right: 10px;
  }
  .input_info_div{
    float: right;
    text-align: right;
    padding-right: 20px;
  }

  .name{
    font-size: 20px;
    margin: 10px 0;
  }
  .room_info{
    width: 128px;
    height: 70px;
    margin: 5px;
    border-radius: unset;
    display: inline-block;
  }
  .number{
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
