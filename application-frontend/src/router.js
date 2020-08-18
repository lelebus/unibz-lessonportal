import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);
export default new Router({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [
        {
            path: '/lessons',
            name: 'lessons',
            component: () => import('./views/Lessons.vue')
        },
        {
            path: '/mylessons',
            name: 'my-lessons',
            component: () => import('./views/MyLessons.vue')
        },
        {
            path: '/ranking',
            name: 'ranking',
            component: () => import('./views/Ranking.vue')
        },
        {
            path: '/login',
            name: 'login',
            component: () => import('./views/Login.vue')
        }
    ]
});