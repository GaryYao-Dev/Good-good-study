<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<style>
	/* Registration */
	#wrap{
		background-image: -ms-linear-gradient(top, #FFFFFF 0%, #D3D8E8 100%);
		/* Mozilla Firefox */
		background-image: -moz-linear-gradient(top, #FFFFFF 0%, #D3D8E8 100%);
		/* Opera */
		background-image: -o-linear-gradient(top, #FFFFFF 0%, #D3D8E8 100%);
		/* Webkit (Safari/Chrome 10) */
		background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0, #FFFFFF), color-stop(1, #D3D8E8));
		/* Webkit (Chrome 11+) */
		background-image: -webkit-linear-gradient(top, #FFFFFF 0%, #D3D8E8 100%);
		/* W3C Markup, IE10 Release Preview */
		background-image: linear-gradient(to bottom, #FFFFFF 0%, #D3D8E8 100%);
		background-repeat: no-repeat;
		background-attachment: fixed;
	}
	logo{
		color:#2f74bc;
		font-size:30px;
		font-weight:bold;
	}
	legend{
		color:#141823;
		font-size:25px;
		font-weight:bold;
	}
	.signup-btn {
		background: #79bc64;
		background-image: -webkit-linear-gradient(top, #79bc64, #578843);
		background-image: -moz-linear-gradient(top, #79bc64, #578843);
		background-image: -ms-linear-gradient(top, #79bc64, #578843);
		background-image: -o-linear-gradient(top, #79bc64, #578843);
		background-image: linear-gradient(to bottom, #79bc64, #578843);
		-webkit-border-radius: 4;
		-moz-border-radius: 4;
		border-radius: 4px;
		text-shadow: 0px 1px 0px #898a88;
		-webkit-box-shadow: 0px 0px 0px #a4e388;
		-moz-box-shadow: 0px 0px 0px #a4e388;
		box-shadow: 0px 0px 0px #a4e388;
		font-family: Arial;
		color: #ffffff;
		font-size: 20px;
		padding: 10px 20px 10px 20px;
		border: solid #3b6e22  1px;
		text-decoration: none;
	}

	.signup-btn:hover {
		background: #79bc64;
		background-image: -webkit-linear-gradient(top, #79bc64, #5e7056);
		background-image: -moz-linear-gradient(top, #79bc64, #5e7056);
		background-image: -ms-linear-gradient(top, #79bc64, #5e7056);
		background-image: -o-linear-gradient(top, #79bc64, #5e7056);
		background-image: linear-gradient(to bottom, #79bc64, #5e7056);
		text-decoration: none;
	}
	.navbar-default .navbar-brand{
		color:#fff;
		font-size:30px;
		font-weight:bold;
	}
	.form .form-control { margin-bottom: 10px; }
	@media (min-width:768px) {
		#home{
			margin-top:50px;
		}
		#home .slogan{
			color: #0e385f;
			line-height: 29px;
			font-weight:bold;
		}
	}
	/*Login*/
	/* Credit to bootsnipp.com for the css for the color graph */
	.colorgraph {
		height: 5px;
		border-top: 0;
		background: #c4e17f;
		border-radius: 5px;
		background-image: -webkit-linear-gradient(left, #c4e17f, #c4e17f 12.5%, #f7fdca 12.5%, #f7fdca 25%, #fecf71 25%, #fecf71 37.5%, #f0776c 37.5%, #f0776c 50%, #db9dbe 50%, #db9dbe 62.5%, #c49cde 62.5%, #c49cde 75%, #669ae1 75%, #669ae1 87.5%, #62c2e4 87.5%, #62c2e4);
		background-image: -moz-linear-gradient(left, #c4e17f, #c4e17f 12.5%, #f7fdca 12.5%, #f7fdca 25%, #fecf71 25%, #fecf71 37.5%, #f0776c 37.5%, #f0776c 50%, #db9dbe 50%, #db9dbe 62.5%, #c49cde 62.5%, #c49cde 75%, #669ae1 75%, #669ae1 87.5%, #62c2e4 87.5%, #62c2e4);
		background-image: -o-linear-gradient(left, #c4e17f, #c4e17f 12.5%, #f7fdca 12.5%, #f7fdca 25%, #fecf71 25%, #fecf71 37.5%, #f0776c 37.5%, #f0776c 50%, #db9dbe 50%, #db9dbe 62.5%, #c49cde 62.5%, #c49cde 75%, #669ae1 75%, #669ae1 87.5%, #62c2e4 87.5%, #62c2e4);
		background-image: linear-gradient(to right, #c4e17f, #c4e17f 12.5%, #f7fdca 12.5%, #f7fdca 25%, #fecf71 25%, #fecf71 37.5%, #f0776c 37.5%, #f0776c 50%, #db9dbe 50%, #db9dbe 62.5%, #c49cde 62.5%, #c49cde 75%, #669ae1 75%, #669ae1 87.5%, #62c2e4 87.5%, #62c2e4);
	}
</style>
	<title>Login</title>
</head>
<body>
<div class="container" id="wrap">
<logo>UNSWBOOk</logo>
<div class="row" style="margin-top:20px">
    <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
		<form role="form" form action="admin_login_servlet" method="post">
			<fieldset>
			<br/>
			<br/>
			<br/>
			
				<h2>Admin Login</h2>
				<hr class="colorgraph">
				<div class="form-group">
                    <input type="username" name="admin_username" id="username" class="form-control input-lg" placeholder="Username">
				</div>
				<div class="form-group">
                    <input type="password" name="admin_password" id="password" class="form-control input-lg" placeholder="Password">
				</div>
				<hr class="colorgraph">
				<div class="row">
						<div class="col-xs-6 col-sm-6 col-md-6 col-md-offset-3">
                        <input type="submit" class="btn btn-lg btn-success btn-block" value="Login">
					</div>
					<div class="col-xs-6 col-sm-6 col-md-6">
						<%--<a href="Registrationhome.jsp" class="btn btn-lg btn-primary btn-block">Register</a>--%>
							
							<br/>
							<br/>
							<br/>
							<br/>
							<br/>
							<br/>
							<br/>
							<br/>
							<br/>
							
							
					</div>
						
				</div>
			</fieldset>
		</form>
		<%
			try {
				if (!(boolean) request.getAttribute("status")) {
		%>
		<script>
            window.alert("Login Failed, please try again.")
		</script>
		<%
				}
			}catch (Exception e){
			}
		%>
	</div>

</div>

</div>

</body>
</html>
