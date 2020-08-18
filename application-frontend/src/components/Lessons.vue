<template>
  <v-data-table
    :headers="headers"
    :items="lessons"
    class="elevation-1"
    loading-text="Loading... Please wait"
    hide-default-footer
  >
    <template v-slot:item.completed="{ item }">
      <v-icon v-if="item.completed" color="red lighten-1">mdi-check-circle-outline</v-icon>
    </template>

    <template v-slot:item.likes="{ item }">
      <v-chip color="light-green lighten-1" text-color="light-green darken-4">
        {{ item.likes }}
        <v-icon right>mdi-18px mdi-thumb-up</v-icon>
      </v-chip>
    </template>
    <template v-slot:item.dislikes="{ item }">
      <v-chip color="red lighten-1" text-color="red lighten-5">
        {{ item.dislikes }}
        <v-icon right>mdi-18px mdi-thumb-down</v-icon>
      </v-chip>
    </template>
  </v-data-table>
</template>

<script>
export default {
  name: "LessonComponent",
  data() {
    return {
      headers: [
        { text: "Title", value: "title", align: "start" },
        { text: "Completed", value: "completed", align: "center" },
        { text: "Likes", value: "likes", align: "center" },
        { text: "Dislikes", value: "dislikes", align: "center" },
      ],
      lessons: [
        {
          title: "Lesson 123",
          likes: 256713,
          dislikes: 3,
          completed: true,
        },
        {
          title: "Lesson 34567",
          likes: 213,
          dislikes: 3,
          completed: true,
        },
        {
          title: "Lesson 123678",
          likes: 213,
          dislikes: 3,
          completed: false,
        },
        {
          title: "Lesson 125673",
          likes: 213,
          dislikes: 3,
          completed: false,
        },
        {
          title: "Lesson 3456",
          likes: 213,
          dislikes: 3,
          completed: true,
        },
        {
          title: "Lesson 026",
          likes: 213,
          dislikes: 3,
          completed: true,
        },
        {
          title: "Lesson 353728892",
          likes: 213,
          dislikes: 3,
          completed: true,
        },
        {
          title: "Lesson Tesy",
          likes: 213,
          dislikes: 3,
          completed: true,
        },
        {
          title: "Lesson sdakkhds",
          likes: 213,
          dislikes: 3,
          completed: false,
        },
        {
          title: "Lesson r72",
          likes: 213,
          dislikes: 3,
          completed: true,
        },
        {
          title: "Lesson 3282389",
          likes: 213,
          dislikes: 3,
          completed: true,
        },
        {
          title: "Lesson 2176813",
          likes: 213,
          dislikes: 3,
          completed: true,
        },
        {
          title: "Lesson 87239",
          likes: 213,
          dislikes: 3,
          completed: true,
        },
      ],
    };
  },
  methods: {},
};
</script>

<style scoped>
.v-data-table {
  width: 100%;
  max-width: 1000px;
}
</style>