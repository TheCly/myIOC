**什么是IOC** ：简单来说，IoC容器就是一个对象仓库，它负责创建、 存储 和管理对象。 在Spring中，IoC容器实现了控制反转（Inversion of Control）原则，即将对象的创建和配置的主动权从程序代码中转移到了外部容器中
<br>
**为什么要用IOC** ： 还记得工厂模式吗？当我想持有某个对象的时候，直接找对应工厂。这避免了构造对象这一个强耦合的操作。
<br>例如：UserService uservice=new UserServiceImpl();
<br>过了一段时间，系统从单体要升级成微服务，原来的UserServiceImpl走的是本地查询，肯定没法用了。
<br>我写了一个UserServiceImpl2来实现走HTTP的微服务版本。然后就去寻找之前所有对UserServiceImpl的引用，修改为UserServiceImpl2。
<br>这明显是不符合设计模式规范的，对新增开放，对修改关闭。
<br>如果我是IOC管理注入的bean，就只需要创建的时候设置一下，从容器取UserService的时候，得到的是UserServiceImpl2就行了
