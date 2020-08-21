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
                      v-model="title"
                      label="Title*"
                      :rules="[v => !!v || 'Title is required']"
                      required
                    ></v-text-field>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col col="12">
                    <v-textarea
                      v-model="description"
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
            <v-btn color="blue darken-1" text @click="addNewLesson" :disabled="!validFormInput">Save</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>

    <notify-success ref="successNotification" message="Lesson has been created successfully"></notify-success>
    <notify-failure ref="failureNotification" message="An error occurred in creating new lesson. Try again."></notify-failure>
  </div>
</template>


<script>
import NotifySuccess from "@/components/NotifySuccess";
import NotifyFailure from "@/components/NotifyFailure";

export default {
  name: "LessonComponent",
  components: {
    NotifySuccess,
    NotifyFailure,
  },
  methods: {
    addNewLesson() {
      console.log("add new lesson");

      if (!this.title) {
        return;
      }

      let success = true;
      // TODO: save data
      if (success == true) {
        this.$refs.successNotification.snackbar = true;
      } else {
        this.$refs.failureNotification.snackbar = true;
        return
      }

      // TODO open created lesson

      this.validFormInput = false;
      this.dialog = false;
      this.$refs.form.reset();
    },
  },
  data: () => ({
    dialog: false,
    validFormInput: false,
  }),
};
</script>