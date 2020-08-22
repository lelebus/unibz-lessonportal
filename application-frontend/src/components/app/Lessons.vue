<template>
  <div class="v-data-table">
    <v-data-table
      :headers="headers"
      :items="lessons"
      @click:row="openLessonModal"
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

    <modal-lesson ref="lessonModal" :id="selectedLesson"></modal-lesson>
  </div>
</template>

<script>
import ModalLesson from "@/components/dialogs/ModalLesson";

export default {
  name: "LessonComponent",
  components: {
    ModalLesson,
  },
  props: {
    lessons: Array,
  },
  methods: {
    openLessonModal(item) {
      this.$store.dispatch('fetchLesson', item.id);
      this.selectedLesson = item.id
      
      this.$refs.lessonModal.dialog = true;
    }
  },
  data() {
    return {
      dialog: false,
      selectedLesson: Object,
      headers: [
        { text: "Title", value: "title", align: "start" },
        { text: "Completed", value: "completed", align: "center" },
        { text: "Likes", value: "likes", align: "center" },
        { text: "Dislikes", value: "dislikes", align: "center" },
      ],
    };
  },
};
</script>

<style scoped>
.v-data-table {
  width: 100%;
  max-width: 1000px;
}
</style>