<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="./public/meta.jsp"></jsp:include>
<title>会员列表 - 管理员列表</title>
</head>
<body>

<jsp:include page="./public/head.jsp"></jsp:include>
<jsp:include page="./public/menu.jsp"></jsp:include>

<section class="Hui-article-box">
	<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页
		<span class="c-gray en">&gt;</span>
		会员管理
		<span class="c-gray en">&gt;</span>
		管理员列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a> </nav>
	<div class="Hui-article">
		<article class="cl pd-20">
			<div class="cl pd-5 bg-1 bk-gray mt-20">
				<span class="l"> 
					<!-- <a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a>  -->
					<a href="javascript:;" onclick="admin_add('添加管理员','admin-add.html','800','500')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加管理员</a> 
				</span>
				<span class="r">共有数据：<strong>${count}</strong> 条</span>
			</div>
			<table class="table table-border table-bordered table-bg">
				<thead>
					<tr>
						<th scope="col" colspan="9">管理员列表</th>
					</tr>
					<tr class="text-c">
						<th hidden width="25"><input type="checkbox" name="" value=""></th>
						<th hidden width="40">ID</th>
						<th width="150">登录名</th>
						<th width="90">手机</th>
						<th>角色</th>
						<th width="100">是否已启用</th>
						<th width="100">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${adminList}" var="admin">
						<tr class="text-c">
							<td hidden><input type="checkbox" value="1" name=""></td>
							<td hidden>${admin.id}</td>
							<td>${admin.username}</td>
							<td>${admin.phone}</td>
							<td>${admin.role}</td>
							<td class="td-status">
							<c:if test="${admin.state == 1}">
								<span class="label label-success radius">已启用</span>
							</c:if>
							<c:if test="${admin.state == 0}">
								<span class="label label-default radius">已禁用</span>
							</c:if>
							</td>
							<td class="td-manage">
								<c:if test="${admin.state == 1}">
									<a style="text-decoration:none" onClick="admin_stop(this,'10001')" href="javascript:;" title="停用"><i class="Hui-iconfont">&#xe631;</i></a> 
								</c:if>
								<c:if test="${admin.state == 0}">
									<a onClick="admin_start(this,id)" href="javascript:;" title="启用" style="text-decoration:none"><i class="Hui-iconfont">&#xe615;</i></a>
								</c:if>
								<a title="编辑" href="javascript:;" onclick="admin_edit('管理员编辑','admin-alter/${admin.id}.html','1','800','500')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a> 
								<a title="删除" href="javascript:;" onclick="admin_del(this,'1')" class="ml-5" style="text-decoration:none" data-admin="${admin.id}"><i class="Hui-iconfont">&#xe6e2;</i></a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</article>
	</div>
</section>

<jsp:include page="./public/foot.jsp"></jsp:include>

<!--请在下方写此页面业务相关的脚本--> 
<script type="text/javascript" src="/staticfile/manage/lib/My97DatePicker/4.8/WdatePicker.js"></script> 
<script type="text/javascript" src="/staticfile/manage/lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="/staticfile/manage/lib/laypage/1.2/laypage.js"></script> 
<script type="text/javascript">
/*
	参数解释：
	title	标题
	url		请求的url
	id		需要操作的数据id
	w		弹出层宽度（缺省调默认值）
	h		弹出层高度（缺省调默认值）
*/
/*管理员-增加*/
function admin_add(title,url,w,h){
	layer_show(title,url,w,h);
}
/*管理员-删除*/
function admin_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			type: "post",
			url: "updateAdminState.html",
			data: {
				'id': $(obj).attr("data-admin"),
				'state': -1
			},
			success: function(result) {
				var jsonObj = JSON.parse(result);
				if( jsonObj.state == 1) {
					$(obj).parents("tr").remove();
					layer.msg('已删除!',{icon:1,time:1000});
				} else {
					layer.msg('删除失败,请稍后再试!',{icon: 5,time:1000});
				}
			},
			error: function(errorMsg) {
				layer.msg('删除错误,请稍后再试!',{icon: 5,time:1000});
			}
		});
	});
}
/*管理员-编辑*/
function admin_edit(title,url,id,w,h){
	layer_show(title,url,w,h);
}
/*管理员-停用*/
function admin_stop(obj,id){
	layer.confirm('确认要停用吗？',function(index){
		$.ajax({
			type: "post",
			url: "updateAdminState.html",
			data: {
				'id': $(obj).next().next().attr("data-admin"),
				'state': 0
			},
			success: function(result) {
				var jsonObj = JSON.parse(result);
				if( jsonObj.state == 1) {
					$(obj).parents("tr").find(".td-manage").prepend('<a onClick="admin_start(this,id)" href="javascript:;" title="启用" style="text-decoration:none"><i class="Hui-iconfont">&#xe615;</i></a>');
					$(obj).parents("tr").find(".td-status").html('<span class="label label-default radius">已禁用</span>');
					$(obj).remove();
					layer.msg('已停用!',{icon: 5,time:1000});
				} else {
					layer.msg('停用失败,请稍后再试!',{icon: 5,time:1000});
				}
			},
			error: function(errorMsg) {
				layer.msg('停用错误,请稍后再试!',{icon: 5,time:1000});
			}
		});
	});
}

/*管理员-启用*/
function admin_start(obj,id){
	layer.confirm('确认要启用吗？',function(index){
		$.ajax({
			type: "post",
			url: "updateAdminState.html",
			data: {
				'id': $(obj).next().next().attr("data-admin"),
				'state': 1
			},
			success: function(result) {
				var jsonObj = JSON.parse(result);
				if( jsonObj.state == 1) {
					$(obj).parents("tr").find(".td-manage").prepend('<a onClick="admin_stop(this,id)" href="javascript:;" title="停用" style="text-decoration:none"><i class="Hui-iconfont">&#xe631;</i></a>');
					$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
					$(obj).remove();
					layer.msg('已启用!', {icon: 6,time:1000});
				} else {
					layer.msg('启用失败,请稍后再试!',{icon: 5,time:1000});
				}
			},
			error: function(errorMsg) {
				layer.msg('启用错误,请稍后再试!',{icon: 5,time:1000});
			}
		});
	});
}
</script> 
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>