<template>
  <div class="container">
    <label for="userId">아이디</label>
    <input type="text" v-model="userId" @blur="checkID" ref="userId" />
    <p v-if="error.id" style="color: red">{{ error.id }}</p>
    <label for="userPassword">비밀번호</label>
    <input type="password" v-model="userPassword" ref="userPassword" />
    <label for="userPasswordCheck">비밀번호확인</label>
    <input
      type="password"
      v-model="userPasswordCheck"
      ref="userPasswordCheck"
    />
    <p v-if="error.password" style="color: red">{{ error.password }}</p>
    <label for="userName">이름</label>
    <input type="text" v-model="userName" ref="userName" />
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
    userPassword() {
      if (
        this.userPasswordCheck.length != 0 &&
        this.userPasswordCheck != this.userPassword
      ) {
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
            console.log(res.data.success);
            if (res.data.success) {
              this.error.id = "";
            } else {
              this.error.id = "이미 사용중인 아이디입니다.";
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
        this.error.id &&
        ((msg = "이미 사용중인 아이디입니다."),
        (err = false),
        this.$refs.userId.focus());
      err &&
        !this.userName &&
        ((msg = "이름을 입력하세요."),
        (err = false),
        this.$refs.userName.focus());

      if (!err) {
        alert(msg);
      } else {
        axios
          .post(
            SERVER.URL + SERVER.ROUTES.SIGNUP,
            {
              userId: this.userId,
              userPassword: this.userPassword,
              userName: this.userName,
            },
            {}
          )
          .then((res) => {
            console.log(res);
            if (res.data.success) {
              this.$emit("changeComponents", "Login");
            } else {
              alert("회원가입에 실패하였습니다.");
            }
          });
      }
    },
  },
};
</script>

<style>
</style>