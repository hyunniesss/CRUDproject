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
      error: "",
    };
  },
  methods: {
    checkID() {
      if (this.userId.length == 0) {
        this.error = "아이디는 필수입니다.";
      } else {
        axios
          .get(SERVER.URL + SERVER.ROUTES.SIGNUP, {
            user_id: this.userId,
          })
          .then((res) => {
              console.log(res)
          });
      }
    },
  },
};
</script>

<style>
</style>