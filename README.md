# Blog个人博客1.0
 >个人博客系统核心使用了Servlet+Jsp技术，严格按照Java的三层架构（Web层，Service层，Dao层）进行编码，采用了MVC的设计模式。
 >整个博客系统分为前台和后台两部分，前台主要用于文章的展示，后台主要用于文章的管理。在整个博客系统设计中，前端技术用到了BootStrap，JavaScript, Jquery, Ajax, 关于前端，其中前台文章展示界面自己利用Bootstrap进行布局设计，后台管理的界面则采用了对国产的H+模板进行改造加工；而后端技术用到了Servelt，Jsp，JDBC，DBUtils, Filter，El&JSTL，Session等, 没有使用任何后台框架。
 >博客的主要功能如下
  ##前台功能
  * 首页喜爱的照片展播
  * 首页最近文章和热门文章展示
  * 分页功能
  * 分类展示
  * 标签回显（包括标签对应的文章数），以及点击标签会搜索相关内容
  * 阅读文章
  * 博主联系方式
  * 点击量和评论数回显
  * 评论
  
  ##后台功能
  * 登录功能
  * 新增文章
  * 删除文章（单个删除，多个删除）
  * 文章修改
  * 按照分类、关键字和分类关键字结合搜索文章
  * 草稿箱
  * 首页喜爱照片编辑（照片上传、照片上移和下移)
  * 个人资料编辑
