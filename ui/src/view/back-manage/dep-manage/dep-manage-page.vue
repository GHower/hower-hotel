<template>
  <Card title="部门结构" shadow style="height: 100%;width: 100%;overflow:hidden">
    <div class="department-outer">
      <div class="zoom-box">
        <zoom-controller v-model="zoom" :min="20" :max="200"></zoom-controller>
      </div>
      <div class="view-box">
        <org-view
          v-if="data"
          :data="data"
          :zoom-handled="zoomHandled"
          @on-menu-click="handleMenuClick"
        ></org-view>
      </div>
    </div>
    <Modal v-model="Modal.open" :title="Modal.title"
        @on-ok="submitData" @on-cancel="resetModalData" width="300">
      <span v-if="Modal.data.id">部门ID</span><Input type="text" v-model="Modal.data.id" v-if="Modal.data.id" disabled/>
      <span >部门名称</span><Input type="text" v-model="Modal.data.name"/>
    </Modal>
  </Card>
</template>

<script>
  import OrgView from './components/org-view'
  import ZoomController from './components/zoom-controller'
  import './index.less'
  import {getDepartmentTree,postDepartment} from '@/api/data'
  export default {
    name: "staff-manage-page",
    components: {
      OrgView,
      ZoomController
    },
    data() {
      return {
        data: null,
        zoom: 100,
        Modal:{
          open:false,
          title:'',
          data:{
            id:'',
            pid:'',
            name:'',
            status:1,
          }
        },
        menuDic:{
          edit: '编辑部门',
          // detail: '查看部门',
          new: '新增子部门',
          delete: '删除部门'
        }
      }
    },
    computed: {
      zoomHandled() {
        return this.zoom / 100
      }
    },
    methods: {
      checkAction(){

      },
      handleMenuClick({data, key}) {
        this.$Message.success({
          duration: 5,
          content: `点击了《${data.name}》节点的'${this.menuDic[key]}'菜单`
        });
        this.Modal.title = this.menuDic[key];
        switch (key) {
          case 'edit':this.edit(data);break;
          case 'new':this.addSubDep(data);break;
          case 'delete':this.delDep(data);break;
        }
      },
      edit(data){
        this.Modal.open =true;
        this.Modal.data = data;
      },
      addSubDep(data){
        this.Modal.open =true;
        this.Modal.data.pid = data.id;
      },
      delDep(data){
        this.$Modal.confirm({
          title:'删除部门',
          content:'确定删除该部门吗?',
          onOk(){
            data.status=0;
            postDepartment(data).then(res=>{
            }).catch(err=>{})
          },
          onCancel(){
          }
        });
      },
      submitData(){
        postDepartment(this.Modal.data).then(res=>{
          this.$Message.success({
            duration: 3,
            content: `修改成功!`
          });
        })
      },
      resetModalData(){
        this.Modal.data={id:'',name:'',status:1};
      },
      getDepartmentData() {
        getDepartmentTree().then(res => {
          this.data = res.data.result;
        });
      }
    },
    mounted() {
      this.getDepartmentData();
    }
  }
</script>
