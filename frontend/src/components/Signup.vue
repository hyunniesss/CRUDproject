<template>
  <div class="container">
    <label for="userId">아이디</label>
    <input type="text" v-model="userId" @blur="checkID" />
    <p v-if="error" style="color: red">{{ error }}</p>
    <label for="userPassword">비밀번호</label>
    <input type="password" v-model="userPassword" />
    <label for="userPasswordCheck">비밀번호확인</label>
    <input type="password" v-model="userPasswordCheck" />
    <label for="userName">이름</label>
    <input type="text" v-model="userName" />
    <button @click="signup">회원가입</button>
  </div>
</template>

<script>
import SERVER from "@/api/url";
import axios from "axios";
export default {
  data() {
    return {
      userId: "",
      userPassword: "",
      userPasswordCheck: "",
      userName: "",
      error: {
        id: "",
        password: "",
      },
    };
  },
  watch: {
    userPasswordCheck() {
      if (this.userPasswordCheck != this.userPassword) {
        this.error.password = "비밀번호가 일치하지 않습니다.";
      } else {
        this.error.password = "";
      }
    },
  },
  methods: {
    checkID() {
      if (this.userId.length == 0) {
        this.error = "아이디는 필수입니다.";
      } else {
        axios
          .get(SERVER.URL + SERVER.ROUTES.SIGNUP, {
            params: {
              user_id: this.userId,
            },
          })
          .then((res) => {
            if (res.data.success) {
              this.error = "";
            } else {
              this.error = "이미 사용중인 아이디입니다.";
            }
          })
          .catch((err) => {
            console.log(err);
          });
      }
    },
    signup() {
      let err = true;
      let msg = "";
      !this.userId &&
        ((msg = "아이디를 입력하세요."),
        (err = false),
        this.$refs.userId.focus());
      err &&
        !this.userPassword &&
        ((msg = "비밀번호를 입력하세요."),
        (err = false),
        this.$refs.userPassword.focus());
      err &&
        this.error.password &&
        ((msg = "비밀번호를 재확인해주세요."),
        (err = false),
        this.$refs.userPasswordCheck.focus());
      err &&
        !this.userName &&
        ((msg = "이름을 입력하세요."),
        (err = false),
        this.$refs.userName.focus());

      if (!err) {
        alert(msg);
      } else {
        axios
          .post(SERVER.URL + SERVER.ROUTES.SIGNUP, {
            body: {
              userId: this.userId,
              userPassword: this.userPassword,
              userName: this.userName,
            },
          })
          .then((res) => {
            console.log(res);
          });
      }
    },
  },
};
</script>

<style>
</style>