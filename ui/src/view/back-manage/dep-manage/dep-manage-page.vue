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
  </Card>
</template>

<script>
  import OrgView from './components/org-view'
  import ZoomController from './components/zoom-controller'
  import './index.less'

  const menuDic = {
    edit: '编辑部门',
    detail: '查看部门',
    new: '新增子部门',
    delete: '删除部门'
  };
  export default {
    name: "staff-manage-page",
    components: {
      OrgView,
      ZoomController
    },
    data() {
      return {
        data: null,
        zoom: 100
      }
    },
    computed: {
      zoomHandled() {
        return this.zoom / 100
      }
    },
    methods: {
      handleMenuClick({data, key}) {
        this.$Message.success({
          duration: 5,
          content: `点击了《${data.label}》节点的'${menuDic[key]}'菜单`
        })
      },
      getDepartmentData() {
        // getOrgData().then(res => {
        //   const { data } = res
        //   this.data = data
        // })
        this.data = {
          id: 1,
          label: '豪尔精品酒店',
          children: [
            {
              id: 2,
              label: '行政部',
              children: [
                {
                  id: 21,
                  label: '后勤保障'
                }, {
                  id: 22,
                  label: '涉外经营'
                }, {
                  id: 23,
                  label: '项目承包'
                }, {
                  id: 24,
                  label: '报批及复验'
                }
              ]
            },
            {
              id: 3,
              label: '财务部',
              children: [
                {
                  id: 31,
                  label: '财务计划、监督'
                }, {
                  id: 32,
                  label: '资金规划'
                }
              ]
            },
            {
              id: 4,
              label: '工程部',
              children:[
                {
                  id:41,
                  label:'设备监管',
                },
              ]
            }, {
              id: 5,
              label: '客房部',
              children:[
                {
                  id:51,
                  label:'客房销售'
                },
                {
                  id:52,
                  label:'客房保洁'
                },
              ]
            },
            {
              id: 6,
              label: 'HR人事'
            }
          ]
        }
      }
    },
    mounted() {
      this.getDepartmentData();
    }
  }
</script>
