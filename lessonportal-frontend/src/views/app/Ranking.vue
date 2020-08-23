<template>
  <v-container fluid>
    <v-layout style="display: block">
      <div class="center-container">
        <h1 class="center-x">Ranking</h1>
      </div>
      <div class="center-container">
        <ranking-component class="center-x" :ranking="rankingList"></ranking-component>
      </div>
    </v-layout>
  </v-container>
</template>

<script>
import RankingComponent from "@/components/app/Ranking";

export default {
  name: "Ranking",
  components: {
    RankingComponent,
  },
  computed: {
    rankingList() {
      return this.$store.state.ranking;
    },
  },
};
</script>

<style scoped>
</style>