<template>
  <div>
    <v-snackbar v-model="snackbar" :timeout="timeout">{{ message }}</v-snackbar>
  </div>
</template>
    
<script>
export default {
  name: "NotifySuccess",
  props: {
    message: String,
  },
  data() {
    return {
      snackbar: false,
      timeout: 2000,
    };
  },
};
</script>