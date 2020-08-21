<template>
  <div class="v-data-table">
    <v-data-table
      :headers="headers"
      :items="lessons"
      :custom-filter="filterCompleted"
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

    <modal-lesson ref="lessonModal" :item="selectedLesson"></modal-lesson>
  </div>
</template>

<script>
import ModalLesson from "@/components/ModalLesson";

export default {
  name: "LessonComponent",
  components: {
    ModalLesson,
  },
  props: {
    filterMyLessons: {
      default: false,
      type: Boolean,
    },
  },
  methods: {
    openLessonModal(item) {
      this.$refs.lessonModal.dialog = true;
      this.selectedLesson = item;
    },
    filterCompleted(value) {
      console.log(value)
      return this.filterMyLessons
    },
  },
  data() {
    return {
      dialog: false,
      search: '',
      selectedLesson: Object,
      headers: [
        { text: "Title", value: "title", align: "start" },
        { text: "Completed", value: "completed", align: "center" },
        { text: "Likes", value: "likes", align: "center" },
        { text: "Dislikes", value: "dislikes", align: "center" },
      ],
      lessons: [
        {
          id: 3,
          title: "Lesson ZHu",
          description:
            "Lorem ipsum merdetn dsaghfasdhh sdhafdshdsfjdsanjkdsa dfjasdfhadshjlkfda sdfhsjnvlkjdwasgjandjlflsadj asdkj asdkljnasjk badsnkjladn adn lkjfasdjk fnasdljkds ljk",
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
          description:
            "Lorem ipsum merdetn dsaghfasdhh sdhafdshdsfjdsanjkdsa dfjasdfhadshjlkfda sdfhsjnvlkjdwasgjandjlflsadj asdkj asdkljnasjk badsnkjladn adn lkjfasdjk fnasdljkds ljk",
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
          dislikes: 7879,
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
};
</script>

<style scoped>
.v-data-table {
  width: 100%;
  max-width: 1000px;
}
</style>