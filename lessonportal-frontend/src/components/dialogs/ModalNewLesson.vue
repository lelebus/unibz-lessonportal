<template>
  <div>
    <v-row justify="center">
      <v-dialog v-model="dialog" persistent max-width="600px">
        <v-card>
          <v-card-title>
            <span class="headline">Add new lesson</span>
          </v-card-title>
          <v-card-text>
            <v-container>
              <v-form v-model="validFormInput" ref="form">
                <v-row>
                  <v-col cols="12">
                    <v-text-field
                      v-model="lesson.title"
                      label="Title*"
                      :rules="[v => !!v || 'Title is required']"
                      required
                    ></v-text-field>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col col="12">
                    <v-textarea
                      v-model="lesson.description"
                      outlined
                      name="description"
                      label="Description"
                      value
                    ></v-textarea>
                  </v-col>
                </v-row>
              </v-form>
            </v-container>
            <small>*indicates required field</small>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="blue darken-1" text @click="dialog = false">Close</v-btn>
            <v-btn color="blue darken-1" text @click="addNewLesson" :disabled="!validFormInput" :loading="loading">Save</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>

    <notify-success ref="successNotification" message="Lesson has been created successfully"></notify-success>
    <notify-failure
      ref="failureNotification"
      message="An error occurred in creating new lesson. Try again."
    ></notify-failure>
  </div>
</template>


<script>
import NotifySuccess from "@/components/snackbars/NotifySuccess";
import NotifyFailure from "@/components/snackbars/NotifyFailure";

export default {
  name: "LessonComponent",
  components: {
    NotifySuccess,
    NotifyFailure,
  },
  methods: {
    addNewLesson() {
      if (!this.lesson.title) {
        return;
      }
      
      this.$store
        .dispatch("createLesson", this.lesson)
        .then(() => {
          this.$refs.successNotification.snackbar = true;
          this.validFormInput = false;
          this.dialog = false;
          this.$refs.form.reset();
        })
        .catch(() => {
          this.$refs.failureNotification.snackbar = true;
        });

      // TODO open created lesson
    },
  },
  data: () => ({
    dialog: false,
    validFormInput: false,
    lesson: {
      title: "",
      description: "",
    },
  }),
  computed: {
    loading() {
      if (this.dialog == false) return false;
      return this.$store.state.loading;
    },
  },
};
</script>