import Vue from 'vue';
import Router from 'vue-router';
import config from '@/config';

Vue.use(Router);

export default new Router({
    mode: 'history',
    base: config.BASE_URL,
    routes: [
        {
            path: '/lessons',
            name: 'lessons',
            component: () => import('./app/Lessons.vue')
        },
        {
            path: '/mylessons',
            name: 'my-lessons',
            component: () => import('./app/MyLessons.vue')
        },
        {
            path: '/ranking',
            name: 'ranking',
            component: () => import('./app/Ranking.vue')
        }
    ]
});

// Router.beforeEach((to, from, next) => {
//     // redirect to login page if not logged in and trying to access a restricted page
//     const publicPages = ['/login', '/register'];
//     const authRequired = !publicPages.includes(to.path);
//     const loggedIn = localStorage.getItem('user');

//     if (authRequired && !loggedIn) {
//         return next('/login');
//     }

//     next();
// })