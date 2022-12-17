declare interface Role {
    id: number,
    name: string
}

declare interface RegisterRequest {
    username: string,
    passwordHash: string,
}

declare interface RegisterRoleRequest {
    name: string
}

declare interface LoginRequest {
    username: string,
    passwordHash: string
}

declare interface User {
    id: number,
    name: string,
    roles: Role[]
}

declare interface UpdateUserNameRequest {
    id?: number,
    name: string
}

declare interface UpdateUserRoleRequest {
    id: number,
    roles: Role[]
}

declare interface RegisterTeacherRequest {
    name: string,
    institute: string,
    sex: string,
    telephone: string,
    email: string,
    passwordHash: string,
    cardId: string,
    type: string,
}

declare interface RegisterStudentRequest {
    name: string,
    grade: string,
    major: string,
    clazz: string,
    institute: string,
    telephone: string,
    email: string,
    passwordHash: string,
    cardId: string,
    sex: string,
}

declare interface Teacher {
    id: number
    name: string,
    institute: string,
    sex: string,
    telephone: string,
    email: string,
    cardId: string,
    type: string,
    roles: Role[]
}

declare interface Student {
    id: number,
    name: string,
    grade: string,
    major: string,
    clazz: string,
    institute: string,
    telephone: string,
    email: string,
    cardId: string,
    sex: string,
    roles: Role[]
}

declare interface UpdateTeacherRequest {
    id: number
    name: string,
    institute: string,
    sex: string,
    telephone: string,
    email: string,
    cardId: string,
    type: string,
    password: string
}

declare interface UpdateStudentRequest {
    id: number,
    name: string,
    grade: string,
    major: string,
    clazz: string,
    institute: string,
    telephone: string,
    email: string,
    cardId: string,
    sex: string,
    password: string
}

declare interface Answer {
    question: string,
    subject: string,
    score: string,
    section: string,
    level: string,
    type: string
}

declare interface ExamManage {
    examCode?: number
    description: string
    source: string
    paperId: number
    examDate: string
    totalTime: string
    grade: string
    term: string
    major: string
    institute: string
    totalScore: string
    type: string
    tips: string
}

declare interface FillQuestion {
    id?: number,
    subject: string,
    question: string,
    answer: string,
    analysis: string,
    score: number,
    level: string,
    section: string
}

declare interface JudgeQuestion {
    id?: number
    subject: string
    question: string
    answer: string
    analysis: string
    score: number
    level: string
    section: string
}

declare interface Message {
    id?: number,
    title: string
    content: string
    time: string
    replays: Replay[]
}

declare interface MultiQuestion {
    id?: number,
    subject: string,
    question: string,
    answerA: string,
    answerB: string,
    answerC: string
    answerD: string
    rightAnswer: string
    analysis: string
    score: number
    section: string
    level: string
}

declare interface PaperManage {
    paperId: number,
    questionType: number,
    questionId: number
}

declare interface QuestionItem {
    subject: string
    paperId: number
    changeNumber: number
    fillNumber: number
    judgeNumber: number
}

declare interface Replay {
    id: number
    messageId: number
    replay: string
    replayTime: string
}

declare interface Score {
    id: number
    examCode: number
    studentId: number
    subject: string
    usualScore: number
    finalScore: number
    score: number
    answerDate: string
}

declare interface PostMessageRequest {
    title: string,
    content: string,
    time: string,
}

declare interface PostReplayRequest {
    replay: string,
    replayTime: string,
    messageId: number
}