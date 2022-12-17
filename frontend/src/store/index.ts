import {defineStore} from "pinia";

export const useGlobalState = defineStore("global", {
    state() {
        return {
            isPractice: false,
            flag: false,
            userInfo: null,
            menus: [
                {
                    index: 1,
                    title: "考试管理",
                    icon: "icon-course-table",
                    content: [
                        {
                            item: "功能介绍",
                            path: "/examDescription"
                        },

                        {
                            item: "考试查询",
                            path: "/selectExam"
                        },

                        {
                            item: "添加考试",
                            path: "/addExam"
                        }
                    ]
                },

                {
                    index: 1,
                    title: "题库管理",
                    icon: "icon-questions",
                    content: [
                        {
                            item: "功能介绍",
                            path: "/answerDescription"
                        },

                        {
                            item: "所有题库",
                            path: "/selectAnswer"
                        },

                        {
                            item3: "增加题库",
                            path: "/addAnswer"
                        },

                        {
                            path: "/addAnswerChildren"
                        }
                    ]
                },

                {
                    index: 3,
                    title: "成绩查询",
                    icon: "icon-performance",
                    content: [
                        {
                            item: "学生成绩查询",
                            path: "/allStudentsGrade"
                        },

                        {
                            path: "/grade"
                        },

                        {
                            item: "成绩分段查询",
                            path: "/selectExamToPart"
                        },

                        {
                            path: "/scorePart"
                        }
                    ]
                },

                {
                    index: 4,
                    title: "学生管理",
                    icon: "icon-role",
                    content: [
                        {
                            item: "学生管理",
                            path:"/studentManage"
                        },
                        {
                            item: "添加学生",
                            path: "/addStudent"
                        }
                    ]
                }
            ]
        }
    },

    actions: {

    }
})