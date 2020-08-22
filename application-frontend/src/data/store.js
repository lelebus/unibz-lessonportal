import Vue from 'vue';
import Vuex from 'vuex';
import api from './api';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    loading: false,
    lessons: [
      {
        id: 3,
        title: "Lesson ZHu",
        description:
          "Lorem ipsum merdetn dsaghfasdhh sdhafdshdsfjdsanjkdsa dfjasdfhadshjlkfda sdfhsjnvlkjdwasgjandjlflsadj asdkj asdkljnasjk badsnkjladn adn lkjfasdjk fnasdljkds ljk",
        likes: 256713,
        dislikes: 3,
        completed: true,
        comments: []
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
      //   {
      //     title: "Lesson 353728892",
      //     likes: 213,
      //     dislikes: 7879,
      //     completed: true,
      //   },
      //   {
      //     title: "Lesson Tesy",
      //     likes: 213,
      //     dislikes: 3,
      //     completed: true,
      //   },
      //   {
      //     title: "Lesson sdakkhds",
      //     likes: 213,
      //     dislikes: 3,
      //     completed: false,
      //   },
      //   {
      //     title: "Lesson r72",
      //     likes: 213,
      //     dislikes: 3,
      //     completed: true,
      //   },
      //   {
      //     title: "Lesson 3282389",
      //     likes: 213,
      //     dislikes: 3,
      //     completed: true,
      //   },
      //   {
      //     title: "Lesson 2176813",
      //     likes: 213,
      //     dislikes: 3,
      //     completed: true,
      //   },
      {
        title: "Lesson 87239",
        likes: 213,
        dislikes: 3,
        completed: false,
      },
    ],
    ranking: [
      {
        position: 1,
        username: "lelebus",
        points: 3455,
      },
      {
        position: 2,
        username: "lelebus",
        points: 3455,
      },
      {
        position: 3,
        username: "lelebus",
        points: 3455,
        currentUser: "selected",
      },
      {
        position: 4,
        username: "lelebus",
        points: 3455,
      },
      {
        position: 5,
        username: "lelebus",
        points: 3455,
      },
      {
        position: 6,
        username: "lelebus",
        points: 3455,
      },
      {
        position: 7,
        username: "lelebus",
        points: 3455,
      },
      {
        position: 8,
        username: "lelebus",
        points: 3455,
      },
    ],
  },
  mutations: {
    startOperation(state) {
      console.log("Start loading")
      console.log(this.state)
      state.loading = true;
    },
    setLesson(state, lesson) {
      let currentLessonIndex = state.lessons.findIndex(item => item.id === lesson.id);
      console.log(lesson.comments)
      state.lessons[currentLessonIndex] = lesson
      console.log(state.lessons[currentLessonIndex])
    }
  },
  actions: {
    fetchLesson: async ({ commit }, id) => {
      commit('startOperation')
      try {
        let lesson = await api.getMockLesson(id) //TODO: mock
        console.log("commit")
        commit('setLesson', lesson)
      } catch (e) {
        console.error(e)
        // TODO
      }
    },
  },
  getters: {
    lesson: state => id => {
      return state.lessons.find(item => item.id === id);
    }
  }
})