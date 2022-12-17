import {createRouter, createWebHistory, RouteRecordRaw} from 'vue-router'

const routes: Array<RouteRecordRaw> = [
    {
        path: '/about',
        name: 'about',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
    },

    {
        path: "/",
        name: "login",
        component: () => import("@/components/common/Login.vue")
    },

    {
        path: "/index",
        component: () => import("@/components/admin/index.vue"),
        children: [
            {
                path: "/",
                component: () => import("@/components/common/Hello.vue")
            },

            {
                path: "/grade",
                component: () => import("@/components/charts/Grade.vue")
            },

            {
                path: '/selectExamToPart', //学生分数段
                component: () => import("@/components/teacher/SelectExamToPart.vue")
            },

            {
                path: '/allStudentsGrade', //所有学生成绩统计
                component: () => import("@/components/teacher/AllStudentsGrade.vue")
            },

            {
                path: '/examDescription', //考试管理功能描述
                component: () => import("@/components/teacher/ExamDescription.vue")
            },
            {
                path: '/selectExam', //查询所有考试
                component: () => import("@/components/teacher/SelectExam.vue")
            },
            {
                path: '/addExam', //添加考试
                component: () => import("@/components/teacher/AddExam.vue")
            },
            {
                path: '/answerDescription', //题库管理功能介绍
                component: () => import("@/components/teacher/AnswerDescription.vue")
            },
            {
                path: '/selectAnswer', //查询所有题库
                component: () => import("@/components/teacher/SelectAnswer.vue")
            },
            {
                path: '/addAnswer', //增加题库主界面
                component: () => import("@/components/teacher/AddAnswer.vue")
            },
            {
                path: '/addAnswerChildren', //点击试卷跳转到添加题库页面
                component: () => import("@/components/teacher/AddAnswerChildren.vue")
            },
            {
                path: '/studentManage', //学生管理界面
                component: () => import("@/components/teacher/StudentManage.vue")
            },
            {
                path: '/addStudent', //添加学生
                component: () => import("@/components/teacher/AddStudent.vue")
            },
            {
                path: '/teacherManage',
                component: () => import('@/components/admin/TeacherManage.vue')
            },
            {
                path: '/addTeacher',
                component: () => import ("@/components/admin/AddTeacher.vue")
            }
        ]

    },

    {
        path: "/student",
        component: () => import("@/components/student/index.vue"),
        children: [
            {
                path: "/",
                component: ()=> import("@/components/student/Exam.vue")
            },

            {
                path: "/startExam",
                component: () => import("@/components/student/StartExam.vue")
            },

            {
                path: "/examMessage",
                component: () => import("@/components/student/ExamMessage.vue")
            },

            {
                path: "/message",
                component: () => import("@/components/student/Message.vue")
            },

            {
                path: "/studentScore",
                component: () => import("@/components/student/AnswerScore.vue")
            },

            {
                path: "/scoreTable",
                component: () => import("@/components/student/ScoreTable.vue")
            }

        ]
    },
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router
