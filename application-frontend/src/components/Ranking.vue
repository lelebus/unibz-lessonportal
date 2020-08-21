<template>
  <v-data-table
    :headers="headers"
    :items="users"
    item-class="currentUser"
    class="elevation-1"
    loading-text="Loading... Please wait"
    hide-default-footer
  ></v-data-table>
</template>

<script>
export default {
  name: "RankingComponent",
  data() {
    return {
      headers: [
        {
          text: "Position",
          value: "position",
          align: "center",
          sortable: false,
        },
        { text: "User", value: "username", align: "center", sortable: false },
        { text: "Points", value: "points", align: "center", sortable: false },
      ],
      users: [
        {
          position: 1,
          username: "lelebus",
          points: 3455,
        },
        {
          position: 2,
          username: "lelebus",
          points: 3455,
        },
        {
          position: 3,
          username: "lelebus",
          points: 3455,
          currentUser: "selected",
        },
        {
          position: 4,
          username: "lelebus",
          points: 3455,
        },
        {
          position: 5,
          username: "lelebus",
          points: 3455,
        },
        {
          position: 6,
          username: "lelebus",
          points: 3455,
        },
        {
          position: 7,
          username: "lelebus",
          points: 3455,
        },
        {
          position: 8,
          username: "lelebus",
          points: 3455,
        },
      ],
    };
  },
  methods: {},
};
</script>

<style>
.v-data-table {
  width: 100%;
  max-width: 800px;
}
.v-data-table tr {
  cursor: default
}
.v-data-table tbody tr.selected:hover {
  background-color: #9CCC65 !important;
}
.v-data-table tbody tr:hover {
  background-color: #E8F5E9 !important;
}
</style>