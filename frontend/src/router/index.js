import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../pages/Login'
import Home from '../pages/Home'

Vue.use(VueRouter)
export default new VueRouter({
    mode: 'history',
    routes: [
        {
            path: '/',
            name: "login",
            component: Login
        },
        {
            path: '/home',
            name: "Home",
            component: Home
        },
    ]
})