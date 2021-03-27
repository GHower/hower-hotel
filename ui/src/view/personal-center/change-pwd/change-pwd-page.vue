<template>
  <div>
    <Card>
      <Row :gutter="16" class="row">
        <Col span="4" offset="6" style="text-align: right">
          <span class="label">输入旧密码:</span>
        </Col>
        <Col span="6">
          <Input ref="old_pwd" type="password" placeholder="请输入旧密码" password v-model="old_pwd"/>
        </Col>
      </Row>
      <Row :gutter="16" class="row">
        <Col span="4" offset="6" style="text-align: right">
          <span class="label">输入新密码:</span>
        </Col>
        <Col span="6">
          <Input ref="new_pwd" type="password" placeholder="8-20位数字和字母组合" password
                 v-model="new_pwd" @on-focus="handleFocus"/>
        </Col>
      </Row>
      <Row :gutter="16" class="row">
        <Col span="4" offset="6" style="text-align: right">
          <span class="label">确认新密码:</span>
        </Col>
        <Col span="6">
          <Input ref="new_pwd2" type="password" password placeholder="确认新密码"
                 v-model="new_pwd2" @on-focus="handleFocus"/>
        </Col>
      </Row>
      <Row class="row" style="text-align: center;margin-top: 20px">
        <div v-if="showTips" style="margin-bottom: 10px;color: red;font-size: 16px">{{err_tips}}</div>
        <Button type="primary" :disabled="btnAble" @click="handleSubmit" size="large">&nbsp;确认&nbsp;</Button>
      </Row>
    </Card>
  </div>
</template>

<script>
  import { postChangepwd,logout } from "@/api/user"
  import { mapActions } from 'vuex'
  export default {
    name: "personal-info-page",
    components: {},
    data() {
      return {
        old_pwd: '',
        new_pwd: '',
        new_pwd2: '',
        err_tips: '',
      }
    },
    methods: {
      ...mapActions([
        'getUserInfo',
        "handleLogOut",
      ]),
      handleSubmit() {
        if (this.new_pwd.length < 11 || this.new_pwd.length > 20) {
          this.err_tips = '密码长度11-20位';
          return false
        }
        if (this.new_pwd !== this.new_pwd2) {
          this.err_tips = '两次密码不一致';
          return false
        }
        //todo:后端请求,验证旧密码
        postChangepwd({
          oldPwd:this.old_pwd,
          newPwd:this.new_pwd
        }).then(res=>{
          this.handleLogOut().then(res=>{
            this.$router.push({
              name: 'login'
            })
          })
        })
      },
      handleFocus(){
        this.err_tips = '';
        console.log(this.err_tips)
      }
    },
    computed: {
      btnAble() {
        return !(this.new_pwd && this.new_pwd2 && this.old_pwd)
      },
      showTips() {
        return this.err_tips !== '' || this.err_tips.length > 0;
      }
    },
    mounted() {
    }
  }
</script>

<style lang="less">
  .row {
    margin: 10px 0;

    .label {
      font-size: 16px;
      margin-right: 5px;
    }
  }


</style>
