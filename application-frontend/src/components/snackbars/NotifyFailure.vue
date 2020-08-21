<template>
  <div>
    <v-snackbar v-model="snackbar">
      {{ message }}
      <template v-slot:action="{ attrs }">
        <v-btn color="red" text v-bind="attrs" @click="snackbar = false">Close</v-btn>
      </template>
    </v-snackbar>
  </div>
</template>
    
<script>
export default {
  name: "NotifyFailure",
  props: {
    message: String,
  },
  data() {
    return {
      snackbar: false,
    };
  },
};
</script>