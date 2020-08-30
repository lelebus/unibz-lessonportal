<template>
  <v-row justify="center">
    <v-dialog v-model="dialog" fullscreen hide-overlay transition="dialog-bottom-transition">
      <v-app-bar color="purple" elevate-on-scroll scroll-target="#comments-scrolling" dark>
        <v-btn icon dark @click="dialog = false">
          <v-icon>mdi-close</v-icon>
        </v-btn>
        <v-toolbar-title>Comments</v-toolbar-title>
      </v-app-bar>
      <v-sheet id="comments-scrolling" ref="commentsScrolling" class="overflow-y-auto">
        <v-container>
          <template v-for="(item, index) in comments">
            <v-row :key="item.timestamp">
              <div class="comment">
                <div class="header d-flex justify-space-between">
                  <div class="font-weight-bold">{{item.username}}</div>
                  <!-- <span class="grey--text text--darken-1">-</span> -->
                  <div class="grey--text text--darken-1">{{item.timestamp}}</div>
                </div>
                <div class="message">{{item.message}}</div>
              </div>
            </v-row>
            <v-divider v-if="index + 1< comments.length" :key="index"></v-divider>
          </template>
        </v-container>
      </v-sheet>

      <div class="input-section">
        <v-container>
          <v-textarea
            class="mx-2"
            v-model="commentMessage"
            label="Comment this lesson"
            rows="1"
            color="red lighten-1"
            auto-grow
          >
            <template slot="append-outer">
              <v-btn @click="sendComment" icon color="red lighten-1">
                <v-icon>mdi-send</v-icon>
              </v-btn>
            </template>
          </v-textarea>
        </v-container>
      </div>
    </v-dialog>
  </v-row>
</template>

<script>
export default {
  name: "ModalComments",
  props: {
    lessonId: Number,
  },
  data: () => ({
    dialog: false,
    commentMessage: "",
  }),
  methods: {
    sendComment() {
      if (!this.commentMessage.length >= 1) {
        return;
      }

      this.$store
        .dispatch("saveLesson", {
          id: this.lessonId,
          comment: this.commentMessage,
        })
        .then();

      // this.$store.dispatch("fetchLesson", this.lessonId)

      // TODO: scroll to bottom
      this.commentMessage = "";
    },
  },
  computed: {
    comments() {
      if (this.lessonId == null) {
        return;
      }
      return this.$store.getters.lesson(this.lessonId).comments;
    }
  }
};
</script>

<style scoped>
.comment {
  width: 100%;
  padding: 2rem;
}
.comment .header {
  margin-bottom: 1rem;
}
.comment .message {
  max-width: 90%;
}
#comments-scrolling {
  max-height: calc(100vh - 210px);
}
.input-section {
  box-shadow: 0px 2px 4px 3px rgba(0, 0, 0, 0.2), 0px 4px 5px 0px rgba(0, 0, 0, 0.14), 0px 1px 10px 0px rgba(0, 0, 0, 0.12);
}
</style>