<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
	<title>MVC TEST</title>
	<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>

<body>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#menu">
					<span class="sr-only"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<span class="navbar-brand">留言板</span>
			</div>

			<div class="collapse navbar-collapse" id="menu">
				<ul class="nav navbar-nav navbar-right">
					<li><a href ="#" data-toggle="modal" data-target="#leave-msg-modal">留下足迹</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="modal fade" tabindex="-1" id="leave-msg-modal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h3 class="modal-title">发表留言</h3>
				</div>

				<form class="form-horizontal" method="POST" action="/Controller">
					<div class="modal-body">
						<div class="col-md-12 form-group">
							<label>昵称</label>
							<input type="text" class="form-control" name="nickname" placeholder="请输入姓名或昵称" required>
						</div>

						<div class="col-md-12 form-group">
							<label>留言内容</label>
							<textarea class="form-control" rows="3" name="message" placeholder="请输入留言内容" required></textarea>
						</div>
						<input name="operation" value="insert" hidden>
					</div>
					<div class="clearfix"></div>

					<div class="modal-footer">
						<input type="submit" class="btn btn-lg btn-primary btn-block" value="确定">
					</div>
				</form>
			</div>
		</div>
	</div>

	<jsp:include page="/Controller">
		<jsp:param name="operation" value="list"></jsp:param>
	</jsp:include>

	<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>