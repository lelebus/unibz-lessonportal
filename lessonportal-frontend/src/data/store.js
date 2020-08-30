import Vue from 'vue';
import Vuex from 'vuex';
import api from './api';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    user: null,
    loading: false,
    loadingCount: 0,
    lessons: [],
    ranking: []
  },

  mutations: {
    loading(state) {
      state.loadingCount++;
      state.loading = true;
    },

    stopLoading(state) {
      state.loadingCount--;
      if (state.loadingCount == 0) {
        state.loading = false;
      }
    },

    setUser(state, username) {
      console.log(username)
      state.user = username
    },

    addLesson(state, lesson) {
      state.lessons.push(lesson)
    },

    setLessons(state, lessons) {
      console.log(lessons)
      state.lessons = lessons
    },

    setLesson(state, lesson) {
      console.log(lesson)
      console.log(lesson.id)
      let currentLesson = state.lessons.find(item => item.id === lesson.id);
      currentLesson.likes = lesson.likes
      currentLesson.dislikes = lesson.dislikes
      currentLesson.comments = lesson.comments
      currentLesson.rating = lesson.rating
      currentLesson.completed = lesson.completed
    },

    setRanking(state, ranking) {
      state.ranking = ranking
    }
  },

  actions: {
    me: async ({ commit }) => {
      let success = false
      commit('loading')
      try {
        let res = await api.me();
        switch (res.status) {
          case 200:
            commit('setUser', res.data.username)
            success = true
            break;
          case 401:
            commit('setUser', null)
            break;
          default:
            throw res.statusText;
        }
      } catch (e) {
        console.error(e)
      }
      commit('stopLoading')
      return new Promise((resolve, reject) => {
        if (!success) {
          reject();
        } else {
          resolve();
        }
      })
    },

    register: async ({ commit }, registerForm) => {
      let success = false
      let errorMessage
      commit('loading')
      try {
        let res = await api.register(registerForm);
        switch (res.status) {
          case 200:
            success = true
            break;
          case 409:
            errorMessage = "This username is already associated to another account. Choose another one."
            break;
          default:
            throw res.statusText;
        }
      } catch (e) {
        console.error(e)
      }
      commit('stopLoading')
      return new Promise((resolve, reject) => {
        if (!success && errorMessage.length > 0) {
          reject(new Error(errorMessage));
        } else if (!success) {
          reject();
        } else {
          resolve();
        }
      })
    },


    login: async ({ commit }, { username, password }) => {
      let success = false
      let errorMessage
      commit('loading')
      try {
        let res = await api.login({ username, password });
        switch (res.status) {
          case 200:
            success = true
            commit('setUser', username)
            break;
          case 401:
            errorMessage = "Wrong credentials."
            break;
          default:
            throw res.statusText;
        }
      } catch (e) {
        console.error(e)
      }
      commit('stopLoading')
      return new Promise((resolve, reject) => {
        if (!success && errorMessage.length > 0) {
          reject(new Error(errorMessage));
        } else if (!success) {
          reject();
        } else {
          resolve();
        }
      })
    },

    logout: async ({ commit }) => {
      commit('loading')
      try {
        await api.logout();
        commit('setUser', null)
      } catch (e) {
        console.error(e)
      }
      commit('stopLoading')
    },

    createLesson: async ({ commit }, lesson) => {
      let success = false
      commit('loading')
      try {
        let res = await api.postLesson(lesson);
        switch (res.status) {
          case 200:
            success = true
            commit('addLesson', res.data)
            break;
          case 401:
            commit('setUser', null)
            break;
          default:
            throw res.statusText;
        }
      } catch (e) {
        console.error(e)
      }
      commit('stopLoading')
      return new Promise((resolve, reject) => {
        if (!success) {
          reject();
        } else {
          resolve();
        }
      })
    },

    fetchLessons: async ({ commit }) => {
      commit('loading')
      try {
        let res = await api.getLessons();
        switch (res.status) {
          case 200:
            commit('setLessons', res.data)
            break;
          case 401:
            commit('setUser', null)
            break;
          default:
            throw res.statusText;
        }
      } catch (e) {
        console.error(e)
      }
      commit('stopLoading')
    },

    fetchLesson: async ({ commit }, id) => {
      commit('loading')
      try {
        let res = await api.getLesson(id);
        switch (res.status) {
          case 200:
            commit('setLesson', res.data)
            break;
          case 401:
            commit('setUser', null)
            break;
          default:
            throw res.statusText;
        }
      } catch (e) {
        console.error(e)
      }
      commit('stopLoading')
    },

    completeLesson: async ({ commit, getters }, { id }) => {
      let success = false
      commit('loading')
      let lesson = getters.lesson(id)
      try {
        let res = await api.postLessonComplete(id);
        switch (res.status) {
          case 200:
            success = true
            lesson.complete = true
            commit('setLesson', lesson)
            break;
          case 401:
            commit('setUser', null)
            break;
          default:
            throw res.statusText;
        }
      } catch (e) {
        console.error(e)
      }
      commit('stopLoading')
      return new Promise((resolve, reject) => {
        if (!success) {
          reject();
        } else {
          resolve();
        }
      })
    },

    saveLesson: async ({ commit, getters }, { id, rating, comment }) => {
      let success = false
      commit('loading')
      let lesson = getters.lesson(id)
      try {
        if (rating && lesson.rating !== rating) {
          console.log("rating")
          let res = await api.postLessonRating(lesson.id, rating);
          switch (res.status) {
            case 200:
              success = true
              lesson.rating = rating
              commit('setLesson', lesson)
              break;
            case 401:
              commit('setUser', null)
              break;
            default:
              throw res.statusText;
          }
        }
        if (comment) {
          console.log("comments")
          let res = await api.postComment(lesson.id, comment);
          switch (res.status) {
            case 200:
              success = true
              lesson.comments = res.data
              commit('setLesson', lesson)
              break;
            case 401:
              commit('setUser', null)
              break;
            default:
              throw res.statusText;
          }
        }
      } catch (e) {
        console.error(e)
      }
      commit('stopLoading')
      return new Promise((resolve, reject) => {
        if (!success) {
          reject();
        } else {
          resolve();
        }
      })
    },

    fetchRanking: async ({ commit }) => {
      commit('loading')
      try {
        let res = await api.getRanking();
        switch (res.status) {
          case 200:
            commit('setRanking', res.data)
            break;
          case 401:
            commit('setUser', null)
            break;
          default:
            throw res.statusText;
        }
      } catch (e) {
        console.error(e)
      }
      commit('stopLoading')
    }
  },

  getters: {
    lesson: state => id => {
      console.log("looking for" + id)
      return state.lessons.find(item => item.id === id);
    },

    completedLessons: state => {
      return state.lessons.filter(lesson => lesson.complete == true);
    }
  }
})