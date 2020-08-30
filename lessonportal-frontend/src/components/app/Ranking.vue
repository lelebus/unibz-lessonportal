<template>
  <v-data-table
    :headers="headers"
    :items="ranking"
    item-class="currentUser"
    class="elevation-1 ranking"
    :loading="loading"
    loading-text="Loading... Please wait"
    hide-default-footer
  ></v-data-table>
</template>

<script>
export default {
  name: "RankingComponent",
  props: {
    ranking: Array
  },
  data() {
    return {
      headers: [
        {
          text: "Position",
          value: "position",
          align: "center",
          sortable: true,
        },
        { text: "User", value: "username", align: "center", sortable: false },
        { text: "Points", value: "points", align: "center", sortable: false },
      ],
    };
  },
  compute: {
    loading() {
      return this.$route.state.loading;
    }
  }
};
</script>

<style>
.v-data-table.ranking {
  width: 100%;
  max-width: 800px;
}
.v-data-table.ranking tr {
  cursor: default
}
.v-data-table.ranking tbody tr.selected:hover {
  background-color: #9CCC65 !important;
}
.v-data-table.ranking tbody tr:hover {
  background-color: #E8F5E9 !important;
}
</style>