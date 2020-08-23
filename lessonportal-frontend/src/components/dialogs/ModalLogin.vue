<template>
  <div>
    <v-row justify="center">
      <v-dialog v-model="dialog" persistent max-width="600px">
        <v-card>
          <v-card-title></v-card-title>
          <v-tabs v-model="tab" centered color="red lighten-1">
            <v-tabs-slider></v-tabs-slider>
            <v-tab>Login</v-tab>
            <v-tab>Register</v-tab>

            <v-tab-item>
              <v-card flat>
                <v-card-text>
                  <v-container centered>
                    <v-form v-model="validFormInput" ref="form">
                      <v-row justify="center">
                        <v-col cols="8">
                          <v-text-field
                            v-model="username"
                            label="Username"
                            :rules="[v => !!v || 'Username is required']"
                            color="purple"
                            required
                          ></v-text-field>
                        </v-col>
                      </v-row>
                      <v-row justify="center">
                        <v-col cols="8">
                          <v-text-field
                            v-model="password"
                            :rules="[rules.length(6)]"
                            color="purple"
                            counter="6"
                            label="Password"
                            type="password"
                            required
                          ></v-text-field>
                        </v-col>
                      </v-row>
                    </v-form>
                  </v-container>
                </v-card-text>
                <v-card-actions class="center-container">
                  <v-btn
                    @click="login"
                    :loading="loading"
                    class="center-x"
                    dark
                    color="red lighten-1"
                    style="margin-bottom: 2rem"
                  >Login</v-btn>
                </v-card-actions>
              </v-card>
            </v-tab-item>

            <v-tab-item>
              <v-card flat>
                <v-card-text>
                  <v-container centered>
                    <v-form v-model="validFormInput" ref="form">
                      <v-row justify="center">
                        <v-col cols="8">
                          <v-text-field
                            v-model="name"
                            label="Name"
                            :rules="[v => !!v || 'Name is required']"
                            color="purple"
                            required
                          ></v-text-field>
                        </v-col>
                      </v-row>
                      <v-row justify="center">
                        <v-col cols="8">
                          <v-text-field
                            v-model="username"
                            label="Username"
                            :rules="[v => !!v || 'Username is required']"
                            color="purple"
                            required
                          ></v-text-field>
                        </v-col>
                      </v-row>
                      <v-row justify="center">
                        <v-col cols="8">
                          <v-text-field
                            v-model="password"
                            :rules="[rules.length(6), rules.password]"
                            color="purple"
                            counter="6"
                            label="Password"
                            type="password"
                            required
                          ></v-text-field>
                        </v-col>
                      </v-row>
                    </v-form>
                  </v-container>
                </v-card-text>
                <v-card-actions class="center-container">
                  <v-btn
                    @click="login"
                    :loading="loading"
                    class="center-x"
                    dark
                    color="purple"
                    style="margin-bottom: 2rem"
                  >Register</v-btn>
                </v-card-actions>
              </v-card>
            </v-tab-item>
          </v-tabs>
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
  name: "ModalLogin",
  components: {
    NotifySuccess,
    NotifyFailure,
  },
  data: () => {
    return {
      dialog: true,
      tab: null,
      validFormInput: false,
      name: "",
      username: "",
      password: "",
      rules: {
        length: (len) => (v) =>
          (v || "").length >= len ||
          `This field must be at least ${len} characters long`,
        password: (v) =>
          !!(v || "").match(
            /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*(_|[^\w])).+$/
          ) ||
          "Password must contain an upper case letter, a numeric character, and a special character",
        required: (v) => !!v || "This field is required",
      },
    };
  },
  methods: {
    login() {
      console.log("login");
    },
  },
};
</script>