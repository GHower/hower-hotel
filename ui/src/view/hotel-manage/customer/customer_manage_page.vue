<template>
  <div>
    <Card>
      <div slot="title">
        会员管理
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
        <Table :loading="loading" :columns="columns" :data="customer" stripe>
          <template slot-scope="{ row }" slot="identity">
            <a href="javascript:" @click="showModal(row.identity)">{{row.identity}}</a>
          </template>
          <template slot-scope="{ row }" slot="vId">
            <span>{{vipMap.filter(e=>e.id===row.vId)[0].name+'会员'}}</span>
          </template>
          <template slot-scope="{ row }" slot="sex">
            {{ row.sex===1 ? '男':'女'}}
          </template>
          <template slot-scope="{ row,index }" slot="action">
            <Button type="primary" size="small" style="margin-right: 5px" @click="showModal(row.identity)">修改</Button>
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
    <Modal
      title="客户详情"
      v-model="modal.open"
      width="300"
      class-name="vertical-center-modal">
      <div>
        <Row class="modal_row">
          <Col span="24">
            证件号码
            <Input size="small" type="text" style="width: auto" class="modal_label"
                   v-model="modal.detail.identity"
                   :disabled="modal.supportEdit"/>
          </Col>
        </Row>
        <Row class="modal_row">
          <Col span="24">
            会员等级
            <Select size="small" style="width: auto" class="modal_label"
                    v-model="modal.detail.vId"
                    :disabled="modal.supportEdit">
              <Option v-for="(item,idx) in vipMap" :value="idx+1" :label="item.name+'会员'"></Option>
            </Select>
          </Col>
        </Row>
        <Row class="modal_row">
          <Col span="24">
            姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名
            <Input size="small" type="text" style="width: auto" class="modal_label"
                   v-model="modal.detail.name"
                   :disabled="modal.supportEdit"/>
          </Col>
        </Row>

        <Row class="modal_row">
          <Col span="24">
            性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别
            <Select size="small" style="width: auto" class="modal_label"
                    v-model="modal.detail.sex"
                    :disabled="modal.supportEdit">
              <Option :value="1" :key="1">男</Option>
              <Option :value="0" :key="0">女</Option>
            </Select>
          </Col>
        </Row>
        <Row class="modal_row">
          <Col span="24">
            联系方式
            <Input size="small" type="text" style="width: auto" class="modal_label"
                   v-model="modal.detail.contact"
                   :disabled="modal.supportEdit"/>
          </Col>
        </Row>
      </div>
      <div slot="footer">
        <Button @click="modal.supportEdit=false">启用修改</Button>
        <Button type="primary" @click="save">保存</Button>
      </div>
    </Modal>
  </div>
</template>

<script>
  export default {
    name: "customer_manage_page",
    components: {},
    data() {
      return {
        columns: [
          {
            key: 'identity',
            slot: 'identity',
            title: '证件号码'
          }, {
            key: 'name',
            title: '姓名'
          },
          {
            slot: 'vId',
            key: 'vId',
            title: '会员等级'
          },
          {
            key: 'contact',
            title: '联系方式'
          },
          {
            key: 'sex',
            slot: 'sex',
            title: '性别'
          },
          {
            key: 'action',
            title: '操作',
            slot: 'action',
            width: 140,
            align: 'center'
          }
        ],
        customer: [
          {
            identity: '441222200003150909',
            name: '亚索',
            vId: 1,
            contact: 13612345678,
            sex: 1,
          },
          {
            identity: '441222200003150919',
            name: '瑞文',
            vId: 2,
            contact: 13612345678,
            sex: 0,
          },
        ],
        loading: false,
        vipMap: [],
        // 查找条件
        searchKey: '',
        searchValue: '',
        //弹框
        modal: {
          open: false,
          subLoading: false,
          delLoading: false,
          supportEdit: true,
          detail: {},
        },
      }
    },
    methods: {
      show(index) {

      },
      remove(index) {

      },
      showModal(identity) {
        console.log(identity)
        //todo: 获取用户更多详情信息
        this.modal.detail = {
          identity: 441222200003150919,
          name: '瑞文',
          vId: 2,
          contact: 13612345678,
          sex: 0,
        };
        this.modal.open = true;
      },

      save() {
        //todo 将修改后的信息保存
      },
      handleSearch() {
        console.log(this.value)
      },
      handleClear(e) {

      },
      changePage(p) {
        //TODO: p；页码
        console.log(p, '页')
      },
      setDefaultSearchKey() {
        this.searchKey = this.columns[0].key !== 'action' ? this.columns[0].key : (this.columns.length > 1 ? this.columns[1].key : '')
      },
    },
    watch: {
      columns(columns) {
        // this.handleColumns(columns)
        this.setDefaultSearchKey()
      },
      value(val) {
        // this.handleTableData()
        // if (this.searchable) this.handleSearch()
      },
    },
    mounted() {
      // todo:获取基础数据
      this.loading = true;
      this.setDefaultSearchKey();
      this.vipMap = [
        {id: 1, name: '一星', discount: 1.00},
        {id: 2, name: '二星', discount: 0.95},
        {id: 3, name: '三星', discount: 0.88},
        {id: 4, name: '四星', discount: 0.80},
        {id: 5, name: '五星', discount: 0.70},
      ];
      this.loading = false;
    }
  }
</script>

<style lang="less">
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

  .modal_row {
    margin: 4px 0;
  }

  .ivu-table-overflowX::-webkit-scrollbar {
    height: 0;
  }

  .vertical-center-modal {
    display: flex;
    align-items: center;
    justify-content: center;

    .ivu-modal {
      top: 0;
    }
  }

  .modal_label {
    margin: 0 10px;
  }
</style>
