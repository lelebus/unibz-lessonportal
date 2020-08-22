<template>
  <v-app>
    <app-navigation></app-navigation>

    <v-main transition="slide-x-transition">
      <router-view></router-view>
      <modal-login></modal-login>
    </v-main>
  </v-app>
</template>

<script>
import AppNavigation from "@/components/app/AppNavigation";
import ModalLogin from '@/components/dialogs/ModalLogin';

export default {
  name: "App",
  components: {
    AppNavigation,
    ModalLogin
  },
  created() {
    this.$store.dispatch("fetchLessons");
    this.$store.dispatch("fetchRanking");
  },
};
</script>