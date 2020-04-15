<template>
  <div id="my-info-container">

  </div>
</template>

<script>
  export default {
    name: "myInfo",
    data() {
      return {
        activeIndex: '1',
        activeIndex2: '1'
      };
    },
    methods: {
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      }
    }
  }
</script>

<style scoped>

</style>
