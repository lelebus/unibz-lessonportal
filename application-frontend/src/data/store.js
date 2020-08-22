import Vue from 'vue';
import Vuex from 'vuex';
import api from './api';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
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
    addLesson(state, lesson) {
      state.lessons.push(lesson)
    },
    setLessons(state, lessons) {
      state.lessons = lessons
    },
    setLesson(state, lesson) {
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
    createLesson: async ({ commit }, lesson) => {
      let success = true
      commit('loading')
      try {
        console.log("create title:" + lesson.title)
        let createdLesson = await api.postLesson(lesson);
        commit('addLesson', createdLesson)
      } catch (e) {
        console.error(e)
        success = false
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
        let lessons = await api.getLessons();
        commit('setLessons', lessons)
      } catch (e) {
        console.error(e)
        // TODO
      }
      commit('stopLoading')
    },
    fetchLesson: async ({ commit }, id) => {
      commit('loading')
      try {
        let lesson = await api.getMockLesson(id); // mock
        commit('setLesson', lesson)
      } catch (e) {
        console.error(e)
        // TODO
      }
      commit('stopLoading')
    },
    completeLesson: async ({ commit, getters }, { id }) => {
      let success = true
      commit('loading')
      let lesson = getters.lesson(id)
      try {
        console.log("update this lesson: " + id)
        await api.updateLesson(lesson, { completed: true });
        lesson.completed = true;
        console.log("waited update")
        commit('setLesson', lesson)
      } catch (e) {
        console.error(e)
        success = false
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
    saveLesson: async ({ commit, getters }, { id, rating }) => {
      let success = true
      commit('loading')
      let lesson = getters.lesson(id)

      try {
        if (lesson.rating !== rating) {
          await api.updateLesson(lesson);
          lesson.rating = rating
          commit('setLesson', lesson)
        }
      } catch (e) {
        console.error(e)
        success = false
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
        let ranking = await api.getRanking();
        commit('setRanking', ranking)
      } catch (e) {
        console.error(e)
        // TODO
      }
      commit('stopLoading')
    }
  },

  getters: {
    lesson: state => id => {
      let lesson = state.lessons.find(item => item.id === id);
      console.log(typeof lesson)
      return lesson;
    },
    completedLessons: state => {
      return state.lessons.filter(lesson => lesson.completed);
    }
  }
})