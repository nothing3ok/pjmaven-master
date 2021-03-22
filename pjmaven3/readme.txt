本版本中我们设计一个类：httprequest 使用这个类的每个实例 表示一个客户端发送过来的请求内容

实现步骤:
1：创建一个新的包：com.webserver.http
使用这个包保存所有有关http 协议内容的类

2：在http 包中定义请求对象：httprequest
并在这个包中定义一个请求信息中各项数据对应的属性

3：定义构造方法，以及解析请求各个部分的细节方法

4：在clienthandler 中实例化httprequest 完成的解析工作