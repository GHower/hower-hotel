<template>
  <div>
    <Card>
      <div class="info_body">
        <Row>
          <Col span="14" offset="4" style="text-align: center">
            <Img class="avatar" src="https://file.iviewui.com/dist/a0e88e83800f138b94d2414621bd9704.png"/>
          </Col>
        </Row>
        <Row>
          <Col span="14" offset="4" style="text-align: center">
            名称
          </Col>
        </Row>
      </div>
      <divider></divider>
      <div class="info_body">
        <Row class="info_row">
          <Col span="8" offset="4">
            用户ID
            <Input type="text" v-model="formData.id" disabled/>
          </Col>
          <Col span="8" offset="2">
            <br/><span style="color: red">用户ID不可修改</span>
          </Col>
        </Row>
        <Row class="info_row">
          <Col span="8" offset="4">
            真实姓名
            <Input type="text" v-model="formData.name" :disabled="edit_disable"/>
          </Col>
          <Col span="8" offset="2">
            所属部门
            <Input type="text" v-model="formData.dep_name" disabled/>
          </Col>
        </Row>
        <Row class="info_row">
          <Col span="8" offset="4">
            性别
            <br/>
            <RadioGroup v-model="formData.sex"
                        button-style="solid"
                        type="button" >
              <Radio label="1"  border :disabled="edit_disable">男</Radio>
              <Radio label="0" border :disabled="edit_disable">女</Radio>
            </RadioGroup>

          </Col>
          <Col span="8" offset="2">
            证件号码
            <Input type="text" v-model="formData.identity" :disabled="edit_disable"/>
          </Col>
        </Row>
        <Row class="info_row">
          <Col span="10" offset="10">
            <Button style="margin-right: 16px" @click="supportEdit">{{edit_disable ? '修改':'取消'}}</Button>
            <Button type="primary" @click="handleSave">保存</Button>
          </Col>
        </Row>
      </div>
    </Card>
  </div>
</template>

<script>

  export default {
    name: "personal-info-page",
    components: {},
    data() {
      return {
        user: {
          id:'100121',
          name:'豪尔',
          sex:String(1),
          identity:'441222200003150919',
          dep_name:'行政部',
        },
        formData:{
          id:'100121',
          name:'豪尔',
          sex:String(1),
          identity:'441222200003150919',
          dep_name:'行政部',
        },
        edit_disable:true,
      }
    },
    methods: {
      resetData(){
        for(let i in this.user){
          this.formData[i] = this.user[i];
        }
      },
      supportEdit(){
        this.edit_disable=!this.edit_disable;
        if (this.edit_disable) {
          this.resetData();
        }
      },
      handleSave(){

      },

    },
    mounted() {

    },

  }
</script>

<style lang="less">
  .info_body {
    padding: 0 200px;

    .avatar {
      width: 100px;
    }
    .info_row{
      margin: 10px 0;
    }
  }
</style>
