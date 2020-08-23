
import config from "@/config";
import axios from "axios";

import store from './store';

export default {
    async getMockLesson(id) {
        // let mock = {}
        return await delay(2000)
            .then(() => {
                console.log("waited")
                let lesson = store.getters.lesson(id)
                lesson.comments = [
                    {
                        timestamp: "Today",
                        username: "lelebus",
                        message: "Wow, crazy stuff!!",
                    },
                    {
                        timestamp: "13-12-1998",
                        username: "valejordao",
                        message:
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Eu volutpat odio facilisis mauris. Diam quis enim lobortis scelerisque. Phasellus egestas tellus rutrum tellus pellentesque eu tincidunt. Morbi tincidunt augue interdum velit. Venenatis tellus in metus vulputate eu. Turpis egestas pretium aenean pharetra magna ac placerat vestibulum lectus. Donec adipiscing tristique risus nec. Vel eros donec ac odio tempor orci dapibus. Lacus laoreet non curabitur gravida arcu ac tortor dignissim convallis. Purus ut faucibus pulvinar elementum integer enim neque volutpat ac. Sit amet facilisis magna etiam tempor orci eu lobortis.",
                    },
                    {
                        timestamp: "13-12-1998",
                        username: "michaelJ",
                        message: "Amazing!",
                    },
                    {
                        timestamp: "Today",
                        username: "lelebus",
                        message: "Wow, crazy stuff!!",
                    },
                    {
                        timestamp: "13-12-1998",
                        username: "valejordao",
                        message:
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Eu volutpat odio facilisis mauris. Diam quis enim lobortis scelerisque. Phasellus egestas tellus rutrum tellus pellentesque eu tincidunt. Morbi tincidunt augue interdum velit. Venenatis tellus in metus vulputate eu. Turpis egestas pretium aenean pharetra magna ac placerat vestibulum lectus. Donec adipiscing tristique risus nec. Vel eros donec ac odio tempor orci dapibus. Lacus laoreet non curabitur gravida arcu ac tortor dignissim convallis. Purus ut faucibus pulvinar elementum integer enim neque volutpat ac. Sit amet facilisis magna etiam tempor orci eu lobortis.",
                    },
                    {
                        timestamp: "13-12-1998",
                        username: "michaelJ",
                        message: "Amazing!",
                    },
                    {
                        timestamp: "Today",
                        username: "lelebus",
                        message: "Wow, crazy stuff!!",
                    },
                    {
                        timestamp: "13-12-1998",
                        username: "valejordao",
                        message:
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Eu volutpat odio facilisis mauris. Diam quis enim lobortis scelerisque. Phasellus egestas tellus rutrum tellus pellentesque eu tincidunt. Morbi tincidunt augue interdum velit. Venenatis tellus in metus vulputate eu. Turpis egestas pretium aenean pharetra magna ac placerat vestibulum lectus. Donec adipiscing tristique risus nec. Vel eros donec ac odio tempor orci dapibus. Lacus laoreet non curabitur gravida arcu ac tortor dignissim convallis. Purus ut faucibus pulvinar elementum integer enim neque volutpat ac. Sit amet facilisis magna etiam tempor orci eu lobortis.",
                    },
                    {
                        timestamp: "13-12-1998",
                        username: "michaelJ",
                        message: "Amazing!",
                    },
                    {
                        timestamp: "Today",
                        username: "lelebus",
                        message: "Wow, crazy stuff!!",
                    },
                    {
                        timestamp: "13-12-1998",
                        username: "valejordao",
                        message:
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Eu volutpat odio facilisis mauris. Diam quis enim lobortis scelerisque. Phasellus egestas tellus rutrum tellus pellentesque eu tincidunt. Morbi tincidunt augue interdum velit. Venenatis tellus in metus vulputate eu. Turpis egestas pretium aenean pharetra magna ac placerat vestibulum lectus. Donec adipiscing tristique risus nec. Vel eros donec ac odio tempor orci dapibus. Lacus laoreet non curabitur gravida arcu ac tortor dignissim convallis. Purus ut faucibus pulvinar elementum integer enim neque volutpat ac. Sit amet facilisis magna etiam tempor orci eu lobortis.",
                    },
                    {
                        timestamp: "13-12-1998",
                        username: "michaelJ",
                        message: "Amazing!",
                    },
                    {
                        timestamp: "Today",
                        username: "lelebus",
                        message: "Wow, crazy stuff!!",
                    },
                    {
                        timestamp: "13-12-1998",
                        username: "valejordao",
                        message:
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Eu volutpat odio facilisis mauris. Diam quis enim lobortis scelerisque. Phasellus egestas tellus rutrum tellus pellentesque eu tincidunt. Morbi tincidunt augue interdum velit. Venenatis tellus in metus vulputate eu. Turpis egestas pretium aenean pharetra magna ac placerat vestibulum lectus. Donec adipiscing tristique risus nec. Vel eros donec ac odio tempor orci dapibus. Lacus laoreet non curabitur gravida arcu ac tortor dignissim convallis. Purus ut faucibus pulvinar elementum integer enim neque volutpat ac. Sit amet facilisis magna etiam tempor orci eu lobortis.",
                    },
                    {
                        timestamp: "13-12-1998",
                        username: "michaelJ",
                        message: "Amazing!",
                    },
                    {
                        timestamp: "Today",
                        username: "lelebus",
                        message: "Wow, crazy stuff!!",
                    },
                    {
                        timestamp: "13-12-1998",
                        username: "valejordao",
                        message:
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Eu volutpat odio facilisis mauris. Diam quis enim lobortis scelerisque. Phasellus egestas tellus rutrum tellus pellentesque eu tincidunt. Morbi tincidunt augue interdum velit. Venenatis tellus in metus vulputate eu. Turpis egestas pretium aenean pharetra magna ac placerat vestibulum lectus. Donec adipiscing tristique risus nec. Vel eros donec ac odio tempor orci dapibus. Lacus laoreet non curabitur gravida arcu ac tortor dignissim convallis. Purus ut faucibus pulvinar elementum integer enim neque volutpat ac. Sit amet facilisis magna etiam tempor orci eu lobortis.",
                    },
                    {
                        timestamp: "13-12-1998",
                        username: "michaelJ",
                        message: "Amazing!",
                    },
                ]
                return lesson;
            })
    },

    getRanking: async () => {
        // TODO
        // return get("ranking");
        return [
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
        ]
    },

    getLessons: async () => {
        // TODO
        // return get("lessons");

        return await delay(2000)
            .then(() => {
                console.log("create Lesson");
                return [
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
                        id: 5,
                        title: "Lesson ZHu",
                        description:
                            "Lorem ipsum merdetn dsaghfasdhh sdhafdshdsfjdsanjkdsa dfjasdfhadshjlkfda sdfhsjnvlkjdwasgjandjlflsadj asdkj asdkljnasjk badsnkjladn adn lkjfasdjk fnasdljkds ljk",
                        likes: 256713,
                        dislikes: 3,
                        completed: true,
                        comments: []
                    },
                    {
                        id: 6,
                        title: "Lesson ZHu",
                        description:
                            "Lorem ipsum merdetn dsaghfasdhh sdhafdshdsfjdsanjkdsa dfjasdfhadshjlkfda sdfhsjnvlkjdwasgjandjlflsadj asdkj asdkljnasjk badsnkjladn adn lkjfasdjk fnasdljkds ljk",
                        likes: 256713,
                        dislikes: 3,
                        completed: true,
                        comments: []
                    },
                    {
                        id: 7,
                        title: "Lesson ZHu",
                        description:
                            "Lorem ipsum merdetn dsaghfasdhh sdhafdshdsfjdsanjkdsa dfjasdfhadshjlkfda sdfhsjnvlkjdwasgjandjlflsadj asdkj asdkljnasjk badsnkjladn adn lkjfasdjk fnasdljkds ljk",
                        likes: 256713,
                        dislikes: 3,
                        completed: false,
                        comments: []
                    },
                    {
                        id: 9,
                        title: "Lesson ZHu",
                        description:
                            "Lorem ipsum merdetn dsaghfasdhh sdhafdshdsfjdsanjkdsa dfjasdfhadshjlkfda sdfhsjnvlkjdwasgjandjlflsadj asdkj asdkljnasjk badsnkjladn adn lkjfasdjk fnasdljkds ljk",
                        likes: 256713,
                        dislikes: 3,
                        completed: false,
                        comments: []
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
                        id: 91,
                        title: "Lesson Last",
                        description:
                            "Lorem ipsum merdetn dsaghfasdhh sdhafdshdsfjdsanjkdsa dfjasdfhadshjlkfda sdfhsjnvlkjdwasgjandjlflsadj asdkj asdkljnasjk badsnkjladn adn lkjfasdjk fnasdljkds ljk",
                        likes: 0,
                        dislikes: 3,
                        completed: false,
                        comments: []
                    },
                ];
            })
    },

    getLesson: async (id) => {
        // TODO
        return get("lessons", { id: id });
    },

    postLesson: async ({ title, description }) => {
        // TODO
        // let id = post("lessons", { title, description })
        return await delay(2000)
            .then(() => {
                return {
                    id: 19890,
                    title: title,
                    description:description,
                    likes: 0,
                    dislikes: 0,
                    completed: false,
                    comments: []
                }
            })
    },

    updateLesson: async (id, params) => {
        // TODO
        console.log(id)
        return await delay(2000)
            .then(() => {
                return {id, params}
            })
    }
};

// async function post(path, parameters) {
//     return await axios.get(config.API_BASE_URL + path, {
//         parameters: parameters
//     })
//         .then(res => {
//             return res
//         })
//         .catch(e => {
//             throw e;
//         });
// }

async function get(path, parameters) {
    return await axios.get(config.API_BASE_URL + path, {
        parameters: parameters
    })
        .then(res => {
            return res
        })
        .catch(e => {
            throw e;
        });
}

function delay(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}