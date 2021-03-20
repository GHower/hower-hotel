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
      <Table :columns="tableTitle" :data="tableData">
        <template slot-scope="{ row,index }" slot="action">
          <Button type="primary" size="small" style="margin-right: 5px" @click="edit(index)">修改</Button>
          <Button type="error" size="small" @click="remove(index)">删除</Button>
        </template>
      </Table>
    </div>
  </Card>
</template>

<script>
  export default {
    name: "staff-manage-page",
    components: {},
    data() {
      return {
        tableTitle: [
          {
            key: 'id',
            title: '员工编号',
          },
          {
            key: 'pId',
            title: '所属部门'
          },
          {
            key: 'shift',
            title: '班次'
          },
          {
            key: 'name',
            title: '姓名',
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
            pId: '1',
            name: '亚索',
            shift: '白班',
            email: '123123123@qq.com',
            contact: '13612345678',
          }
        ],
        searchKey:'',
        searchValue: '',
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
      setDefaultSearchKey(){
        this.searchKey = this.tableTitle[0].key !== 'action' ? this.tableTitle[0].key : (this.tableTitle.length > 1 ? this.tableTitle[1].key : '')
      }
    },
    mounted() {
      this.setDefaultSearchKey();
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
