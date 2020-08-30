
import config from "@/config";
import axios from "axios";
axios.defaults.withCredentials = true;

export default {

    login: async({ username, password}) => {
        return post("/login", { username: username, password: password});
    },

    logout: async() => {
        return post("/logout");
    },

    // test
    register: async(form) => {
        return post("/users", form);
    },

    me: async() => {
        return await get("/users/me");
    },

    getLessons: async () => {
        return await get("/lessons");
    },

    getLesson: async (id) => {
        return get("/lessons/" + id);
    },

    postLesson: async ({ title, description }) => {
        return post("/lessons", { title, description });
    },

    postLessonComplete: async (id) => {
        return post("/lessons/" + id);
    },

    postLessonRating: async(id, rating) => {
        return put("/lessons/" + id, { rating });
    },

    postComment: async(id, message) => {
        return post("/lessons/" + id + "/comments", {message: message});
    },

    getRanking: async() => {
        return get("/ranking");
    }
};

async function get(path, params) {
    return axios.get(config.API_BASE_URL + path, params, { withCredentials: true, validateStatus: () => true })
        .then(res => {
            return res
        })
        .catch(e => {
            throw e;
        });
}

async function post(path, params) {
    console.log("request ok")
    return await axios.post(config.API_BASE_URL + path, params, { withCredentials: true, validateStatus: () => true })
        .then(res => {
            console.log(res)
            return res
        })
        .catch(e => {
            throw e;
        });
}

async function put(path, params) {
    console.log("put request")
    return axios.put(config.API_BASE_URL + path, params, { withCredentials: true, validateStatus: () => true })
        .then(res => {
            return res
        })
        .catch(e => {
            throw e;
        });
}