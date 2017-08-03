<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"><meta name="renderer" content="webkit">

    <title>廖志行博客后台管理系统</title>

    <link href="${pageContext.request.contextPath }/css/bootstrap.min.css?v=3.4.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/font-awesome/css/font-awesome.css?v=4.3.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/css/style.css?v=2.2.0" rel="stylesheet">

</head>

<body>
    <div id="wrapper">
        <nav class="navbar-default navbar-static-side" role="navigation">
            <div class="sidebar-collapse">
                <ul class="nav" id="side-menu">
                    <li class="nav-header">

                        <div class="dropdown profile-element"> <span>
                            <img alt="image" class="img-circle" src="${pageContext.request.contextPath }/${admin.avata }"  width="50%" height="50%"/>
                             </span>
                            <a data-toggle="dropdown" class="dropdown-toggle" href="javascript:void(0);">
                                <span class="clear"> <span class="block m-t-xs"> <strong class="font-bold">${admin.realname }</strong>
                             </span>  <span class="text-muted text-xs block">超级管理员 <b class="caret"></b></span> </span>
                            </a>
                            <ul class="dropdown-menu animated fadeInRight m-t-xs">
                                <li><a href="${pageContext.request.contextPath }/supporter/edit_profile.jsp">个人资料</a>
                                </li>
                                <li class="divider"></li>
                                <li><a href="${pageContext.request.contextPath }/admin/logout">安全退出</a>
                                </li>
                            </ul>
                        </div>
                        <div class="logo-element">
                            Jexhen
                        </div>

                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath }"><i class="fa fa-th-large"></i> <span class="nav-label">主页</span></a>
                    </li>
                    <li>
                    	<a href="index.html#"><i class="fa fa-desktop"></i> <span class="nav-label">页面</span></a>
                        <ul class="nav nav-second-level">
                        	<li><a href="${pageContext.request.contextPath }/supporter/edit_profile.jsp">个人资料</a>
                        </ul>
					</li>
                    <li class="active">
                        <a href="javascript:void(0)"><i class="fa fa-table"></i> <span class="nav-label">博客管理</span></a>
                        <ul class="nav nav-second-level">
                            <li><a href="${pageContext.request.contextPath }/admin/getArticleToSupporter">已发表</a>
                            </li>
                            <li><a href="${pageContext.request.contextPath }/admin/getDraftToSupporter">草稿箱</a>
                            <li class="active"><a href="${pageContext.request.contextPath }/admin/getCarouselToSupporter">Carousel</a></li>
                            </li>
                        </ul>
                    </li>

                    <li>
                        <a href="http://www.uyan.cc/sites"><i class="fa fa-comments"></i> <span class="nav-label">评论管理</span></a>
                    </li>
                </ul>

            </div>
        </nav>

        <div id="page-wrapper" class="gray-bg dashbard-1">
            <div class="row border-bottom">
                <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                    <div class="navbar-header">
                        <a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="javascript:void(0);"><i class="fa fa-bars"></i> </a>
                    </div>
                    <ul class="nav navbar-top-links navbar-right">
                        <li>
                            <span class="m-r-sm text-muted welcome-message"><a href="${pageContext.request.contextPath }/admin/getArticleToSupporter" title="返回首页"><i class="fa fa-home"></i></a>欢迎使用博客后台管理系统</span>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath }/admin/logout">
                                <i class="fa fa-sign-out"></i> 退出
                            </a>
                        </li>
                    </ul>

                </nav>
            </div>
            <div class="row wrapper border-bottom white-bg page-heading">
                <div class="col-lg-10">
                    <h2>Carousel</h2>
                </div>
                <div class="col-lg-2">

                </div>
            </div>
            <div class="wrapper wrapper-content animated fadeInRight">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="ibox float-e-margins">
                            <div class="ibox-title">
                                <h5>所有图片</h5>
                                <div class="ibox-tools">
                                    <a class="collapse-link">
                                        <i class="fa fa-chevron-up"></i>
                                    </a>
                                    <a class="dropdown-toggle" data-toggle="dropdown" href="table_basic.html#">
                                        <i class="fa fa-wrench"></i>
                                    </a>
                                    <ul class="dropdown-menu dropdown-user">
                                        <li><a href="javascript:void(0)" onclick="newCarousel()">新建</a>
                                        </li>
                                        <li id="delete"><a href="javascript:void(0)">删除</a>
                                        </li>
                                    </ul>
                                    <a class="close-link">
                                        <i class="fa fa-times"></i>
                                    </a>
                                </div>
                            </div>
                            <div class="ibox-content">
                                <div class="row">
                                </div>
                                <div class="table-responsive">
                                    <table class="table table-striped">
                                        <thead>
                                            <tr>
                                                <th></th>
                                                <th>序号</th>
                                                <th>图片预览</th>
                                                <th>图片描述</th>
                                                <th>上移</th>
                                                <th>下移</th>
                                                <th>修改</th>
                                                <th>删除</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        	<c:forEach items="${carousel }" var="image" varStatus="cnt">
                                        	<tr>
                                               <td>
                                                   <input type="checkbox" name="input[]" value="${image.caid }">
                                               </td>
                                               <td>${cnt.count }</td>
                                               <td class="col-lg-3"><img alt="" src="${pageContext.request.contextPath }/${image.csrc }" width="20%"></td>
                                               <td><input type="text" value="${image.cdesc }" maxlength="5"></td>
                                               <td><a href="javascript:void(0)" onclick="up(${cnt.count })"><i class="fa fa-angle-double-up text-navy"></i></a>
                                               </td>
                                               <td><a href="javascript:void(0)" onclick="down(${cnt.count })"><i class="fa fa-angle-double-down text-navy"></i></a>
                                               </td>
                                               <td><a href="javascript:void(0)" onclick="activeUpload(${image.caid })"><i class="fa fa-file-text text-navy"></i></a>
                                               </td>
                                               <td><a href="javascript:void(0)" onclick="deleteCarousel('${image.caid }')"><i class="fa fa-times text-navy"></i></a>
                                               </td>
                                           </tr>
                                        	</c:forEach>
                                        	
                                        </tbody>
                                    </table>
                                </div>

                            </div>
                        </div>
                    </div>

                </div>
            </div>
            
            <div class="modal fade" tabindex="-1" role="dialog" id="uploadmodal">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title">选择您要上传的图片</h4>
			      </div>
			      <div class="modal-body">
			        <input type="file" name="filename" id="image">
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			        <button type="button" class="btn btn-primary" id="upload">上传</button>
			      </div>
			    </div><!-- /.modal-content -->
			  </div><!-- /.modal-dialog -->
			</div><!-- /.modal -->
            
            
            <div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" id="tipsmodal">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title">页面提示</h4>
			      </div>
			      <div class="modal-body" id="tips">
			        	
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			        <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="window.location.reload()">确定</button>
			      </div>
			    </div><!-- /.modal-content -->
			  </div><!-- /.modal-dialog -->
			</div><!-- /.modal -->
            
            
            <div class="footer">
                <div class="pull-right">
                    By：<a href="${pageContext.request.contextPath }" target="_blank">廖志行</a>
                </div>
                <div>
                    <strong>Copyright</strong> Jexhen &copy; 2017
                </div>
            </div>

        </div>
    </div>



    <!-- Mainly scripts -->
    <script src="${pageContext.request.contextPath }/js/jquery-2.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath }/js/bootstrap.min.js?v=3.4.0"></script>
    <script src="${pageContext.request.contextPath }/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="${pageContext.request.contextPath }/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

    <!-- Peity -->
    <script src="${pageContext.request.contextPath }/js/plugins/peity/jquery.peity.min.js"></script>

    <!-- Custom and plugin javascript -->
    <script src="${pageContext.request.contextPath }/js/hplus.js?v=2.2.0"></script>
    <script src="${pageContext.request.contextPath }/js/plugins/pace/pace.min.js"></script>

    <!-- iCheck -->
    <script src="${pageContext.request.contextPath }/js/plugins/iCheck/icheck.min.js"></script>

    <!-- Peity -->
    <script src="${pageContext.request.contextPath }/js/demo/peity-demo.js"></script>
    
	<script src="${pageContext.request.contextPath }/js/ajaxfileupload.js"></script>
	
    <script>
	  	//弹出更改上传的模态框
	    function activeUpload(caid) {
	    	g_caid = caid;//弹出上传框，而且要记住是为哪一个caid的上传
	    	$("#uploadmodal").modal();
	    }
        $(function(){
        	
        	$("#upload").click(function(cid, cdesc){
        		$.ajaxFileUpload({
        			url:'${pageContext.request.contextPath }/admin/updateCarouselImage',
        			secureuri:false,
        			data:{
        				"caid":g_caid,//gcaid是个全局变量用来存储
        				"cdesc":cdesc
        			},
        			dataType:'TEXT',
        			fileElementId:'image',
        			success: function(data, status) {
        				if (data==="true") {
        					showTips("上传成功");
        				}
        			}
        		});
        	});
        	var arr = [];
        	$("#delete").click(function(){
        		$("input:checked").each(function(){
        			arr.push($(this).val());
        		});
        		var isConfirm = confirm("您确认删除所选的" + arr.length + "张图片吗？");
        		if (isConfirm) {
	        		$.ajax({
	        			type:"post",
	        			url:"${pageContext.request.contextPath }/admin/removeCarousel",
	        			data:{"caids":arr},
	        			success:function(data){
	        				if (data==="true") {
	        					showTips("删除成功");
	        				}
	        			}
	        		});
        		}
        	});
        	
        });
        //删除对应caid的轮播图片
        function deleteCarousel(caid){
        	var isConfirm = confirm("您确认删除该张图片吗？");
        	if (isConfirm) {
        		$.ajax({
        			type:"post",
        			url:"${pageContext.request.contextPath }/admin/removeCarousel",
        			data:{"caid":caid},
        			success:function(data){
        				if (data==="true") {
        					showTips("删除成功");
        				}
        			}
        		});
        	}
        }
        //新增一张轮播图
        function newCarousel(){
        	if ("${fn:length(carousel)}">=5){
        		showTips("最多只能添加5张轮播图,请删除一些后再试！");
        	} else {
        		$.ajax({
        			url:"${pageContext.request.contextPath }/admin/newCarousel",
        			type:"post",
        			success:function(data){
        				if (data==="true"){
        					showTips("添加成功");
        				} else {
        					showTips("添加失败,稍后再试!");
        				}
        			}
        		});
        	}
        	
        }
        //对轮播图进行位置上移
        function up(index) {
        	if (index == 1) 
        		return;//第1行不能往前移
        	var nowCaid = getCaid(index);//当前行
        	var otherCaid = getCaid(index-1);//上一行
        	$.ajax({
        		url:'${pageContext.request.contextPath }/admin/swapCarousel',
        		type:'post',
        		data:{'nowCaid':nowCaid,'otherCaid':otherCaid},
        		dataType:'json',
        		success:function(data){
        			//swapCarousel实质是将两者的信息交换,返回的结果已经交换好,并且设计时规定小的caid排前，所以只需要根据caid大小即可知道谁前谁后
        			if (data[0].caid>data[1].caid){
        				updateTable(index-1, data[1]);//小的caid放到前一行
        				updateTable(index, data[0]);//大的caid放到当前行
        			} else {
        				updateTable(index-1, data[0]);//小的caid放到前一行
        				updateTable(index, data[1]);//大的caid放到当前行
        			}
        		}
        	});
        }
        //对轮播图进行下移
        function down(index) {
        	if (index == "${fn:length(carousel)}") 
        		return;//最后一行不能往后移
        	var nowCaid = getCaid(index);//当前行
        	var otherCaid = getCaid(index+1);//下一行
        	$.ajax({
        		url:'${pageContext.request.contextPath }/admin/swapCarousel',
        		type:'post',
        		data:{'nowCaid':nowCaid,'otherCaid':otherCaid},
        		dataType:'json',
        		success:function(data){
        			//swapCarousel实质是将两者的信息交换,返回的结果已经交换好,并且设计时规定小的caid排前，所以只需要根据caid大小即可知道谁前谁后
        			if (data[0].caid>data[1].caid){
        				updateTable(index+1, data[0]);//大的caid放到下一行
        				updateTable(index, data[1]);//小的caid放到当前行
        			} else {
        				updateTable(index+1, data[1]);//大的caid放到下一行
        				updateTable(index, data[0]);//小的caid放到当前行
        			}
        		}
        	});
        }
        //获取表格中对应行的caid
        function getCaid(row) {
        	var tr = $("tbody tr")[row-1];
        	var td = $(tr).children()[0];//需要获取的单元格
        	var checkBox = $(td).children()[0];//单元格的信息
        	var checkBoxVal = $(checkBox).val();
        	return checkBoxVal;
        }
        //更新图片展示表格，供up()上移和down()下移函数调用
        //参数row需要更改的行，image提供更改信息的图片
        function updateTable(row, image){
        	var tr = $("tbody tr")[row-1];//对应的行
        	//var td = $(tr).children()[0];//需要获取的单元格
        	//var checkBox = $(td).children()[0];//单元格的信息
        	//var checkBoxVal = $(checkBox).val();
        	//var checkBoxVal = $($($($("tbody tr")[index-1]).children()[0]).children()[0]).val();//checkBox的value,即caid
        	var td = $(tr).children()[2];//获取对应的行的第3个单元格,即图片预览
        	/* var img = "<img alt='' src='"+image.csrc+"?"+Math.random()+"' width='20%'>";
        	$(td).empty();
        	$(td).append(img); */
        	var img = $(td).children()[0];//获得图片预览的第1个子元素即img
        	$(img).attr('src','${pageContext.request.contextPath }/' + image.csrc);//修改img的src属性
        	/************************/
        	td = $(tr).children()[3];//获得第4个单元格即图片信息
        	$($(td).children()[0]).val(image.cdesc);//修改图片描述
        }
        
        //弹出提示信息的模态框
        function showTips(tips) {
        	$("#tips").empty();
        	$("#tips").append(tips);
        	$("#tipsmodal").modal();
        }
    </script>


</body>

</html>
