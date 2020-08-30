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
                            :rules="[v => !!v || 'Username is required', rules.lowercase]"
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
                    @click="register"
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

    <notify-failure ref="failureNotification" :message="errorMessage"></notify-failure>
  </div>
</template>


<script>
import NotifyFailure from "@/components/snackbars/NotifyFailure";

export default {
  name: "ModalLogin",
  components: {
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
      errorMessage: "Error occurred. Try again.",
      rules: {
        lowercase: (v) =>
          (v || "").match(/^(?=.*[a-z]).+$/) ||
          "Username can be composed of only lowercase letters",
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
      this.$store
        .dispatch("login", { username: this.username, password: this.password })
        .then(() => {
          this.$store.dispatch("fetchLessons");
          this.$store.dispatch("fetchRanking");

          this.$refs.form.reset();
          this.validFormInput = false;
        })
        .catch(e => {
          this.errorMessage = e.toString()
          this.$refs.failureNotification.snackbar = true;
        });
    },
    register() {
      this.$store
        .dispatch("register", { name: this.name, username: this.username, password: this.password })
        .then(() => {
          this.login()
        })
        .catch( e => {
          this.errorMessage = e.toString()
          this.$refs.failureNotification.snackbar = true;
        });
    },
  },
  computed: {
    loading() {
      if (this.dialog == false) return false;
      return this.$store.state.loading;
    },
    dialog() {
      return this.$store.state.user == null;
    },
  },
};
</script>