<template>
  <div>
    <Card>
      <div slot="title">
        <span style="font-size: 18px;font-weight: bold">会员管理</span>
      </div>
      <div class="">
        <div class="search-con search-con-top">
          <Select v-model="searchKey" class="search-col">
            <Option v-for="item in columns" v-if="item.key ===  'id' ||item.key ===  'name'" :value="item.key"
                    :key="item.key">{{ item.title }}
            </Option>
          </Select>
          <Input @on-change="handleClear" placeholder="输入关键字搜索" class="search-input" v-model="searchValue"/>
          <Button @click="handleSearch" class="search-btn" type="primary">
            <Icon type="search"/>&nbsp;&nbsp;搜索
          </Button>
        </div>
        <Table :loading="loading" :columns="columns" :data="customer" stripe>
          <template slot-scope="{ row,index }" slot="id">
            <a href="javascript:" @click="showModal(row.id,index)">{{row.id}}</a>
          </template>
          <template slot-scope="{ row }" slot="vId">
            <span>{{vipMap.filter(e=>e.id===row.vid)[0].name}}</span>
          </template>
          <template slot-scope="{ row }" slot="sex">
            {{ row.sex==='1' ? '男':'女'}}
          </template>
          <template slot-scope="{ row,index }" slot="action">
            <Button type="primary" size="small" style="margin-right: 5px" @click="showModal(row.id,index)">修改</Button>
            <Button type="error" size="small" @click="remove(row.id,index)">删除</Button>
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
                   v-model="modal.detail.id"
                   :disabled="modal.supportEdit"/>
          </Col>
        </Row>
        <Row class="modal_row">
          <Col span="24">
            会员等级
            <Select size="small" style="width: auto" class="modal_label"
                    v-model="modal.detail.vid"
                    :disabled="modal.supportEdit">
              <Option v-for="(item,idx) in vipMap" :value="idx+1" :label="item.name"></Option>
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
              <Option :value="'1'" :key="'1'">男</Option>
              <Option :value="'0'" :key="'0'">女</Option>
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
        <Button @click="modal.supportEdit=!modal.supportEdit">{{modal.supportEdit ? '启用':'关闭'}}修改</Button>
        <Button type="primary" @click="save" :loading="modal.subLoading">保存</Button>
      </div>
    </Modal>
  </div>
</template>

<script>
  import {getCustomerVip, getCustomerData, getCustomerOfSearch, getCustomerDataById, postCustomerData} from '@/api/data'

  export default {
    name: "customer_manage_page",
    components: {},
    data() {
      return {
        columns: [
          {
            key: 'id',
            slot: 'id',
            title: '证件号码'
          }, {
            key: 'name',
            title: '姓名'
          },
          {
            slot: 'vId',
            key: 'vid',
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
        customer: [],
        loading: false,
        vipMap: [],
        // 查找条件
        searchKey: '',
        searchValue: '',
        //弹框
        modal: {
          open: false,
          subLoading: false,//提交
          supportEdit: true,
          index:0,
          detail: {
            id: '',
            name: '',
            vid: 1,
            contact: 13612345678,
            sex: 1,
          },
        },
        page: {
          current: 1,
          pageSize: 10,
          total: 10,
          isSearch: false,
        }
      }
    },
    methods: {
      showModal(identity,idx) {
        getCustomerDataById({
          id: identity
        }).then(res => {
          this.modal.detail = res.data.result;
          this.modal.index=idx;
        }).catch(err => {

        });
        this.modal.open = true;
      },
      save() {
        //todo 将修改后的信息保存
        this.modal.subLoading=true;
        postCustomerData(
          this.modal.detail
        ).then(res=>{
          if(res.data.result){
            this.modal.subLoading=false;
            this.modal.open=false;
            for(let key in this.modal.detail){
              this.customer[this.modal.index][key] = this.modal.detail[key];
            }
            this.$Message.success({
              content:'已保存',
              duration:2.5,
            })
          }
        }).catch(err=>{
        })
      },
      remove(id,index){
        this.$Modal.warning({
          title:'删除操作',
          content:'你确定删除吗？',
          onOk:()=>{
            postCustomerData({
              id:id,
              status:0
            }).then(res=>{
              if(this.page.isSearch) this.handleSearch();
              else this.getTableData();
            }).catch(err=>{})
          },
        })
      },
      // 查找需要更新page
      handleSearch() {
        this.loading = true;
        getCustomerOfSearch({
          column: this.searchKey,
          value: this.searchValue,
        }).then(res => {
          console.log(res);
          let result = res.data.result;
          this.page.total = result['total'];
          this.page.current = result['current'];
          this.customer = result['records'];
          this.page.isSearch = true;
          this.loading = false;
        }).catch(err => {

        })
      },
      handleClear(e) {
        if (this.searchValue === '') {
          this.getTableData();
          this.page.isSearch = false;
        }
      },
      changePage(p) {
        this.page.current = p;
        if (this.page.isSearch) {
          this.handleSearch(this.page.current)
        } else {
          this.getTableData();
        }
      },
      setDefaultSearchKey() {
        this.searchKey = this.columns[0].key !== 'action' ? this.columns[0].key : (this.columns.length > 1 ? this.columns[1].key : '')
      },
      getTableData() {
        getCustomerData({
          current: this.page.current
        }).then(res => {
          let result = res.data.result;
          this.customer = result['records'];
          this.page.total = result['total'];
          this.page.current = result['current'];
          this.loading = false;
        }).catch(err => {
          console.log(err)
        })
      }
    },
    watch: {
      columns(columns) {
        this.setDefaultSearchKey()
      },
      value(val) {
      },
    },
    mounted() {
      this.loading = true;
      this.setDefaultSearchKey();
      getCustomerVip().then(res => {
        this.vipMap = res.data.result;
        this.getTableData();
        this.loading = false;
      }).catch(err => {
        console.log(err)
      });
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
