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
                  @click="showDrawer(_item)"
          >
            <p class="number">{{_item.number}}</p>
            <!--            <p>张三</p>-->
          </Button>
        </div>
      </div>
    </Card>
    <Drawer width="600" v-model="drawer.open" @on-close="closeDrawer">
      <p slot="header" style="font-size: 18px;text-align: center">
        {{drawer.title}}
      </p>
      <div class="order_info" v-if="drawer.order_info.orderInfo.id">
        <Row>
          <Col span="12">
            <span class="order_label">订单号</span><span>{{drawer.order_info.orderInfo.id}}</span>
          </Col>
          <Col span="12">
            <span class="order_label">房型</span><span>{{drawer.order_info.roomType}}</span>
          </Col>
        </Row>
        <Row>
          <Col span="12">
            <span class="order_label">预定方</span><span>{{drawer.order_info.customerInfo.name}}</span>
          </Col>
          <Col span="12">
            <span class="order_label">房价</span><span>{{drawer.order_info.price}}元</span>
          </Col>
        </Row>
        <Row>
          <Col span="12">
            <span class="order_label">订单来源</span><span>{{drawer.order_info.orderInfo.source}}</span>
          </Col>
          <Col span="12">
            <span class="order_label">联系方式</span><span>{{drawer.order_info.customerInfo.contact}}</span>
          </Col>
        </Row>
        <Row>
          <Col span="12">
            <span class="order_label">入住时间</span><span>{{convertTimeFormat(drawer.order_info.orderInfo.inTime)}}</span>
          </Col>
          <Col span="12">
            <span class="order_label">离店时间</span><span>{{convertTimeFormat(drawer.order_info.orderInfo.outTime)}}</span>
          </Col>
        </Row>
        <Row>
          <Col span="3">
            <span class="order_label">备注信息</span>
          </Col>
          <Col span="20">
            <Input v-model="drawer.order_info.orderInfo.remarks" type="textarea" :autosize="true" readonly/>
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
          <Table size="small" :columns="drawer.columns" :data="drawer.order_info.guests">
            <template slot-scope="{ row }" slot="name">
              <strong>{{ row.name }}</strong>
            </template>
            <template slot-scope="{ row }" slot="sex">
              {{ row.sex===1 ? '男':'女'}}
            </template>
            <template slot-scope="{ row, index }" slot="action">
              <Button type="error" size="small" @click="remove(index)">删除</Button>
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
            <template slot-scope="{ row }" slot="createTime">
              {{convertTimeFormat(row.createTime)}}
            </template>
            <template slot-scope="{ row }" slot="staffInfo">
              {{row.staffInfo.name}}
            </template>
          </Table>
        </Row>
      </div>
    </Drawer>
    <Modal v-model="inputInfoModal.open">

    </Modal>
  </div>
</template>

<script>
  import {convertTimeFormat} from "@/libs/util";
  import {
    getRoomInfoListDTO,
    getOrderByRoom,
    getOrderPayInfo,

  } from '@/api/data'

  export default {
    name: 'rooms_status',
    components: {},
    data() {
      return {
        drawer: {
          open: false,
          title: '',// 房型+房号,如 豪华大床房-8201
          order_info: {
            orderInfo: {},
            customerInfo: {},
            roomInfo: {},
            guests: [],
            roomType: '',
            staffInfo: {},
          },
          columns: [
            {
              title: '证件号码',
              key: 'id',
              width: 160
            },
            {
              title: '名称',
              slot: 'name'
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
          pay_info: {
            columns: [
              {
                title: '入账方式',
                key: 'payWay'
              },
              {
                title: '金额',
                key: 'money',
              },
              {
                title: '时间',
                key: 'createTime',
                slot:'createTime',
                width: 150
              },
              {
                title: '操作员',
                slot: 'staffInfo',
              }
            ],
            datas: [
              {
                id:'',
                payWay: '现金',
                money: 200,
                createTime: '2021-03-15 15:00:08',
                staffInfo: {
                  id: 1001,
                  name: '邑大'
                },
              }
            ]
          },
        },
        rooms_status_type: [
          {id: 1, color: '#229453', name: '净房'},
          {id: 2, color: '#f1dfcb', name: '脏房'},
          {id: 3, color: '#ed5a65', name: '租住中'},
          {id: 4, color: '#858585', name: '已停售'},
        ],
        room_infos: [],
        inputInfoModal: {
          open: false,
        }
      }
    },
    methods: {
      getBgColor(key) {
        return this.rooms_status_type.filter(e => e.id === key)[0].color
      },
      showDrawer(room) {
        this.drawer.open = true;
        console.log(room);
        this.drawer.title = this.room_infos.filter(e => e.id === room.typeId)[0].name;
        this.drawer.title += " - " + room.number;
        console.log(this.drawer.title);
        this.getOrderByRoomId(room);
      },
      closeDrawer() {
        this.resetDrawerData();
        this.drawer.open = false;
      },
      resetDrawerData() {
        this.drawer.title = '';
        this.drawer.order_info = {
          orderInfo: {},
          customerInfo: {},
          roomInfo: {},
          guests: [],
          roomType: '',
          staffInfo: {},
        };

      },
      inputInfo() {
        this.inputInfoModal = true;
      },
      convertTimeFormat(timeArray) {
        return convertTimeFormat(timeArray);
      },
      remove(r) {
        //TODO:删除住客
      },
      getRoomInfoListDTO() {
        getRoomInfoListDTO().then(res => {
          this.room_infos = res.data.result
        })
      },
      getOrderByRoomId(room) {
        getOrderByRoom({
          id: room.id
        }).then(res => {
          console.log(res.data.result);
          this.drawer.order_info = res.data.result;
          if(this.drawer.order_info!==null){
            this.getOrderPayInfo(this.drawer.order_info.orderInfo.id);
          }
        }).catch(err => {
        })
      },
      getOrderPayInfo(oId) {
        getOrderPayInfo({
          oId:oId
        }).then(res=>{
          this.drawer.pay_info.datas = res.data.result;
          console.log(res.data.result)
        })
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

  .input_info_div {
    float: right;
    text-align: right;
    padding-right: 20px;
  }

  .name {
    font-size: 20px;
    margin: 10px 0;
  }

  .room_info {
    width: 128px;
    height: 70px;
    margin: 5px;
    border-radius: unset;
    display: inline-block;
  }

  .number {
    font-size: 16px;
    color: black;
    margin: 4px;
    padding: 0 3px;
  }

  .user_in_room {

  }

  .order_info .ivu-col {
    margin-bottom: 10px;
  }

  .order_label {
    margin: 0 10px;
  }

</style>
