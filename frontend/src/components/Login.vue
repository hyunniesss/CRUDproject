<template>
  <div>
    <button @click="moveTo">회원가입</button>
  </div>
</template>

<script>
export default {
  methods: {
    moveTo() {
      this.$emit("changeComponents", "Signup");
    },
  },
};
</script>

<style>
</style>