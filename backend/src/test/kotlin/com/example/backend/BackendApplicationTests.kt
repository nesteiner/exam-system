package com.example.backend

import com.example.backend.model.*
import com.example.backend.repository.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.data.domain.PageRequest
import java.sql.SQLIntegrityConstraintViolationException

@SpringBootTest
class BackendApplicationTests {
    @Autowired
    lateinit var studentRepository: StudentRepository
    @Autowired
    lateinit var adminRepository: AdminRepository
    @Autowired
    lateinit var teacherRepository: TeacherRepository
    @Autowired
    lateinit var roleRepository: RoleRepository
    @Autowired
    lateinit var examManageRepository: ExamManageRepository
    @Autowired
    lateinit var fillQuestionRepository: FillQuestionRepository
    @Autowired
    lateinit var judgeQuestionRepository: JudgeQuestionRepository
    @Autowired
    lateinit var messageRepository: MessageRepository
    @Autowired
    lateinit var multiQuestionRepository: MultiQuestionRepository
    @Autowired
    lateinit var paperRepository: PaperRepository
    @Autowired
    lateinit var replayRepository: ReplayRepository
    @Autowired
    lateinit var scoreRepository: ScoreRepository
    @Autowired
    lateinit var answerRepository: AnswerRepository

    @Test
    fun injectAllData() {
        injectUserAndRoles()
        injectExamManages()
        injectFillQuestions()
        injectJudgeQuestions()
        injectMessages()
        injectMultiQuestions()
        injectPaperManages()
        injectReplays()
        injectScores()
    }
    @Test
    fun injectUserAndRoles() {
        studentRepository.deleteAll()
        adminRepository.deleteAll()
        teacherRepository.deleteAll()
        roleRepository.deleteAll()


        val roles = listOf<Role>(
            Role(null, "student"),
            Role(null, "admin"),
            Role(null, "teacher")
        )

        roleRepository.saveAll(roles)

        // val users = listOf<User>(
//            User(null, "hello", "5f4dcc3b5aa765d61d8327deb882cf99", listOf(roles[0])),
//            User(null, "world", "5f4dcc3b5aa765d61d8327deb882cf99", listOf(roles[0])),
//            User(null, "admin", "5f4dcc3b5aa765d61d8327deb882cf99", listOf(roles[1])),
//            User(20154001, "隔壁老王", "5f4dcc3b5aa765d61d8327deb882cf99", listOf(roles[0])),
//            User(20154084, "大咸鱼", "5f4dcc3b5aa765d61d8327deb882cf99", listOf(roles[0])),
//            User(20155003, "张大宝","5f4dcc3b5aa765d61d8327deb882cf99", listOf(roles[0])),
//            User(20155007, "翠花", "5f4dcc3b5aa765d61d8327deb882cf99", listOf(roles[0])),
//            User(20155008, "小王", "5f4dcc3b5aa765d61d8327deb882cf99", listOf(roles[0])),
//            User(20081001, "张", "5f4dcc3b5aa765d61d8327deb882cf99", listOf(roles[2])),
//            User(20081002, "王大志", "5f4dcc3b5aa765d61d8327deb882cf99", listOf(roles[2]))

        // )

        val students = listOf<Student>(
            Student(20154001, "隔壁老王", "2015", "计算机科学与技术", "2", "软件工程学院", "13585439532", "gblw@163.com", "5f4dcc3b5aa765d61d8327deb882cf99", "3412312", "男", listOf(roles[0])),
            Student(20154084, "大咸鱼", "2015", "计算机科学与技术", "2", "软件工程学院", "13658377857", "13658377857@sina.cn", "5f4dcc3b5aa765d61d8327deb882cf99", "124123124535", "男", listOf(roles[0])),
            Student(20155003, "张大宝", "2015", "信息工程", "1", "软件工程学院", "15583829425", "15583829425@163.com", "5f4dcc3b5aa765d61d8327deb882cf99", "3242342", "男", listOf(roles[0])),
            Student(20155007, "翠花", "2015", "网络工程", "1", "软件工程学院", "18734538457", "18734538457@163.com", "5f4dcc3b5aa765d61d8327deb882cf99", "2423423", "女", listOf(roles[0])),
            Student(20155008, "小王", "2015", "信息工程", "2", "软件工程学院", "15523619564", "15523619564@163.com", "5f4dcc3b5aa765d61d8327deb882cf99", "500234199704022353", "男", listOf(roles[0]))
        )


        val teachers = listOf<Teacher>(
            Teacher(20081001, "张", "软件工程学院", "男", "13598458442", "13598458442@163.com", "5f4dcc3b5aa765d61d8327deb882cf99", "423423283498", "讲师", listOf(roles[2])),
            Teacher(20081002, "王大志", "软件工程学院", "男", "1231221312", "1231221312@163.com", "5f4dcc3b5aa765d61d8327deb882cf99", "24241234", "讲师", listOf(roles[2])),
        )

        val admin = Admin(9527, "超级管理员", "男", "13658377857", "1253838283@qq.com", "5f4dcc3b5aa765d61d8327deb882cf99", "3132", listOf(roles[1]))

        studentRepository.saveAll(students)
        teacherRepository.saveAll(teachers)
        adminRepository.save(admin)
    }

    @Test
    fun injectExamManages() {
        val examManages = listOf<ExamManage>(
            ExamManage(20190001, "2019年上期期末考试", "计算机网络", 1001, "2019-03-21", 120, "2018", "1", "计算机科学与技术", "软件工程学院", 100, "期末考试", "快乐千万条，学习第一条，平时不努力，考试两行泪。"),
            ExamManage(20190002, "2019年上期期末考试", "数据库理论", 1002, "2019-03-07", 90, "2018", "2", "网络工程", "软件工程学院", 100, "期末考试", "快乐千万条，学习第一条，平时不努力，考试两行泪。"),
            ExamManage(20190003, "2018年下期期末考试", "数据结构", 1003, "2019-02-27", 90, "2017", "1", "软件工程", "软件工程学院", 100, "期末考试", "快乐千万条，学习第一条，平时不努力，考试两行泪。"),
            ExamManage(20190005, "2018年上期期末考试", "计算机导论", 1004, "2019-03-14", 90, "2018", "1", "计算机科学与技术", "软件工程学院", 100, "期末考试", "快乐千万条，学习第一条，平时不努力，考试两行泪。"),
            ExamManage(20190006, "2017年上期期末考试", "软件工程", 1005, "2019-03-21", 120, "2018", "1", "计算机科学与技术", "软件工程学院", 100, "期末考试", "快乐千万条，学习第一条，平时不努力，考试两行泪。"),
            ExamManage(20190007, "2018年上期期末考试", "操作系统", 1006, "2019-03-13", 120, "2018", "2", "计算机科学与技术", "软件工程学院", 100, "期末考试", "快乐千万条，学习第一条，平时不努力，考试两行泪。"),
            ExamManage(20190008, "2018年下期期末考试", "C语言", 1007, "2019-03-13", 120, "2018", "1", "信息工程", "软件工程学院", 100, "期末考试", "快乐千万条，学习第一条，平时不努力，考试两行泪。"),
            ExamManage(20190009, "2018年上期期末考试", "高等数学", 1008, "2019-03-07", 90, "2018", "1", "信息工程", "软件工程学院", 100, "期末考试", "快乐千万条，学习第一条，平时不努力，考试两行泪。"),
            ExamManage(20190013, "测试", "测试", 1009, "2019-04-24", 120, "2017", "1", "测试", "测试", 100, "期末考试", "请不要带与考试无关的书籍。")
        )

        examManageRepository.deleteAll()
        examManageRepository.saveAll(examManages)
    }

    @Test
    fun injectFillQuestions() {
        val fillQuestions = listOf<FillQuestion>(
            FillQuestion(10000, "计算机网络", "从计算机网络系统组成的角度看，计算机网络可以分为()和()", "通信子网资源子网", "", 2, "3", ""),
            FillQuestion(10001, "计算机网络", "收发电子邮件，属于ISO/OSI RM中 ()层的功能。", "应用", "", 2, "1", ""),
            FillQuestion(10002, "计算机网络", "在TCP/IP层次模型中与OSI参考模型第四层相对应的主要协议有()和(),其中后者提供无连接的不可靠传输服", "TCP（传输控制协议） UDP（用户数据报协议） ", "", 2, "2", ""),
            FillQuestion(10003, "计算机网络", "计算机网络中常用的三种有线媒体是 (),()和 ()", "同轴电缆.双绞线 光纤", "", 2, "1", ""),
            FillQuestion(10004, "计算机网络", "国内最早的四大网络包括原邮电部的ChinaNet. 原电子部的ChinaGBN. 教育部的()和中科院的CSTnet", "CERnet (或中国教育科研网)", "", 2, "4", ""),
            FillQuestion(10005, "计算机网络", "复盖一个国家，地区或几个洲的计算机网络称为()，在同一建筑或复盖几公里内范围的网络称为()，而介于两者之间的是()", " 广域网       局域网     城域网", "", 2, "1", ""),
            FillQuestion(10006, "计算机网络", "Outlook等常用电子邮件软件接收邮件使用的协议是(),发送邮件时使用的协议是()", "POP3    SMTP    ", "", 2, "1", ""),
            FillQuestion(10007, "计算机网络", "通信系统中，称调制前的电信号为()信号，调制后的信号为调制信号", "基带", "", 2, "1", ""),
            FillQuestion(10008, "计算机网络", "按照IPV4标准,IP地址205.3.127.13属于()类地址", "C", "", 2, "1", ""),
            FillQuestion(10009, "计算机网络", "计算机网络采用()技术，而传统电话网络则采用()技术", "分组交换电路交换", "", 2, "1", ""),
            FillQuestion(10010, "计算机网络", "计算机内传输的信号是()，而公用电话系统的传输系统只能传输()", "数字信号模拟信号", "", 2, "1", ""),
            FillQuestion(10011, "计算机网络", "通信系统中，称调制前的电信号为()，调制后的信号叫()。", "基带信号调制信号", "", 2, "1", ""),
            FillQuestion(10012, "计算机网络", "IP地址分()和()两个部分", "网络号主机号", "", 2, "1", ""),
            FillQuestion(10013, "计算机网络", " IP地址协议作网间网中()层协议，提供无连接的数据报传输机制，IP数据报也分为()和()两个部分", "网络报头数据区", "", 2, "2", ""),
            FillQuestion(10014, "计算机网络", "()是一个简单的远程终端协议。", "TELNET", "", 2, "1", ""),
            FillQuestion(10015, "计算机网络", "在同一个系统内，相邻层之间交换信息的连接点称之为()，而低层模块向高层提供功能性的支持称之为()。", "接口服务", "", 2, "1", ""),
            FillQuestion(10016, "计算机网络", "Internet广泛使用的电子邮件传送协议是()", "SMTP", "", 2, "1", ""),
            FillQuestion(10017, "计算机网络", "按交换方式来分类，计算机网络可以分为电路交换网，  报文交换网  和()三种", "分组交换网", "", 2, "3", ""),
            FillQuestion(10018, "计算机网络", "Intranet分层结构包括网络、(),应用三个层次。", "服务", "", 2, "1", ""),
            FillQuestion(10019, "计算机网络", "WWW上的每一个网页都有一个独立的地址，这些地址称为  ()", "统一资源定位器/URL ", "", 2, "2", ""),
            FillQuestion(10020, "计算机网络", "分组交换网中，附加信息用来在网络中进行路由选择、() 和流量控制", "差错纠正  ", "", 2, "4", ""),
            FillQuestion(10021, "计算机网络", "根据IEEE802模型的标准将数据链路层划分为LLC子层和 ()子层。", " MAC ", "", 2, "3", ""),
            FillQuestion(10022, "计算机网络", "据交换的路由信息的不同，路由算法可以分为两大类：  ()  和链路状态算法", "距离向量算法", "", 2, "3", ""),
            FillQuestion(10023, "计算机网络", "假定某信道受奈氏准则限制的最高码元速率为2000码元/秒。如果采用振幅调制，把码元的振幅划分为16个不同等级来传送，那么可以获得的数据率为 () b/s。", "80000 ", "", 2, "5", ""),
            FillQuestion(10024, "计算机网络", "交换型以太网系统中的 ()  ，以其为核心联接站点或者网段，端口之间帧的输入和输出已不再受到CSMA/CD媒体访问控制协议的约束。", "以太网交换器 ", "", 2, "5", ""),
            FillQuestion(10025, "计算机网络", "局域网络参考模型是以 ()标准为基础的", "IEEE802", "", 2, "5", ""),
            FillQuestion(10026, "计算机网络", "路由器的核心是 () 。", " 路由表", "", 2, "3", ""),
            FillQuestion(10027, "计算机网络", "若 HDLC 帧数据段中出现比特串“ 01011111110 ”，则比特填充后的输出为()", "10111110110", "", 2, "5", ""),
            FillQuestion(10028, "计算机网络", "数字调制的三种基本形式：移幅键控法ASK、 ()、移相键控法PSK", "移频键控法FSK", "", 2, "5", "")
        )

        fillQuestionRepository.deleteAll()
        fillQuestionRepository.saveAll(fillQuestions)
    }

    @Test
    fun injectJudgeQuestions() {
        val judgeQuestions = listOf<JudgeQuestion>(
            JudgeQuestion(10001, "计算机网络", "与有线网相比,无线网的数据传输率一般相对较慢", "T", "", 2, "1", ""),
            JudgeQuestion(10002, "计算机网络", "OSI参考模型中,不同节点的同等层具有不同的功能", "F", "", 2, "1", ""),
            JudgeQuestion(10003, "计算机网络", "普通电脑不能作为服务器", "F", "", 2, "1", ""),
            JudgeQuestion(10004, "计算机网络", "没有网线的电脑不能连入互联网", "F", "", 2, "1", ""),
            JudgeQuestion(10005, "计算机网络", "网卡必须安装驱动程序", "T", "", 2, "2", ""),
            JudgeQuestion(10006, "计算机网络", "UTP为屏蔽双绞线", "F", "", 2, "2", ""),
            JudgeQuestion(10007, "计算机网络", "网络接口卡又称为网卡,它是构成网络的基本部件", "T", "", 2, "2", ""),
            JudgeQuestion(10008, "计算机网络", "无线AP可以成倍地扩展网络覆盖范围", "T", "", 2, "3", ""),
            JudgeQuestion(10009, "计算机网络", "SMTP是一组用于由源地址到目的地址传送邮件的协议", "T", "", 2, "4", ""),
            JudgeQuestion(10010, "计算机网络", "任务管理器可以关闭所有的进程", "F", "", 2, "3", ""),
            JudgeQuestion(10011, "计算机网络", "利用BT下载时,用户越多,下载速度越快", "T", "", 2, "2", ""),
            JudgeQuestion(10012, "计算机网络", "INTERNET上向朋友发送电子邮件,必须知道对方的真实姓名和家庭住址", "F", "", 2, "1", "")
        )

        judgeQuestionRepository.deleteAll()
        judgeQuestionRepository.saveAll(judgeQuestions)
    }

    @Test
    fun injectMessages() {
        val messages = listOf<Message>(
            Message(2, "晚上加班,这感觉非常的nice", "今天是星期一的晚上,下班后回到宿舍继续写我的毕业设计,看着项目功能日渐丰满好开心哦,你们也要元气满满哦！", "2019-03-18"),
            Message(15, "咸鱼", "我是一条咸鱼", "2019-03-18"),
            Message(16, "今天中午吃什么", "今天去吃莲藕排骨汤吧，虽然也不好吃，但这里真的没什么东西可以吃了", "2019-03-18"),
            Message(20, "这个网站不错", "博主大大好厉害，网页看着很清新呢，喜欢这样嘞风格。", "2019-03-18"),
            Message(21, "男孩子", "哇，这么可爱，那肯定是男孩子呀。", "2019-03-18"),
            Message(22, "好人", "你是个好人，可是我们不合适。", "2019-03-18"),
            Message(25, "小乔", "花会枯萎，爱永不凋零，小乔要努力变强。", "2019-03-18"),
            Message(26, "妲己", "妲己会一直爱主人，因为被设定成这样，来和妲己玩耍吧。", "2019-03-18"),
            Message(27, "土味情话", "妈妈从小对我讲，不要早恋，遇到你以后，我决定不听妈妈的话了。", "2019-03-31"),
            Message(35, "贝塔", "开坦克", "2019-09-05")
        )

        messageRepository.deleteAll()
        messageRepository.saveAll(messages)
    }

    @Test
    fun injectMultiQuestions() {
        val multiQuestions = listOf<MultiQuestion>(
            MultiQuestion(10000, "计算机网络", "DNS 服务器和DHCP服务器的作用是（）", "将IP地址翻译为计算机名，为客户机分配IP地址", "将IP地址翻译为计算机名、解析计算机的MAC地址", "将计算机名翻译为IP地址、为客户机分配IP地址", "将计算机名翻译为IP地址、解析计算机的MAC地址", "C", "", 2, "应用层", "2"),
            MultiQuestion(10001, "计算机网络", "HTTP协议通常使用什么协议进行传输（）", "ARP", "DHCP", "UDP", "TCP", "D", "", 2, "应用层", "2"),
            MultiQuestion(10003, "计算机网络", "查看DNS缓存记录的命令（）", "ipconfig/displaydns", "nslookup", "ipconfig/release", "ipconfig/flushdns", "A", "", 2, "应用层", "3"),
            MultiQuestion(10004, "计算机网络", "DHCP(        )报文的目的IP地址为255.255.255.255", "DhcpDisover", "DhcpOffer", "DhcpAck", "DhcpNack", "A", "", 2, "应用层", "2"),
            MultiQuestion(10005, "计算机网络", "下列地址中，（  ）不是DHCP服务器分配的IP地址", "196.254.109.100", "169.254.12.42", "69.254.48.45", "96.254.54.15", "B", "", 2, "应用层", "2"),
            MultiQuestion(10006, "计算机网络", "DHCP通常可以为客户端自动配置哪些网络参数（）", "IP，掩码，网关，DNS", "IP，掩码，域名，SMTP", "网关，掩码，浏览器，FTP", "IP，网关，DNS，服务器", "A", "", 2, "应用层", "2"),
            MultiQuestion(10007, "计算机网络", "DNS服务器在名称解析过程中正确的查询顺序为（）", "本地缓存记录→区域记录→转发域名服务器→根域名服务器", "区域记录→本地缓存记录→转发域名服务器→根域名服务器", "本地缓存记录→区域记录→根域名服务器→转发域名服务器", "区域记录→本地缓存记录→根域名服务器→转发域名服务器", "A", "", 2, "应用层", "3"),
            MultiQuestion(10008, "计算机网络", "在TCP/IP协议中，序号小于（  ）的端口称为熟知端口(well-known port)。", "1024", "64", "256", "128", "A", "", 2, "传输层", "1"),
            MultiQuestion(10009, "计算机网络", "在Internet上用TCP/IP播放视频，想用传输层的最快协议，以减少时延，要使用（ ）", "UDP协议的低开销特性", "UDP协议的高开销特性", "TCP协议的低开销特性", "TCP协议的高开销特性", "A", "", 2, "传输层", "2"),
            MultiQuestion(10010, "计算机网络", "在TCP协议中采用（ ）来区分不同的应用进程", "端口号", "IP地址", "协议类型", "MAC地址", "A", "", 2, "传输层", "3"),
            MultiQuestion(10011, "计算机网络", "可靠的传输协议中的“可靠”指的是（ ）", "使用面向连接的会话", "使用“尽力而为”的传输", "使用滑动窗口来维持可靠性", "使用确认重传机制来确保传输的数据不丢失", "D", "", 2, "传输层", "2"),
            MultiQuestion(10012, "计算机网络", "假设拥塞窗口为50KB，接收窗口为80KB，TCP能够发送的最大字节数为（ ）", "50KB", "80KB", "130KB", "30KB", "A", "", 2, "传输层", "4"),
            MultiQuestion(10013, "计算机网络", "主机A向主机B发送一个（SYN=1，seq=2000）的TCP报文，期望与主机B建立连接，若主机B接受连接请求，则主机B发送的正确有TCP报文可能是（ ）", "（SYN=0,ACK=0,seq=2001,ack=2001）", "（SYN=1,ACK=1,seq=2000,ack=2000）", "•	C.（SYN=1,ACK=1,seq=2001,ack=2001）", "（SYN=0,ACK=1,seq=2000,ack=2000）", "C", "", 2, "传输层", "2"),
            MultiQuestion(10014, "计算机网络", "主机A向主机B连续发送了两个TCP报文段，其序号分别为70和100。试问： （1）第一个报文段携带了（）个字节的数据？", " 70", "30", "100", "170", "B", "", 2, "传输层", "3"),
            MultiQuestion(10015, "计算机网络", "PCM脉码调制的过程（ ）", "采样、量化、编码", "量化、编码、采样", "编码、量化、采样", "采样、编码、量化", "A", "", 2, "物理层", "4"),
            MultiQuestion(10016, "计算机网络", "若某采用4相位调制的通信链路的数据传输速率为2400bps，则该链路的波特率为（）", "600Baud", "1200Baud", "4800Baud", "9600Baud", "B", "", 2, "物理层", "1"),
            MultiQuestion(10017, "计算机网络", "以下关于数据传输速率的描述中，错误的是( )", "数据传输速率表示每秒钟传输构成数据代码的二进制比特数", "对于二进制数据，数据传输速率为S=1/T (bps)", "常用的数据传输速率单位有: 1Mbps=1.024×106bps", "数据传输速率是描述数据传输系统性能的重要技术指标之一", "C", "", 2, "物理层", "2"),
            MultiQuestion(10018, "计算机网络", "以下关于时分多路复用概念的描述中，错误的是.(  ).", "时分多路复用将线路使用的时间分成多个时间片", "时分多路复用分为同步时分多路复用与统计时分多路复用", "时分多路复用使用“帧”与数据链路层“帧”的概念、作用是不同的", "统计时分多路复用将时间片预先分配给各个信道", "D", "", 2, "物理层", "2"),
            MultiQuestion(10019, "计算机网络", "1000BASE-T标准支持的传输介质是（）", "双绞线", "同轴电缆", "光纤", "无线电", "A", "", 2, "物理层", "1"),
            MultiQuestion(10020, "计算机网络", "一个以太网交换机，读取整个数据帧，对数据帧进行差错校验后再转发出去，这种交换方式称为 （）", "直通交换", "无碎片交换", "无差错交换", "存储转发交换", "D", "", 2, "数据链路层", "2"),
            MultiQuestion(10021, "计算机网络", "关于VLAN，下面的描述中正确的是（）", "一个新的交换机没有配置VLAN", "通过配置VLAN减少了冲突域的数量", "一个VLAN不能跨越多个交换机", "各个VLAN属于不同的广播域", "D", "", 2, "数据链路层", "2"),
            MultiQuestion(10022, "计算机网络", "以太网协议中使用物理地址作用是什么？", ".用于不同子网中的主机进行通信", "作为第二层设备的唯一标识", "用于区别第二层第三层的协议数据单元", "保存主机可检测未知的远程设备", "B", "", 2, "数据链路层", "2"),
            MultiQuestion(10023, "计算机网络", "以太网采用的CSMA/CD协议，当冲突发生时要通过二进制指数后退算法计算后退延时， 关于这个算法，以下论述中错误的是 （）", "冲突次数越多，后退的时间越短", "平均后退次数的多少与负载大小有关", "后退时延的平均值与负载大小有关", "重发次数达到一定极限后放弃发送", "A", "", 2, "数据链路层", "3"),
            MultiQuestion(10024, "计算机网络", "以下关于交换机获取与其端口连接设备的MAC地址的叙述中，正确的是（）", "交换机从路由表中提取设备的MAC地址", "交换机检查端口流入分组的源地址", "交换机之间互相交换地址表", "网络管理员手工输入设备的MAC地址", "B", "", 2, "数据链路层", "2"),
            MultiQuestion(10025, "计算机网络", "如果G (x）为11010010，以下4个CRC校验比特序列中只有哪个可能是正确的 ？", "1101011001", "101011011", "11011011", "1011001", "B", "", 2, "数据链路层", "1"),
            MultiQuestion(10026, "计算机网络", "以下关于Ethernet物理地址的描述中，错误的是", "Ethernet物理地址又叫做MAC地址", "48位的Ethernet物理地址允许分配的地址数达到247个", "网卡的物理地址写入主机的EPROM中", "每一块网卡的物理地址在全世界是唯一的", "C", "", 2, "数据链路层", "3"),
            MultiQuestion(10027, "计算机网络", "下列帧类型中，不属于HDLC帧类型的是（）", "信息帧", "确认帧", "监控帧", "无编号帧", "B", "", 2, "数据链路层", "1"),
            MultiQuestion(10028, "计算机网络", "通过交换机连接的一组站点，关于它们的广播域和冲突域说法正确的是（）", "组成一个冲突域，但不是一个广播域", "组成一个广播域，但不是一个冲突域", "组成一个冲突域，也是一个广播域", "既不一个冲突域，也不是一个广播域", "B", "", 2, "数据链路层", "3"),
            MultiQuestion(10029, "计算机网络", "数据链路层的数据单位是（）", "帧", "字节", "比特", "分组", "A", "", 2, "数据链路层", "1"),
            MultiQuestion(10030, "计算机网络", "LAN参考模型可分为物理层、（ ）", "MAC，LLC等三层", "LLC，MHS等三层", "MAC，FTAM等三层", "LLC，VT等三层", "A", "", 2, "数据链路层", "3"),
            MultiQuestion(10031, "测试", "测试", "A", "B", "C", "D", "B", "解析", 2, "测试", "4"),
            MultiQuestion(10032, "计算机网络", "DNS 服务器和DHCP服务器的作用是（）", "A", "B", "C", "D", "B", "哦解析", 2, "网络层", "2")
        )

        multiQuestionRepository.deleteAll()
        multiQuestionRepository.saveAll(multiQuestions)
    }

    @Test
    fun injectPaperManages() {
        val papermanages = listOf<PaperManage>(
            PaperManage(1001, 1, 10013),
            PaperManage(1001, 1, 10005),
            PaperManage(1001, 1, 10001),
            PaperManage(1001, 1, 10004),
            PaperManage(1001, 1, 10000),
            PaperManage(1001, 1, 10016),
            PaperManage(1001, 1, 10032),
            PaperManage(1001, 1, 10025),
            PaperManage(1001, 1, 10030),
            PaperManage(1001, 1, 10017),
            PaperManage(1001, 1, 10027),
            PaperManage(1001, 1, 10023),
            PaperManage(1001, 1, 10014),
            PaperManage(1001, 1, 10009),
            PaperManage(1001, 1, 10008),
            PaperManage(1001, 1, 10011),
            PaperManage(1001, 1, 10010),
            PaperManage(1001, 1, 10021),
            PaperManage(1001, 1, 10022),
            PaperManage(1001, 1, 10026),
            PaperManage(1001, 2, 10017),
            PaperManage(1001, 2, 10012),
            PaperManage(1001, 2, 10004),
            PaperManage(1001, 2, 10005),
            PaperManage(1001, 2, 10003),
            PaperManage(1001, 2, 10008),
            PaperManage(1001, 2, 10020),
            PaperManage(1001, 2, 10013),
            PaperManage(1001, 2, 10006),
            PaperManage(1001, 2, 10015),
            PaperManage(1001, 3, 10002),
            PaperManage(1001, 3, 10009),
            PaperManage(1001, 3, 10003),
            PaperManage(1001, 3, 10005),
            PaperManage(1001, 3, 10004),
            PaperManage(1001, 3, 10012),
            PaperManage(1001, 3, 10011),
            PaperManage(1001, 3, 10006),
            PaperManage(1001, 3, 10008),
            PaperManage(1001, 3, 10001)
        )

        paperRepository.deleteAll()
        paperRepository.saveAll(papermanages)
    }

    @Test
    fun injectReplays() {
        val replays = listOf<Replay>(
            Replay(26, 2, "好呀，我来了，你别跑！", "2019-03-18"),
            Replay(22, 15, "心疼你2秒钟", "2019-03-18"),
            Replay(25, 16, "我也要变强", "2019-03-19"),
            Replay(25, 20, "俺也一样", "2019-03-19"),
            Replay(21, 21, "既然这样，那就迎男而上吧", "2019-03-19"),
            Replay(16, 22, "那条街确实没什么东西可以吃了。。。", "2019-03-19"),
            Replay(15, 25, "咸鱼其实还可以吃，而你,emmmm", "2019-03-19"),
            Replay(21, 26, "我不怕人多", "2019-03-19"),
            Replay(20, 27, "是嘞，还行。", "2019-03-19"),
            Replay(26, 35, "我也爱你ε=(´ο｀*)))唉", "2019-03-19"),
            Replay(27, 2, "你这个坏孩子", "2019-03-31"),
            Replay(33, 15, "是的，今天是周一。", "2019-04-14"),
            Replay(34, 16, "1111", "2019-04-16"),
            Replay(34, 20, "2222", "2019-04-16"),
            Replay(35, 21, "666", "2019-09-05")
        )

        replayRepository.deleteAll()
        replayRepository.saveAll(replays)
    }

    @Test
    fun injectScores() {
        val scores = listOf<Score>(
            Score(4, 20190001, 20154084, "计算机网络", 60, 70, 60, "2019-04-03"),
            Score(14, 20190002, 20154084, "数据库理论", 60, 78, 60, "2019-04-20"),
            Score(15, 20190002, 20154084, "数据库理论", 60, 80, 60, "2019-04-20"),
            Score(16, 20190002, 20154084, "数据库理论", 60, 83, 60, "2019-04-20"),
            Score(17, 20190001, 20154001, "计算机网络", 60, 74, 60, "2019-04-13"),
            Score(18, 20190001, 20154001, "计算机网络", 60, 70, 60, "2019-04-10"),
            Score(19, 20190001, 20155003, "计算机网络", 60, 86, 60, "2019-04-14"),
            Score(20, 20190001, 20155007, "计算机网络", 60, 90, 60, "2019-04-11"),
            Score(21, 20190001, 20155007, "计算机网络", 60, 92, 60, "2019-04-12"),
            Score(22, 20190001, 20155008, "计算机网络", 60, 80, 60, "2019-04-12"),
            Score(23, 20190001, 20155003, "计算机网络", 60, 90, 60, "2019-04-13"),
            Score(24, 20190001, 20155008, "计算机网络", 60, 88, 60, "2019-04-14"),
            Score(27, 20190001, 20154084, "计算机网络", 60, 80, 60, "2019-04-25"),
            Score(30, 20190001, 20154084, "计算机网络", 60, 0, 60, "2019-04-29"),
            Score(31, 20190001, 20154084, "计算机网络", 60, 0, 60, "2019-09-06")
        )

        scoreRepository.deleteAll()
        scoreRepository.saveAll(scores)
    }

    @Test
    fun testAnswerRepository() {
        answerRepository.findAll(PageRequest.of(0, 10)).forEach({println(it)})
    }

    @Test
    fun testExamManageRepository() {
        // examManageRepository.deleteByExamCode(1)
        examManageRepository.findAll().forEach {
            println(it.examCode)
        }

        println(examManageRepository.findFirstByOrderByPaperIdDesc() ?: "fuck")
    }

    @Test
    fun testFillQuestionRepository() {
        fillQuestionRepository.findAllBySubject("计算机网络", PageRequest.of(0, 10)).forEach {
            println(it.getId())
        }
    }

    @Test
    fun testInjectDuplicateCardId() {
        val Steiner = Student(null,
            "Steiner",
            "2020",
            "软件工程",
            "2",
            "计算机学院",
            "18967700988",
            "steiner3044@163.com",
            "5f4dcc3b5aa765d61d8327deb882cf99",
            "3412312",
            "男",
            listOf(Role(1, "student")))

        try {
            studentRepository.save(Steiner)
        } catch (exception: SQLIntegrityConstraintViolationException) {
            println("SQLIntegrityConstraintViolationExceptioin")
        } catch (exception: DataIntegrityViolationException) {
            println("DataIntegrityViolationException")
            println(exception.message)
        }
    }

    @Test
    fun testFillQuestionService() {
        fillQuestionRepository.findAllByPaperId(10000).forEach { println(it) }
    }

    @Test
    fun testInjectId() {
        val data1 = FillQuestion(null, "计算机网络", "从计算机网络系统组成的角度看，计算机网络可以分为()和()", "通信子网资源子网", "", 2, "3", "")
        val question1 = fillQuestionRepository.save(data1)
        println(question1.id)

        val data2 = FillQuestion(999, "计算机网络", "从计算机网络系统组成的角度看，计算机网络可以分为()和()", "通信子网资源子网", "", 2, "3", "")
        val question2 = fillQuestionRepository.save(data2)
        println(question2.id)
    }

    @Test
    fun testPage() {
        val page = fillQuestionRepository.findAll(PageRequest.of(0, 10))
        println(page.totalPages)
    }

    @Test
    fun findQuestions() {
        val paperId = 1002L
        val multiQuestions = multiQuestionRepository.findAllByPaperId(paperId)
        val fillQuestions = fillQuestionRepository.findAllByPaperId(paperId)
        val judgeQuestions = judgeQuestionRepository.findAllByPaperId(paperId)

        println(multiQuestions)
        println(fillQuestions)
        println(judgeQuestions)
    }
}
