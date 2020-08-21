<template>
  <div>
    <v-card flat tile width="100%" class="red lighten-1 text-center">
      <!-- <v-card-title class="float-left">{{appTitle}}</v-card-title> -->
      <v-card-text>
        <v-btn class="mx-4 float-left" icon href="/logout" style="visibility: hidden">
          <v-icon size="24px">mdi-logout</v-icon>
        </v-btn>

        <v-btn v-for="section in sections" :key="section" class="mx-4" :to="section.section" icon>
          <v-icon size="24px">{{ section.icon }}</v-icon>
        </v-btn>

        <v-btn class="mx-4 float-right" icon href="/logout">
          <v-icon size="24px">mdi-logout</v-icon>
        </v-btn>
      </v-card-text>
    </v-card>
  </div>
</template>

<script>
export default {
  name: "AppNavigation",

  data() {
    return {
      appTitle: "Final Project",
      sections: [
        {
          section: "lessons",
          icon: "mdi-file-find",
        },
        {
          section: "mylessons",
          icon: "mdi-certificate",
        },
        {
          section: "ranking",
          icon: "mdi-star-circle",
        },
      ],
    };
  },
};
</script>

<style scoped>
</style>