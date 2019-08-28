<template>
  <Login>
    <template slot="dialog">
      <div class="login-input-form-title">
        <p>用户登录</p>
      </div>
      <el-form 
        :model="ruleForm" 
        status-icon 
        :rules="rules" 
        ref="ruleForm" 
        class="demo-ruleForm">
        <el-form-item prop="account">
          <el-input v-model="ruleForm.account" placeholder="请输入账号"/>
        </el-form-item>
        <el-form-item prop="pass">
          <el-input 
            type="password" 
            v-model="ruleForm.pass"
            autocomplete="off"
            placeholder="请输入密码"/>
        </el-form-item>
        <div class="login-input-form-remeber">
          <el-checkbox label="记住我" name="type"></el-checkbox>
        </div>
        <el-form-item>
          <el-button type="primary" @click="loginClick('ruleForm')">登录</el-button>
        </el-form-item>
        <div class="login-input-form-register">
          <router-link to="/user/modify">找回密码</router-link>
          <p>还没有账号？ <router-link to="/user/register">马上注册</router-link></p> 
        </div>
        <div class="login-input-form-other">
          <span class="login-input-form-other-title">或使用以下方式登陆</span>
          <div class="login-input-form-other-content">
            <span class="login-input-form-other-WX">WX</span>
            <span class="login-input-form-other-QQ">QQ</span>
          </div>
        </div>
      </el-form>
    </template>
  </Login>
</template>

<script>
import Login from '@/components/Login'

export default {
  head: {
    title:'登陆'
  },
  components: {
    Login: Login
  },
  data() {
    return {
      ruleForm: {
        pass: '',
        account: '',
      },
      rules: {
        pass: [
          { type: 'string', required:true, message:'请输入正确的密码',trigger:'blur'}
        ],
        account: [
          { type: 'string', required:true, message:'请输入正确的账号',trigger:'blur'}
        ],
      }
    }
  },
  methods: {
    loginClick(formName){
      this.$refs[formName].validate((valid) => {
        if(valid) {
          alert('登陆成功');
          this.$router.push('../project/main')
        } else {
          console.log(valid);
        }
      })
    }
  }
}
</script>

<style scoped>
  .login-content .login-input .login-input-form-other {
    position: relative;
    width: 100%;
    margin-top: 40px;
    padding-top: 20px;
    border-top: #e4e7ed solid 1px;
  }
  .login-content .login-input .login-input-form-other .login-input-form-other-content {
    display: flex;
    margin-top:5px; 
  }
  .login-content .login-input .login-input-form-other .login-input-form-other-content .login-input-form-other-WX {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 50px;
    height: 50px;
    background-color: #84898f;
    border-radius: 50%;
  }
  .login-content .login-input .login-input-form-other .login-input-form-other-content .login-input-form-other-QQ {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 50px;  
    height: 50px;
    background-color: #84898f;
    border-radius: 50%;
    margin-left: 10px;
  }
  .login-content .login-input .login-input-form-other .login-input-form-other-title {
    position: absolute;
    color: #9ba3af;
    background-color:white;
    top: -10px;
    left: 30%;
    font-size: 14px;
    
  }
  .login-content .login-input .login-input-form .login-input-form-title{
    width: 80%;
    margin-bottom: 20px;
    font-size: 18px;
  }
  .login-content .login-input .login-input-form .el-form{
    width: 80%;
  }
  .login-content .login-input .login-input-form button{
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    height:100%; 
  }
  .login-input-form-register {
    display: flex;
    justify-content: space-between;
    font-size: 14px;
  }
  .login-input-form-remeber {
    margin-bottom: 10px;
    font-size: 14px;
    color: #202d40;
  }
  a {
    text-decoration-line:none
  }

</style>