<template>
  <div class="content">
    <div class="left">
      <img
        style="width: 100%; height: 400px; margin:auto; border-radius: 8px;"
        src="../assets/image/left.jpg"
      />
    </div>
    <div class="right">
      <div style="margin-bottom: 10px;">
        <span class="title">管理员登录</span>
      </div>
      <div class="content-head">
        <p class="font" style="margin-bottom: 20px;">
          请使用账号密码或人脸登录
        </p>
        <img
          src="../assets/image/menu.png"
          style="width: 50px; height: 50px; margin-bottom: 10px;"
          alt=""
        />
      </div>
      <div style="display: flex; flex-direction: column; width: 100%;">
        <input type="text" placeholder="USERNAME" class="input" />
        <input type="password" placeholder="PASSWORD" class="input" />
      </div>
      <div class="bottom">
        <div style="display: flex; align-items: center;">
          <input type="checkbox" />
          <span class="font">人脸识别登录</span>
        </div>
        <div>
          <span class="font-little">建议在Chrome下使用本系统</span>
        </div>
      </div>

      <Button class="login-btn">戳我登录</Button>
    </div>
  </div>
</template>

<script>
export default {
  name: "index",
};
</script>

<style scoped>
.content {
  display: flex;
  justify-content: center;
  align-items: center;
  left: 0;
  top: 0;
  position: absolute;
  width: 100%;
  height: 100%;
  background-image: url("../assets/image/background.jpg");
  background-size: cover;
}

.left {
  flex: 0 0 40%;
  height: 100%;
  display: flex;
  align-content: center;
}

.input {
  padding-left: 10px;
  height: 50px;
  width: 100%;
  margin-top: 30px;
  border-radius: 5px;
  outline: none;
  border: none;
}

.right {
  flex: 0 0 30%;
  margin-left: 40px;
  display: flex;
  justify-content: center;
  align-items: flex-start;
  flex-direction: column;
}

.content-head {
  display: flex;
  justify-content: space-between;
  width: 100%;
  align-items: flex-end;
  height: 60px;
  border-bottom: 2px solid white;
}

.title {
  font-size: 36px;
  font-weight: 500;
  color: white;
}

.font {
  font-size: 16px;
  color: rgb(232, 232, 232);
}

.bottom {
  display: flex;
  justify-content: space-between;
  width: 100%;
  align-items: center;
  margin-top: 20px;
}

.font-little {
  font-size: 16px;
  color: #eeeeee;
}

.login-btn {
  width: 100%;
  height: 50px;
  margin-top: 10px;
  color: white;
  outline: none;
  border: none;
  border-radius: 5px;
  background-color: rgb(114, 205, 183);
}
</style>
