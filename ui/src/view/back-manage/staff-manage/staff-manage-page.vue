<template>
  <Card title="人员管理">
    <div>
      <div class="search-con search-con-top">
        <Select v-model="searchKey" class="search-col">
          <Option v-for="item in tableTitle" v-if="item.key ===  'id' ||item.key ===  'name'" :value="item.key"
                  :key="item.key">{{ item.title }}
          </Option>
        </Select>
        <Input @on-change="handleClear" placeholder="输入关键字搜索" class="search-input" v-model="searchValue"/>
        <Button @click="handleSearch" class="search-btn" type="primary">
          <Icon type="search"/>&nbsp;&nbsp;搜索
        </Button>
      </div>
      <Table :columns="tableTitle" :data="tableData" :loading="tableLoading">
        <template slot-scope="{ row,index }" slot="id">
          <a href="javascript:" @click="edit(row.id,index)">{{row.id}}</a>
        </template>
        <template slot-scope="{ row,index }" slot="depId">
          <span>{{mapDepName(row)}}</span>
        </template>
        <template slot-scope="{ row,index }" slot="shift">
          <span>{{row.shift===0?'白班':'夜班'}}</span>
        </template>
        <template slot-scope="{ row,index }" slot="action">
          <Button type="primary" size="small" style="margin-right: 5px" @click="edit(row.id,index)">修改</Button>
          <Button type="error" size="small" @click="remove(row.id,index)">辞退</Button>
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
    <Modal
      title="员工详情"
      v-model="modal.open"
      width="300"
      class-name="vertical-center-modal">
      <div>
        <Row class="modal_row">
          <Col span="24">
            员工编号
            <Input size="small" type="text" style="width: auto" class="modal_label"
                   v-model="modal.detail.id"
                   :disabled="modal.supportEdit"/>
          </Col>
        </Row>
        <Row class="modal_row">
          <Col span="24">
            所在部门
            <Select size="small" style="width: auto" class="modal_label"
                    v-model="modal.detail.depId"
                    :disabled="modal.supportEdit">
              <Option v-for="(item,idx) in depList" :value="item.id" :label="item.name"></Option>
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
            班&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;次
            <Select size="small" style="width: auto" class="modal_label"
                    v-model="modal.detail.shift"
                    :disabled="modal.supportEdit">
              <Option :value="1" :key="1">夜班</Option>
              <Option :value="0" :key="0">白班</Option>
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
  </Card>
</template>

<script>
  import {getDepList, getStaffInfoPage, getStaffInfoSearch, postStaffInfo,getStaffInfo} from '@/api/data'

  export default {
    name: "staff-manage-page",
    components: {},
    data() {
      return {
        tableTitle: [
          {
            key: 'id',
            slot: 'id',
            title: '员工编号',
          },
          {
            key: 'name',
            title: '姓名',
          },
          {
            key: 'depId',
            slot: 'depId',
            title: '所属部门'
          },
          {
            key: 'shift',
            slot: 'shift',
            title: '班次'
          },
          {
            key: 'contact',
            title: '联系方式',
          },
          {
            key: 'action',
            slot: 'action',
            title: '操作',
          }
        ],
        tableData: [
          {
            id: 10001,
            depId: 2,
            name: '亚索',
            shift: '白班',
            email: '123123123@qq.com',
            contact: '13612345678',
          }
        ],
        searchKey: '',
        searchValue: '',
        depList: [{
          id: '', name: '', status: 1
        }],
        tableLoading: false,
        page: {
          current: 1,
          pageSize: 10,
          total: 10,
          isSearch: false,
        },
        modal: {
          open: false,
          subLoading: false,//提交
          supportEdit: true,
          index:0,
          detail: {
            id: '',
            name: '',
            depId: 1,
            contact: 13612345678,
            shift: 1,
          },
        }
      }
    },
    methods: {
      edit(id,idx) {
        postStaffInfo({}).then(res => {
          if (res.data.status === 403) {
            this.$Message.warning({
              top: 100,
              duration: 2,
              content: '您的权限不足！'
            });
            return false;
          }
          getStaffInfo(id).then(res=>{
            this.modal.open=true;
            this.modal.index = idx;
            this.modal.detail = res.data.result;
          })
        }).catch(err => {});
      },
      remove(id,idx) {
        postStaffInfo({}).then(res => {
          if (res.data.status === 403) {
            this.$Message.warning({
              top: 100,
              duration: 2,
              content: '您的权限不足！'
            });
            return false;
          }
          this.$Modal.confirm({
            title:"辞退",
            content:"你确定辞退该员工吗？",
            onOk(){
              postStaffInfo({
                id:id,
                status:0
              }).then(res=>{
                console.log(res)
              })
            }
          })
        })
      },
      save(){
        postStaffInfo(this.modal.detail).then(res=>{
          this.$Message.success({
            top: 100,
            duration: 2,
            content: '已修改'
          });
          this.modal.open=false;
          if(this.page.isSearch){
            this.getStaffInfoSearch();
          }else{
            this.getStaffInfoPage();
          }
        })
      },

      handleSearch() {
        this.tableLoading = true;
        this.getStaffInfoSearch();
      },
      getStaffInfoSearch(){
        getStaffInfoSearch({
          column: this.searchKey,
          value: this.searchValue,
        }).then(res => {
          this.tableData = res.data.result['records'];
          this.page.current = res.data.result['current'];
          this.page.total = res.data.result['total'];
          this.page.isSearch = true;
          this.tableLoading = false;
        }).catch(err => {
        });
      },
      handleClear(e) {
        if (this.searchValue === '') {
          this.getStaffInfoPage(1);
          this.page.isSearch = false;
        }
      },
      mapDepName(row) {
        return this.depList.filter((cur) => cur.id === row.depId)[0].name
      },
      DepName2Id(row) {
        return this.depList.filter((cur) => cur.name === row.name)[0].id
      },
      setDefaultSearchKey() {
        this.searchKey = this.tableTitle[0].key !== 'action' ? this.tableTitle[0].key : (this.tableTitle.length > 1 ? this.tableTitle[1].key : '')
      },
      changePage(p) {
        this.page.current = p;
        if (this.page.isSearch) {
          this.handleSearch(this.page.current)
        } else {
          this.getStaffInfoPage();
        }
      },
      getStaffInfoPage() {
        getStaffInfoPage({
          current: this.page.current
        }).then(res => {
          this.tableData = res.data.result['records'];
          this.page.current = res.data.result['current'];
          this.page.total = res.data.result['total'];
          this.tableLoading = false;
        }).catch(err => {
        });
      }
    },
    computed: {},
    mounted() {
      this.tableLoading = true;
      this.setDefaultSearchKey();
      getDepList().then(res => {
        this.depList = res.data.result;
        this.getStaffInfoPage(1);
      }).catch(err => {
      })
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
  .modal_row {
    margin: 4px 0;
  }
</style>
