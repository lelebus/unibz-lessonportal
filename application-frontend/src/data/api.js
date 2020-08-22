
import config from "@/config";
import axios from "axios";

export default {
    getMockLesson() {
        return {
            id: 3,
            title: "Lesson ZHu",
            description:
                "Lorem ipsum merdetn dsaghfasdhh sdhafdshdsfjdsanjkdsa dfjasdfhadshjlkfda sdfhsjnvlkjdwasgjandjlflsadj asdkj asdkljnasjk badsnkjladn adn lkjfasdjk fnasdljkds ljk",
            likes: 256713,
            dislikes: 3,
            completed: true,
            comments: [
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
            ],
        }
    },

    getLesson: async (id) => {
        return get("lessons", { id: id });
    }
}

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