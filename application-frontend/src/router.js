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
            component: () => import('./views/app/Lessons.vue')
        },
        {
            path: '/mylessons',
            name: 'my-lessons',
            component: () => import('./views/app/MyLessons.vue')
        },
        {
            path: '/ranking',
            name: 'ranking',
            component: () => import('./views/app/Ranking.vue')
        },
        {
            path: '/login',
            name: 'login',
            component: () => import('./views/Login.vue')
        },
        {
            path: '/register',
            name: 'registration',
            component: () => import('./views/Registration.vue')
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