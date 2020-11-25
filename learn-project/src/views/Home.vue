<template>
  <div class="box1">
    <div class="head">
      <img
        style="width: 100%; height: 400px; margin:auto; border-radius: 8px;"
        src="../assets/left.jpg"
        model="heightFit"
        v-show="!isFaceLogin"
      />
      <div class="head-in">
        <video
          id="video"
          width="630px"
          height="370px"
          autoplay="autoplay"
          style="width: 100%; height: 400px; margin:auto; border-radius: 8px;"
          v-show="isFaceLogin"
        ></video>
        <canvas
          id="canvas"
          width="630px"
          height="370px"
          style="width: 100%; height: 400px; margin:auto; border-radius: 8px;"
          v-show="isFaceLogin"
        >
        </canvas>
      </div>
    </div>

    <div class="content">
      <div style="margin-bottom: 10px;">
        <span class="title">管理员登录</span>
      </div>
      <div class="content-head">
        <p class="font" style="margin-bottom: 20px;">
          请使用账号密码或人脸登录
        </p>
        <img
          src="../assets/分类.png"
          style="width: 50px; height: 50px; margin-bottom: 10px;"
          alt=""
        />
      </div>
      <div style="display: flex; flex-direction: column; width: 100%;">
        <input
          type="text"
          placeholder="USERNAME"
          class="input1"
          v-model="username"
        />
        <input
          type="pasword"
          placeholder="PASSWORD"
          class="input1"
          :disabled="isFaceLogin == true"
          v-model="password"
        />
      </div>
      <div class="bottom">
        <div style="display: flex; align-items: center;">
          <input
            type="checkbox"
            name="form-face"
            style="zoom:130%"
            v-model="isFaceLogin"
            @change="useFaceLogin()"
          />
          <span class="font">人脸识别登录</span>
        </div>
        <div>
          <span class="font1">建议在Chrome下使用本系统</span>
        </div>
      </div>

      <button class="login-btn" @click="login()">戳我登录{{ message }}</button>
    </div>
  </div>
</template>

<script>
export default {
  name: "Home",
  data() {
    return {
      message: "Hello Vue!",
      isFaceLogin: false, //是否开启人脸登录
      mediaStreamTrack: null, //全局视频对象
      video: null,
      isCameraOpen: false, //摄像头是否打开
      username: "",
      password: "",
    };
  },
  created() {},
  methods: {
    login() {
      let username = this.username;
      if (username == null || username == undefined || username == "") {
        alert("用户名不能为空！");
        return;
      }
      //使用人脸识别
      if (this.isFaceLogin) {
        let video = document.getElementById("video");
        let canvas = document.getElementById("canvas");
        let ctx = canvas.getContext("2d");
        ctx.drawImage(video, 0, 0, 630, 370);
        let img = document.getElementById("canvas").toDataURL();
        //获取base64图片
        console.log(img);
        let img64 = img.split("base64,")[1];
        this.axios
          .post("http://admin.sn.com:8005/adminMsg/adminFaceLogin", {
            username: username,
            img64: img64,
          })
          .then((res) => {
            console.log(res.data);
            if (res.data.status == 200) {
              alert("人脸识别成功！点击确认进入系统");
            } else {
              alert(res.data.msg);
            }
          });
      } else {
        let password = this.password;
        if (password == null || password == undefined || password == "") {
          alert("密码不能为空");
          return;
        }
        let adminUserLogin = {};
        adminUserLogin.username = username;
        adminUserLogin.password = password;
        //用户名密码登录
        this.axios
          .post("http://admin.sn.com:8005/adminMsg/adminLogin", adminUserLogin)
          .then((res) => {
            console.log(res.data);
            if (res.data.status == 200) {
              alert("登录成功");
              this.username = "";
              this.passwrod = "";
            } else {
              alert(res.data.msg);
            }
          });
      }
    },

    useFaceLogin() {
      //如果true，打开摄像头
      if (this.isFaceLogin) {
        this.openFace();
      } else {
        this.closeFace();
      }
    },
    openFace() {
      //如果摄头已经打开，就不继续执行下方代码
      if (this.isCameraOpen) {
        return;
      }
      let constraints = {
        video: { width: 630, height: 370 },
        audio: false,
      };
      //获取video摄像头
      this.video = document.getElementById("video");
      let promise = navigator.mediaDevices.getUserMedia(constraints);
      promise.then((mediaStream) => {
        this.mediaStreamTrack = mediaStream.getVideoTracks();
        this.video.srcObject = mediaStream;
        this.video.play();
      });
      this.video.onloadedmedata = function() {
        this.isCameraOpen = true;
      };
    },
    closeFace() {
      let stream = this.video.srcObject;
      if (stream == null) {
        return;
      }
      let tracks = stream.getTracks();
      tracks.forEach(function(track) {
        track.stop();
      });
      this.video.srcObject = null;
    },
  },
};
</script>

<style scoped>
.box1 {
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  width: 100%;
  height: 100%;
  background: url("../assets/image.jpg") no-repeat center;
  /* background-color: rgba(106, 61, 95, 0.5); */
}

.head {
  flex: 0 0 40%;
  height: 100%;
  display: flex;
  align-content: center;
}
.head-in {
  height: 100%;
  display: flex;
  flex-direction: column;
  align-content: center;
  justify-items: center;
}

.input1 {
  padding-left: 10px;
  height: 50px;
  width: 100%;
  margin-top: 30px;
  border-radius: 5px;
  outline: none;
  border: none;
}

.content {
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
  font-weight: bold;
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

.font1 {
  font-size: 14px;
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
