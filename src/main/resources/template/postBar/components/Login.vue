<template>
  <div class="login-main">
    <div class="login-content">
      <div class="login-carousel">
        <el-carousel :interval="4000" type="card" height="750px">
          <el-carousel-item v-for="item in 6" :key="item">
            <img src="@/assets/timg.jpg" alt="" srcset="" class="login-carousel-image" v-show="(item%2 == 0)">
            <img src="@/assets/t123.jpg" alt="" srcset="" class="login-carousel-image" v-show="(item%2 == 1)" >
          </el-carousel-item>
        <div class="login-input">
          <div class="login-input-form">
              <slot name="dialog">
              </slot>
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
    }
  },
  methods: {
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