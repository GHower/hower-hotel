<template>
  <Card title="人员管理">
    <div>
      <div class="search-con search-con-top">
        <Select v-model="searchKey" class="search-col">
          <Option v-for="item in tableTitle" v-if="item.key !== 'action'" :value="item.key"
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
          <Button type="error" size="small" @click="remove(index)">删除</Button>
        </template>
      </Table>
    </div>
  </Card>
</template>

<script>
  import {getDepList,getStaffInfoPage} from '@/api/data'

  /**
   * todo: 分页
   * todo: 搜索、修改、删除、权限检查
   */
  export default {
    name: "staff-manage-page",
    components: {},
    data() {
      return {
        tableTitle: [
          {
            key: 'id',
            slot:'id',
            title: '员工编号',
          },
          {
            key: 'name',
            title: '姓名',
          },
          {
            key: 'depId',
            slot:'depId',
            title: '所属部门'
          },
          {
            key: 'shift',
            slot:'shift',
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

        searchKey:'',
        searchValue: '',

        depList:[],
        tableLoading:false,

      }
    },
    methods: {
      actionBefore() {
        //权限检查
        return true;
      },
      edit(idx) {
        if (this.actionBefore()) {
          this.$Message.warning({
            top: 100,
            duration: 2,
            content: '您的权限不足！'
          })
        }
      },
      remove(idx) {
        if (this.actionBefore()) {
          this.$Message.warning({
            top: 100,
            duration: 2,
            content: '您的权限不足！'
          })
        }
      },
      handleSearch() {
        console.log(this.value)
      },
      handleClear(e) {

      },
      mapDepName(row){
        return this.depList.filter((cur)=>cur.id===row.depId)[0].name
      },
      setDefaultSearchKey(){
        this.searchKey = this.tableTitle[0].key !== 'action' ? this.tableTitle[0].key : (this.tableTitle.length > 1 ? this.tableTitle[1].key : '')
      },
    },
    computed:{

    },
    mounted() {
      this.tableLoading=true;
      this.setDefaultSearchKey();
      getDepList().then(res=>{
        this.depList = res.data.result;

        getStaffInfoPage({
          current:1
        }).then(res=>{
            this.tableData=res.data.result['records'];

        }).catch(err=>{

        });
        this.tableLoading=false;
      }).catch(err=>{})
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
