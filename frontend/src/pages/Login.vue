<template>
  <div class="container">
    <component :is="page" @changeComponents="changeComponent"> </component>
  </div>
</template>

<script>
import Login from "../components/Login";
import Signup from "../components/Signup";

export default {
  components: {
    Login,
    Signup,
  },
  data() {
    return {
      page: "login",
    };
  },
  created() {},
  methods: {
    changeComponent(value) {
      this.page = value;
    },
  },
};
</script>

<style>
</style>