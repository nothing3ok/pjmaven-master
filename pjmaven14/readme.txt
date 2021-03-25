本版本解决空请求问题

http协议中有说明这个情况，允许客户端发送空请求，
这意味发送了空请求过来后服务端在介意请求时如果直接读取第一行内容
按照空格拆分请求行的三部分时就会出现数组下标越界的问题

解决办法：
当httprequest在解析请求中的请求时，若发现是空请求则抛出空请求异常（自定义异常）给clienthandler ，
使其忽略本次请求处理


实现：
1 在com.webserver。http 包中添加一个自定义异常：
emptyrequestexception 空请求异常

2 在httprequest 的解析请求行方法：parserequestLine中，当读取第一行字符串发现是空字符串时，则抛出空
请求异常给构造方法（因为实在httprequest的狗崽方法中调用的解析请求行方法）

3 httprequest 在调用解析请求行的方法时若发现其抛出了空请求异常则直接继续将该异常抛出给
clienthandler

4 clienthandler 若捕获到空请求异常 则跳过处理和相应的步骤，忽略本次请求