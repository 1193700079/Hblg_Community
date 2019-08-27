<template>
  <div class="login-main">
    <div class="login-content">
      <div class="login-carousel">
        <el-carousel :interval="4000" type="card" height="750px">
          <el-carousel-item v-for="item in 6" :key="item">
            <img src="../../assets/timg.jpg" alt="" srcset="" class="login-carousel-image">
          </el-carousel-item>
        <div class="login-input">
          <div class="login-input-form">
            <div class="login-input-form-title">
              <p>用户登录</p>
            </div>
            <el-form 
              :model="ruleForm" 
              status-icon :rules="rules" 
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
                <router-link to="/">找回密码</router-link>
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
          </div>
        </div>
        </el-carousel>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      ruleForm: {
        pass: '',
        account: '',
      },
      rules: {
        pass: [
          { type: 'number', required:true, message:'请输入密码',trigger:'blur'}
        ],
        account: [
          { type: 'number', required:true, message:'请输入账号',trigger:'blur'}
        ],
      }
    }
  }
}
</script>

<style scoped>
  .el-carousel__item--card {
    border-radius: 5px; 
  }
  .login-main {
    display: flex;
    align-items: center;
    height: 100vh;
    background-color:#f5f7fa; 
  }
  .login-content {
    width: 100%;
    min-width: 1850px;
  }
  .login-content .login-carousel {
    position: relative;
    min-height: 750px;
    margin-left:-600px;
  }
  .login-content .login-carousel .login-carousel-image {
    height: 100%;
    width: 100%;
  }
  .login-content .login-input {
    position: absolute;
    height: 495px;
    width:402px;
    right: 130px;
    top: 125px;
    background-color: #ffffff;
    z-index:9999;
    opacity: 0.97;
    border-radius: 8px; 
  }
  .login-content .login-input .login-input-form {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    width: 100%;
    height:100%;
  }
  .login-content .login-input .login-input-form-other {
    position: relative;
    width: 100%;
    margin-top: 20px;
    padding-top: 20px;
    border-top: #e4e7ed solid 1px;
  }
  .login-content .login-input .login-input-form-other .login-input-form-other-content {
    display: flex;
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
    justify-content: space-between
  }
  .login-input-form-remeber {
    margin-bottom: 10px;
    font-size: 14px;
    color: #202d40;
  }
  a {
    text-decoration-line:none
  }
  .el-form-item .el-form-item__content{
    margin-left: 0 !important;
  }
  .el-carousel__item:nth-child(2n) {
    background-color: black;
    width:50%;
  }

  .el-carousel__item:nth-child(2n+1) {
    background-color: black;
    width:50%;
  }
</style>