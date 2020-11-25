<template>
  <div class="container">
    <label for="user_id">아이디</label>
    <input type="text" v-model="user_id" id="user_id" />
    <v-btn @click="checkID">중복검사</v-btn>
    <p>{{user_check}}</p>
  </div>
</template>

<script>
import axios from "axios";
import SERVER from "@/api/url";
export default {
  data() {
    return {
      user_id: "",
      user_check:''
    };
  },
  created() {},
  methods: {
    checkID() {
      axios
        .get(SERVER.URL + SERVER.ROUTES.SIGNUP, {
          user_id: this.user_id,
        })
        .then((res) => {
          console.log(res);
          if (res.data.success) {
            this.user_check = "";
          } else {
            this.user_check = "이미 사용중입니다.";
          }
        });
    },
  },
};
</script>

<style>
</style>