<template>
  <v-container fluid>
    <v-layout style="display: block">
      <div class="center-container">
        <h1 class="center-x">Lessons</h1>
      </div>
      <div class="center-container">
        <lesson-component class="center-x"></lesson-component>
      </div>

      <div class="text-center">
        <v-btn
          @click="$refs.newLesson.dialog = true"
          style="margin-top: 1.5rem"
          class="mx-2"
          fab
          dark
          large
          color="purple"
        >
          <v-icon dark>mdi-shape-square-rounded-plus</v-icon>
        </v-btn>
      </div>
      <modal-new-lesson ref="newLesson"></modal-new-lesson>
    </v-layout>
  </v-container>
</template>

<script>
import LessonComponent from "@/components/Lessons";
import ModalNewLesson from "@/components/ModalNewLesson";

export default {
  name: "Lessons",
  components: {
    LessonComponent,
    ModalNewLesson,
  },
};
</script>