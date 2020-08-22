<template>
  <div>
    <v-row justify="center">
      <v-dialog v-model="dialog" width="600px">
        <v-card>
          <v-card-title>
            <span class="headline">{{item.title}}</span>
            <v-spacer></v-spacer>

            <v-chip color="light-green lighten-1" text-color="light-green darken-4">
              {{ item.likes }}
              <v-icon right>mdi-18px mdi-thumb-up</v-icon>
            </v-chip>
            <v-chip color="red lighten-1" text-color="red lighten-5">
              {{ item.dislikes }}
              <v-icon right>mdi-18px mdi-thumb-down</v-icon>
            </v-chip>
          </v-card-title>
          <v-card-text>{{item.description}}</v-card-text>

          <v-card-actions v-if="item.completed == false" class="center-container">
            <v-btn @click="completeLesson" class="center-x" dark color="purple">Mark as completed</v-btn>
          </v-card-actions>

          <v-card-actions v-if="item.completed == true">
            <v-badge :content="item.comments.length" :value="item.comments.length" color="purple" overlap>
              <v-btn icon @click="$refs.commentsModal.dialog = true">
                <v-icon>mdi-comment</v-icon>
              </v-btn>
            </v-badge>

            <v-btn-toggle v-model="rating" color="#EF5350" rounded group>
              <v-btn value="like" icon>
                <v-icon>mdi-thumb-up</v-icon>
              </v-btn>
              <v-btn value="dislike" icon>
                <v-icon>mdi-thumb-down</v-icon>
              </v-btn>
            </v-btn-toggle>
            <v-spacer></v-spacer>
            <v-btn color="blue darken-1" text @click="dialog = false">Close</v-btn>
            <v-btn color="blue darken-1" text @click="saveLesson">Save</v-btn>
          </v-card-actions>
        </v-card>
        <modal-comments ref="commentsModal" :comments="item.comments"></modal-comments>
      </v-dialog>
    </v-row>

    <notify-success ref="successNotification" message="Lesson has been saved successfully"></notify-success>
    <notify-failure
      ref="failureNotification"
      message="An error occurred in saving this lesson. Try again."
    ></notify-failure>
  </div>
</template>

<script>
import ModalComments from "@/components/dialogs/DialogComments";
import NotifySuccess from "@/components/snackbars/NotifySuccess";
import NotifyFailure from "@/components/snackbars/NotifyFailure";

export default {
  name: "ModalLesson",
  components: {
    ModalComments,
    NotifySuccess,
    NotifyFailure,
  },
  props: {
    id: Number
  },
  methods: {
    completeLesson() {
      console.log("lesson completed");
      // TODO: save data
      this.item.completed = true;
    },
    saveLesson() {
      console.log("saveLesson");

      let success = true;
      // TODO: save data
      if (success == true) {
        this.$refs.successNotification.snackbar = true;
      } else {
        this.$refs.failureNotification.snackbar = true;
      }
    },
  },
  data: () => ({
    dialog: false,
    item: Object,
    rating: String
  }),
  watch: {
    dialog() {
      this.item = this.$store.getters.lesson(this.id)
    }
  }
};
</script>

<style scoped>
.v-card__text {
  padding-bottom: 1.5rem;
}
.v-card__title .v-chip {
  margin-right: 1rem;
}
.v-card__actions {
  padding: 1rem 24px;
}
.v-card__actions .v-btn-toggle > .v-btn {
  border-radius: 50%;
}
.v-card__actions .v-badge {
  margin-right: 2rem;
}
</style>