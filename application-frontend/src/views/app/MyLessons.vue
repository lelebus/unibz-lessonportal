<template>
  <v-container fluid>
    <v-layout style="display: block">
      <div class="center-container">
        <h1 class="center-x">My Lessons</h1>
      </div>
      <div class="center-container">
        <lesson-component class="center-x" :lessons="lessons"></lesson-component>
      </div>
    </v-layout>
  </v-container>
</template>

<script>
import LessonComponent from "@/components/app/Lessons";

export default {
  name: "MyLessons",
  components: {
    LessonComponent,
  },
  computed: {
    lessons() {
      return this.$store.state.lessons.filter( lesson => lesson.completed );
    },
  },
};
</script>