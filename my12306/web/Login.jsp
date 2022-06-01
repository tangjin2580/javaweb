<%@page import="com.util.TextUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    String username="";
    String autoLogin="";
    Cookie[] cookies =request.getCookies();
    if(cookies != null && cookies.length > 0){
    	for(Cookie cookie : cookies){
    		if(cookie.getName().equals("username"))
    			username=cookie.getValue();
    		else if(cookie.getName().equals("autoLogin"))
    			autoLogin=cookie.getValue();
    	}
    }
    if(!TextUtils.isEmpty(username)&&!TextUtils.isEmpty(autoLogin)){
    	response.sendRedirect("autoLogin");
    }
    %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>12306购票系统</title>
<link href="css/css.css" rel="stylesheet" type="text/css">
</style>
<script language="javascript">
	function UserRegistration(){
	window.navigate("UserRegistration.html");
	}
</script>
<script language="javascript">
	function UserLogin(){
	window.navigate("Admin/Index.html");
	}
</script>
</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
 <form name="form1" method="post" action="login">
   <table width="933" border="0" align="center" cellpadding="0" cellspacing="0" style="margin:120px;">
  <tr>
    <td height="412" valign="top" background="images/bg_img1.jpg"><table height="300" border="0" cellspacing="0">
      <tr>
        <td width="538">&nbsp;</td>
        <td height="130" colspan="6">&nbsp;</td>
        </tr>
      <tr>
        <td rowspan="9">&nbsp;</td>
        <td width="98" height="20" align="right"><img src="images/text_yh.gif" width="60" height="18"></td>
        <td width="16">&nbsp;</td>
        <td width="136"><input name="username" type="text" id="username" size="18" value="<%=username %>"/></td>
        <td width="55">&nbsp;</td>
        <td width="44">&nbsp;</td>
        <td width="32">&nbsp;</td>
      </tr>
      <tr>
        <td height="20" align="right">&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td height="20" align="right"><img src="images/text_password.gif" width="60" height="18"></td>
        <td>&nbsp;</td>
        <td><input name="password" type="password" id="password" size="18" /></td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td height="20" align="right">&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td height="20" align="right"><img src="images/text_yzm.gif" width="60" height="18"></td>
        <td>&nbsp;</td>
        <td><input name="code" type="text" id="code" size="18" /></td>
        <td><span class="text_cray1"><img src="yzm"onclick="this.src='yzm?'+Math.random()" alt="" height="20" /></span></td>
        <td><img src="images/text_sx.gif" width="32" height="18"></td>
        <td align="left">&nbsp;</td>
      </tr>
      <tr>
        <td height="30">&nbsp;</td>
        <td>&nbsp;</td>
        <td valign="bottom"><table width="100%" border="0" cellspacing="0">
          <tr>
            <td width="26" align="left"><input name="autoLogin" type="checkbox" value="1" style=" margin:0 auto;"/></td>
            <td width="170"><img src="images/text_zddl.gif" width="60" height="18"></td>
          </tr>
        </table></td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td height="20">&nbsp;</td>
        <td>&nbsp;</td>
        <td colspan="2">&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td height="20">&nbsp;</td>
        <td>&nbsp;</td>
        <td colspan="2"><table width="200" border="0" cellspacing="0">
          <tr>
            <td width="78"><input type="submit"  class="butlogin" value="" /></td>
            <td>&nbsp;</td>
            <td width="78"><input type="button"  class="butzc" onClick="UserRegistration()"></td>
          </tr>
        </table></td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td height="20">&nbsp;</td>
        <td>&nbsp;</td>
        <td>${msg}</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
    </table></td>
  </tr>
</table>
 </form>
</body>
</html>
