<template>
  <Login>
    <template slot="dialog">
      <div class="login-input-form-title">
        <p>账号注册</p>
      </div>
      <el-form 
        :model="ruleForm" 
        status-icon 
        :rules="rules" 
        ref="ruleForm" 
        class="demo-ruleForm">
        <el-form-item prop="phone">
          <el-input v-model="ruleForm.phone" placeholder="请输入手机号"/>
        </el-form-item>
        <el-form-item prop="verify" class="verify-input">
          <el-input v-model="ruleForm.verify" placeholder="请输入验证码"/>
          <el-button
            ref="verify" 
            class="verify-input-button" 
            type="text" 
            :disabled="verifyChange"
            @click="handleGetCode">{{ getVerify }}</el-button>
        </el-form-item>
        <el-form-item prop="pass">
          <el-input 
            type="password" 
            v-model="ruleForm.pass"
            autocomplete="off"
            placeholder="请输入密码"/>
        </el-form-item>
        <el-form-item prop="passAgain">
          <el-input 
            type="password" 
            v-model="ruleForm.passAgain"
            autocomplete="off"
            placeholder="请再次输入密码"/>
        </el-form-item>
        <el-form-item class = "group-button">
          <el-button type="primary" @click="modifyClick('ruleForm')">注册账号</el-button>
          <el-button @click="returnClick()">返回登录</el-button>
        </el-form-item>
      </el-form>
    </template>
  </Login>
</template>

<script>
import Login from '@/components/Login'
import { setInterval, clearInterval } from 'timers';
export default {
  head: {
    title:'账号注册'
  },
  components: {
    Login:Login
  },
  data() {
    return {
      getVerify:'获取验证码',
      verifyChange: false,
      ruleForm:{
        phone:'',
        verify:'',
        pass:'',
        passAgain:'',
      },
      rules: {
        phone: [ 
          { type: 'number', required:true, message:'请输入正确的手机号', trigger:'blur' }
        ],
        verify: [ 
          { type: 'string', required:true, message:'请输入正确的验证码', trigger:'blur' }
        ],
        pass: [ 
          { type: 'string', required:true, message:'请输入正确的密码', trigger:'blur' }
        ],
        passAgain: [ 
          { type: 'string', required:true, message:'请再次输入密码', trigger:'blur' }
        ]
      }
    }
  },
  methods: {
    modifyClick(formName) {
      this.$refs[formName].validate((valid)=>{
        if(valid) {
          console.log("TCL: modifyClick -> valid", valid)
        } else {
          console.log("TCL: modifyClick -> valid")
        }
      })
    },
    returnClick() {
      this.$router.push('/user/login')
    },
    handleGetCode() {
      let count = 60;
      let set = window.setInterval(()=>{
        if(count != 0) {
          count--;
          let getVerify = count.toString().padStart(2,'0');
          this.getVerify = `${getVerify}秒再发送`;
          this.$refs.verify.$el.style.color = "grey"
          console.log("TCL: handleGetCode -> this.$refs.verify.$el.style", this.$refs.verify.$el)
          this.verifyChange = true;
        } else {
          this.getVerify = `获取验证码` ;
          window.clearInterval(set);
          this.$refs.verify.$el.style.color = "#66adfa"
          this.verifyChange = false;
        }
      },1000)
    },
  }

}
</script>

<style scoped>
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
  .login-content .login-input .login-input-form .group-button button{
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    height:100%; 
    margin: 10px 0;
  }
  .el-form-item__content {
    display: flex;
    flex-direction: row;
  }
  .verify-input .el-input {
    width: 75%;
  }
</style>