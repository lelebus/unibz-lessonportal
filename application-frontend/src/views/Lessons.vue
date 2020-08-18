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
        <v-btn href="/newlesson" style="margin-top: 1.5rem" class="mx-2" fab dark large color="purple">
          <v-icon dark>mdi-shape-square-rounded-plus</v-icon>
        </v-btn>
      </div>
    </v-layout>
  </v-container>
</template>

<script>
import LessonComponent from "@/components/Lessons";

export default {
  name: "Lessons",
  components: {
    LessonComponent,
  },
};
</script>