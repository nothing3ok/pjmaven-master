本版本开始，实现服务处理业务工作

实际上网时，我们经常在某网站做注册，
登入等操作，这时需要配合用户在页面上提交某些数据，并且后台要通过业务逻辑代码配合完成该操作

本版本先完成如何让用户在页面可以提交数据，并且服务端如何通过解析请求得到用户提交的数据



实现：
1 首先在myweb 这个webapp 添加一个注册页面：reg.htm
在这个页面中完成用户提交注册信息的操作


2  修改httprequest 添加对含有参数的请求的解析工作
  由于当客户端使用get 请求提交一个form 表单时，请求中含有了参数，因此，
  一个请求中的请求行就有两种情况：
  不含有参数的，如：
  /wyweb/index.html

  有参数的 如：
  /wyweb/reg?username=mingzi&password=123&.....

  所以我们要对请求中请求行里的url部分做进一步的解析工作
  在httprequest 中在定义三个属性：
  string requestURI 保存url 中‘？’左侧的请求部分
  string queryString 保存url 中‘？’右侧的参数部分
  Map parameters ：用来保存每一组参数

  在定义一个方法parseURL，用来进一步解析url，并在解析请求行
  parseRequestLine方法中得到url后调用


