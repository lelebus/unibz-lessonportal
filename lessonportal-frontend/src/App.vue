<template>
  <v-app>
    <app-navigation></app-navigation>

    <v-main transition="slide-x-transition">
      <router-view></router-view>
      <modal-login v-if="!loggedIn"></modal-login>
    </v-main>
  </v-app>
</template>

<script>
import AppNavigation from "@/components/app/AppNavigation";
import ModalLogin from "@/components/dialogs/ModalLogin";

export default {
  name: "App",
  components: {
    AppNavigation,
    ModalLogin,
  },
  created() {
    this.$store.dispatch("me").then(() => {
      this.$store.dispatch("fetchLessons");
      this.$store.dispatch("fetchRanking");
    });
  },
  computed: {
    loggedIn() {
      return this.$store.state.user != null;
    }
  }
};
</script>